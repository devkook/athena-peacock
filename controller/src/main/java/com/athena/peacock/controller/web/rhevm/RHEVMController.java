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
import com.athena.peacock.controller.web.rhevm.dto.RHEVMDto;
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
	 * 지정된 Agent(machineID)의 소프트웨어 설치 목록 조회
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
		
		jsonRes.setTotal(rhevmService.getVirtualMachineListCnt(vms));
		jsonRes.setList(rhevmService.getVirtualList(vms));
		
		return jsonRes;
	}
}