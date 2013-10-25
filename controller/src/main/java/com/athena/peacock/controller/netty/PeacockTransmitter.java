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
 * Sang-cheon Park	2013. 8. 20.		First Draft.
 */
package com.athena.peacock.controller.netty;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;

/**
 * <pre>
 * Peacock Server와 Peacock Agents 사이의 Provisioning 관련 제어 메시지를 송신하기 위한 클래스
 * PeacockServerHandler를 직접 호출하지 않기 위해 구현
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("peacockTransmitter")
public class PeacockTransmitter {

    @Inject
    @Named("peacockServerHandler")
    private PeacockServerHandler handler;
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param datagram
	 * @throws Exception 
	 */
    public ProvisioningResponseMessage sendMessage(PeacockDatagram<AbstractMessage> datagram) throws Exception {
		return handler.sendMessage(datagram);
	}//end of sendMessage()
}
//end of PeacockTransmitter.java