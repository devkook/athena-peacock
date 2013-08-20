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

import com.athena.peacock.agent.netty.PeacockTransmitter;
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
	
	private PeacockTransmitter peacockTransmitter;

	/**
	 * @param peacockTransmitter the peacockTransmitter to set
	 */
	public void setPeacockTransmitter(PeacockTransmitter peacockTransmitter) {
		this.peacockTransmitter = peacockTransmitter;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.agent.scheduler.quartz.BaseJob#executeInternal(com.athena.peacock.agent.scheduler.quartz.JobExecution)
	 */
	@Override
	protected void executeInternal(JobExecution context) throws InternalJobExecutionException {
		
		if (!peacockTransmitter.isConnected()) {
			return;
		}
		
		try {
			Mem mem = SigarUtil.getMem();
			CpuPerc cpu = SigarUtil.getCpuPerc();
			
			AgentSystemStatusMessage message = new AgentSystemStatusMessage();
			message.setAgentId(IOUtils.toString(new File(PeacockConstant.AGENT_ID_FILE).toURI()));
			
			// set memory info
			message.setActualFreeMem(mem.getActualFree());
			message.setActualUsedMem(mem.getActualUsed());
			message.setFreeMem(mem.getFree());
			message.setFreePercentMem(mem.getFreePercent());
			message.setRamMem(mem.getRam());
			message.setTotalMem(mem.getTotal());
			message.setUsedMem(mem.getUsed());
			message.setUsedPercentMem(mem.getUsedPercent());
			
			// set cpu info
			message.setUserCpu(CpuPerc.format(cpu.getUser()).replaceAll("%", ""));
			message.setSysCpu(CpuPerc.format(cpu.getSys()).replaceAll("%", ""));
			message.setIdleCpu(CpuPerc.format(cpu.getIdle()).replaceAll("%", ""));
			message.setNiceCpu(CpuPerc.format(cpu.getNice()).replaceAll("%", ""));
			message.setWaitCpu(CpuPerc.format(cpu.getWait()).replaceAll("%", ""));
			message.setCombinedCpu(CpuPerc.format(cpu.getCombined()).replaceAll("%", ""));
			
			peacockTransmitter.sendMessage(new PeacockDatagram<AgentSystemStatusMessage>(message));
		} catch (SigarException e) {
			logger.error("SigarException has occurred.", e);
			throw new InternalJobExecutionException(e);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
			throw new InternalJobExecutionException(e);
		} catch (Exception e) {
			logger.error("Unhandled Exception has occurred.", e);
			throw new InternalJobExecutionException(e);
		}
	}//end of executeInternal()

}
//end of SystemMonitoringJob.java