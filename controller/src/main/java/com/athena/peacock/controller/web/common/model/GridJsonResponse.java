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
 * Bong-Jin Kwon	2013. 9. 27.		First Draft.
 */
package com.athena.peacock.controller.web.common.model;

import java.util.List;

/**
 * <pre>
 * Extjs Grid에 의한 서버 list 조회 작업 결과에 대한 JSON Response 클래스.
 * - ex) {"total": 30, "list":[{....},{....}]}
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class GridJsonResponse {
	
	private int total;
	private List list;

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public GridJsonResponse() {
		// TODO Auto-generated constructor stub
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
//end of GridJsonResponse.java