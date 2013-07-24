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
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;
import com.athena.peacock.common.netty.message.AgentSystemStatusMessage;
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
public class PeacockClient {

    private final String host;
    private final int port;

    public PeacockClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .handler(new LoggingHandler(LogLevel.WARN))
             .handler(new PeacockClientInitializer());

            // Start the connection attempt.
            Channel ch = b.connect(host, port).sync().channel();

            // Read commands from the stdin.
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            
            while (true) {
            	System.out.println("command, response, system_status, initial_info, bye are available");
            	System.out.print("=> ");
            	
                String line = in.readLine();
                
                if("command".equals(line)) {
                	ProvisioningCommandMessage message = new ProvisioningCommandMessage();
                	PeacockDatagram<ProvisioningCommandMessage> datagram = new PeacockDatagram<ProvisioningCommandMessage>(message);
                	
                	ch.writeAndFlush(datagram);
                } else if("response".equals(line)) {
                	ProvisioningResponseMessage message = new ProvisioningResponseMessage();
                	PeacockDatagram<ProvisioningResponseMessage> datagram = new PeacockDatagram<ProvisioningResponseMessage>(message);
                	
                	ch.writeAndFlush(datagram);
                } else if("system_status".equals(line)) {
                	AgentSystemStatusMessage message = new AgentSystemStatusMessage();
                	PeacockDatagram<AgentSystemStatusMessage> datagram = new PeacockDatagram<AgentSystemStatusMessage>(message);
                	
                	ch.writeAndFlush(datagram);
                } else if("initial_info".equals(line)) {
                	AgentInitialInfoMessage message = new AgentInitialInfoMessage();
                	PeacockDatagram<AgentInitialInfoMessage> datagram = new PeacockDatagram<AgentInitialInfoMessage>(message);
                	
                	ch.writeAndFlush(datagram);
                } else if("bye".equals(line)) {
                    // Sends the received line to the server.
                    lastWriteFuture = ch.writeAndFlush(line);
                    
                    // If user typed the 'bye' command, wait until the server closes the connection.
                    ch.closeFuture().sync();
                    break;
                } else {
                	System.out.println(line + " is invalid command.");
                	continue;
                }
            }

            // Wait until all messages are flushed before closing the channel.
            if (lastWriteFuture != null) {
                lastWriteFuture.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
    	
    	args = new String[]{"localhost", "8080"};
    	
        // Print usage if no argument is specified.
    	if (args.length != 2) {
            System.err.println(
                    "Usage: " + PeacockClient.class.getSimpleName() +
                    " <host> <port>");
            return;
        }

        // Parse options.
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        new PeacockClient(host, port).run();
    }

}
//end of PeacockClient.java