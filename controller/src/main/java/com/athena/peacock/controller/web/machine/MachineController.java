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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.athena.peacock.common.core.action.ShellAction;
import com.athena.peacock.common.core.command.Command;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.machine.MachineDto;
import com.athena.peacock.controller.machine.MachineService;
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
	 * Provisioning Command 실행을 위한 테스트 메소
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
}
// end of MachineController.java