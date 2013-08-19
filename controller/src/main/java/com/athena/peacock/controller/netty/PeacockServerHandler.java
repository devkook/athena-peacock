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

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead0() has invoked.");
		logger.info("[Server] IP Address => " + ctx.channel().remoteAddress().toString());
		logger.info("[Server] Object => " + msg.getClass().getName());
		logger.info("[Server] Contents => " + msg.toString());
		
		if("bye".equals(msg.toString())) {
			// Response and exit.
			ChannelFuture future = ctx.write("This channel will be closed.");
			future.addListener(ChannelFutureListener.CLOSE);
		} else {
			if(msg instanceof PeacockDatagram) {
				MessageType messageType = ((PeacockDatagram<?>)msg).getMessageType();

				switch (messageType) {
					case COMMAND : 
						ProvisioningCommandMessage commandMsg = ((PeacockDatagram<ProvisioningCommandMessage>)msg).getMessage();
						System.out.println("Message => " + commandMsg);
						break;
					case RESPONSE : 
						ProvisioningResponseMessage responseMsg = ((PeacockDatagram<ProvisioningResponseMessage>)msg).getMessage();
						System.out.println("Message => " + responseMsg);
						break;
					case SYSTEM_STATUS : 
						AgentSystemStatusMessage statusMsg = ((PeacockDatagram<AgentSystemStatusMessage>)msg).getMessage();
						System.out.println("Message => " + statusMsg);
						break;
					case INITIAL_INFO : 
						AgentInitialInfoMessage infoMsg = ((PeacockDatagram<AgentInitialInfoMessage>)msg).getMessage();
						System.out.println("Message => " + infoMsg);
						
						// register a new channel
						ChannelManagement.registerChannel(infoMsg.getAgentId(), ctx.channel());
						break;
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
		logger.info("channelActive() has invoked.");
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelInactive() has invoked.");

		// deregister a closed channel
		ChannelManagement.deregisterChannel(ctx.channel());
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
    
    public void sendMessage(PeacockDatagram<ProvisioningCommandMessage> datagram) {
    	Channel channel = ChannelManagement.getChannel(datagram.getMessage().getAgentId());
    	
    	// TODO callback
    	channel.write(datagram);
    }
    
    /**
     * <pre>
     * Channel 관리를 위한 클래스
     * </pre>
     * @author Sang-cheon Park
     * @version 1.0
     */
    private static class ChannelManagement {
    	
        private static Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();
        
        /**
         * <pre>
         * 신규 채널을 등록한다.
         * </pre>
         * @param agentId
         * @param channel
         */
        public static void registerChannel(String agentId, Channel channel) {
        	logger.debug("agentId({}) will be added to channelMap.", agentId);
        	channelMap.put(agentId, channel);
        }//end of registerChannel()
        
        /**
         * <pre>
         * agentId에 해당하는 채널을 map에서 제거한다.
         * </pre>
         * @param agentId
         */
        public static void deregisterChannel(String agentId) {
        	logger.debug("agentId({}) will be removed from channelMap.", agentId);
        	channelMap.remove(agentId);
        }//end of deregisterChannel()
        
        /**
         * <pre>
         * 연결 종료된 채널을 map에서 제거한다.
         * </pre>
         * @param channel
         */
        public static void deregisterChannel(Channel channel) {
        	Iterator<Entry<String, Channel>> iter = channelMap.entrySet().iterator();
        	
        	Entry<String, Channel> entry = null;
        	while (iter.hasNext()) {
        		entry = iter.next();
        		
        		if (entry.getValue() != null && entry.getValue() == channel) {
        			deregisterChannel(entry.getKey());
        			break;
        		}
        	}
        }//end of deregisterChannel()
        
        /**
         * <pre>
         * agentId에 해당하는 채널 정보를 가져온다.
         * </pre>
         * @param agentId
         * @return
         */
        public static Channel getChannel(String agentId) {
        	return channelMap.get(agentId);
        }//end of getChannel()
    }
    //end of ChannelManagement.java

}
//end of PeacockServerHandler.java