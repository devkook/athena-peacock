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

import java.util.List;

import org.springframework.stereotype.Repository;

import com.athena.peacock.controller.web.common.dao.AbstractBaseDao;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Repository("lbListenerDao")
public class LBListenerDao extends AbstractBaseDao {
	
	public void insertLBListener(LBListenerDto lbListener) {
		sqlSession.insert("LBListenerMapper.insertLBListener", lbListener);
	}
	
	public void updateLBListener(LBListenerDto lbListener) {
		sqlSession.update("LBListenerMapper.updateLBListener", lbListener);
	}
	
	public void deleteLBListener(LBListenerDto lbListener) {
		sqlSession.delete("LBListenerMapper.deleteLBListener", lbListener);
	}
	
	public LBListenerDto getLBListener(LBListenerDto lbListener) {
		return sqlSession.selectOne("LBListenerMapper.getLBListener", lbListener);
	}
	
	public int getLBListenerListCnt(LBListenerDto lbListener) {
		return sqlSession.selectOne("LBListenerMapper.getLBListenerListCnt", lbListener);
	}

	public List<LBListenerDto> getLBListenerList(LBListenerDto lbListener) {
		return sqlSession.selectList("LBListenerMapper.getLBListenerList", lbListener);
	}
}
//end of LBListenerDao.java