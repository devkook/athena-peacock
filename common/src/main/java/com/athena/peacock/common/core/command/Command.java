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
 * Sang-cheon Park	2013. 9. 2.		First Draft.
 */
package com.athena.peacock.common.core.command;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.common.core.action.Action;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;

/**
 * <pre>
 * Set of actions
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class Command {
	
	private static final Logger logger = LoggerFactory.getLogger(Command.class);
	
	private String name;
	private List<Action> actions;
	
	public Command(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public void addAction(Action action) {
		if (actions == null) {
			actions = new ArrayList<Action>();
		}
		
		actions.add(action);
	}
	
    public void execute(ProvisioningResponseMessage response) {
    	
        for (Action action : actions) {
        	logger.debug("[{}] will be start.", action.getClass().getCanonicalName());
        	
        	response.addResult(name, action.perform());
            
        	logger.debug("[{}] has done.", action.getClass().getCanonicalName());
        }
    }
}
//end of Command.java