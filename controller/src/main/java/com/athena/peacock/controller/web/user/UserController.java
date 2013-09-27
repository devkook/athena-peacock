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
 * Bong-Jin Kwon	2013. 9. 24.		First Draft.
 */
package com.athena.peacock.controller.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.web.common.model.DtoJsonResponse;
import com.athena.peacock.controller.web.common.model.ExtjsGridParam;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;

/**
 * <pre>
 * 
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, ExtjsGridParam gridParam){
		
		jsonRes.setTotal(service.getUserListTotalCount(gridParam));
		jsonRes.setList(service.getUserList(gridParam));
		
		return jsonRes;
	}
	
	@RequestMapping("/create")
	public @ResponseBody SimpleJsonResponse create(SimpleJsonResponse jsonRes, UserDto user){
		
		try{
			service.insertUser(user);
			jsonRes.setMsg("Create success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Create fail.");
			
			e.printStackTrace();
		}
		
		
		return jsonRes;
	}
	
	@RequestMapping("/update")
	public @ResponseBody SimpleJsonResponse update(SimpleJsonResponse jsonRes, UserDto user){
		
		try{
			service.updateUser(user);
			jsonRes.setMsg("Update success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Update fail.");
			
			e.printStackTrace();
		}
		
		
		return jsonRes;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody SimpleJsonResponse delete(SimpleJsonResponse jsonRes, @RequestParam("user_id") int user_id){
		
		try{
			service.deleteUser(user_id);
			jsonRes.setMsg("Delete success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Delete fail.");
			
			e.printStackTrace();
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/getUser")
	public @ResponseBody DtoJsonResponse getUser(DtoJsonResponse jsonRes, @RequestParam("user_id") int user_id){
		
		jsonRes.setData(service.getUser(user_id));
		
		return jsonRes;
	}

}
//end of UserController.java