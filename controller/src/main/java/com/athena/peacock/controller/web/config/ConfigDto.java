/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License;
Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing;
software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND;
either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Revision History
 * Author			Date				Description
 * ---------------	----------------	------------
 * Sang-cheon Park	2013. 10. 16.		First Draft.
 */
package com.athena.peacock.controller.web.config;

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ConfigDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private String machineId;
	private Integer softwareId;
	private Integer configFileId;
	private String configFileLocation;
	private String configFileName;
	private String configFileContents;
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
	 * @return the configFileId
	 */
	public Integer getConfigFileId() {
		return configFileId;
	}

	/**
	 * @param configFileId the configFileId to set
	 */
	public void setConfigFileId(Integer configFileId) {
		this.configFileId = configFileId;
	}

	/**
	 * @return the configFileLocation
	 */
	public String getConfigFileLocation() {
		return configFileLocation;
	}

	/**
	 * @param configFileLocation the configFileLocation to set
	 */
	public void setConfigFileLocation(String configFileLocation) {
		this.configFileLocation = configFileLocation;
	}

	/**
	 * @return the configFileName
	 */
	public String getConfigFileName() {
		return configFileName;
	}

	/**
	 * @param configFileName the configFileName to set
	 */
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	/**
	 * @return the configFileContents
	 */
	public String getConfigFileContents() {
		return configFileContents;
	}

	/**
	 * @param configFileContents the configFileContents to set
	 */
	public void setConfigFileContents(String configFileContents) {
		this.configFileContents = configFileContents;
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
//end of ConfigDto.java