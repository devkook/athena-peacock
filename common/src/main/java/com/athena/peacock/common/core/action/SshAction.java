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

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.common.core.action.support.TargetHost;
import com.athena.peacock.common.core.util.SshExecUtil;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class SshAction extends Action {
	
	private static final Logger logger = LoggerFactory.getLogger(SshAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private TargetHost targetHost;
    private List<String> commandList;

    public SshAction(int sequence) {
		super(sequence);
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
	 * @return the commandList
	 */
	public List<String> getCommandList() {
		return commandList;
	}

	/**
	 * @param commandList the commandList to set
	 */
	public void setCommandList(List<String> commandList) {
		this.commandList = commandList;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.common.core.action.Action#perform()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String perform() {
    	logger.debug("\n- Target Host Info : [{}]", targetHost.toString());
    	
		String result = "F";
    	
        try {
			SshExecUtil.executeCommand(targetHost, commandList);
			result = "S";
			
			logger.debug("Execute Command(s) Result : \n{}", IOUtils.toString(SshExecUtil.output.toURI()));
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
        
        return result;
    }//end of perform()

}
//end of SshAction.java