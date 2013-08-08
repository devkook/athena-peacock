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

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.constant.PeacockConstant;
import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;

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
    
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(getAgentInitialInfo());
    }

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead0() has invoked.");

		logger.info("[Client] Object => " + msg.getClass().getName());
		logger.info("[Client] Contents => " + msg.toString());
	}

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Unexpected exception from downstream.", cause);
        ctx.close();
    }
    
    /**
     * <pre>
     * Agent의 시스템 정보를 조회한다.
     * </pre>
     * @return
     * @throws IOException
     */
    private PeacockDatagram<AgentInitialInfoMessage> getAgentInitialInfo() throws IOException {
    	String agentId = IOUtils.toString(new File(PeacockConstant.AGENT_ID_FILE).toURI());
		
		AgentInitialInfoMessage message = new AgentInitialInfoMessage();
		message.setAgentId(agentId);
		message.setOsName(System.getProperty("os.name"));
		message.setOsArch(System.getProperty("os.arch"));
		message.setOsVersion(System.getProperty("os.version"));
		message.setJavaVersion(System.getProperty("java.version"));
		message.setUserName(System.getProperty("user.name"));
		
		return new PeacockDatagram<AgentInitialInfoMessage>(message);
    }//end of getAgentInitialInfo()
}
//end of PeacockClientHandler.java