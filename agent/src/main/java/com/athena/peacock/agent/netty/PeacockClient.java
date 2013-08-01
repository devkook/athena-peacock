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
 * Sang-cheon Park	2013. 7. 17.		First Draft.
 */
package com.athena.peacock.agent.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.agent.util.AgentConfigUtil;
import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.common.netty.PeacockDatagram;

/**
 * <pre>
 * Netty Client
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("peacockClient")
public class PeacockClient {

    private final String host = AgentConfigUtil.getConfig(PeacockConstant.SERVER_IP);
    private final int port = Integer.parseInt(AgentConfigUtil.getConfig(PeacockConstant.SERVER_PORT));

    @Inject
    @Named("eventLoopGroup")
    private EventLoopGroup group;

    @Inject
    @Named("peacockClientInitializer")
    private PeacockClientInitializer initializer;
    
    private Channel channel;
    
    /**
     * <pre>
     * Bean 생성 시 수행되는 메소드로 Server와의 연결을 수립한다.
     * </pre>
     * @throws Exception
     */
    @PostConstruct
	public void start() throws Exception {
    	Bootstrap b = new Bootstrap();
        b.group(group)
         .channel(NioSocketChannel.class)
         .handler(new LoggingHandler(LogLevel.WARN))
         .handler(initializer);
        
        // Start the connection attempt.
        channel = b.connect(host, port).sync().channel();
	}//end of start()

	/**
	 * <pre>
	 * Bean 소멸 시 수행되는 메소드로 Server와의 연결을 종료한다.
	 * </pre>
	 */
	@PreDestroy
	public void stop() {
		channel.close();
	}//end of stop()
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param datagram
	 */
	public void sendMessage(PeacockDatagram<?> datagram) {
		channel.writeAndFlush(datagram);
	}//end of send()
	
}
//end of PeacockClient.java