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
package com.athena.peacock.controller.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("peacockServer")
public class PeacockServer {
	
    @Value("#{contextProperties['listen.port']}")
    private int port;

    @Inject
    @Named("bossGroup")
    private EventLoopGroup bossGroup;

    @Inject
    @Named("workerGroup")
    private EventLoopGroup workerGroup;

    @Inject
    @Named("peacockServerInitializer")
    private PeacockServerInitializer initializer;

    @Inject
    @Named("peacockServerHandler")
    private PeacockServerHandler handler;
    
    private Channel channel;
	
	@PostConstruct
	public void start() throws Exception {
		
		new Thread() {
			@Override
			public void run() {
		        try {
					ServerBootstrap b = new ServerBootstrap();
			        b.group(bossGroup, workerGroup)
			         .channel(NioServerSocketChannel.class)
			         .handler(new LoggingHandler(LogLevel.WARN))
			         .childHandler(initializer);

			        // Bind and start to accept incoming connections.
					channel = b.bind(port).sync().channel().closeFuture().sync().channel();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	@PreDestroy
	public void stop() {
		if (channel != null) {
			channel.close();
		}
	}
}
//end of PeacockServer.java