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
 * Sang-cheon Park	2013. 8. 1.		First Draft.
 */
package com.athena.peacock.agent.scheduler;

/**
 * <pre>
 * Batch Job 실행시 발생하는 예외를 Wrapping하기 위한 Exception 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class InternalJobExecutionException extends RuntimeException {

    private static final long serialVersionUID = -8566230065054696121L;

    public InternalJobExecutionException(Throwable cause) {
		super(cause);
	}

	public InternalJobExecutionException(String msg) {
		super(msg);
	}

	public InternalJobExecutionException(String msg, Throwable cause) {
		super(msg, cause);
	}
}//end of InternalJobExecutionException.java