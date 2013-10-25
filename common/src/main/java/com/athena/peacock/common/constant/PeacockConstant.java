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
package com.athena.peacock.common.constant;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public final class PeacockConstant {

	/** 서버 접속 정보등의 설정 정보가 저장된 파일 경로 */
	public static final String CONFIG_FILE_KEY 		= "peacock.agent.config.file.name";
	/** Agent ID가 저장된 파일 경로 */
	public static final String AGENT_ID_FILE_KEY	= "peacock.agent.agent.file.name";
	/** 패키지 정보 수집 이력이 저장된 파일 경로 */
	public static final String PACKAGE_FILE_KEY		= "peacock.agent.package.file.name";
	/** Agent에서 접속할 서버의 IP */
	public static final String SERVER_IP 			= "ServerIP";
	/** Agent에서 접속할 서버의 포트 */
	public static final String SERVER_PORT 			= "ServerPort";
	/** wget을 이용한 software 다운로드 url */
	public static final String REPOSITORY_URL		= "RepositoryUrl";
	/** 모니터링 항목 리스트 */
	public static final String MON_FACTOR_LIST 		= "MON_FACTOR_LIST";
}
//end of PeacockConstant.java