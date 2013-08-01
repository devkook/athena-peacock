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
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Batch Job의 상태를 trace 할 수 있는  Listener
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JobLogListener implements JobListener {
    
	private static final Logger logger = LoggerFactory.getLogger(JobLogListener.class);
	private String name;
	
	public void setName(String name) {
	    this.name = name;
	}

	public String getName() {
		return name == null ? "JobLogListener" : name;
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param context
	 * @see org.quartz.JobListener#jobExecutionVetoed(org.quartz.JobExecutionContext)
	 */
	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param context
	 * @see org.quartz.JobListener#jobToBeExecuted(org.quartz.JobExecutionContext)
	 */
	public void jobToBeExecuted(JobExecutionContext context) {
	}

	/**
	 * <pre>
	 * Batch Job 실행 시 Job 상세정보 이력을 저장한다.
	 * </pre>
	 * @param context
	 * @param jobException
	 * @see org.quartz.JobListener#jobWasExecuted(org.quartz.JobExecutionContext, org.quartz.JobExecutionException)
	 */
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		// 시작 로그
		JobDetailLog log = new JobDetailLog();

		try {
			log.setActualFireTime(context.getFireTime());
			log.setScheduledFireTime(context.getScheduledFireTime());
			log.setJobGroup(context.getJobDetail().getGroup());
			log.setJobName(context.getJobDetail().getName());
			log.setRunningTime(context.getJobRunTime());
			log.setSchedulerName(context.getScheduler().getSchedulerName());
			log.setTriggerName(context.getTrigger().getName());
			
			logger.debug("JobDetailLog : [{}]", log);
		} catch (SchedulerException e) {
		    logger.error("Job Detail 정보 로깅 중 예기치 못한 상황이 발생했습니다.", e);
		}
	}
}//end of JobLogListener.java