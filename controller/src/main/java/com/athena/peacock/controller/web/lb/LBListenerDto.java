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
 * Sang-cheon Park	2013. 10. 29.		First Draft.
 */
package com.athena.peacock.controller.web.lb;

import com.athena.peacock.controller.web.common.dto.BaseDto;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class LBListenerDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	
	private Integer loadBalancerId;
	private Integer listenerId;
	private String protocol;
	private Integer listenPort;
	private String stickinessYn;
	private Integer backendPort;

	/**
	 * @return the loadBalancerId
	 */
	public Integer getLoadBalancerId() {
		return loadBalancerId;
	}

	/**
	 * @param loadBalancerId the loadBalancerId to set
	 */
	public void setLoadBalancerId(Integer loadBalancerId) {
		this.loadBalancerId = loadBalancerId;
	}

	/**
	 * @return the listenerId
	 */
	public Integer getListenerId() {
		return listenerId;
	}

	/**
	 * @param listenerId the listenerId to set
	 */
	public void setListenerId(Integer listenerId) {
		this.listenerId = listenerId;
	}

	/**
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * @param protocol the protocol to set
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * @return the listenPort
	 */
	public Integer getListenPort() {
		return listenPort;
	}

	/**
	 * @param listenPort the listenPort to set
	 */
	public void setListenPort(Integer listenPort) {
		this.listenPort = listenPort;
	}

	/**
	 * @return the stickinessYn
	 */
	public String getStickinessYn() {
		return stickinessYn;
	}

	/**
	 * @param stickinessYn the stickinessYn to set
	 */
	public void setStickinessYn(String stickinessYn) {
		this.stickinessYn = stickinessYn;
	}

	/**
	 * @return the backendPort
	 */
	public Integer getBackendPort() {
		return backendPort;
	}

	/**
	 * @param backendPort the backendPort to set
	 */
	public void setBackendPort(Integer backendPort) {
		this.backendPort = backendPort;
	}
}
//end of LBListenerDto.java