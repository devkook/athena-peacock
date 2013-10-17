/* 
 * Copyright 2013 The Athena-Peacock Project.
 *
 * Licensed under the Apache License;
	private String Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing;
	private String software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND;
	private String either express or implied.
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
public class ConfigRepoDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private Integer configFileId;
	private Integer softwareId;
	private String configFileSourceLocation;
	private String configFileTargetLocation;
	private String configFileName;
	private String properties;

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
	 * @return the configFileSourceLocation
	 */
	public String getConfigFileSourceLocation() {
		return configFileSourceLocation;
	}

	/**
	 * @param configFileSourceLocation the configFileSourceLocation to set
	 */
	public void setConfigFileSourceLocation(String configFileSourceLocation) {
		this.configFileSourceLocation = configFileSourceLocation;
	}

	/**
	 * @return the configFileTargetLocation
	 */
	public String getConfigFileTargetLocation() {
		return configFileTargetLocation;
	}

	/**
	 * @param configFileTargetLocation the configFileTargetLocation to set
	 */
	public void setConfigFileTargetLocation(String configFileTargetLocation) {
		this.configFileTargetLocation = configFileTargetLocation;
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
	 * @return the properties
	 */
	public String getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(String properties) {
		this.properties = properties;
	}
}
//end of ConfigRepoDto.java