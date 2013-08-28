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

	/** Actual total free system memory */
	private long actualFreeMem;
	/** Actual total used system memory */
	private long actualUsedMem;
	/** Total free system memory */
	private long freeMem;
	/** Percent total free system memory */
	private double freePercentMem;
	/** System Random Access Memory */
	private long ramMem;
	/** Total system memory */
	private long totalMem;
	/** Total used system memory */
	private long usedMem;
	/** Percent total used system memory */
	private double usedPercentMem;

	/** Percentage of CPU utilization while executing at the user, or application, level. */
	private String userCpu;
	/** Percentage of CPU utilization while executing at the system, or kernel, level. */
	private String sysCpu;
	/** The percentage of time that the CPU or CPUs were not processing 
	 *  any commands and the system did not have an outstanding disk I/O request. */
	private String idleCpu;
	/** Percentage of time the CPU or CPUs were not processing any commands 
	 *  but during which the system had an outstanding disk I/O request. */
	private String waitCpu;
	/** Percentage of CPU utilization while executing at the user level with nice priority. */
	private String niceCpu;
	/** Sum of User, Sys, Nice, Wait */
	private String combinedCpu;
	
	public AgentSystemStatusMessage() {
		super(MessageType.SYSTEM_STATUS);
	}

	/**
	 * @return the actualFreeMem
	 */
	public long getActualFreeMem() {
		return actualFreeMem;
	}

	/**
	 * @param actualFreeMem the actualFreeMem to set
	 */
	public void setActualFreeMem(long actualFreeMem) {
		this.actualFreeMem = actualFreeMem;
	}

	/**
	 * @return the actualUsedMem
	 */
	public long getActualUsedMem() {
		return actualUsedMem;
	}

	/**
	 * @param actualUsedMem the actualUsedMem to set
	 */
	public void setActualUsedMem(long actualUsedMem) {
		this.actualUsedMem = actualUsedMem;
	}

	/**
	 * @return the freeMem
	 */
	public long getFreeMem() {
		return freeMem;
	}

	/**
	 * @param freeMem the freeMem to set
	 */
	public void setFreeMem(long freeMem) {
		this.freeMem = freeMem;
	}

	/**
	 * @return the freePercentMem
	 */
	public double getFreePercentMem() {
		return freePercentMem;
	}

	/**
	 * @param freePercentMem the freePercentMem to set
	 */
	public void setFreePercentMem(double freePercentMem) {
		this.freePercentMem = freePercentMem;
	}

	/**
	 * @return the ramMem
	 */
	public long getRamMem() {
		return ramMem;
	}

	/**
	 * @param ramMem the ramMem to set
	 */
	public void setRamMem(long ramMem) {
		this.ramMem = ramMem;
	}

	/**
	 * @return the totalMem
	 */
	public long getTotalMem() {
		return totalMem;
	}

	/**
	 * @param totalMem the totalMem to set
	 */
	public void setTotalMem(long totalMem) {
		this.totalMem = totalMem;
	}

	/**
	 * @return the usedMem
	 */
	public long getUsedMem() {
		return usedMem;
	}

	/**
	 * @param usedMem the usedMem to set
	 */
	public void setUsedMem(long usedMem) {
		this.usedMem = usedMem;
	}

	/**
	 * @return the usedPercentMem
	 */
	public double getUsedPercentMem() {
		return usedPercentMem;
	}

	/**
	 * @param usedPercentMem the usedPercentMem to set
	 */
	public void setUsedPercentMem(double usedPercentMem) {
		this.usedPercentMem = usedPercentMem;
	}

	/**
	 * @return the userCpu
	 */
	public String getUserCpu() {
		return userCpu;
	}

	/**
	 * @param userCpu the userCpu to set
	 */
	public void setUserCpu(String userCpu) {
		this.userCpu = userCpu;
	}

	/**
	 * @return the sysCpu
	 */
	public String getSysCpu() {
		return sysCpu;
	}

	/**
	 * @param sysCpu the sysCpu to set
	 */
	public void setSysCpu(String sysCpu) {
		this.sysCpu = sysCpu;
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
	 * @return the waitCpu
	 */
	public String getWaitCpu() {
		return waitCpu;
	}

	/**
	 * @param waitCpu the waitCpu to set
	 */
	public void setWaitCpu(String waitCpu) {
		this.waitCpu = waitCpu;
	}

	/**
	 * @return the niceCpu
	 */
	public String getNiceCpu() {
		return niceCpu;
	}

	/**
	 * @param niceCpu the niceCpu to set
	 */
	public void setNiceCpu(String niceCpu) {
		this.niceCpu = niceCpu;
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
	
}
//end of AgentSystemStatus.java