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
 * Sang-cheon Park	2013. 10. 21.		First Draft.
 */
package com.athena.peacock.controller.common.provisioning;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.core.action.ConfigAction;
import com.athena.peacock.common.core.action.FileWriteAction;
import com.athena.peacock.common.core.action.ShellAction;
import com.athena.peacock.common.core.action.support.Property;
import com.athena.peacock.common.core.command.Command;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.netty.PeacockTransmitter;

/**
 * <pre>
 * Software 설치 및 Config 파일 변경 등 프로비저닝 관련 지원 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("provisioningHandler")
public class ProvisioningHandler {

    protected final Logger logger = LoggerFactory.getLogger(ProvisioningHandler.class);

    @Inject
    @Named("peacockTransmitter")
	private PeacockTransmitter peacockTransmitter;

	public List<String> install(ProvisioningDetail provisioningDetail) throws Exception {
		if (provisioningDetail.getType().toLowerCase().equals("apache")) {
			return apache_install(provisioningDetail);
		} else
			
		return null;
	}

	public String remove(ProvisioningDetail provisioningDetail) {
		return null;
	}
    
	private List<String> apache_install(ProvisioningDetail provisioningDetail) throws Exception {
		ProvisioningCommandMessage cmdMsg = new ProvisioningCommandMessage();
		cmdMsg.setAgentId(provisioningDetail.getMachineId());
		cmdMsg.setBlocking(true);
		
		String targetDir = provisioningDetail.getTargetDir();
		String version = provisioningDetail.getVersion();
		
		Command command = new Command("Apache INSTALL");
		int sequence = 0;
		
		ShellAction s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/" + version + "/httpd-" + version + ".tar.gz");
		//s_action.addArguments(">");
		//s_action.addArguments("/dev/null");
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
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("wget");
		s_action.addArguments("${RepositoryUrl}/apache/pcre-devel-8.13-1.4.x86_64.rpm");
		command.addAction(s_action);
		
		s_action = new ShellAction(sequence++);
		s_action.setWorkingDiretory("/usr/local/src");
		s_action.setCommand("rpm");
		s_action.addArguments("-Uvh");
		s_action.addArguments("pcre-devel-8.13-1.4.x86_64.rpm");
		command.addAction(s_action);
		
		/*
		s_action = new ShellAction(sequence++);
		s_action.setCommand("yum");
		s_action.addArguments("install");
		s_action.addArguments("-y");
		s_action.addArguments("pcre-devel.x86_64");
		//s_action.addArguments("apr-devel");
		//s_action.addArguments("apr-util-devel");
		//s_action.addArguments("gcc");
		//s_action.addArguments("zlib-devel");
		//s_action.addArguments("openssl-devel");
		command.addAction(s_action);
		*/
		
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
		
		return response.getResults();
	}
}
//end of ProvisioningHandler.java