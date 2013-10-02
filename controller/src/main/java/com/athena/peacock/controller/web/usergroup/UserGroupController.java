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
 * Bong-Jin Kwon	2013. 10. 2.		First Draft.
 */
package com.athena.peacock.controller.web.usergroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.web.common.model.DtoJsonResponse;
import com.athena.peacock.controller.web.common.model.ExtjsGridParam;
import com.athena.peacock.controller.web.common.model.GridJsonResponse;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;
import com.athena.peacock.controller.web.user.UserDto;

/**
 * <pre>
 * 사용자 그룹 관리 컨트롤러.
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
@Controller
@RequestMapping("/usergroup")
public class UserGroupController {
	
	@Autowired
	private UserGroupService service;

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public UserGroupController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/list")
	public @ResponseBody GridJsonResponse list(GridJsonResponse jsonRes, ExtjsGridParam gridParam){
		
		jsonRes.setTotal(service.getUserGroupListTotalCount(gridParam));
		jsonRes.setList(service.getUserGroupList(gridParam));
		
		return jsonRes;
	}
	
	@RequestMapping("/create")
	public @ResponseBody SimpleJsonResponse create(SimpleJsonResponse jsonRes, UserGroupDto userGroup){
		
		try{
			service.insertUserGroup(userGroup);
			jsonRes.setMsg("Create success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Create fail.");
			
			e.printStackTrace();
		}
		
		
		return jsonRes;
	}
	
	@RequestMapping("/update")
	public @ResponseBody SimpleJsonResponse update(SimpleJsonResponse jsonRes, UserGroupDto userGroup){
		
		try{
			service.updateUserGroup(userGroup);
			jsonRes.setMsg("Update success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Update fail.");
			
			e.printStackTrace();
		}
		
		
		return jsonRes;
	}
	
	@RequestMapping("/delete")
	public @ResponseBody SimpleJsonResponse delete(SimpleJsonResponse jsonRes, @RequestParam("group_id") int group_id){
		
		try{
			service.deleteUserGroup(group_id);
			jsonRes.setMsg("Delete success.");
			
		}catch(Exception e){
			
			jsonRes.setSuccess(false);
			jsonRes.setMsg("Delete fail.");
			
			e.printStackTrace();
		}
		
		return jsonRes;
	}
	
	@RequestMapping("/getUserGroup")
	public @ResponseBody DtoJsonResponse getUserGroup(DtoJsonResponse jsonRes, @RequestParam("group_id") int group_id){
		
		jsonRes.setData(service.getUserGroup(group_id));
		
		return jsonRes;
	}

}
//end of UserGroupController.java