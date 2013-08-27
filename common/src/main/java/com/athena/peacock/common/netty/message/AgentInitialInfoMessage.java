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
public class AgentInitialInfoMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;

	/** mac address */
	private String macAddr;
	/** os.name */
	private String osName;
	/** os.arch */
	private String osArch;
	/** os.version */
	private String osVersion;
	/** cpu clock */
	private int cpuClock;
	/** cpu num */
	private int cpuNum;
	/** cpu model */
	private String cpuModel;
	/** cpu vendor */
	private String cpuVendor;
	/** memory capacity */
	private long memSize;
	/** ip address */
	private String ipAddr;
	/** host name */
	private String hostName;
	
	public AgentInitialInfoMessage() {
		super(MessageType.INITIAL_INFO);
	}

	/**
	 * @return the macAddr
	 */
	public String getMacAddr() {
		return macAddr;
	}

	/**
	 * @param macAddr the macAddr to set
	 */
	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
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
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the cpuClock
	 */
	public int getCpuClock() {
		return cpuClock;
	}

	/**
	 * @param cpuClock the cpuClock to set
	 */
	public void setCpuClock(int cpuClock) {
		this.cpuClock = cpuClock;
	}

	/**
	 * @return the cpuNum
	 */
	public int getCpuNum() {
		return cpuNum;
	}

	/**
	 * @param cpuNum the cpuNum to set
	 */
	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}

	/**
	 * @return the cpuModel
	 */
	public String getCpuModel() {
		return cpuModel;
	}

	/**
	 * @param cpuModel the cpuModel to set
	 */
	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	/**
	 * @return the cpuVendor
	 */
	public String getCpuVendor() {
		return cpuVendor;
	}

	/**
	 * @param cpuVendor the cpuVendor to set
	 */
	public void setCpuVendor(String cpuVendor) {
		this.cpuVendor = cpuVendor;
	}

	/**
	 * @return the memSize
	 */
	public long getMemSize() {
		return memSize;
	}

	/**
	 * @param memSize the memSize to set
	 */
	public void setMemSize(long memSize) {
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
//end of AgentInitialInfo.java