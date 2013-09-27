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

/**
 * <pre>
 * Single Dto 를 json response 하기위한 클래스.
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class DtoJsonResponse extends SimpleJsonResponse {

	private Object data;
	
	public DtoJsonResponse() {
		// TODO Auto-generated constructor stub
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
//end of DtoJsonResponse.java