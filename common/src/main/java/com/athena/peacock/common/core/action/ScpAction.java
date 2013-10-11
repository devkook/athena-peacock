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
 * Sang-cheon Park	2013. 10. 11.		First Draft.
 */
package com.athena.peacock.common.core.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.common.core.action.support.TargetHost;
import com.athena.peacock.common.core.util.ScpUtil;

/**
 * <pre>
 * 지정된 호스트로 파일 또는 디렉토리를 전송하기 위한 액션 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ScpAction extends Action {

	private static final Logger logger = LoggerFactory.getLogger(ScpAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private TargetHost targetHost;
    private String source;
    private String target;

	public ScpAction(int sequence) {
		super(sequence);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the targetHost
	 */
	public TargetHost getTargetHost() {
		return targetHost;
	}

	/**
	 * @param targetHost the targetHost to set
	 */
	public void setTargetHost(TargetHost targetHost) {
		this.targetHost = targetHost;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.common.core.action.Action#perform()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String perform() {
    	logger.debug("\n- Target Host Info : [{}]\n- Source : [{}]\n- Target : [{}]", new Object[]{targetHost.toString(), source, target});
    	
		String result = "F";
    	
        try {
			ScpUtil.upload(targetHost, source, target);
			result = "S";
		} catch (Exception e) {
			logger.error("Unhandled exception has occurred.", e);
		}
        
        return result;
    }//end of perform()

}
//end of ScpAction.java