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

import com.athena.peacock.controller.web.software.SoftwareRepoDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("softwareRepoService")
@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
public class SoftwareRepoService {
    
	@Inject
	@Named("softwareRepoDao")
	private SoftwareRepoDao softwareRepoDao;
	
	public void insertSoftwareRepo(SoftwareRepoDto softwareRepo) throws Exception {
		softwareRepoDao.insertSoftwareRepo(softwareRepo);
	}
	
	public void updateSoftwareRepo(SoftwareRepoDto softwareRepo) throws Exception {
		softwareRepoDao.updateSoftwareRepo(softwareRepo);
	}
	
	public void deleteSoftwareRepo(String softwareId) throws Exception {
		softwareRepoDao.deleteSoftwareRepo(softwareId);
	}
	
	public SoftwareRepoDto getSoftwareRepo(String softwareId) throws Exception {
		return softwareRepoDao.getSoftwareRepo(softwareId);
	}
	
	public List<SoftwareRepoDto> getSoftwareRepoList(SoftwareRepoDto softwareRepo) throws Exception {
		return softwareRepoDao.getSoftwareRepoList(softwareRepo);
	}
	
}
//end of SoftwareRepoService.java