package com.athena.peacock.controller.web.user;

import org.springframework.stereotype.Repository;

import com.athena.peacock.controller.web.common.dao.AbstractBaseDao;

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

	public void insertUser(UserDTO user){
		sqlSession.insert("UserMapper.insertUser", user);
	}
	
	public UserDTO getUser(int userId){
		return sqlSession.selectOne("UserMapper.getUser", userId);
	}
	
	public void updateUser(UserDTO user){
		sqlSession.update("UserMapper.updateUser", user);
	}
	
	public void deleteUser(int userId){
		sqlSession.delete("UserMapper.deleteUser", userId);
	}


}