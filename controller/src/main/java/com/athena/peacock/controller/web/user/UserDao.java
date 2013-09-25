package com.athena.peacock.controller.web.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.athena.peacock.controller.web.common.dao.AbstractBaseDao;
import com.athena.peacock.controller.web.common.model.ExtjsGridParam;

/**
 * UserDao
 *
 * @author Bong-Jin Kwon
 * @version 1.0
 */
@Repository
public class UserDao extends AbstractBaseDao {

	/**
	 * UserDao
	 *
	 * @param
	 * @exception
	 */
	public UserDao() {
	}

	public void insertUser(UserDto user){
		sqlSession.insert("UserMapper.insertUser", user);
	}
	
	public List<UserDto> getUserList(ExtjsGridParam gridParam){
		return sqlSession.selectList("UserMapper.getUserList", gridParam);
	}
	
	public int getUserListTotalCount(ExtjsGridParam gridParam){
		
		return sqlSession.selectOne("UserMapper.getUserListTotalCount", gridParam);
	}
	
	public UserDto getUser(int userId){
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public void updateUser(UserDto user){
		sqlSession.update("UserMapper.updateUser", user);
	}
	
	public void deleteUser(int userId){
		sqlSession.delete("UserMapper.deleteUser", userId);
	}


}