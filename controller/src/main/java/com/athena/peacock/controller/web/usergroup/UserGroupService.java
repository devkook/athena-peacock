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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.athena.peacock.controller.web.common.model.ExtjsGridParam;

/**
 * <pre>
 * 
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
@Service
public class UserGroupService {
	
	@Autowired
	private UserGroupDao dao;

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public UserGroupService() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertUserGroup(UserGroupDto userGroup){
		dao.insertUserGroup(userGroup);
	}
	
	public List<UserGroupDto> getUserGroupList(ExtjsGridParam gridParam){
		return dao.getUserGroupList(gridParam);
	}
	
	public int getUserGroupListTotalCount(ExtjsGridParam gridParam){
		
		return dao.getUserGroupListTotalCount(gridParam);
	}
	
	public UserGroupDto getUserGroup(int groupId){
		return dao.getUserGroup(groupId);
	}
	
	public void updateUserGroup(UserGroupDto userGroup){
		dao.updateUserGroup(userGroup);
	}
	
	public void deleteUserGroup(int groupId){
		dao.deleteUserGroup(groupId);
	}

}
//end of UserGroupService.java