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
 * Sang-cheon Park	2013. 10. 16.		First Draft.
 */
package com.athena.peacock.controller.web.config;

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
@Repository("configRepoDao")
public class ConfigRepoDao extends AbstractBaseDao {
	
	public void insertConfigRepo(ConfigRepoDto configRepo) {
		sqlSession.insert("ConfigRepoMapper.insertConfigRepo", configRepo);
	}
	
	public void updateConfigRepo(ConfigRepoDto configRepo) {
		sqlSession.update("ConfigRepoMapper.updateConfigRepo", configRepo);
	}
	
	public void deleteConfigRepo(ConfigRepoDto configRepo) {
		sqlSession.delete("ConfigRepoMapper.deleteConfigRepo", configRepo);
	}
	
	public ConfigRepoDto getConfigRepo(ConfigRepoDto configRepo) {
		return sqlSession.selectOne("ConfigRepoMapper.getConfigRepo", configRepo);
	}
	
	public int getConfigRepoListCnt(ConfigRepoDto configRepo) {
		return sqlSession.selectOne("ConfigRepoMapper.getConfigRepoListCnt", configRepo);
	}

	public List<ConfigRepoDto> getConfigRepoList(ConfigRepoDto configRepo) {
		return sqlSession.selectList("ConfigRepoMapper.getConfigRepoList", configRepo);
	}
}
//end of ConfigRepoDao.java