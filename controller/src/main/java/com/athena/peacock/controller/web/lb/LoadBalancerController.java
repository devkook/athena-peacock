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
 * Sang-cheon Park	2013. 10. 29.		First Draft.
 */
package com.athena.peacock.controller.web.lb;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;
import com.athena.peacock.controller.web.user.UserController;
import com.athena.peacock.controller.web.user.UserDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/lb")
public class LoadBalancerController {

    protected final Logger logger = LoggerFactory.getLogger(LoadBalancerController.class);

	@Inject
	@Named("loadBalancerService")
	private LoadBalancerService loadBalancerService;
	
	@Inject
	@Named("lbListenerService")
	private LBListenerService lbListenerService;
	
	/**
	 * <pre>
	 * L/B 목록을 조회한다.
	 * </pre>
	 * @param jsonRes
	 * @param loadBalancer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		//Assert.notNull(loadBalancer.getMachineId(), "machineId can not be null.");
		
		jsonRes.setTotal(loadBalancerService.getLoadBalancerListCnt(loadBalancer));
		jsonRes.setList(loadBalancerService.getLoadBalancerList(loadBalancer));
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * 해당 L/B의 Listener 목록을 조회한다.
	 * </pre>
	 * @param jsonRes
	 * @param lbListener
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/listenerlist")
	public @ResponseBody GridJsonResponse listenerlist(GridJsonResponse jsonRes, LBListenerDto lbListener) throws Exception {
		Assert.notNull(lbListener.getLoadBalancerId(), "loadBalancerId can not be null.");
		
		jsonRes.setTotal(lbListenerService.getLBListenerListCnt(lbListener));
		jsonRes.setList(lbListenerService.getLBListenerList(lbListener));
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * 해당 L/B에 등록된 Agent(Machine) 목록을 조회한다.
	 * </pre>
	 * @param jsonRes
	 * @param lbListener
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/machinelist")
	public @ResponseBody GridJsonResponse machinelist(GridJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		Assert.notNull(loadBalancer.getLoadBalancerId(), "loadBalancerId can not be null.");
		
		jsonRes.setTotal(loadBalancerService.getLBMachineMapListCnt(loadBalancer));
		jsonRes.setList(loadBalancerService.getLBMachineMapList(loadBalancer));
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * 새로운 L/B를 생성한다.
	 * </pre>
	 * @param request
	 * @param jsonRes
	 * @param loadBalancer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/create")
	public @ResponseBody SimpleJsonResponse create(HttpServletRequest request, SimpleJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		Assert.notNull(loadBalancer.getMachineId(), "machineId can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				loadBalancer.setRegUserId(userDto.getUser_id());
				loadBalancer.setUpdUserId(userDto.getUser_id());
			}
			
			loadBalancerService.createLoadBalancer(loadBalancer);
			jsonRes.setMsg("Load Balancer가 생성되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Load Balancer 생성 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * L/B에 Instance를 추가한다.
	 * </pre>
	 * @param request
	 * @param jsonRes
	 * @param loadBalancer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addMachine")
	public @ResponseBody SimpleJsonResponse addMachine(HttpServletRequest request, SimpleJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		Assert.notNull(loadBalancer.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(loadBalancer.getMachineId(), "machineId can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				loadBalancer.setRegUserId(userDto.getUser_id());
				loadBalancer.setUpdUserId(userDto.getUser_id());
			}
			
			loadBalancerService.insertLBMachineMap(loadBalancer);
			jsonRes.setMsg("Instance가 추가되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Instance 추가 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	/**
	 * <pre>
	 * L/B에서 Instance를 제거한다.
	 * </pre>
	 * @param request
	 * @param jsonRes
	 * @param loadBalancer
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteMachine")
	public @ResponseBody SimpleJsonResponse deleteMachine(HttpServletRequest request, SimpleJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		Assert.notNull(loadBalancer.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(loadBalancer.getMachineId(), "machineId can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				loadBalancer.setRegUserId(userDto.getUser_id());
				loadBalancer.setUpdUserId(userDto.getUser_id());
			}
			
			loadBalancerService.deleteLBMachineMap(loadBalancer);
			jsonRes.setMsg("Instance가 삭제되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Instance 삭제 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/addListener")
	public @ResponseBody SimpleJsonResponse addListener(HttpServletRequest request, SimpleJsonResponse jsonRes, LBListenerDto lbListener) throws Exception {
		Assert.notNull(lbListener.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(lbListener.getListenPort(), "listenPort can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				lbListener.setRegUserId(userDto.getUser_id());
				lbListener.setUpdUserId(userDto.getUser_id());
			}
			
			lbListenerService.insertLBListener(lbListener);
			jsonRes.setMsg("Load Balancer Listener 설정이 추가되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Load Balancer Listener 설정 추가 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/updateListener")
	public @ResponseBody SimpleJsonResponse updateListener(HttpServletRequest request, SimpleJsonResponse jsonRes, LBListenerDto lbListener) throws Exception {
		Assert.notNull(lbListener.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(lbListener.getListenPort(), "listenPort can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				lbListener.setRegUserId(userDto.getUser_id());
				lbListener.setUpdUserId(userDto.getUser_id());
			}
			
			lbListenerService.updateLBListener(lbListener);
			jsonRes.setMsg("Load Balancer Listener 설정이 수정되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Load Balancer Listener 설정 수정 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/deleteListener")
	public @ResponseBody SimpleJsonResponse deleteListener(HttpServletRequest request, SimpleJsonResponse jsonRes, LBListenerDto lbListener) throws Exception {
		Assert.notNull(lbListener.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(lbListener.getListenPort(), "listenPort can not be null.");

		try {
			UserDto userDto = (UserDto)request.getSession().getAttribute(UserController.SESSION_USER_KEY);
			if (userDto != null) {
				lbListener.setRegUserId(userDto.getUser_id());
				lbListener.setUpdUserId(userDto.getUser_id());
			}
			
			lbListenerService.deleteLBListener(lbListener);
			jsonRes.setMsg("Load Balancer Listener 설정이 삭제되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Load Balancer Listener 설정 삭제 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/applyListener")
	public @ResponseBody SimpleJsonResponse applyListener(HttpServletRequest request, SimpleJsonResponse jsonRes, LoadBalancerDto loadBalancer) throws Exception {
		Assert.notNull(loadBalancer.getLoadBalancerId(), "loadBalancerId can not be null.");
		Assert.notNull(loadBalancer.getMachineId(), "machineId can not be null.");

		try {			
			String urlPrefix = "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() + "/repo";
			lbListenerService.applyListener(loadBalancer, urlPrefix);
			jsonRes.setMsg("Load Balancer Listener 설정이 적용되었습니다.");
		} catch (Exception e) {
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Load Balancer Listener 설정 적용 중 에러가 발생하였습니다.");
			
			logger.error("Unhandled Expeption has occurred. ", e);
		}
		
		return jsonRes;
	}

}
//end of LoadBalancerController.java