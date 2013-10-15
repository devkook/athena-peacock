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
 * Sang-cheon Park	2013. 9. 9.		First Draft.
 */
package com.athena.peacock.controller.web.machine;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athena.peacock.common.core.action.ConfigAction;
import com.athena.peacock.common.core.action.FileWriteAction;
import com.athena.peacock.common.core.action.ShellAction;
import com.athena.peacock.common.core.action.support.Property;
import com.athena.peacock.common.core.command.Command;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.netty.PeacockServerHandler;
import com.athena.peacock.controller.netty.PeacockTransmitter;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller("machineController")
@RequestMapping("/machine")
public class MachineController {

	@Inject
	@Named("machineService")
	private MachineService machineService;

    @Inject
    @Named("peacockTransmitter")
	private PeacockTransmitter peacockTransmitter;

    @Inject
    @Named("peacockServerHandler")
    private PeacockServerHandler handler;

	@RequestMapping("/list")
	public ModelAndView list(MachineDto machine, Model model) throws Exception {
		List<MachineDto> machineList = machineService.getMachineList(machine);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("machineList", machineList);
		
		return mav;
	}

	/**
	 * <pre>
	 * Provisioning Command 실행을 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/command")
	public ModelAndView command() throws Exception {
		List<MachineDto> machineList = machineService.getMachineList(new MachineDto());
		
		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId(machineList.get(0).getMachineId());
		cmdMsg.setBlocking(true);
		
		Command command = new Command("UNINSTALL");
		int sequence = 0;
		ShellAction action = new ShellAction(sequence++);
		action.setCommand("/bin/cat");
		action.addArguments("-n");
		action.addArguments("/etc/hosts");
		command.addAction(action);
		cmdMsg.addCommand(command);
		
		command = new Command("INSTALL");
		sequence = 0;
		action = new ShellAction(sequence++);
		action.setCommand("ls");
		action.addArguments("-al");
		action.addArguments("~/");
		command.addAction(action);
		cmdMsg.addCommand(command);
		
		command = new Command("CONFIGURATION");
		sequence = 0;
		action = new ShellAction(sequence++);
		action.setCommand("ls");
		action.addArguments("~/");
		command.addAction(action);
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}

	/**
	 * <pre>
	 * Agent에 Apache HTTP Daemon 설치를 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/apache")
	public ModelAndView apache() throws Exception {
		//List<MachineDto> machineList = machineService.getMachineList(new MachineDto());
		
		String targetDir = "/usr/local/apache";
		String version = "2.2.25";
		
		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId("e8a478c2faff41c6a94a52540020c3a0");
		cmdMsg.setBlocking(true);
		
		Command command = new Command("Apache INSTALL");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/httpd-" + version + ".tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("tar");
		s_action.addArguments("xvzf");
		s_action.addArguments("httpd-" + version + ".tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/apr-1.4.6.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/apr-util-1.5.2.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("tar");
		s_action.addArguments("xvzf");
		s_action.addArguments("apr-1.4.6.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("tar");
		s_action.addArguments("xvzf");
		s_action.addArguments("apr-util-1.5.2.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("mv");
		s_action.addArguments("apr-1.4.6");
		s_action.addArguments("apr");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version + "/srclib");
		s_action.setCommand("mv");
		s_action.addArguments("apr-util-1.5.2");
		s_action.addArguments("apr-util");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setCommand("yum");
		s_action.addArguments("install");
		s_action.addArguments("-y");
//		s_action.addArguments("apr-devel");
//		s_action.addArguments("apr-util-devel");
//		s_action.addArguments("gcc");
		s_action.addArguments("pcre-devel.x86_64");
//		s_action.addArguments("zlib-devel");
//		s_action.addArguments("openssl-devel");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version);
		s_action.setCommand("./configure");
		s_action.addArguments("--prefix=" + targetDir);
		s_action.addArguments("--enable-mods-shared=most");
		s_action.addArguments("--enable-ssl");
		s_action.addArguments("--with-ssl=/usr/local/openssl");
		s_action.addArguments("--enable-modules=ssl");
		s_action.addArguments("--enable-rewrite");
		s_action.addArguments("--with-included-apr");
		s_action.addArguments("--with-included-apr-util");
		s_action.addArguments("--enable-deflate");
		s_action.addArguments("--enable-expires");
		s_action.addArguments("--enable-headers");
		s_action.addArguments("--enable-proxy");
		
		if (version.startsWith("2.2")) {
			//s_action.addArguments("--with-mpm=prefork");
			s_action.addArguments("--with-mpm=worker");
		} else if (version.startsWith("2.3") || version.startsWith("2.4")) {
			s_action.addArguments("--enable-mpms-shared=all");
		}
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version);
		s_action.setCommand("make");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/httpd-" + version);
		s_action.setCommand("make");
		s_action.addArguments("install");
		command.addAction(s_action);
		
		// Add Apache INSTALL Command
		cmdMsg.addCommand(command);
		
		command = new Command("JK Connector INSTALL");
		sequence = 0;
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/tomcat-connectors-1.2.37-src.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("tar");
		s_action.addArguments("xvzf");
		s_action.addArguments("tomcat-connectors-1.2.37-src.tar.gz");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/tomcat-connectors-1.2.37-src/native");
		s_action.setCommand("./configure");
		s_action.addArguments("--with-apxs=" + targetDir + "/bin/apxs");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src/tomcat-connectors-1.2.37-src/native");
		s_action.setCommand("make");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setCommand("cp");
		s_action.addArguments("-f");
		s_action.addArguments("/usr/local/src/tomcat-connectors-1.2.37-src/native/apache-2.0/mod_jk.so");
		s_action.addArguments(targetDir + "/modules/mod_jk.so");
		command.addAction(s_action);
		
		// Add JK Connector INSTALL Command
		cmdMsg.addCommand(command);
		
		command = new Command("CONFIGURATION");
		sequence = 0;
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory(targetDir + "/conf");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/conf/httpd.conf");
		s_action.addArguments("-O");
		s_action.addArguments("httpd.conf");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory(targetDir + "/conf");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/conf/mod-jk.conf");
		s_action.addArguments("-O");
		s_action.addArguments("mod-jk.conf");
		command.addAction(s_action);

//		s_action = new ShellAction(sequence++);
//		s_action.setWorkingDiretory(targetDir + "/conf");
//		s_action.setCommand("wget");
//		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/conf/workers.properties");
//		s_action.addArguments("-O");
//		s_action.addArguments("workers.properties");
//		command.addAction(s_action);

		FileWriteAction fw_action = new FileWriteAction(sequence++);
		fw_action.setContents("/*.jsp=loadbalancer\r\n/*.do=loadbalancer");
		fw_action.setFileName(targetDir + "/conf/workers.properties");
		command.addAction(fw_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory(targetDir + "/conf");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/conf/uriworkermap.properties");
		s_action.addArguments("-O");
		s_action.addArguments("uriworkermap.properties");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory(targetDir + "/conf/extra");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/conf/httpd-mpm.conf");
		s_action.addArguments("-O");
		s_action.addArguments("httpd-mpm.conf");
		command.addAction(s_action);
		
		List<Property> properties = new ArrayList<Property>();
		Property property = new Property();
		property.setKey("ServerRoot");
		property.setValue(targetDir);
		properties.add(property);
		
		property = new Property();
		property.setKey("Port");
		property.setValue("80");
		properties.add(property);
		
		property = new Property();
		property.setKey("ServerName");
		property.setValue("localhost");
		properties.add(property);

		ConfigAction c_action = new ConfigAction(sequence++);
		c_action.setFileName(targetDir + "/conf/httpd.conf");
		c_action.setProperties(properties);
		command.addAction(c_action);
		
		// Add CONFIGURATION Command
		cmdMsg.addCommand(command);
		
		command = new Command("CHECK");
		sequence = 0;
		s_action = new ShellAction(sequence++);
		s_action.setCommand(targetDir + "/bin/apachectl");
		s_action.addArguments("-V");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand(targetDir + "/bin/apachectl");
		s_action.addArguments("-l");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand(targetDir + "/bin/apachectl");
		s_action.addArguments("start");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("sleep");
		s_action.addArguments("1");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("curl");
		s_action.addArguments("http://localhost");
		command.addAction(s_action);

		// Add CHECK Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}

	/**
	 * <pre>
	 * Agent에 MySQL 설치를 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mysql")
	public ModelAndView mysql() throws Exception {
		String dataDir = "/data/mysql";
		String port = "3316";
		String password = "peacock";
		
		String version = "5.5.34";
		
		if (StringUtils.isEmpty(dataDir)) {
			dataDir = "/var/lib/mysql";
		}
		
		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId("e8a478c2faff41c6a94a52540020c3a0");
		cmdMsg.setBlocking(true);
		
		Command command = new Command("CONFIGURATION");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/etc");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/mysql/" + version + "/my.cnf");
		s_action.addArguments("-O");
		s_action.addArguments("my.cnf");
		command.addAction(s_action);
		
		List<Property> properties = new ArrayList<Property>();
		Property property = new Property();
		property.setKey("mysql.datadir");
		property.setValue(dataDir);
		properties.add(property);
		
		property = new Property();
		property.setKey("mysql.port");
		property.setValue(port);
		properties.add(property);
		
		ConfigAction c_action = new ConfigAction(sequence++);
		c_action.setFileName("/etc/my.cnf");
		c_action.setProperties(properties);
		command.addAction(c_action);
		
		// Add CONFIGURATION Command
		cmdMsg.addCommand(command);
		
		command = new Command("MySQL INSTALL");
		sequence = 0;
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/mysql/" + version + "/MySQL-server.rpm");
		s_action.addArguments("-O");
		s_action.addArguments("MySQL-server.rpm");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/mysql/" + version + "/MySQL-client.rpm");
		s_action.addArguments("-O");
		s_action.addArguments("MySQL-client.rpm");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("rpm");
		s_action.addArguments("-Uvh");
		s_action.addArguments("MySQL-server.rpm");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("rpm");
		s_action.addArguments("-Uvh");
		s_action.addArguments("MySQL-client.rpm");
		command.addAction(s_action);
		
		// Add MySQL INSTALL Command
		cmdMsg.addCommand(command);
		
		command = new Command("Change Password");
		sequence = 0;
		s_action = new ShellAction(sequence++);
		s_action.setCommand("service");
		s_action.addArguments("mysql");
		s_action.addArguments("start");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("mysqladmin");
		s_action.addArguments("-u");
		s_action.addArguments("root");
		s_action.addArguments("password");
		s_action.addArguments(password);
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setCommand("mysqladmin");
		s_action.addArguments("-u");
		s_action.addArguments("root");
		s_action.addArguments("-h");
		s_action.addArguments("localhost.localdomain");
		s_action.addArguments("-P");
		s_action.addArguments(port);
		s_action.addArguments("password");
		s_action.addArguments(password);
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("mysql");
		s_action.addArguments("-u");
		s_action.addArguments("root");
		s_action.addArguments("-p" + password);
		s_action.addArguments("-e");
		s_action.addArguments("'select User,Host,Password from mysql.user'");
		command.addAction(s_action);

		// Add Change Password Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}

	/**
	 * <pre>
	 * Agent에 MySQL 제거를 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/mysql_init")
	public ModelAndView mysql_init() throws Exception {

		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId("e8a478c2faff41c6a94a52540020c3a0");
		cmdMsg.setBlocking(true);
		
		Command command = new Command("DELETE");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setCommand("service");
		s_action.addArguments("mysql");
		s_action.addArguments("stop");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("rpm");
		s_action.addArguments("--erase");
		s_action.addArguments("MySQL-client");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("rpm");
		s_action.addArguments("--erase");
		s_action.addArguments("MySQL-server");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setCommand("userdel");
		s_action.addArguments("-r");
		s_action.addArguments("mysql");
		command.addAction(s_action);

		// Add DELETE Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}

	/**
	 * <pre>
	 * Agent에 JBoss 설치를 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jboss")
	public ModelAndView jboss() throws Exception {

		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId("e8a478c2faff41c6a94a52540020c3a0");
		cmdMsg.setBlocking(true);
		
		/**
		 * JBoss Variables
		 */
		String jbossHome = "/peacock/jboss-as";
		String serverHome = "/peacock/servers";
		String serverName = "test1";
		String partitionName = "partition";
		String bindAddress = "0.0.0.0";
		String bindPort = "ports-default";
		
		/**
		 * DataSource Variables
		 */
		String databaseType = "mysql";
		String jndiName = "testDS";
		String connectionUrl = "jdbc:mysql:/localhost:3316/peacock";
		String userName = "root";
		String password = "peacock";
		String minPoolSize = "10";
		String maxPoolSize = "20";
		
		Command command = new Command("Pre-install");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setCommand("mkdir");
		s_action.addArguments("-p");
		s_action.addArguments(jbossHome);
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setCommand("mkdir");
		s_action.addArguments("-p");
		s_action.addArguments(serverHome);
		command.addAction(s_action);
		
		// Add Pre-install Command
		cmdMsg.addCommand(command);
		
		command = new Command("JBoss INSTALL");
		sequence = 0;
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/jboss/jboss-eap-5.2.0.zip");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("unzip");
		s_action.addArguments("-o");
		s_action.addArguments("jboss-eap-5.2.0.zip");
		s_action.addArguments("-d");
		s_action.addArguments(jbossHome);
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/jboss/jboss-cluster-template-5.2.0.zip");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("unzip");
		s_action.addArguments("-o");
		s_action.addArguments("jboss-cluster-template-5.2.0.zip");
		s_action.addArguments("-d");
		s_action.addArguments(serverHome + "/" + serverName);
		command.addAction(s_action);
		
		List<Property> properties = new ArrayList<Property>();
		Property property = null;
		
		property = new Property();
		property.setKey("jboss.home");
		property.setValue(jbossHome);
		properties.add(property);
		
		property = new Property();
		property.setKey("server.home");
		property.setValue(serverHome);
		properties.add(property);
		
		property = new Property();
		property.setKey("server.name");
		property.setValue(serverName);
		properties.add(property);
		
		property = new Property();
		property.setKey("partition.name");
		property.setValue(partitionName);
		properties.add(property);
		
		property = new Property();
		property.setKey("bind.address");
		property.setValue(bindAddress);
		properties.add(property);
		
		property = new Property();
		property.setKey("bind.port");
		property.setValue(bindPort);
		properties.add(property);
		
		ConfigAction c_action = new ConfigAction(sequence++);
		c_action.setFileName(serverHome + "/" + serverName + "/bin/env.sh");
		c_action.setProperties(properties);
		command.addAction(c_action);
		
		// Add JBoss INSTALL Command
		cmdMsg.addCommand(command);
		
		command = new Command("DataSource Configuration");
		sequence = 0;

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory(serverHome + "/" + serverName);
		s_action.setCommand("wget");
		
		if (databaseType.equals("oracle")) {
			s_action.addArguments("${RepositoryUrl}/jboss/datasource/oracle-ds.xml");
		} else if (databaseType.equals("mysql")) {
			s_action.addArguments("${RepositoryUrl}/jboss/datasource/mysql-ds.xml");
		} else if (databaseType.equals("cubrid")) {
			s_action.addArguments("${RepositoryUrl}/jboss/datasource/cubrid-ds.xml");
		}
		s_action.addArguments("-O");
		s_action.addArguments(serverName + "-ds.xml");
		command.addAction(s_action);
		
		properties = new ArrayList<Property>();
		property = new Property();
		property.setKey("jndi.name");
		property.setValue(jndiName);
		properties.add(property);
		
		property = new Property();
		property.setKey("connection.url");
		property.setValue(connectionUrl);
		properties.add(property);
		
		property = new Property();
		property.setKey("user.name");
		property.setValue(userName);
		properties.add(property);
		
		property = new Property();
		property.setKey("user.password");
		property.setValue(password);
		properties.add(property);
		
		property = new Property();
		property.setKey("pool.min");
		property.setValue(minPoolSize);
		properties.add(property);
		
		property = new Property();
		property.setKey("pool.max");
		property.setValue(maxPoolSize);
		properties.add(property);
		
		c_action = new ConfigAction(sequence++);
		c_action.setFileName(serverHome + "/" + serverName + "/" + serverName + "-ds.xml");
		c_action.setProperties(properties);
		command.addAction(c_action);
		
		// Add DataSource Configuration Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}

	/**
	 * <pre>
	 * Agent에 Tomcat 설치를 위한 테스트 메소드
	 * </pre>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tomcat")
	public ModelAndView tomcat() throws Exception {

		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId("e8a478c2faff41c6a94a52540020c3a0");
		cmdMsg.setBlocking(true);
		
		/**
		 * Tomcat Variables
		 */
		String javaHome = "";
		String serverName = "test2";
		String catalinaHome = "/peacock/tomcat";
		//String catalinaBase = "/peacock/servers/\\$SERVER_NAME";
		String catalinaBase = "/peacock/servers";
		String portOffset = "0";
		String compUser = "root";
		
		Command command = new Command("Pre-install");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setCommand("mkdir");
		s_action.addArguments("-p");
		s_action.addArguments(catalinaHome);
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setCommand("mkdir");
		s_action.addArguments("-p");
		s_action.addArguments(catalinaBase + "/" + serverName);
		command.addAction(s_action);
		
		// Add Pre-install Command
		cmdMsg.addCommand(command);
		
		command = new Command("Tomcat Install");
		sequence = 0;

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/tomcat/apache-tomcat-6.0.37.zip");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("unzip");
		s_action.addArguments("-o");
		s_action.addArguments("apache-tomcat-6.0.37.zip");
		s_action.addArguments("-d");
		s_action.addArguments(catalinaHome);
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/tomcat/tomcat-template-6.0.37.zip");
		command.addAction(s_action);

		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("unzip");
		s_action.addArguments("-o");
		s_action.addArguments("tomcat-template-6.0.37.zip");
		s_action.addArguments("-d");
		s_action.addArguments(catalinaBase + "/" + serverName);
		command.addAction(s_action);
		
		List<Property> properties = new ArrayList<Property>();
		Property property = null;
		
		property = new Property();
		property.setKey("java.home");
		property.setValue(javaHome);
		properties.add(property);
		
		property = new Property();
		property.setKey("server.name");
		property.setValue(serverName);
		properties.add(property);
		
		property = new Property();
		property.setKey("catalina.home");
		property.setValue(catalinaHome);
		properties.add(property);
		
		property = new Property();
		property.setKey("catalina.base");
		property.setValue(catalinaBase);
		properties.add(property);
		
		property = new Property();
		property.setKey("port.offset");
		property.setValue(portOffset);
		properties.add(property);
		
		property = new Property();
		property.setKey("comp.user");
		property.setValue(compUser);
		properties.add(property);
		
		ConfigAction c_action = new ConfigAction(sequence++);
		c_action.setFileName(catalinaBase + "/" + serverName + "/bin/env.sh");
		c_action.setProperties(properties);
		command.addAction(c_action);
		
		// Add Tomcat INSTALL Command
		cmdMsg.addCommand(command);
		
		PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(cmdMsg);
		ProvisioningResponseMessage response = peacockTransmitter.sendMessage(datagram);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", response.getResults());
		
		return mav;
	}
}
// end of MachineController.java