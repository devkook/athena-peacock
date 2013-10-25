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
package com.athena.peacock.controller.web.ospackage;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.MessageType;
import com.athena.peacock.common.netty.message.OSPackageInfoMessage;
import com.athena.peacock.controller.netty.PeacockTransmitter;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller("packageController")
@RequestMapping("/package")
public class PackageController {
	
	@Inject
	@Named("packageService")
	private PackageService packageService;

    @Inject
    @Named("peacockTransmitter")
	private PeacockTransmitter peacockTransmitter;

	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, PackageDto ospackage) throws Exception {
		Assert.isTrue(StringUtils.isNotEmpty(ospackage.getMachineId()), "machineId must not be null.");
		
		jsonRes.setTotal(packageService.getPackageListCnt(ospackage));
		jsonRes.setList(packageService.getPackageList(ospackage));
		
		return jsonRes;
	}

	@RequestMapping("/reload")
	public @ResponseBody SimpleJsonResponse reload(SimpleJsonResponse jsonRes, PackageDto ospackage) throws Exception {
		Assert.isTrue(StringUtils.isNotEmpty(ospackage.getMachineId()), "machineId must not be null.");

		OSPackageInfoMessage msg = new OSPackageInfoMessage(MessageType.PACKAGE_INFO) ;
		msg.setAgentId(ospackage.getMachineId());
		msg.setBlocking(false);
		
		PeacockDatagram<AbstractMessage> datagram = new PeacockDatagram<AbstractMessage>(msg);
		peacockTransmitter.sendMessage(datagram);
		
		jsonRes.setSuccess(true);
		jsonRes.setMsg("패키지 정보 재 수집 요청이 전달되었습니다.");
		
		return jsonRes;
	}
}
//end of PackageController.java