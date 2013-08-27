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
 * Sang-cheon Park	2013. 8. 25.		First Draft.
 */
package com.athena.peacock.controller.machine;

import com.athena.peacock.controller.web.common.dao.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MachineDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String machineId;
	private String machineMacAddr;
	private String isVm;
	private String osName;
	private String osVer;
	private String osArch;
	private String cpuClock;
	private String cpuNum;
	private String memSize;
	private String ipAddr;
	private String hostName;

	/**
	 * @return the machineId
	 */
	public String getMachineId() {
		return machineId;
	}

	/**
	 * @param machineId the machineId to set
	 */
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	/**
	 * @return the machineMacAddr
	 */
	public String getMachineMacAddr() {
		return machineMacAddr;
	}

	/**
	 * @param machineMacAddr the machineMacAddr to set
	 */
	public void setMachineMacAddr(String machineMacAddr) {
		this.machineMacAddr = machineMacAddr;
	}

	/**
	 * @return the isVm
	 */
	public String getIsVm() {
		return isVm;
	}

	/**
	 * @param isVm the isVm to set
	 */
	public void setIsVm(String isVm) {
		this.isVm = isVm;
	}

	/**
	 * @return the osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @param osName the osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}

	/**
	 * @return the osVer
	 */
	public String getOsVer() {
		return osVer;
	}

	/**
	 * @param osVer the osVer to set
	 */
	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}

	/**
	 * @return the osArch
	 */
	public String getOsArch() {
		return osArch;
	}

	/**
	 * @param osArch the osArch to set
	 */
	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	/**
	 * @return the cpuClock
	 */
	public String getCpuClock() {
		return cpuClock;
	}

	/**
	 * @param cpuClock the cpuClock to set
	 */
	public void setCpuClock(String cpuClock) {
		this.cpuClock = cpuClock;
	}

	/**
	 * @return the cpuNum
	 */
	public String getCpuNum() {
		return cpuNum;
	}

	/**
	 * @param cpuNum the cpuNum to set
	 */
	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	/**
	 * @return the memSize
	 */
	public String getMemSize() {
		return memSize;
	}

	/**
	 * @param memSize the memSize to set
	 */
	public void setMemSize(String memSize) {
		this.memSize = memSize;
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * @return the hostName
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * @param hostName the hostName to set
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
}
//end of MachineDto.java