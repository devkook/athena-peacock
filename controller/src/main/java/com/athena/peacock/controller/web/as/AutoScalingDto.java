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
 * Sang-cheon Park	2013. 11. 1.		First Draft.
 */
package com.athena.peacock.controller.web.as;

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class AutoScalingDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Integer autoScalingId;
	private String autoScalingName;
	private String vmTemplateId;
	private Integer minMachineSize;
	private Integer maxMachineSize;

	/**
	 * @return the autoScalingId
	 */
	public Integer getAutoScalingId() {
		return autoScalingId;
	}

	/**
	 * @param autoScalingId the autoScalingId to set
	 */
	public void setAutoScalingId(Integer autoScalingId) {
		this.autoScalingId = autoScalingId;
	}

	/**
	 * @return the autoScalingName
	 */
	public String getAutoScalingName() {
		return autoScalingName;
	}

	/**
	 * @param autoScalingName the autoScalingName to set
	 */
	public void setAutoScalingName(String autoScalingName) {
		this.autoScalingName = autoScalingName;
	}

	/**
	 * @return the vmTemplateId
	 */
	public String getVmTemplateId() {
		return vmTemplateId;
	}

	/**
	 * @param vmTemplateId the vmTemplateId to set
	 */
	public void setVmTemplateId(String vmTemplateId) {
		this.vmTemplateId = vmTemplateId;
	}

	/**
	 * @return the minMachineSize
	 */
	public Integer getMinMachineSize() {
		return minMachineSize;
	}

	/**
	 * @param minMachineSize the minMachineSize to set
	 */
	public void setMinMachineSize(Integer minMachineSize) {
		this.minMachineSize = minMachineSize;
	}

	/**
	 * @return the maxMachineSize
	 */
	public Integer getMaxMachineSize() {
		return maxMachineSize;
	}

	/**
	 * @param maxMachineSize the maxMachineSize to set
	 */
	public void setMaxMachineSize(Integer maxMachineSize) {
		this.maxMachineSize = maxMachineSize;
	}
}
//end of AutoScalingDto.java