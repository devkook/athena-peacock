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
package com.athena.peacock.common.core.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.codehaus.plexus.util.cli.CommandLineUtils.StringStreamConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.common.core.action.support.AgentConfigUtil;

/**
 * <pre>
 * Action for Shell Command
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ShellAction extends Action {

	private static final Logger logger = LoggerFactory.getLogger(ShellAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private String workingDiretory;
	private String command;
	private List<String> arguments;
	
	public ShellAction(int sequence) {
		super(sequence);
	}

	/**
	 * @return the workingDiretory
	 */
	public String getWorkingDiretory() {
		return workingDiretory;
	}

	/**
	 * @param workingDiretory the workingDiretory to set
	 */
	public void setWorkingDiretory(String workingDiretory) {
		this.workingDiretory = workingDiretory;
	}

	/**
	 * @return the command
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * @param command the command to set
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * @return the arguments
	 */
	public List<String> getArguments() {
		if (arguments == null) {
			arguments = new ArrayList<String>();
		}
		
		return arguments;
	}

	/**
	 * @param arguments the arguments to set
	 */
	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	/**
	 * @param argument the argument to add
	 */
	public void addArguments(String argument) {
		getArguments().add(argument);
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.common.core.action.Action#perform()
	 */
	@Override
	public String perform() {
		StringStreamConsumer consumer = null;
		
		try {
			Commandline commandLine = new Commandline();
			
			if (!StringUtils.isEmpty(workingDiretory)) {
				commandLine.setWorkingDirectory(workingDiretory);
			}
			
			commandLine.setExecutable(command);
			
			if (getArguments().size() > 0) {
				for (String argument : arguments) {
					if (argument.indexOf("RepositoryUrl") > -1) {
						commandLine.createArg().setLine(argument.replaceAll("\\$\\{RepositoryUrl\\}", AgentConfigUtil.getConfig(PeacockConstant.REPOSITORY_URL)));
					} else {
						commandLine.createArg().setLine(argument);
					}
				}
			}
			
			/** verify command string */
			logger.debug("{} ~]$ {}\n", workingDiretory, commandLine.toString());
			
			/** also enable StringWriter, PrintWriter, WriterStreamConsumer and etc. */
			consumer = new CommandLineUtils.StringStreamConsumer();

			int returnCode = CommandLineUtils.executeCommandLine(commandLine, consumer, consumer, Integer.MAX_VALUE);
			
			if (returnCode == 0) {
				// success
				logger.debug(consumer.getOutput());
			} else {
				// fail
				logger.error(consumer.getOutput());
			}
		} catch (CommandLineException e) {
			logger.error("CommandLineException has occurred. : ", e);
		}

		return consumer.getOutput();
	}
	
}
//end of ShellAction.java