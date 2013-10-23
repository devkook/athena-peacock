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
import com.athena.peacock.controller.web.machine.MachineDto;
import com.athena.peacock.controller.web.software.SoftwareDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Repository("softwareDao")
public class SoftwareDao extends AbstractBaseDao {
	
	public void insertSoftware(SoftwareDto software) {
		sqlSession.insert("SoftwareMapper.insertSoftware", software);
	}
	
	public void updateSoftware(SoftwareDto software) {
		sqlSession.update("SoftwareMapper.updateSoftware", software);
	}
	
	public void deleteSoftware(SoftwareDto software) {
		sqlSession.delete("SoftwareMapper.deleteSoftware", software);
	}
	
	public SoftwareDto getSoftware(SoftwareDto software) {
		return sqlSession.selectOne("SoftwareMapper.getSoftware", software);
	}

	public List<SoftwareDto> getSoftwareList(SoftwareDto software) {
		return sqlSession.selectList("SoftwareMapper.getSoftwareList", software);
	}

	public int getSoftwareInstallListCnt(MachineDto machine) {
		return sqlSession.selectOne("SoftwareMapper.getSoftwareInstallListCnt", machine);
	}

	public List<SoftwareDto> getSoftwareInstallList(MachineDto machine) {
		return sqlSession.selectList("SoftwareMapper.getSoftwareInstallList", machine);
	}

	public List<SoftwareDto> getSoftwareInstallListAll(String machineId) {
		return sqlSession.selectList("SoftwareMapper.getSoftwareInstallListAll", machineId);
	}
}
//end of SoftwareDao.java