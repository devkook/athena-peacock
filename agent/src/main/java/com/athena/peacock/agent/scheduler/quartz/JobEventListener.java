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
package com.athena.peacock.agent.scheduler.quartz;

/**
 * <pre>
 * {@link BaseJob}의 이벤트 처리 기능을 제공하는 인터페이스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public interface JobEventListener {

	/**
	 * <pre>
	 * 비즈니스 로직 수행 후 처리해야 하는 로직이 필요한 경우 구현하는 메소드(후행 처리 메소드)
	 * </pre>
	 * @param context
	 */
	void afterJob(JobExecution context);

	/**
	 * <pre>
	 * 비즈니스 로직 수행 전에 처리해야 하는 로직이 필요한 경우 구현하는 메소드(선행 처리 메소드)
	 * </pre>
	 * @param context
	 */
	void beforeJob(JobExecution context);

	/**
	 * <pre>
	 * {@link BaseJob#executeInternal(JobExecution)}에서 예외가 발생한 경우 자동으로 호출되는 예외 처리 메소드
	 * </pre>
	 * @param context
	 * @param e
	 */
	void onError(JobExecution context, Throwable e);
}//end of JobEventListener.java