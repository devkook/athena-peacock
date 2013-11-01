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
public class ASPolicyDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Integer autoScalingId;
	private String policyName;
	private String monFactorId;
	private Integer thresholdUpLimit;
	private Integer thresholdDownLimit;
	private Integer thresholdUpPeriod;
	private Integer thresholdDownPeriod;
	private Integer increaseUnit;
	private Integer decreaseUnit;

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
	 * @return the policyName
	 */
	public String getPolicyName() {
		return policyName;
	}

	/**
	 * @param policyName the policyName to set
	 */
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	/**
	 * @return the monFactorId
	 */
	public String getMonFactorId() {
		return monFactorId;
	}

	/**
	 * @param monFactorId the monFactorId to set
	 */
	public void setMonFactorId(String monFactorId) {
		this.monFactorId = monFactorId;
	}

	/**
	 * @return the thresholdUpLimit
	 */
	public Integer getThresholdUpLimit() {
		return thresholdUpLimit;
	}

	/**
	 * @param thresholdUpLimit the thresholdUpLimit to set
	 */
	public void setThresholdUpLimit(Integer thresholdUpLimit) {
		this.thresholdUpLimit = thresholdUpLimit;
	}

	/**
	 * @return the thresholdDownLimit
	 */
	public Integer getThresholdDownLimit() {
		return thresholdDownLimit;
	}

	/**
	 * @param thresholdDownLimit the thresholdDownLimit to set
	 */
	public void setThresholdDownLimit(Integer thresholdDownLimit) {
		this.thresholdDownLimit = thresholdDownLimit;
	}

	/**
	 * @return the thresholdUpPeriod
	 */
	public Integer getThresholdUpPeriod() {
		return thresholdUpPeriod;
	}

	/**
	 * @param thresholdUpPeriod the thresholdUpPeriod to set
	 */
	public void setThresholdUpPeriod(Integer thresholdUpPeriod) {
		this.thresholdUpPeriod = thresholdUpPeriod;
	}

	/**
	 * @return the thresholdDownPeriod
	 */
	public Integer getThresholdDownPeriod() {
		return thresholdDownPeriod;
	}

	/**
	 * @param thresholdDownPeriod the thresholdDownPeriod to set
	 */
	public void setThresholdDownPeriod(Integer thresholdDownPeriod) {
		this.thresholdDownPeriod = thresholdDownPeriod;
	}

	/**
	 * @return the increaseUnit
	 */
	public Integer getIncreaseUnit() {
		return increaseUnit;
	}

	/**
	 * @param increaseUnit the increaseUnit to set
	 */
	public void setIncreaseUnit(Integer increaseUnit) {
		this.increaseUnit = increaseUnit;
	}

	/**
	 * @return the decreaseUnit
	 */
	public Integer getDecreaseUnit() {
		return decreaseUnit;
	}

	/**
	 * @param decreaseUnit the decreaseUnit to set
	 */
	public void setDecreaseUnit(Integer decreaseUnit) {
		this.decreaseUnit = decreaseUnit;
	}
}
//end of ASPolicyDto.java