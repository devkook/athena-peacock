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

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class AgentInitialInfoMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;
	
	/** os.name */
	private String osName;
	/** os.arch */
	private String osArch;
	/** os.version */
	private String osVersion;
	/** java.version */
	private String javaVersion;
	/** user.name */
	private String userName;
	
	public AgentInitialInfoMessage() {
		super(MessageType.INITIAL_INFO);
	}

	/**
	 * @return the osName
	 */
	public String getOsName() {
		return osName;
	}

	/**
	 * @param osName the osName to set
	 */
	public void setOsName(String osName) {
		this.osName = osName;
	}

	/**
	 * @return the osArch
	 */
	public String getOsArch() {
		return osArch;
	}

	/**
	 * @param osArch the osArch to set
	 */
	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	/**
	 * @return the osVersion
	 */
	public String getOsVersion() {
		return osVersion;
	}

	/**
	 * @param osVersion the osVersion to set
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	/**
	 * @return the javaVersion
	 */
	public String getJavaVersion() {
		return javaVersion;
	}

	/**
	 * @param javaVersion the javaVersion to set
	 */
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
//end of AgentInitialInfo.java