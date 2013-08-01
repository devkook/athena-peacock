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
package com.athena.peacock.agent.job;

import com.athena.peacock.agent.netty.PeacockClient;
import com.athena.peacock.agent.scheduler.InternalJobExecutionException;
import com.athena.peacock.agent.scheduler.quartz.BaseJob;
import com.athena.peacock.agent.scheduler.quartz.JobExecution;

/**
 * <pre>
 * SIGAR를 이용한 주기적 System Monitoring을 수행하기 위한 Quartz Job
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SystemMonitoringJob extends BaseJob {
	
	/* (non-Javadoc)
	 * @see com.athena.peacock.agent.scheduler.quartz.BaseJob#executeInternal(com.athena.peacock.agent.scheduler.quartz.JobExecution)
	 */
	@Override
	protected void executeInternal(JobExecution context) throws InternalJobExecutionException {

		// TODO Auto-generated method stub
		System.out.println("TODO System Monitoring using SIGAR");

		PeacockClient client = (PeacockClient)this.context.getBean("peacockClient");
		//client.send(datagram);
	}//end of executeInternal()

}
//end of SystemMonitoringJob.java