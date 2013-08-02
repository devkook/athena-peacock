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

import org.quartz.JobExecutionContext;

import com.athena.peacock.agent.scheduler.JobStatus;

/**
 * <pre>
 * {@link JobExecutionContext}의 래핑 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JobExecution {

	private JobStatus jobStatus;
	private JobExecutionContext context;

	private JobExecution(JobExecutionContext context) {
		this.context = context;
	}

	public void initJobStatus() {
		jobStatus = JobStatus.start();
	}

	public JobStatus getJobStatus() {
		return this.jobStatus;
	}

	public JobExecutionContext getContext() {
		return context;
	}

	public static JobExecution newExecution(JobExecutionContext context) {
		JobExecution execution = new JobExecution(context);
		execution.initJobStatus();
		return execution;
	}
}//end of JobExecution.java