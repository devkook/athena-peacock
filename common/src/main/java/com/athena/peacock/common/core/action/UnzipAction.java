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
 * Sang-cheon Park	2013. 10. 11.		First Draft.
 */
package com.athena.peacock.common.core.action;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.athena.peacock.common.core.util.ZipUtil;

/**
 * <pre>
 * 지정된 파일에 대한 압축해제를 위한 Action 클래스
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
public class UnzipAction extends Action {

	private static final Logger logger = LoggerFactory.getLogger(UnzipAction.class);
	
	private static final long serialVersionUID = 1L;

	// 압축해제 대상 파일
	private String sourceFile;
	// 압축해제 결과 디렉토리
	private String destDir;

	public UnzipAction(int sequence) {
		super(sequence);
	}

	/**
	 * @return the sourceFile
	 */
	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * @param sourceFile the sourceFile to set
	 */
	public void setSourceFile(String sourceFile) {
		this.sourceFile = sourceFile;
	}

	/**
	 * @return the destDir
	 */
	public String getDestDir() {
		return destDir;
	}

	/**
	 * @param destDir the destDir to set
	 */
	public void setDestDir(String destDir) {
		this.destDir = destDir;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.common.core.action.Action#perform()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String perform() {
    	logger.debug("Before decompress [{}] file to [{}]", sourceFile, destDir);
    	
		String result = "F";
    	
    	try {
    		destDir = ZipUtil.decompress(sourceFile, destDir);
			result = "S";
			
			logger.debug("[{}] Decompress has done successfully.", destDir);
		} catch (IOException e) {
			logger.error("IOException has occurred.", e);
		}
    	
    	return result;
    }//end of perform()

}
//end of UnzipAction.java