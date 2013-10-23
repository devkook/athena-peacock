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
 * Bong-Jin Kwon	2013. 9. 25.		First Draft.
 */
package com.athena.peacock.controller.web.user;

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
public class UserService {

	@Autowired
	private UserDao dao;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertUser(UserDto user){
		dao.insertUser(user);
	}
	
	public List<UserDto> getUserList(ExtjsGridParam gridParam){
		return dao.getUserList(gridParam);
	}
	
	public int getUserListTotalCount(ExtjsGridParam gridParam){
		
		return dao.getUserListTotalCount(gridParam);
	}
	
	public UserDto getUser(int userId){
		return dao.getUser(userId);
	}
	
	public void updateUser(UserDto user){
		dao.updateUser(user);
	}
	
	public void deleteUser(int userId){
		dao.deleteUser(userId);
	}
	
	public UserDto getLoginUser(String loginId, String passwd){
		UserDto user = new UserDto();
		user.setLogin_id(loginId);
		user.setPasswd(passwd);
		
		return dao.getLoginUser(user);
	}

}
//end of UserService.java