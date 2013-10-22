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

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("configRepoService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class ConfigRepoService {
    
	@Inject
	@Named("configRepoDao")
	private ConfigRepoDao configRepoDao;
	
	public void insertConfigRepo(ConfigRepoDto configRepo) throws Exception {
		configRepoDao.insertConfigRepo(configRepo);
	}
	
	public void updateConfigRepo(ConfigRepoDto configRepo) throws Exception {
		configRepoDao.updateConfigRepo(configRepo);
	}
	
	public void deleteConfigRepo(ConfigRepoDto configRepo) throws Exception {
		configRepoDao.deleteConfigRepo(configRepo);
	}
	
	public ConfigRepoDto getConfigRepo(ConfigRepoDto configRepo) throws Exception {
		return configRepoDao.getConfigRepo(configRepo);
	}

	public int getConfigRepoListCnt(ConfigRepoDto configRepo) {
		return configRepoDao.getConfigRepoListCnt(configRepo);
	}
	
	public List<ConfigRepoDto> getConfigRepoList(ConfigRepoDto configRepo) throws Exception {
		return configRepoDao.getConfigRepoList(configRepo);
	}

}
//end of ConfigRepoService.java