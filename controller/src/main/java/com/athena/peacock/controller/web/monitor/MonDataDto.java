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
package com.athena.peacock.controller.web.monitor;

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MonDataDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private String machineId;
	private String monFactorId;
	private int monDataId;
	private String monDataValue;
	private String timeRange = "30m";
	private String period = "1m";

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
	 * @return the monDataId
	 */
	public int getMonDataId() {
		return monDataId;
	}

	/**
	 * @param monDataId the monDataId to set
	 */
	public void setMonDataId(int monDataId) {
		this.monDataId = monDataId;
	}

	/**
	 * @return the monDataValue
	 */
	public String getMonDataValue() {
		return monDataValue;
	}

	/**
	 * @param monDataValue the monDataValue to set
	 */
	public void setMonDataValue(String monDataValue) {
		this.monDataValue = monDataValue;
	}

	/**
	 * @return the timeRange
	 */
	public String getTimeRange() {
		return timeRange;
	}

	/**
	 * @param timeRange the timeRange to set
	 */
	public void setTimeRange(String timeRange) {
		this.timeRange = timeRange;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
}
//end of MonDataDto.java