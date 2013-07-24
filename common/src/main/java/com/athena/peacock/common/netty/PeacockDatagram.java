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
package com.athena.peacock.common.netty;

import java.io.Serializable;

import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.MessageType;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class PeacockDatagram<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private T message;
	
	public PeacockDatagram(T message) {
		if(message instanceof AbstractMessage) {
			this.message = message;
		} else {
			throw new IllegalArgumentException(message.getClass().getName() + " does not supported.");
		}
	}

	/**
	 * @return the messageType
	 * @throws Exception 
	 */
	public MessageType getMessageType() {
		return message != null ? ((AbstractMessage) message).getMessageType() : null;
	}

	/**
	 * @return the message
	 */
	public T getMessage() {
		return message;
	}

	/**
	 * @return the message
	 */
	public T getMessage(Class<T> clazz) {
		return clazz.cast(message);
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(T message) {
		this.message = message;
	}
	
}
//end of Message.java