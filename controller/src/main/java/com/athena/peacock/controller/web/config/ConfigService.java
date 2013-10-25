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
@Service("configService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class ConfigService {
    
	@Inject
	@Named("configDao")
	private ConfigDao configDao;
	
	public void insertConfigList(List<ConfigDto> configList) throws Exception {
		for (ConfigDto config : configList) {
			configDao.insertConfig(config);
		}
	}
	
	public void insertConfig(ConfigDto config) throws Exception {
		configDao.insertConfig(config);
	}
	
	public void updateConfig(ConfigDto config) throws Exception {
		
		// Agent의 물리 파일 변경 (FileWriteAction 이용)
		
		configDao.deleteConfig(config);
		configDao.insertConfig(config);
	}
	
	public void deleteConfig(ConfigDto config) throws Exception {
		configDao.deleteConfig(config);
	}
	
	public ConfigDto getConfig(ConfigDto config) throws Exception {
		return configDao.getConfig(config);
	}

	public int getConfigListCnt(ConfigDto config) throws Exception {
		return configDao.getConfigListCnt(config);
	}
	
	public List<ConfigDto> getConfigList(ConfigDto config) throws Exception {
		return configDao.getConfigList(config);
	}

}
//end of ConfigService.java