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
 * Sang-cheon Park	2013. 10. 24.		First Draft.
 */
package com.athena.peacock.controller.web.ospackage;

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
@Repository("packageDao")
public class PackageDao extends AbstractBaseDao {

	public void insertPackage(PackageDto ospackage) {
		sqlSession.selectOne("PackageMapper.insertPackage", ospackage);
	}
	
	public int getPackageListCnt(PackageDto ospackage) {
		return sqlSession.selectOne("PackageMapper.getPackageListCnt", ospackage);
	}
	
	public List<PackageDto> getPackageList(PackageDto ospackage) {
		return sqlSession.selectList("PackageMapper.getPackageList", ospackage);
	}

	public void deletePackage(String machineId) {
		sqlSession.delete("PackageMapper.deletePackage", machineId);
	}
	
}
//end of PackageDao.java