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
 * 현재 스케줄링 잡의 상태를 나타내는 클래스
 * code 값
 * - RUNNING
 * - COMPLETED
 * - FAILED
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class JobStatus {

    public static final String RUNNING = "RUNNING";
    public static final String COMPLETED = "COMPLETED";
    public static final String FAILED = "FAILED";

    private String currentStatus;

    private JobStatus() {
        this.currentStatus = RUNNING;
    }

    public static JobStatus start() {
        return new JobStatus();
    }

    public String getStatusCode() {
        return this.currentStatus;
    }

    public void complete() {
        this.currentStatus = COMPLETED;
    }

    public void fail() {
        this.currentStatus = FAILED;
    }

    public boolean isCompleted() {
        return COMPLETED.equals(currentStatus);
    }

    public boolean isFailed() {
        return FAILED.equals(currentStatus);
    }
}//end of JobStatus.java