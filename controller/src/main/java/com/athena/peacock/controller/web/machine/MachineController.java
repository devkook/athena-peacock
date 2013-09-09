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

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.athena.peacock.controller.machine.MachineDto;
import com.athena.peacock.controller.machine.MachineService;

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

	@RequestMapping("/list")
	public String list(MachineDto machine, ModelMap model) throws Exception {
		List<MachineDto> machineList = machineService.getMachineList(machine);
		model.addAttribute("machineList", machineList);
		
		return "jsonView";
	}
}
// end of MachineController.java