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
 * Sang-cheon Park	2013. 8. 25.		First Draft.
 */
package com.athena.peacock.controller.web.machine;

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
@Repository("machineDao")
public class MachineDao extends AbstractBaseDao {
	
	public MachineDto getMachine(String machineId) {
		return sqlSession.selectOne("MachineMapper.getMachine", machineId);
	}
	
	public void insertMachine(MachineDto machine) {
		sqlSession.insert("MachineMapper.insertMachine", machine);
	}
	
	public void updateMachine(MachineDto machine) {
		sqlSession.update("MachineMapper.updateMachine", machine);
	}
	
	public void deleteMachine(String machineId) {
		sqlSession.delete("MachineMapper.deleteMachine", machineId);
	}
	
	public int getMachineListCnt(MachineDto machine) {
		return sqlSession.selectOne("MachineMapper.getMachineListCnt", machine);
	}

	public List<MachineDto> getMachineList(MachineDto machine) {
		return sqlSession.selectList("MachineMapper.getMachineList", machine);
	}
}
//end of MachineDao.java