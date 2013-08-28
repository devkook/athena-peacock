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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;
import com.athena.peacock.common.netty.message.AgentSystemStatusMessage;
import com.athena.peacock.common.netty.message.MessageType;
import com.athena.peacock.common.netty.message.ProvisioningCommandMessage;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.common.core.handler.MonFactorHandler;
import com.athena.peacock.controller.common.provider.AppContext;
import com.athena.peacock.controller.machine.MachineDto;
import com.athena.peacock.controller.machine.MachineService;
import com.athena.peacock.controller.monitor.MonDataDto;
import com.athena.peacock.controller.monitor.MonFactorDto;
import com.athena.peacock.controller.monitor.MonitorService;

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
	
	@Inject
	@Named("machineService")
	private MachineService machineService;
	
	@Inject
	@Named("monitorService")
	private MonitorService monitorService;

	private final Lock lock = new ReentrantLock();
	private final Queue<Callback> callbacks = new ConcurrentLinkedQueue<Callback>();

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
						
						if (responseMsg.isBlocking()) {
							callbacks.poll().handle(responseMsg);
						}
						break;
					case SYSTEM_STATUS : 
						AgentSystemStatusMessage statusMsg = ((PeacockDatagram<AgentSystemStatusMessage>)msg).getMessage();

						//ThreadLocal cannot use.
						//List<MonFactorDto> monFactorList = (List<MonFactorDto>) ThreadLocalUtil.get(PeacockConstant.MON_FACTOR_LIST);
						List<MonFactorDto> monFactorList = AppContext.getBean(MonFactorHandler.class).getMonFactorList();
						
						List<MonDataDto> monDataList = new ArrayList<MonDataDto>();
						MonDataDto monData = null;
						
						for (MonFactorDto monFactor : monFactorList) {
							monData = new MonDataDto();
							
							monData.setMachineId(statusMsg.getAgentId());
							monData.setMonFactorId(monFactor.getMonFactorId());
							monData.setMonDataValue(getMonDataValue(monFactor, statusMsg));
							monData.setRegUserId(1);
							monData.setUpdUserId(1);
							
							monDataList.add(monData);
						}
						
						if (this.monitorService == null) {
							monitorService = AppContext.getBean(MonitorService.class);
						}
						
						monitorService.insertMonDataList(monDataList);
						
						break;
					case INITIAL_INFO : 
						AgentInitialInfoMessage infoMsg = ((PeacockDatagram<AgentInitialInfoMessage>)msg).getMessage();
						
						// register a new channel
						ChannelManagement.registerChannel(infoMsg.getAgentId(), ctx.channel());
						
						String ipAddr = ctx.channel().remoteAddress().toString();
						ipAddr = ipAddr.substring(1, ipAddr.indexOf(":"));
						
						MachineDto machine = new MachineDto();
						
						machine.setMachineId(infoMsg.getAgentId());
						machine.setMachineMacAddr(infoMsg.getMacAddr());
						machine.setIsVm("Y");
						machine.setOsName(infoMsg.getOsName());
						machine.setOsVer(infoMsg.getOsVersion());
						machine.setOsArch(infoMsg.getOsArch());
						machine.setCpuClock(Integer.toString(infoMsg.getCpuClock()));
						machine.setCpuNum(Integer.toString(infoMsg.getCpuNum()));
						machine.setMemSize(Long.toString(infoMsg.getMemSize()));
						machine.setIpAddr(ipAddr);
						machine.setHostName(infoMsg.getHostName());
						machine.setRegUserId(1);
						machine.setUpdUserId(1);
						
						if (this.machineService == null) {
							machineService = AppContext.getBean(MachineService.class);
						}
						
						machineService.insertMachine(machine);
						
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
        
        if (!(cause instanceof DataAccessException)) {
        	ctx.close();
        }
    }
    
    private String getMonDataValue(MonFactorDto monFactor, AgentSystemStatusMessage statusMsg) {
    	String value = null;
    			
    	if (monFactor.getMonFactorName().toLowerCase().indexOf("cpu") > -1) {
			if (monFactor.getMonFactorName().toLowerCase().indexOf("user") > -1) {
				value = statusMsg.getUserCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("sys") > -1) {
				value = statusMsg.getSysCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("idle") > -1) {
				value = statusMsg.getIdleCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("wait") > -1) {
				value = statusMsg.getWaitCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("nice") > -1) {
				value = statusMsg.getNiceCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("combined") > -1) {
				value = statusMsg.getCombinedCpu();
			}    		
		} else if (monFactor.getMonFactorName().toLowerCase().indexOf("memory") > -1) {
			if (monFactor.getMonFactorName().toLowerCase().indexOf("actual_free") > -1) {
				value = statusMsg.getActualFreeMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("actual_used") > -1) {
				value = statusMsg.getActualUsedMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("free_percent") > -1) {
				value = statusMsg.getFreePercentMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("used_percent") > -1) {
				value = statusMsg.getUsedPercentMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("ram") > -1) {
				value = statusMsg.getRamMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("total") > -1) {
				value = statusMsg.getTotalMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("free") > -1) {
				value = statusMsg.getFreeMem();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("used") > -1) {
				value = statusMsg.getUsedMem();
			}
		} 
    	
    	return value;
    }
    
    /**
     * <pre>
     * 해당 Agent로 Provisioning 관련 명령을 전달하고 필요 시 응답을 반환한다.
     * </pre>
     * @param datagram
     * @return
     */
    public ProvisioningResponseMessage sendMessage(PeacockDatagram<ProvisioningCommandMessage> datagram) {
    	
    	Channel channel = ChannelManagement.getChannel(datagram.getMessage().getAgentId());
    	boolean isBlocking = datagram.getMessage().isBlocking();
    	
    	if (isBlocking) {
			Callback callback = new Callback(); 
			lock.lock(); 
			
			try { 
				callbacks.add(callback); 
		    	
				if (channel != null) {
					channel.write(datagram);
				}
			} finally { 
			  lock.unlock(); 
			} 
			
			return callback.get(); 
    	} else {
			if (channel != null) {
				channel.write(datagram);
			}
    		
    		return null;
    	}
    }//end of sendMessage()
    
    /**
     * <pre>
     * Channel 관리를 위한 클래스
     * </pre>
     * @author Sang-cheon Park
     * @version 1.0
     */
    private static class ChannelManagement {
    	
    	static Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();
        
        /**
         * <pre>
         * 신규 채널을 등록한다.
         * </pre>
         * @param agentId
         * @param channel
         */
        static void registerChannel(String agentId, Channel channel) {
        	logger.debug("agentId({}) will be added to channelMap.", agentId);
        	channelMap.put(agentId, channel);
        }//end of registerChannel()
        
        /**
         * <pre>
         * agentId에 해당하는 채널을 map에서 제거한다.
         * </pre>
         * @param agentId
         */
        static void deregisterChannel(String agentId) {
        	logger.debug("agentId({}) will be removed from channelMap.", agentId);
        	channelMap.remove(agentId);
        }//end of deregisterChannel()
        
        /**
         * <pre>
         * 연결 종료된 채널을 map에서 제거한다.
         * </pre>
         * @param channel
         */
        static void deregisterChannel(Channel channel) {
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
        static Channel getChannel(String agentId) {
        	return channelMap.get(agentId);
        }//end of getChannel()
    }
    //end of ChannelManagement.java
    
	/**
     * <pre>
     * 서버의 처리 순서대로 받기 위한 콜백 클래스
     * </pre>
     * @author Sang-cheon Park
     * @version 1.0
	 */
	private static class Callback {

		private final CountDownLatch latch = new CountDownLatch(1);

		private ProvisioningResponseMessage response;

		ProvisioningResponseMessage get() {
			try {
				latch.await();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return response;
		}

		void handle(ProvisioningResponseMessage response) {
			this.response = response;
			latch.countDown();
		}
	}
	//end of Callback.java
	
}
//end of PeacockServerHandler.java