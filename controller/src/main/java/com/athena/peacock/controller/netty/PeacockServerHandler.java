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

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;
import com.athena.peacock.common.netty.message.AgentSystemStatusMessage;
import com.athena.peacock.common.netty.message.MessageType;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;

/**
 * <pre>
 *
 * </pre>
 * 
 * @author Sang-cheon Park
 * @version 1.0
 */
@Component
@Qualifier("peacockServerHandler")
@Sharable
public class PeacockServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger(PeacockServerHandler.class);
    public static Map<SocketAddress, Channel> CHANNEL_MAP = new ConcurrentHashMap<SocketAddress, Channel>();

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead0() has invoked.");
		logger.info("[Server] Object => " + msg.getClass().getName());
		logger.info("[Server] Contents => " + msg.toString());
		
		if("bye".equals(msg.toString())) {
			// Response and exit.
			ChannelFuture future = ctx.write("This channel will be closed.");
			future.addListener(ChannelFutureListener.CLOSE);
		} else {
			if(msg instanceof PeacockDatagram) {
				MessageType messageType = ((PeacockDatagram<?>)msg).getMessageType();

				System.out.println("Message Type => " + messageType.value());
				
				if(messageType.equals(MessageType.COMMAND)) {
					ProvisioningCommandMessage message = ((PeacockDatagram<ProvisioningCommandMessage>)msg).getMessage();
					System.out.println("Message => " + message);
				} else if(messageType.equals(MessageType.RESPONSE)) {
					ProvisioningResponseMessage message = ((PeacockDatagram<ProvisioningResponseMessage>)msg).getMessage();
					System.out.println("Message => " + message);
				} else if(messageType.equals(MessageType.SYSTEM_STATUS)) {
					AgentSystemStatusMessage message = ((PeacockDatagram<AgentSystemStatusMessage>)msg).getMessage();
					System.out.println("Message => " + message);
				} else if(messageType.equals(MessageType.INITIAL_INFO)) {
					AgentInitialInfoMessage message = ((PeacockDatagram<AgentInitialInfoMessage>)msg).getMessage();
					System.out.println("Message => " + message);
				} 
				
				ctx.write(msg);
			}
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelReadComplete() has invoked.");
		ctx.flush();
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {		
		
		SocketAddress addr = ctx.channel().remoteAddress();
		System.out.println(addr);
		
		CHANNEL_MAP.put(ctx.channel().remoteAddress(), ctx.channel());
		logger.info("channelActive() has invoked. channel_size => " + CHANNEL_MAP.size());
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		CHANNEL_MAP.remove(ctx.channel().remoteAddress());
		logger.info("channelInactive() has invoked. channel_size => " + CHANNEL_MAP.size());
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.info("handlerAdded() has invoked.");
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Unexpected exception from downstream.", cause);
        ctx.close();
    }

}
//end of PeacockServerHandler.java