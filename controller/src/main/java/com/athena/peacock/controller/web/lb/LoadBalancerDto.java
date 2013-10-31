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

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class LoadBalancerDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Integer loadBalancerId;
	private String machineId;
	private String lbName;
	private String lbDnsName;
	private Integer asGroupId;
	
	// machine_tbl의 IP_ADDR 조회 용
	private String ipAddr;

	/**
	 * @return the loadBalancerId
	 */
	public Integer getLoadBalancerId() {
		return loadBalancerId;
	}

	/**
	 * @param loadBalancerId the loadBalancerId to set
	 */
	public void setLoadBalancerId(Integer loadBalancerId) {
		this.loadBalancerId = loadBalancerId;
	}

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
	 * @return the lbName
	 */
	public String getLbName() {
		return lbName;
	}

	/**
	 * @param lbName the lbName to set
	 */
	public void setLbName(String lbName) {
		this.lbName = lbName;
	}

	/**
	 * @return the lbDnsName
	 */
	public String getLbDnsName() {
		return lbDnsName;
	}

	/**
	 * @param lbDnsName the lbDnsName to set
	 */
	public void setLbDnsName(String lbDnsName) {
		this.lbDnsName = lbDnsName;
	}

	/**
	 * @return the asGroupId
	 */
	public Integer getAsGroupId() {
		return asGroupId;
	}

	/**
	 * @param asGroupId the asGroupId to set
	 */
	public void setAsGroupId(Integer asGroupId) {
		this.asGroupId = asGroupId;
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
}
//end of LoadBalancerDto.java