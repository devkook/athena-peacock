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
	private String installDate;
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
	public String getInstallDate() {
		return installDate;
	}

	/**
	 * @param installDate the installDate to set
	 */
	public void setInstallDate(String installDate) {
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
	
	public static void main(String[] args) {
		String result = "virt-what\n"
						+ "x86_64\n"
						+ "37231\n"
						+ "1.11\n"
						+ "1.2.el6\n"
						+ "Thu 10 Oct 2013 12:45:17 PM KST\n"
						+ "Detect if we are running in a virtual machine\n"
						+ "virt-what is a shell script which can be used to detect if the program\n"
						+ "is running in a virtual machine.\n"
						+ "\n"
						+ "The program prints out a list of \"facts\" about the virtual machine,\n"
						+ "derived from heuristics.  One fact is printed per line.\n"
						+ "\n"
						+ "If nothing is printed and the script exits with code 0 (no error),\n"
						+ "then it can mean either that the program is running on bare-metal or\n"
						+ "the program is running inside a type of virtual machine which we don't\n"
						+ "know about or cannot detect.\n"
						+ "\n"
						+ "Current types of virtualization detected:\n"
						+ "\n"
						+ " - hyperv       Microsoft Hyper-V\n"
						+ " - kvm          Linux Kernel Virtual Machine (KVM)\n"
						+ " - openvz       OpenVZ or Virtuozzo\n"
						+ " - powervm_lx86 IBM PowerVM Lx86 Linux/x86 emulator\n"
						+ " - qemu         QEMU (unaccelerated)\n"
						+ " - uml          User-Mode Linux (UML)\n"
						+ " - virtage      Hitachi Virtualization Manager (HVM) Virtage LPAR\n"
						+ " - virtualbox   VirtualBox\n"
						+ " - virtualpc    Microsoft VirtualPC\n"
						+ " - vmware       VMware\n"
						+ " - xen          Xen\n"
						+ " - xen-dom0     Xen dom0 (privileged domain)\n"
						+ " - xen-domU     Xen domU (paravirtualized guest domain)\n"
						+ " - xen-hvm      Xen guest fully virtualized (HVM)";
		
		int start = 0, end = 0;
		PackageInfo packageInfo = new PackageInfo();
		
		start = 0;
		end = result.indexOf("\n", start);
		packageInfo.setName(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setArch(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setSize(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setVersion(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setRelease(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setInstallDate(result.substring(start, end));
		
		start = end + 1;
		end = result.indexOf("\n", start);
		packageInfo.setSummary(result.substring(start, end));
		
		start = end + 1;
		packageInfo.setDescription(result.substring(start));
		
		System.out.println("name => " + packageInfo.getName());
		System.out.println("arch => " + packageInfo.getArch());
		System.out.println("size => " + packageInfo.getSize());
		System.out.println("version => " + packageInfo.getVersion());
		System.out.println("release => " + packageInfo.getRelease());
		System.out.println("installDate => " + packageInfo.getInstallDate());
		System.out.println("summary => " + packageInfo.getSummary());
		System.out.println("desc => " + packageInfo.getDescription());
	}
}
//end of PackageInfo.java