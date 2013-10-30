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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.athena.peacock.common.core.action.ShellAction;
import com.athena.peacock.common.core.command.Command;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.netty.PeacockTransmitter;
import com.athena.peacock.controller.web.machine.MachineDao;
import com.athena.peacock.controller.web.machine.MachineDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("loadBalancerService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class LoadBalancerService {
	
    protected final Logger logger = LoggerFactory.getLogger(LoadBalancerService.class);
    
	@Inject
	@Named("loadBalancerDao")
	private LoadBalancerDao loadBalancerDao;
	
	@Inject
	@Named("lbMachineMapDao")
	private LBMachineMapDao lbMachineMapDao;
	
	@Inject
	@Named("machineDao")
	private MachineDao machineDao;
	
    @Inject
    @Named("peacockTransmitter")
	private PeacockTransmitter peacockTransmitter;
	
	public void insertLoadBalancer(LoadBalancerDto loadBalancer) throws Exception {
		loadBalancerDao.insertLoadBalancer(loadBalancer);
	}
	
	public void updateLoadBalancer(LoadBalancerDto loadBalancer) throws Exception {
		loadBalancerDao.updateLoadBalancer(loadBalancer);
	}
	
	public void deleteLoadBalancer(int loadBalancerId) throws Exception {
		loadBalancerDao.deleteLoadBalancer(loadBalancerId);
	}
	
	public LoadBalancerDto getLoadBalancer(int loadBalancerId) throws Exception {
		return loadBalancerDao.getLoadBalancer(loadBalancerId);
	}

	public int getLoadBalancerListCnt(LoadBalancerDto loadBalancer) throws Exception {
		return loadBalancerDao.getLoadBalancerListCnt(loadBalancer);
	}
	
	public List<LoadBalancerDto> getLoadBalancerList(LoadBalancerDto loadBalancer) throws Exception {
		return loadBalancerDao.getLoadBalancerList(loadBalancer);
	}

	public void insertLBMachineMap(LoadBalancerDto loadBalancer) {
		lbMachineMapDao.insertLBMachineMap(loadBalancer);
	}

	public void deleteLBMachineMap(LoadBalancerDto loadBalancer) {
		lbMachineMapDao.deleteLBMachineMap(loadBalancer);
	}

	public int getLBMachineMapListCnt(LoadBalancerDto loadBalancer) {
		return lbMachineMapDao.getLBMachineMapListCnt(loadBalancer);
	}

	public List<?> getLBMachineMapList(LoadBalancerDto loadBalancer) {
		return lbMachineMapDao.getLBMachineMapList(loadBalancer);
	}

	public boolean createLoadBalancer(LoadBalancerDto loadBalancer) throws Exception {
		MachineDto machine = machineDao.getMachine(loadBalancer.getMachineId());
		
		String fileName = "haproxy-1.4.22-5.el6_4.i686.rpm";
		if (machine != null) {
			if (machine.getOsArch().indexOf("64") > -1) {
				fileName = "haproxy-1.4.22-5.el6_4.x86_64.rpm";
			}
		} else {
			return false;
		}
		
		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId(loadBalancer.getMachineId());
		cmdMsg.setBlocking(true);
		
		Command command = new Command("HAProxy INSTALL");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/haproxy/" + fileName);
		s_action.addArguments("-O");
		s_action.addArguments(fileName);
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("rpm");
		s_action.addArguments("-Uvh");
		s_action.addArguments(fileName);
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/etc/haproxy");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/haproxy/haproxy.cfg");
		s_action.addArguments("-O");
		s_action.addArguments("haproxy.cfg");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("service");
		s_action.addArguments("haproxy");
		s_action.addArguments("start");
		command.addAction(s_action);
		
		// Add HAProxy INSTALL Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<AbstractMessage> datagram = new PeacockDatagram<AbstractMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		logger.debug("Created Load Balancer as below.\n{}", response.getResults());
		
		// TODO loadBalancer에 AS_GROUP_ID 관련 내용 확인
		
		loadBalancerDao.insertLoadBalancer(loadBalancer);
		
		return true;
	}
}
//end of LoadBalancerService.java