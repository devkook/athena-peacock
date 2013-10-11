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
 * Sang-cheon Park	2013. 10. 10.		First Draft.
 */
package com.athena.peacock.common.core.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class FileWriteAction extends Action {

	private static final Logger logger = LoggerFactory.getLogger(FileWriteAction.class);
	
	private static final long serialVersionUID = 1L;
	
	private String contents;
	private String fileName;
	
	public FileWriteAction(int sequence) {
		super(sequence);
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see com.athena.peacock.common.core.action.Action#perform()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String perform() {
		String result = "F";
		
		try {
			String separator = File.separator;
			
			fileName = fileName.replaceAll("\\\\", separator);
			
			String path = fileName.substring(0, fileName.lastIndexOf(separator));
			String name = fileName.substring(fileName.lastIndexOf(separator) + 1);
			
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			
			FileOutputStream fos = new FileOutputStream(new File(path, name));
			
			IOUtils.write(contents, fos, "UTF-8");
			IOUtils.closeQuietly(fos);
			result = "S";
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException has occurred. : ", e);
		} catch (IOException e) {
			logger.error("IOException has occurred. : ", e);
		}
		
		return result;
	}

}
//end of FileWriteAction.java