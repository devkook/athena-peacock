package com.athena.peacock.controller.web.user;

import java.util.List;

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

	public void insertUser(UserDto user){
		sqlSession.insert("UserMapper.insertUser", user);
	}
	
	public List<UserDto> getUserList(){
		return sqlSession.selectList("UserMapper.getUserList");
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