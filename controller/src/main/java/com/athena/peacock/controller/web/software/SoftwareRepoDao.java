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
package com.athena.peacock.controller.web.software;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.athena.peacock.controller.web.common.dao.AbstractBaseDao;
import com.athena.peacock.controller.web.software.SoftwareRepoDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Repository("softwareRepoDao")
public class SoftwareRepoDao extends AbstractBaseDao {
	
	public void insertSoftwareRepo(SoftwareRepoDto softwareRepo) {
		sqlSession.insert("SoftwareRepoMapper.insertSoftwareRepo", softwareRepo);
	}
	
	public void updateSoftwareRepo(SoftwareRepoDto softwareRepo) {
		sqlSession.update("SoftwareRepoMapper.updateSoftwareRepo", softwareRepo);
	}
	
	public void deleteSoftwareRepo(String softwareId) {
		sqlSession.delete("SoftwareRepoMapper.deleteSoftwareRepo", softwareId);
	}
	
	public SoftwareRepoDto getSoftwareRepo(String softwareId) {
		return sqlSession.selectOne("SoftwareRepoMapper.getSoftwareRepo", softwareId);
	}

	public List<SoftwareRepoDto> getSoftwareRepoList(SoftwareRepoDto softwareRepo) {
		return sqlSession.selectList("SoftwareRepoMapper.getSoftwareRepoList", softwareRepo);
	}
}
//end of SoftwareRepoDao.java