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
@Repository("lbMachineMapDao")
public class LBMachineMapDao extends AbstractBaseDao {
	
	public void insertLBMachineMap(LoadBalancerDto loadBalancer) {
		sqlSession.insert("LBMachineMapMapper.insertLBMachineMap", loadBalancer);
	}
	
	public void deleteLBMachineMap(int loadBalancerId) {
		sqlSession.delete("LBMachineMapMapper.deleteLBMachineMap", loadBalancerId);
	}
	
	public LoadBalancerDto getLBMachineMap(int loadBalancerId) {
		return sqlSession.selectOne("LBMachineMapMapper.getLBMachineMap", loadBalancerId);
	}

	public int getLBMachineMapListCnt(LoadBalancerDto loadBalancer) {
		return sqlSession.selectOne("LBMachineMapMapper.getLBMachineMapListCnt", loadBalancer);
	}

	public List<LoadBalancerDto> getLBMachineMapList(LoadBalancerDto loadBalancer) {
		return sqlSession.selectList("LBMachineMapMapper.getLBMachineMapList", loadBalancer);
	}
}
//end of LBMachineMapDao.java