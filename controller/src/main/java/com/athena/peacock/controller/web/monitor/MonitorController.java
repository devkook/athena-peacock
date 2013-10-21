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
 * Sang-cheon Park	2013. 10. 18.		First Draft.
 */
package com.athena.peacock.controller.web.monitor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.common.core.handler.MonFactorHandler;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller("monitorController")
@RequestMapping("/monitor")
public class MonitorController {
	
	@Inject
	@Named("monitorService")
	private MonitorService monitorService;
	
	@Inject
	@Named("monFactorHandler")
	private MonFactorHandler monFactorHandler;
	
	@RequestMapping("/list")
	public @ResponseBody List<MonDataDto> list(GridJsonResponse jsonRes, MonDataDto monData) throws Exception {
		Assert.notNull(monData.getMachineId(), "machineId can not be null.");
		Assert.notNull(monData.getMonFactorId(), "monFactorId can not be null.");
		
		List<MonDataDto> monDataList = monitorService.getMonDataList(monData);
		
		
		return monDataList;
	}
	
	@RequestMapping("/factor_list")
	public @ResponseBody GridJsonResponse factorList(GridJsonResponse jsonRes) throws Exception {
		
		List<MonFactorDto> monFactorList = monFactorHandler.getMonFactorList();
		
		jsonRes.setTotal(monFactorList.size());
		jsonRes.setList(monFactorList);
		
		return jsonRes;
	}
}
//end of MonitorController.java