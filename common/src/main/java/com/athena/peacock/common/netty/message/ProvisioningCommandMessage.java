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
 * Sang-cheon Park	2013. 7. 18.		First Draft.
 */
package com.athena.peacock.common.netty.message;

import java.util.ArrayList;
import java.util.List;

import com.athena.peacock.common.core.command.Command;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ProvisioningCommandMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;
	
    private List<Command> commands;

    /**
	 * @return the commands
	 */
	public List<Command> getCommands() {
    	if (commands == null) {
    		commands = new ArrayList<Command>();
    	}
    	
    	return commands;
	}
	
    /**
     * <pre>
     * Adds command object to command list
     * </pre>
     *
     * @param command
     */
    public void addCommand(Command command) {
    	getCommands().add(command);
    }

	/**
     * <pre>
     * Executes command 
     * </pre>
     */
    public void executeCommands(ProvisioningResponseMessage response) {
    	/*
    	 * Command 수행에 필요한 단위 Action을 모두 포함시킬 수도 있지만,
    	 * install, uninstall, configuration 등의 작업을 별도의 Command로 구성할 필요가 있을 경우
    	 * 사용될 수 있는 실행환경을 제공한다. 
    	 */
        for (Command command : commands) {
            command.execute(response);
        }
    }
	
	public ProvisioningCommandMessage() {
		super(MessageType.COMMAND);
	}
}
//end of ProvisioningCommand.java