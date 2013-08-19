package com.athena.peacock.controller.web.common.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * AbstractBaseDao - DAO 구현시 이 클래스를 상속받아서 구현한다.
 *
 * @author idkbj
 * @version
 * @see
 */
public abstract class AbstractBaseDao {

	/**
	 * MySQL Client (MyBatis)
	 */
	@Autowired
	protected SqlSession sqlSession;


	/**
	 * AbstractBaseDao
	 *
	 * @param
	 * @exception
	 */
	public AbstractBaseDao() {
	}

	

}
