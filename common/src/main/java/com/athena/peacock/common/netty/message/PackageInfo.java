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

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class PackageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String arch;
	private String size;
	private String version;
	private String release;
	private Date installDate;
	private String summary;
	private String description;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the arch
	 */
	public String getArch() {
		return arch;
	}

	/**
	 * @param arch the arch to set
	 */
	public void setArch(String arch) {
		this.arch = arch;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the release
	 */
	public String getRelease() {
		return release;
	}

	/**
	 * @param release the release to set
	 */
	public void setRelease(String release) {
		this.release = release;
	}

	/**
	 * @return the installDate
	 */
	public Date getInstallDate() {
		return installDate;
	}

	/**
	 * @param installDate the installDate to set
	 */
	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
//end of PackageInfo.java