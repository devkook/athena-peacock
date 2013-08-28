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
 * Sang-cheon Park	2013. 7. 18.		First Draft.
 */
package com.athena.peacock.common.netty.message;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class AgentSystemStatusMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;
	
	/** The percentage of time that the CPU or CPUs were not processing 
	 *  any commands and the system did not have an outstanding disk I/O request. */
	private String idleCpu;
	/** Percentage of CPU utilization while executing at all(system, kernel, user, application). */
	private String combinedCpu;
	/** Total system memory */
	private String totalMem;
	/** Total free system memory */
	private String freeMem;
	/** Total used system memory */
	private String usedMem;
	
	public AgentSystemStatusMessage() {
		super(MessageType.SYSTEM_STATUS);
	}

	/**
	 * @return the idleCpu
	 */
	public String getIdleCpu() {
		return idleCpu;
	}

	/**
	 * @param idleCpu the idleCpu to set
	 */
	public void setIdleCpu(String idleCpu) {
		this.idleCpu = idleCpu;
	}

	/**
	 * @return the combinedCpu
	 */
	public String getCombinedCpu() {
		return combinedCpu;
	}

	/**
	 * @param combinedCpu the combinedCpu to set
	 */
	public void setCombinedCpu(String combinedCpu) {
		this.combinedCpu = combinedCpu;
	}

	/**
	 * @return the totalMem
	 */
	public String getTotalMem() {
		return totalMem;
	}

	/**
	 * @param totalMem the totalMem to set
	 */
	public void setTotalMem(String totalMem) {
		this.totalMem = totalMem;
	}

	/**
	 * @return the freeMem
	 */
	public String getFreeMem() {
		return freeMem;
	}

	/**
	 * @param freeMem the freeMem to set
	 */
	public void setFreeMem(String freeMem) {
		this.freeMem = freeMem;
	}

	/**
	 * @return the usedMem
	 */
	public String getUsedMem() {
		return usedMem;
	}

	/**
	 * @param usedMem the usedMem to set
	 */
	public void setUsedMem(String usedMem) {
		this.usedMem = usedMem;
	}
	
}
//end of AgentSystemStatus.java