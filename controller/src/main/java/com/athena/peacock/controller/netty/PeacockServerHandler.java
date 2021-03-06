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
import org.springframework.stereotype.Component;

import com.athena.peacock.common.netty.PeacockDatagram;
import com.athena.peacock.common.netty.message.AbstractMessage;
import com.athena.peacock.common.netty.message.AgentInitialInfoMessage;
import com.athena.peacock.common.netty.message.AgentSystemStatusMessage;
import com.athena.peacock.common.netty.message.MessageType;
import com.athena.peacock.common.netty.message.OSPackageInfoMessage;
import com.athena.peacock.common.netty.message.PackageInfo;
import com.athena.peacock.common.netty.message.ProvisioningResponseMessage;
import com.athena.peacock.controller.common.core.handler.MonFactorHandler;
import com.athena.peacock.controller.common.provider.AppContext;
import com.athena.peacock.controller.web.machine.MachineDto;
import com.athena.peacock.controller.web.machine.MachineService;
import com.athena.peacock.controller.web.monitor.MonDataDto;
import com.athena.peacock.controller.web.monitor.MonFactorDto;
import com.athena.peacock.controller.web.monitor.MonitorService;
import com.athena.peacock.controller.web.ospackage.PackageDto;
import com.athena.peacock.controller.web.ospackage.PackageService;

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
	
	@Inject
	@Named("packageService")
	private PackageService packageService;

	@SuppressWarnings("unchecked")
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("channelRead0() has invoked.");
		logger.info("[Server] IP Address => " + ctx.channel().remoteAddress().toString());
		logger.info("[Server] Object => " + msg.getClass().getName());
		//logger.info("[Server] Contents => " + msg.toString());
		
		if("bye".equals(msg.toString())) {
			// Response and exit.
			ChannelFuture future = ctx.write("This channel will be closed.");
			future.addListener(ChannelFutureListener.CLOSE);
		} else {
			if(msg instanceof PeacockDatagram) {
				MessageType messageType = ((PeacockDatagram<?>)msg).getMessageType();

				switch (messageType) {
					case COMMAND : 
						break;
					case RESPONSE : 
						ProvisioningResponseMessage responseMsg = ((PeacockDatagram<ProvisioningResponseMessage>)msg).getMessage();
						
						if (responseMsg.isBlocking()) {
							CallbackManagement.poll().handle(responseMsg);
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
					case PACKAGE_INFO :
						OSPackageInfoMessage packageMsg = ((PeacockDatagram<OSPackageInfoMessage>)msg).getMessage();
						List<PackageInfo> packageInfoList = packageMsg.getPackageInfoList();
						PackageInfo packageInfo = null;
						List<PackageDto> packageList = new ArrayList<PackageDto>();
						PackageDto ospackage = null;
						for (int i = 0; i < packageInfoList.size(); i++) {
							packageInfo = packageInfoList.get(i);
							
							ospackage = new PackageDto();
							ospackage.setPkgId(i + 1);
							ospackage.setMachineId(packageMsg.getAgentId());
							ospackage.setName(packageInfo.getName());
							ospackage.setArch(packageInfo.getArch());
							ospackage.setSize(packageInfo.getSize());
							ospackage.setVersion(packageInfo.getVersion());
							ospackage.setReleaseInfo(packageInfo.getRelease());
							ospackage.setInstallDate(packageInfo.getInstallDate());
							ospackage.setSummary(packageInfo.getSummary());
							ospackage.setDescription(packageInfo.getDescription());
							
							packageList.add(ospackage);
						}
						
						if (packageList.size() > 0) {
							if (packageService == null) {
								packageService = AppContext.getBean(PackageService.class);
							}
							
							packageService.insertPackageList(packageList);
						}
						
						break;
				}
			}
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		logger.info("channelReadComplete() has invoked.");
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
        
        // ctx will not be closed.
        //if (!(cause instanceof NestedRuntimeException)) {
        //	ctx.close();
        //}
    }
    
    private String getMonDataValue(MonFactorDto monFactor, AgentSystemStatusMessage statusMsg) {
    	String value = null;
    			
    	if (monFactor.getMonFactorName().toLowerCase().indexOf("cpu") > -1) {
			if (monFactor.getMonFactorName().toLowerCase().indexOf("idle") > -1) {
				value = statusMsg.getIdleCpu();
			} else if (monFactor.getMonFactorName().toLowerCase().indexOf("combined") > -1) {
				value = statusMsg.getCombinedCpu();
			}    		
		} else if (monFactor.getMonFactorName().toLowerCase().indexOf("memory") > -1) {
			if (monFactor.getMonFactorName().toLowerCase().indexOf("total") > -1) {
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
    public ProvisioningResponseMessage sendMessage(PeacockDatagram<AbstractMessage> datagram) {
    	Channel channel = ChannelManagement.getChannel(datagram.getMessage().getAgentId());
    	boolean isBlocking = datagram.getMessage().isBlocking();

    	if (isBlocking) {
			Callback callback = new Callback(); 
			CallbackManagement.lock(); 
			
			try { 
				CallbackManagement.add(callback); 
				
				if (channel != null) {
					channel.writeAndFlush(datagram);
				}
			} finally { 
				CallbackManagement.unlock(); 
			} 
			
			return callback.get(); 
    	} else {
			if (channel != null) {
				channel.writeAndFlush(datagram);
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
    static class ChannelManagement {
    	
    	static Map<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();
        
        /**
         * <pre>
         * 신규 채널을 등록한다.
         * </pre>
         * @param agentId
         * @param channel
         */
        static void registerChannel(String agentId, Channel channel) {
        	logger.debug("agentId({}) and channel({}) will be added to channelMap.", agentId, channel);
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
	static class Callback {

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
	
	/**
	 * <pre>
	 * Multi-thread 환경에서 Callback 객체를 관리하기 위한 클래스 
	 * </pre>
	 * @author Sang-cheon Park
	 * @version 1.0
	 */
	static class CallbackManagement {
		private static final Lock lock = new ReentrantLock();
		private static final Queue<Callback> callbacks = new ConcurrentLinkedQueue<Callback>();
		
		static void lock() {
			lock.lock();
		}
		
		static void unlock() {
			lock.unlock();
		}
		
		static void add(Callback callback) {
			callbacks.add(callback);
		}
		
		static Callback poll() {
			return callbacks.poll();
		}
		
		static int getSize() {
			return callbacks.size();
		}
	}
	//end of CallbackManagement.java
	
}
//end of PeacockServerHandler.java