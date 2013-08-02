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
 * Sang-cheon Park	2013. 8. 1.		First Draft.
 */
package com.athena.peacock.agent.job;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.SigarException;

import com.athena.peacock.agent.netty.PeacockClient;
import com.athena.peacock.agent.scheduler.InternalJobExecutionException;
import com.athena.peacock.agent.scheduler.quartz.BaseJob;
import com.athena.peacock.agent.scheduler.quartz.JobExecution;
import com.athena.peacock.agent.util.SigarUtil;
import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentSystemStatusMessage;

/**
 * <pre>
 * SIGAR를 이용한 주기적 System Monitoring을 수행하기 위한 Quartz Job
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SystemMonitoringJob extends BaseJob {
	
	private PeacockClient peacockClient;

	/**
	 * @param peacockClient the peacockClient to set
	 */
	public void setPeacockClient(PeacockClient peacockClient) {
		this.peacockClient = peacockClient;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.agent.scheduler.quartz.BaseJob#executeInternal(com.athena.peacock.agent.scheduler.quartz.JobExecution)
	 */
	@Override
	protected void executeInternal(JobExecution context) throws InternalJobExecutionException {
		try {
			Mem mem = SigarUtil.getMem();
			CpuPerc[] cpuPercList = SigarUtil.getCpuPercList();
			
			AgentSystemStatusMessage message = new AgentSystemStatusMessage();
			message.setAgentId(IOUtils.toString(new File(PeacockConstant.AGENT_ID_FILE).toURI()));
			message.setActualFreeMem(mem.getActualFree());
			message.setActualUsedMem(mem.getActualUsed());
			message.setFreeMem(mem.getFree());
			message.setFreePercentMem(mem.getFreePercent());
			message.setRamMem(mem.getRam());
			message.setTotalMem(mem.getTotal());
			message.setUsedMem(mem.getUsed());
			message.setUsedPercentMem(mem.getUsedPercent());
			
			for (CpuPerc cpuPerc : cpuPercList) {
				message.addCombinedCpuPerc(cpuPerc.getCombined());
				message.addIdleCpuPerc(cpuPerc.getIdle());
				message.addIrqCpuPerc(cpuPerc.getIrq());
				message.addNiceCpuPerc(cpuPerc.getNice());
				message.addSoftIrqCpuPerc(cpuPerc.getSoftIrq());
				message.addStolenCpuPerc(cpuPerc.getStolen());
				message.addSysCpuPerc(cpuPerc.getSys());
				message.addUserCpuPerc(cpuPerc.getUser());
				message.addWaitCpuPerc(cpuPerc.getWait());
			}
			
			peacockClient.sendMessage(new PeacockDatagram<AgentSystemStatusMessage>(message));
		} catch (SigarException e) {
			logger.error("SigarException has occurred.", e);
			throw new InternalJobExecutionException(e);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
			throw new InternalJobExecutionException(e);
		}
	}//end of executeInternal()

}
//end of SystemMonitoringJob.java