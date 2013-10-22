/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License;Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing;software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND;either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2013. 10. 16.		First Draft.
 */
package com.athena.peacock.controller.web.software;

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SoftwareDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String machineId;
	private Integer softwareId;
	private String installLocation;
	private String installStat;
	private String installLog;
	private String description;
	private String deleteYn;
	
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
	 * @return the softwareId
	 */
	public Integer getSoftwareId() {
		return softwareId;
	}
	
	/**
	 * @param softwareId the softwareId to set
	 */
	public void setSoftwareId(Integer softwareId) {
		this.softwareId = softwareId;
	}
	
	/**
	 * @return the installLocation
	 */
	public String getInstallLocation() {
		return installLocation;
	}
	
	/**
	 * @param installLocation the installLocation to set
	 */
	public void setInstallLocation(String installLocation) {
		this.installLocation = installLocation;
	}

	/**
	 * @return the installStat
	 */
	public String getInstallStat() {
		return installStat;
	}

	/**
	 * @param installStat the installStat to set
	 */
	public void setInstallStat(String installStat) {
		this.installStat = installStat;
	}

	/**
	 * @return the installLog
	 */
	public String getInstallLog() {
		return installLog;
	}

	/**
	 * @param installLog the installLog to set
	 */
	public void setInstallLog(String installLog) {
		this.installLog = installLog;
	}
	
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the deleteYn
	 */
	public String getDeleteYn() {
		return deleteYn;
	}
	
	/**
	 * @param deleteYn the deleteYn to set
	 */
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
}
//end of SoftwareDto.java