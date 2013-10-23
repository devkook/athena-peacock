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

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.athena.peacock.controller.web.config.ConfigDao;
import com.athena.peacock.controller.web.config.ConfigDto;
import com.athena.peacock.controller.web.software.SoftwareDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("softwareService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class SoftwareService {
    
	@Inject
	@Named("softwareDao")
	private SoftwareDao softwareDao;
	
	@Inject
	@Named("configDao")
	private ConfigDao configDao;
	
	public void insertSoftware(SoftwareDto software) throws Exception {		
		if (softwareDao.getSoftware(software) != null) {
			softwareDao.updateSoftware(software);
		} else {
			softwareDao.insertSoftware(software);
		}
	}
	
	public void insertSoftware(SoftwareDto software, List<ConfigDto> configList) throws Exception {		
		if (softwareDao.getSoftware(software) != null) {
			softwareDao.updateSoftware(software);
		} else {
			softwareDao.insertSoftware(software);
		}
		
		// 기존에 존재하는 config 파일 정보를 삭제 처리한다.
		if (configList.size() > 0) {
			configDao.deleteConfig(configList.get(0));
		}
		
		for (ConfigDto config : configList) {
			configDao.insertConfig(config);
		}
	}
	
	public void updateSoftware(SoftwareDto software) throws Exception {
		softwareDao.updateSoftware(software);
	}
	
	public void deleteSoftware(SoftwareDto software) throws Exception {
		softwareDao.deleteSoftware(software);
	}
	
	public SoftwareDto getSoftware(SoftwareDto software) throws Exception {
		return softwareDao.getSoftware(software);
	}
	
	public List<SoftwareDto> getSoftwareList(SoftwareDto software) throws Exception {
		return softwareDao.getSoftwareList(software);
	}

}
//end of SoftwareService.java