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

import org.springframework.stereotype.Repository;

import com.athena.peacock.controller.web.common.dao.AbstractBaseDao;
import com.athena.peacock.controller.web.common.model.ExtjsGridParam;
import com.athena.peacock.controller.web.user.UserDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
@Repository
public class UserGroupDao extends AbstractBaseDao {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public UserGroupDao() {
		// TODO Auto-generated constructor stub
	}
	
	public void insertUserGroup(UserGroupDto userGroup){
		sqlSession.insert("UserGroupMapper.insertUserGroup", userGroup);
	}
	
	public List<UserGroupDto> getUserGroupList(ExtjsGridParam gridParam){
		return sqlSession.selectList("UserGroupMapper.getUserGroupList", gridParam);
	}
	
	public int getUserGroupListTotalCount(ExtjsGridParam gridParam){
		
		return sqlSession.selectOne("UserGroupMapper.getUserGroupListTotalCount", gridParam);
	}
	
	public UserGroupDto getUserGroup(int groupId){
		return sqlSession.selectOne("UserGroupMapper.getUserGroup", groupId);
	}
	
	public void updateUserGroup(UserGroupDto userGroup){
		sqlSession.update("UserGroupMapper.updateUserGroup", userGroup);
	}
	
	public void deleteUserGroup(int groupId){
		sqlSession.delete("UserGroupMapper.deleteUserGroup", groupId);
	}

}
//end of UserGroupDao.java