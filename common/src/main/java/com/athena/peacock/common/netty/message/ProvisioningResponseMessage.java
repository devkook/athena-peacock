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
 * Sang-cheon Park	2013. 7. 18.		First Draft.
 */
package com.athena.peacock.common.netty.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class ProvisioningResponseMessage extends AbstractMessage {

	private static final long serialVersionUID = 1L;
	
	Map<String, List<Object>> results;
	
	public ProvisioningResponseMessage() {
		super(MessageType.RESPONSE);
	}

	/**
	 * @return the results
	 */
	public Map<String, List<Object>> getResults() {
		if (results == null) {
			results = new HashMap<String, List<Object>>();
		}
		
		return results;
	}
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param name Command Name
	 * @return
	 */
	public List<Object> getResult(String name) {
		Map<String, List<Object>> _results = getResults();
		List<Object> _list = _results.get(name);
		
		if (_list == null) {
			_list = new ArrayList<Object>();
			_results.put(name, _list);
		}
		
		return _list;
	}
	
	public void addResult(String name, Object result) {
		getResult(name).add(result);
	}

}
//end of ProvisioningResponse.java