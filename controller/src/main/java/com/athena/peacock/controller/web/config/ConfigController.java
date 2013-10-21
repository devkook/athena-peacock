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
package com.athena.peacock.controller.web.config;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.web.common.model.DtoJsonResponse;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/config")
public class ConfigController {

	@Inject
	@Named("configService")
	private ConfigService configService;

	@Inject
	@Named("configRepoService")
	private ConfigRepoService configRepoService;
	
	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, ConfigDto config) throws Exception {
		Assert.notNull(config.getMachineId(), "machineId can not be null.");
		Assert.notNull(config.getSoftwareId(), "softwareId can not be null.");
		
		jsonRes.setTotal(configService.getConfigListCnt(config));
		jsonRes.setList(configService.getConfigList(config));
		
		return jsonRes;
	}
	
	@RequestMapping("/repo_list")
	public @ResponseBody GridJsonResponse repoList(GridJsonResponse jsonRes, ConfigRepoDto configRepo) throws Exception {
		Assert.notNull(configRepo.getSoftwareId(), "softwareId can not be null.");
		
		jsonRes.setTotal(configRepoService.getConfigRepoListCnt(configRepo));
		jsonRes.setList(configRepoService.getConfigRepoList(configRepo));
		
		return jsonRes;
	}
	
	@RequestMapping("/update")
	public @ResponseBody SimpleJsonResponse update(SimpleJsonResponse jsonRes, ConfigDto config) throws Exception {
		Assert.notNull(config.getMachineId(), "machineId can not be null.");
		Assert.notNull(config.getSoftwareId(), "softwareId can not be null.");
		Assert.notNull(config.getConfigFileId(), "configFileId can not be null.");
		
		try {
			configService.updateConfig(config);
			jsonRes.setMsg("Update success.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Update fail.");
			e.printStackTrace();
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/getConfig")
	public @ResponseBody DtoJsonResponse getConfig(DtoJsonResponse jsonRes, ConfigDto config) throws Exception {
		Assert.notNull(config.getMachineId(), "machineId can not be null.");
		Assert.notNull(config.getSoftwareId(), "softwareId can not be null.");
		Assert.notNull(config.getConfigFileId(), "configFileId can not be null.");
		
		jsonRes.setData(configService.getConfig(config));
		
		return jsonRes;
	}

}
//end of ConfigController.java