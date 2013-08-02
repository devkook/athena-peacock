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

import java.util.ArrayList;
import java.util.List;

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

//	/** Total system cpu user time */
//	private long userCpu;
//	/** Total system cpu kernel time */
//	private long sysCpu;
//	/** Total system cpu nice time */
//	private long niceCpu;
//	/** Total system cpu idle time */
//	private long idleCpu;
//	/** Total system cpu io wait time */
//	private long waitCpu;
//	/** Total system cpu time servicing interrupts */
//	private long irqCpu;
//	/** Total system cpu time servicing softirqs */
//	private long softIrqCpu;
//	/** Total system cpu involuntary wait time */
//	private long stolenCpu;
//	/** Total system cpu time */
//	private long totalCpu;
//
//	/** cpu user time */
//	private long[] userCpus;
//	/** cpu kernel time */
//	private long[] sysCpus;
//	/** cpu nice time */
//	private long[] niceCpus;
//	/** cpu idle time */
//	private long[] idleCpus;
//	/** cpu io wait time */
//	private long[] waitCpus;
//	/** cpu time servicing interrupts */
//	private long[] irqCpus;
//	/** cpu time servicing softirqs */
//	private long[] softIrqCpus;
//	/** cpu involuntary wait time */
//	private long[] stolenCpus;
//	/** cpu time */
//	private long[] totalCpus;
	
	/** Percent user + sys + nice + wait */
	private List<Double> combinedCpuPerc;
	/** Percent idle cpu */
	private List<Double> idleCpuPerc;
	/** Percent servicing interrupts cpu */
	private List<Double> irqCpuPerc;
	/** Percent nice cpu */
	private List<Double> niceCpuPerc;
	/** Percent servicing softirqs cpu */
	private List<Double> softIrqCpuPerc;
	/** Percent involuntary wait cpu */
	private List<Double> stolenCpuPerc;
	/** Percent kernel cpu */
	private List<Double> sysCpuPerc;
	/** Percent user cpu */
	private List<Double> userCpuPerc;
	/** Percent io wait cpu */
	private List<Double> waitCpuPerc;
	
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
	 * @return the combinedCpuPerc
	 */
	public List<Double> getCombinedCpuPerc() {
		if (combinedCpuPerc == null) {
			combinedCpuPerc = new ArrayList<Double>();
		}
		return combinedCpuPerc;
	}

	/**
	 * @param combinedCpuPerc the combinedCpuPerc to set
	 */
	public void addCombinedCpuPerc(Double combinedCpuPerc) {
		getCombinedCpuPerc().add(combinedCpuPerc);
	}

	/**
	 * @return the idleCpuPerc
	 */
	public List<Double> getIdleCpuPerc() {
		if (idleCpuPerc == null) {
			idleCpuPerc = new ArrayList<Double>();
		}
		return idleCpuPerc;
	}

	/**
	 * @param idleCpuPerc the idleCpuPerc to set
	 */
	public void addIdleCpuPerc(Double idleCpuPerc) {
		getIdleCpuPerc().add(idleCpuPerc);
	}

	/**
	 * @return the irqCpuPerc
	 */
	public List<Double> getIrqCpuPerc() {
		if (irqCpuPerc == null) {
			irqCpuPerc = new ArrayList<Double>();
		}
		return irqCpuPerc;
	}

	/**
	 * @param irqCpuPerc the irqCpuPerc to set
	 */
	public void addIrqCpuPerc(Double irqCpuPerc) {
		getIrqCpuPerc().add(irqCpuPerc);
	}

	/**
	 * @return the niceCpuPerc
	 */
	public List<Double> getNiceCpuPerc() {
		if (niceCpuPerc == null) {
			niceCpuPerc = new ArrayList<Double>();
		}
		return niceCpuPerc;
	}

	/**
	 * @param niceCpuPerc the niceCpuPerc to set
	 */
	public void addNiceCpuPerc(Double niceCpuPerc) {
		getNiceCpuPerc().add(niceCpuPerc);
	}

	/**
	 * @return the softIrqCpuPerc
	 */
	public List<Double> getSoftIrqCpuPerc() {
		if (softIrqCpuPerc == null) {
			softIrqCpuPerc = new ArrayList<Double>();
		}
		return softIrqCpuPerc;
	}

	/**
	 * @param softIrqCpuPerc the softIrqCpuPerc to set
	 */
	public void addSoftIrqCpuPerc(Double softIrqCpuPerc) {
		getSoftIrqCpuPerc().add(softIrqCpuPerc);
	}

	/**
	 * @return the stolenCpuPerc
	 */
	public List<Double> getStolenCpuPerc() {
		if (stolenCpuPerc == null) {
			stolenCpuPerc = new ArrayList<Double>();
		}
		return stolenCpuPerc;
	}

	/**
	 * @param stolenCpuPerc the stolenCpuPerc to set
	 */
	public void addStolenCpuPerc(Double stolenCpuPerc) {
		getStolenCpuPerc().add(stolenCpuPerc);
	}

	/**
	 * @return the sysCpuPerc
	 */
	public List<Double> getSysCpuPerc() {
		if (sysCpuPerc == null) {
			sysCpuPerc = new ArrayList<Double>();
		}
		return sysCpuPerc;
	}

	/**
	 * @param sysCpuPerc the sysCpuPerc to set
	 */
	public void addSysCpuPerc(Double sysCpuPerc) {
		getSysCpuPerc().add(sysCpuPerc);
	}

	/**
	 * @return the userCpuPerc
	 */
	public List<Double> getUserCpuPerc() {
		if (userCpuPerc == null) {
			userCpuPerc = new ArrayList<Double>();
		}
		return userCpuPerc;
	}

	/**
	 * @param userCpuPerc the userCpuPerc to set
	 */
	public void addUserCpuPerc(Double userCpuPerc) {
		getUserCpuPerc().add(userCpuPerc);
	}

	/**
	 * @return the waitCpuPerc
	 */
	public List<Double> getWaitCpuPerc() {
		if (waitCpuPerc == null) {
			waitCpuPerc = new ArrayList<Double>();
		}
		return waitCpuPerc;
	}

	/**
	 * @param waitCpuPerc the waitCpuPerc to set
	 */
	public void addWaitCpuPerc(Double waitCpuPerc) {
		getWaitCpuPerc().add(waitCpuPerc);
	}
	
}
//end of AgentSystemStatus.java