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

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.net.InetAddress;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.agent.util.MacAddressUtil;
import com.athena.peacock.agent.util.PropertyUtil;
import com.athena.peacock.agent.util.SigarUtil;
import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;
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
@Qualifier("peacockClientHandler")
@Sharable
public class PeacockClientHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger logger = LoggerFactory.getLogger(PeacockClientHandler.class);

    private boolean connected = false;
    private Channel channel;
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelActive() has invoked.");
		
    	connected = true;
    	channel = ctx.channel();
		ctx.writeAndFlush(getAgentInitialInfo());
    }

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.debug("channelRead0() has invoked.");

		logger.debug("[Client] Object => " + msg.getClass().getName());
		logger.debug("[Client] Contents => " + msg.toString());
		
		if(msg instanceof PeacockDatagram) {
			MessageType messageType = ((PeacockDatagram<?>)msg).getMessageType();
			
			if (messageType.equals(MessageType.COMMAND)) {
				ProvisioningResponseMessage response = new ProvisioningResponseMessage();
				response.setAgentId(((PeacockDatagram<ProvisioningCommandMessage>)msg).getMessage().getAgentId());
				response.setBlocking(((PeacockDatagram<ProvisioningCommandMessage>)msg).getMessage().isBlocking());
				
				((PeacockDatagram<ProvisioningCommandMessage>)msg).getMessage().executeCommands(response);
				
				ctx.writeAndFlush(new PeacockDatagram<ProvisioningResponseMessage>(response));
			}
		}
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Unexpected exception from downstream.", cause);
        ctx.close();
    }
    
    /* (non-Javadoc)
     * @see io.netty.channel.ChannelInboundHandlerAdapter#channelInactive(io.netty.channel.ChannelHandlerContext)
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		logger.debug("channelInactive() has invoked.");

		// Stop the agent daemon if the connection has lost.
		System.exit(-1);
		
		/*
    	connected = false;
    	channel = null;
    	*/
    }
    
    /**
	 * @return the connected
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * @return the channel
	 */
	private Channel getChannel() {
		return channel;
	}

	/**
	 * @return the channel
	 */
	public void close() {
		getChannel().close();
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 * @param datagram
	 */
	public void sendMessage(PeacockDatagram<?> datagram) {
		getChannel().writeAndFlush(datagram);
	}//end of sendMessage()

	/**
     * <pre>
     * Agent의 시스템 정보를 조회한다.
     * </pre>
     * @return
	 * @throws Exception 
     */
    private PeacockDatagram<AgentInitialInfoMessage> getAgentInitialInfo() throws Exception {
    	String agentId = IOUtils.toString(new File(PropertyUtil.getProperty(PeacockConstant.AGENT_ID_FILE_KEY)).toURI());
		
		AgentInitialInfoMessage message = new AgentInitialInfoMessage();
		message.setMacAddr(MacAddressUtil.getMacAddressList().get(0));
		message.setAgentId(agentId);
		message.setOsName(System.getProperty("os.name"));
		message.setOsArch(System.getProperty("os.arch"));
		message.setOsVersion(System.getProperty("os.version"));
		message.setCpuClock(SigarUtil.getCpuClock());
		message.setCpuNum(SigarUtil.getCpuNum());
		message.setCpuModel(SigarUtil.getCpuModel());
		message.setCpuVendor(SigarUtil.getCpuVendor());
		message.setMemSize(SigarUtil.getMemSize());
		message.setHostName(SigarUtil.getNetInfo().getHostName());
		//message.setHostName(InetAddress.getLocalHost().getHostName());
		
		try {
			// 
			message.setIpAddr(InetAddress.getLocalHost().getHostAddress());
		} catch (Exception e) {
			// ignore
			logger.info("[{}] has occurred but ignore this exception.", e.getMessage());
		}
		
		return new PeacockDatagram<AgentInitialInfoMessage>(message);
    }//end of getAgentInitialInfo()
}
//end of PeacockClientHandler.java