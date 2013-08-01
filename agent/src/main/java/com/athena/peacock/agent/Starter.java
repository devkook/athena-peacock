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
 * Sang-cheon Park	2013. 7. 30.		First Draft.
 */
package com.athena.peacock.agent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.athena.peacock.agent.util.AgentConfigUtil;
import com.athena.peacock.agent.util.PeacockAgentIDGenerator;
import com.athena.peacock.common.constant.PeacockConstant;

/**
 * <pre>
 * Peacock Agent 구동을 위한 Starter 클래스로써 다음과 같은 작업을 수행한다.
 * 	<ul>
 * 		<li>/peacock/conf/peacock.conf 파일로부터 설정 정보를 load 한다.</li>
 * 		<li>Agent ID 확인을 위해 /peacock/.agent 파일 존재 여부를 확인하며, 해당 파일이 없을 경우 신규 Agent ID를 생성하고 파일에 저장한다.</li>
 * 		<li>Spring Context 파일을 load 함으로써 초기 시스템 정보 및 Quartz에 의한 1분 단위 시스템 모니터링을 수행하고 서버로 전송한다.</li>
 * 	</ul>
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class Starter {
	
    private static final Logger logger = LoggerFactory.getLogger(Starter.class);

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		/**
		 * /peacock/conf/peacock.conf 파일을 load 중 에러가 발생했는지의 여부를 검사하고 에러 발생 시 프로그램을 종료한다.
		 */
		String errorMsg = "\n\"/peacock/conf/agent.conf file\" does not exist or cannot read.\n"
						+ "Please check \"/peacock/conf/agent.conf\" file exists and can read.";
		
		Assert.isTrue(AgentConfigUtil.exception == null, errorMsg);
		Assert.notNull(AgentConfigUtil.getConfig(PeacockConstant.SERVER_IP), "ServerIP cannot be empty.");
		Assert.notNull(AgentConfigUtil.getConfig(PeacockConstant.SERVER_PORT), "ServerPort cannot be empty.");
		
		/**
		 * Agent ID 확인을 위해 /peacock/.agent 파일 존재 여부를 확인하며, 
		 * 해당 파일이 없을 경우 신규 Agent ID를 생성하고 파일에 저장한다.
		 */
		String agentId = null;
		
		File file = new File(PeacockConstant.AGENT_ID_FILE);
		boolean isNew = false;
		
		if(file.exists()) {
			try {
				agentId = IOUtils.toString(file.toURI());
				
				// 파일 내에 agent ID가 비어있거나 agent ID의 길이가 32가 아닐 경우 신규로 생성한다.
				if(StringUtils.isEmpty(agentId) || agentId.length() != 32) {
					throw new IOException();
				}
			} catch (IOException e) {
	            logger.error("/peacock/.agent file cannot read or saved invalid agent ID.", e);
	            
	            agentId = PeacockAgentIDGenerator.generateId();
	            isNew = true;
			}
		} else {
			agentId = PeacockAgentIDGenerator.generateId();
			isNew = true;
		}
		
		if(isNew) {
            logger.info("New Agent-ID({}) be generated.", agentId);
            
            try {
    			file.setWritable(true);
				OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file));
				output.write(agentId);
				file.setReadOnly();
				IOUtils.closeQuietly(output);
			} catch (UnsupportedEncodingException e) {
				logger.error("UnsupportedEncodingException has occurred : ", e);
			} catch (FileNotFoundException e) {
				logger.error("FileNotFoundException has occurred : ", e);
			} catch (IOException e) {
				logger.error("IOException has occurred : ", e);
			}
		}
		
		// Spring Application Context Loading
		logger.debug("Starting application context...");
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/context-*.xml");
        applicationContext.registerShutdownHook();
	}//end of main()

}
//end of Starter.java