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
package com.athena.peacock.controller.web.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer regUserId;
	private Date regDt;
	private Integer updUserId;
	private Date updDt;
	
	/** 페이징 관련 */
	private int start;
	private int limit = 10;
	
	/**
	 * @return the regUserId
	 */
	public Integer getRegUserId() {
		return regUserId;
	}
	
	/**
	 * @param regUserId the regUserId to set
	 */
	public void setRegUserId(Integer regUserId) {
		this.regUserId = regUserId;
	}
	
	/**
	 * @return the regDt
	 */
	public Date getRegDt() {
		return regDt;
	}
	
	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	
	/**
	 * @return the updUserId
	 */
	public Integer getUpdUserId() {
		return updUserId;
	}
	
	/**
	 * @param updUserId the updUserId to set
	 */
	public void setUpdUserId(Integer updUserId) {
		this.updUserId = updUserId;
	}
	
	/**
	 * @return the updDt
	 */
	public Date getUpdDt() {
		return updDt;
	}
	
	/**
	 * @param updDt the updDt to set
	 */
	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

	/**
	 * @return the start
	 */
	public int getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(int start) {
		this.start = start;
	}

	/**
	 * @return the limit
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
//end of BaseDto.java