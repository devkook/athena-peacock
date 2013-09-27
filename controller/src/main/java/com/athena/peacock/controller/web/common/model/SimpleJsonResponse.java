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
 * 서버 C,U,D 작업 결과에 대한 JSON Response 클래스.
 * - ex) {"success": true, "msg":"Create sucess"}
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class SimpleJsonResponse {
	
	/**
	 * 서버 작업 성공여부
	 */
	private boolean success = true;
	
	/**
	 * 서버 작업 결과 메시지.
	 */
	private String msg;

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public SimpleJsonResponse() {
		// TODO Auto-generated constructor stub
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
//end of SimpleJsonResponse.java