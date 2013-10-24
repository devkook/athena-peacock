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
 * Sang-cheon Park	2013. 10. 24.		First Draft.
 */
package com.athena.peacock.common.netty.message;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class OSPackageInfoMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;
	
	private List<PackageInfo> packageInfoList;

	public OSPackageInfoMessage(MessageType messageType) {
		super(MessageType.PACKAGE_INFO);
	}

	/**
	 * @return the packageInfoList
	 */
	public List<PackageInfo> getPackageInfoList() {
		if (packageInfoList == null) {
			packageInfoList = new ArrayList<PackageInfo>();
		}
		return packageInfoList;
	}

	/**
	 * @param packageInfo the packageInfo to add
	 */
	public void addPackageInfo(PackageInfo packageInfo) {
		getPackageInfoList().add(packageInfo);
	}

	/**
	 * @param packageInfoList the packageInfoList to set
	 */
	public void setPackageInfoList(List<PackageInfo> packageInfoList) {
		this.packageInfoList = packageInfoList;
	}
}
//end of OSPackageInfoMessage.java