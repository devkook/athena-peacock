package com.athena.peacock.controller.web.rhevm;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;
import com.athena.peacock.controller.web.rhevm.dto.VMDto;


/**
 * <pre>
 * This is a controller for RHEV-M API.
 * RHEV-M API를 이용한 작업을 수행하는 컨트롤러
 * </pre>
 * @author Ji-Woong Choi
 * @version 1.0
 */
@Controller
@RequestMapping("/rhevm")
public class RHEVMController {

    protected final Logger logger = LoggerFactory.getLogger(RHEVMController.class);

	@Inject
	@Named("rhevmService")
	private RHEVMService rhevmService;

		
	/**
	 * <pre>
	 * 지정된 RHEV-M(rhevmId)에 해당하는 Virtual Machine 목록을 조회
	 * </pre>
	 * @param jsonRes
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vms/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, VMDto vms) throws Exception {
		// 아래는 향후 Multi RHEV-M을 컨트롤할 때 rhevmId를 입력받아 처리하도록 함
		//Assert.isTrue(!StringUtils.isEmpty(vms.getRhevmId()), "rhevmId must not be null.");
		
		jsonRes.setList(rhevmService.getVirtualList(vms));
		
		return jsonRes;
	}
	
	
	/**
	 * <pre>
	 * 지정된 RHEV-M(rhevmId)에 해당하는 특정 Virtual Machine을 조회
	 * </pre>
	 * @param jsonRes
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vms/info")
	public @ResponseBody SimpleJsonResponse retrieve(SimpleJsonResponse jsonRes, VMDto vm) throws Exception {
		// 아래는 향후 Multi RHEV-M을 컨트롤할 때 rhevmId를 입력받아 처리하도록 함
		//Assert.isTrue(!StringUtils.isEmpty(vm.getRhevmId()), "rhevmId must not be null.");
		Assert.isTrue(!StringUtils.isEmpty(vm.getVmId()), "vmId must not be null.");
		
		jsonRes.setData(rhevmService.getVirtualMachine(vm));
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * 지정된 Virtual Machine에 할당된 네트워크 인터페이스 조회
	 * </pre>
	 * @param jsonRes
	 * @param machineId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/vms/nics")
	public @ResponseBody GridJsonResponse nics(GridJsonResponse jsonRes, VMDto vm) throws Exception {
		Assert.isTrue(!StringUtils.isEmpty(vm.getVmId()), "vmId must not be null.");
		logger.info("NIC Request Accepted. Virtual Machine ID: " + vm.getVmId());
		
		jsonRes.setList(rhevmService.getNIC(vm));
		
		return jsonRes;
	}
}