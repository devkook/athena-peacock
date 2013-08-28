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
 * Sang-cheon Park	2013. 8. 27.		First Draft.
 */
package com.athena.peacock.controller.common.core.handler;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.controller.common.core.InitializingTask;
import com.athena.peacock.controller.common.util.ThreadLocalUtil;
import com.athena.peacock.controller.monitor.MonFactorDao;
import com.athena.peacock.controller.monitor.MonFactorDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("monFactorHandler")
public class MonFactorHandler implements InitializingTask {

    protected final Logger logger = LoggerFactory.getLogger(MonFactorHandler.class);
    
    private List<MonFactorDto> monFactorList;
    
    @Inject
    @Named("monFactorDao")
    private MonFactorDao monFactorDao;

	/* (non-Javadoc)
	 * @see com.athena.peacock.controller.common.core.InitializingTask#init()
	 */
	@Override
	public void init() {
		try {
			monFactorList = monFactorDao.getMonFactorList();
			ThreadLocalUtil.add(PeacockConstant.MON_FACTOR_LIST, monFactorList);
			
			logger.debug("mon_factor_tbl fetch result : [{}]", monFactorList);
		} catch (Exception e) {
			logger.error("can not initiate mon_factor_tbl info : ", e);
		}
	}

	/**
	 * @return the monFactorList
	 */
	public List<MonFactorDto> getMonFactorList() {
		return monFactorList;
	}

}
//end of MonFactorHandler.java