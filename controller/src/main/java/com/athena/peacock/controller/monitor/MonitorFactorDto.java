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
package com.athena.peacock.controller.monitor;

import com.athena.peacock.controller.web.common.dao.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class MonitorFactorDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private int monFactorId;
	private String monFactorName;
	
	/**
	 * @return the monFactorId
	 */
	public int getMonFactorId() {
		return monFactorId;
	}
	
	/**
	 * @param monFactorId the monFactorId to set
	 */
	public void setMonFactorId(int monFactorId) {
		this.monFactorId = monFactorId;
	}
	
	/**
	 * @return the monFactorName
	 */
	public String getMonFactorName() {
		return monFactorName;
	}
	
	/**
	 * @param monFactorName the monFactorName to set
	 */
	public void setMonFactorName(String monFactorName) {
		this.monFactorName = monFactorName;
	}
}
//end of MonitorFactorDto.java