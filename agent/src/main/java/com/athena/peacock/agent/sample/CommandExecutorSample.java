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
 * Sang-cheon Park	2013. 8. 14.		First Draft.
 */
package com.athena.peacock.agent.sample;

import java.io.File;

import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils.StringStreamConsumer;
import org.codehaus.plexus.util.cli.Commandline;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class CommandExecutorSample {

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param args
	 * @throws CommandLineException 
	 */
	public static void main(String[] args) throws CommandLineException {
		
		File executable = new File("/bin/cat");
		
		Commandline commandLine = new Commandline();
		commandLine.setExecutable(executable.getAbsolutePath());

		/** change working directory if necessary */
		commandLine.setWorkingDirectory("/");
		
		/** invoke createArg() and setValue() one by one for each arguments */
		commandLine.createArg().setValue("-n");
		commandLine.createArg().setValue("/etc/hosts");
		
		/** invoke createArg() and setLine() for entire arguments */
		//commandLine.createArg().setLine("-n /etc/hosts");
		
		/** verify command string */
		System.out.println("~]$ " + commandLine.toString() + "\n");
		
		/** also enable StringWriter, PrintWriter, WriterStreamConsumer and etc. */
		StringStreamConsumer consumer = new CommandLineUtils.StringStreamConsumer();

		int returnCode = CommandLineUtils.executeCommandLine(commandLine, consumer, consumer, Integer.MAX_VALUE);
		
		if (returnCode == 0) {
			// success
			System.out.println("==============[SUCCEED]==============");
			System.out.println(consumer.getOutput());
		} else {
			// fail
			System.err.println("==============[FAILED]==============");
			System.err.println(consumer.getOutput());
		}
	}

}
//end of CommandExecutorSample.java