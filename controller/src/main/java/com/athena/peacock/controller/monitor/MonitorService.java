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
package com.athena.peacock.controller.monitor;

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
@Service("monitorService")
public class MonitorService {
    
	@Inject
	@Named("monDataDao")
	private MonDataDao monDataDao;

	@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
	public void insertMonData(MonDataDto monData) throws Exception {
		monDataDao.insertMonData(monData);
	}

	@Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED)
	public void insertMonDataList(List<MonDataDto> monDataList) throws Exception {
		for (MonDataDto monData : monDataList) {
			monDataDao.insertMonData(monData);
		}
	}
}
//end of MonitorService.java