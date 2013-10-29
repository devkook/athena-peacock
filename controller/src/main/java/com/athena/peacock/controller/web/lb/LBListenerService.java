/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2013. 10. 29.		First Draft.
 */
package com.athena.peacock.controller.web.lb;

import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.athena.peacock.common.core.action.FileWriteAction;
import com.athena.peacock.common.core.action.ShellAction;
import com.athena.peacock.common.core.command.Command;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.netty.PeacockTransmitter;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("lbListenerService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class LBListenerService {
	
    protected final Logger logger = LoggerFactory.getLogger(LBListenerService.class);
    
	@Inject
	@Named("lbListenerDao")
	private LBListenerDao lbListenerDao;
	
	@Inject
	@Named("lbMachineMapDao")
	private LBMachineMapDao lbMachineMapDao;

    @Inject
    @Named("peacockTransmitter")
	private PeacockTransmitter peacockTransmitter;
	
	public void insertLBListener(LBListenerDto lbListener) throws Exception {
		lbListenerDao.insertLBListener(lbListener);
	}
	
	public void updateLBListener(LBListenerDto lbListener) throws Exception {
		lbListenerDao.updateLBListener(lbListener);
	}
	
	public void deleteLBListener(LBListenerDto lbListener) throws Exception {
		lbListenerDao.deleteLBListener(lbListener);
	}
	
	public LBListenerDto getLBListener(LBListenerDto lbListener) throws Exception {
		return lbListenerDao.getLBListener(lbListener);
	}
	
	public int getLBListenerListCnt(LBListenerDto lbListener) throws Exception {
		return lbListenerDao.getLBListenerListCnt(lbListener);
	}
	
	public List<LBListenerDto> getLBListenerList(LBListenerDto lbListener) throws Exception {
		return lbListenerDao.getLBListenerList(lbListener);
	}

	public void applyListener(LoadBalancerDto loadBalancer, String urlPrefix) throws Exception {
		LBListenerDto dto1 = new LBListenerDto();
		dto1.setLoadBalancerId(loadBalancer.getLoadBalancerId());
		dto1.setLimit(Integer.MAX_VALUE);
		
		List<LBListenerDto> lbListenerList = lbListenerDao.getLBListenerList(dto1);
		
		LoadBalancerDto dto2 = new LoadBalancerDto();
		dto2.setLoadBalancerId(loadBalancer.getLoadBalancerId());
		dto2.setLimit(Integer.MAX_VALUE);
		
		List<LoadBalancerDto> machineList = lbMachineMapDao.getLBMachineMapList(dto2);
		
		int idx1 = 1, idx2 = 1;
		StringBuilder sb = new StringBuilder();
		for (LBListenerDto lbListener : lbListenerList) {
			sb.append("listen").append("\t").append("listener-" + idx1++).append(" :").append(lbListener.getListenPort()).append("\n");

			if (lbListener.getProtocol().toLowerCase().equals("tcp")) {
				sb.append("\t").append("mode").append("\t").append("tcp").append("\n");
				sb.append("\t").append("option").append("\t").append("tcplog").append("\n");
			} else if (lbListener.getProtocol().toLowerCase().equals("http")) {
				sb.append("\t").append("mode").append("\t").append("http").append("\n");
				sb.append("\t").append("option").append("\t").append("httplog").append("\n");
				sb.append("\t").append("option").append("\t").append("http-server-close").append("\n");
				sb.append("\t").append("option").append("\t").append("forwardfor").append("\t").append("except 127.0.0.0/8").append("\n");
				sb.append("\t").append("option").append("\t").append("httpclose").append("\n");
				
				if ("Y".equals(lbListener.getStickinessYn().toUpperCase())) {
					sb.append("\t").append("cookie").append("\t").append("SRVSTICKY insert indirect nocache").append("\n");
				}
			}
			
			idx2 = 1;
			for (LoadBalancerDto machine : machineList) {
				sb.append("\t").append("server").append("\t").append("s-" + idx2).append(" ").append(machine.getIpAddr()).append(":").append(lbListener.getBackendPort()).append(" check");
				
				if (lbListener.getProtocol().toLowerCase().equals("http") && "Y".equals(lbListener.getStickinessYn().toUpperCase())) {
					sb.append(" cookie ").append("s-" + idx2);
				}
				
				sb.append("\n");
				idx2++;
			}
		}

		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId(loadBalancer.getMachineId());
		cmdMsg.setBlocking(true);
		
		Command command = new Command("CONFIGURATION");
		int sequence = 0;
		
		String haproxyCfg = IOUtils.toString(new URL(urlPrefix + "/haproxy/haproxy.cfg"), "UTF-8");
		haproxyCfg = haproxyCfg.replaceAll("\\$\\{PROXY_SETTING\\}", sb.toString());
		
		FileWriteAction fw_action = new FileWriteAction(sequence++);
		fw_action.setContents(haproxyCfg);
		fw_action.setFileName("/etc/haproxy/haproxy.cfg");
		command.addAction(fw_action);
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setCommand("service");
		s_action.addArguments("haproxy");
		s_action.addArguments("restart");
		command.addAction(s_action);
		
		// Add HAProxy INSTALL Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<AbstractMessage> datagram = new PeacockDatagram<AbstractMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		logger.debug("Applied Load Balancer's config as below.\n{}", response.getResults());
	}
}
//end of LBListenerService.java