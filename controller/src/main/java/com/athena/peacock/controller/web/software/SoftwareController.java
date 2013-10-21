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
 * Sang-cheon Park	2013. 10. 16.		First Draft.
 */
package com.athena.peacock.controller.web.software;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.common.provisioning.ProvisioningDetail;
import com.athena.peacock.controller.common.provisioning.ProvisioningHandler;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;
import com.athena.peacock.controller.web.machine.MachineDto;

/**
 * <pre>
 * 소프트웨어 설치 및 제거를 위한 컨트롤러
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/software")
public class SoftwareController {

    protected final Logger logger = LoggerFactory.getLogger(SoftwareController.class);

	@Inject
	@Named("softwareRepoService")
	private SoftwareRepoService softwareRepoService;
	
	@Inject
	@Named("provisioningHandler")
	private ProvisioningHandler provisioningHandler;
	
	/**
	 * <pre>
	 * 지정된 Agent(machineID)의 소프트웨어 설치 목록 조회
	 * </pre>
	 * @param jsonRes
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, MachineDto machine) throws Exception {
		Assert.isTrue(!StringUtils.isEmpty(machine.getMachineId()), "machineId must not be null.");
		
		jsonRes.setTotal(softwareRepoService.getSoftwareInstallListCnt(machine));
		jsonRes.setList(softwareRepoService.getSoftwareInstallList(machine));
		
		return jsonRes;
	}

	/**
	 * <pre>
	 * Agent에 소프트웨어를 설치
	 * </pre>
	 * @param jsonRes
	 * @param provisioningDetail
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/install")
	public @ResponseBody SimpleJsonResponse install(SimpleJsonResponse jsonRes, ProvisioningDetail provisioningDetail) {
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getType()), "type must not be null.");
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getVersion()), "version must not be null.");
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getMachineId()), "machineId must not be null.");

		try {
			// 기 설치 여부 검사
			boolean installed = false;
			boolean installedDiffVersion = false;
			List<SoftwareRepoDto> softwareRepoList = softwareRepoService.getSoftwareInstallListAll(provisioningDetail.getMachineId());
			
			for (SoftwareRepoDto softwareRepo : softwareRepoList) {
				if (softwareRepo.getSoftwareName().toLowerCase().indexOf(provisioningDetail.getType().toLowerCase()) > -1 && softwareRepo.getInstallYn().equals("Y")) {
					if (softwareRepo.getSoftwareVersion().equals(provisioningDetail.getVersion())) {
						installed = true;
					} else {
						installedDiffVersion = true;
					}
				}
			}
			
			if (installed) {
				jsonRes.setSuccess(false);
				jsonRes.setMsg("이미 설치된 소프트웨어입니다.");
				return jsonRes;
			} 
			
			if (installedDiffVersion) {
				jsonRes.setSuccess(false);
				jsonRes.setMsg("다른 버전의 소프트웨어가 설치되어 있습니다. 해당 소프트웨어 삭제 후 설치하여 주십시오.");
				return jsonRes;
			}
		
			provisioningHandler.install(provisioningDetail);
			jsonRes.setMsg("Install success.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Install fail.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * Agent에 소프트웨어를 삭제
	 * </pre>
	 * @param jsonRes
	 * @param provisioningDetail
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/remove")
	public @ResponseBody SimpleJsonResponse remove(SimpleJsonResponse jsonRes, ProvisioningDetail provisioningDetail) throws Exception {
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getType()), "type must not be null.");
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getVersion()), "version must not be null.");
		Assert.isTrue(!StringUtils.isEmpty(provisioningDetail.getMachineId()), "machineId must not be null.");
		
		try {
			provisioningHandler.remove(provisioningDetail);
			jsonRes.setMsg("remove success.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("remove fail.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
}
//end of SoftwareController.java