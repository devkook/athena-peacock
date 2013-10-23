package com.athena.peacock.controller.web.rhevm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;





import com.athena.peacock.controller.web.rhevm.dto.NetworkDto;
import com.athena.peacock.controller.web.rhevm.dto.VMDto;

/**
 * <pre>
 * RHEV-M Service Class
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Service("rhevmService")
public class RHEVMService {
	
	private static List<VMDto> list = new ArrayList<VMDto>();
	private static List<NetworkDto> nics = new ArrayList<NetworkDto>();
	
	static {
		VMDto vm = new VMDto();
		vm.setVmId("ncia-1");
		vm.setCluster("Default");
		vm.setName("NCIA_WebServer");
		vm.setDomain("Internal");
		vm.setDescription("NCIA Web Server for Home Page");
		vm.setHost("cloud01.ncia.go.kr");
		vm.setCreationTime("2013-10-10 14:30:25");
		vm.setStartTime("2013-10-11 12:32:23");
		vm.setStatus("Up");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setType("Red Hat VirtIO");
		vm.setTemplate("RHEL6-x86_64");
		vm.setMemory("2048MB");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setCores("2(1 Sockets, 2 Cores per Socket)");
		vm.setDisplay("VNC");
		vm.setOrigin("RHEV");
		vm.setPriority("Low");
		list.add(vm);
		
		vm = new VMDto();
		vm.setVmId("ncia-2");
		vm.setCluster("Default");
		vm.setName("NCIA_JBoss");
		vm.setDomain("Internal");
		vm.setDescription("NCIA Middleware for Home Page");
		vm.setHost("cloud01.ncia.go.kr");
		vm.setCreationTime("2013-10-21 14:30:25");
		vm.setStartTime("2013-10-21 14:33:19");
		vm.setStatus("Down");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setType("Red Hat VirtIO");
		vm.setTemplate("RHEL6-x86_64");
		vm.setMemory("4096MB");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setCores("4(2 Sockets, 2 Cores per Socket)");
		vm.setDisplay("SPICE");
		vm.setOrigin("RHEV");
		vm.setPriority("HIGH");
		list.add(vm);
		
		vm = new VMDto();
		vm.setVmId("ncia-3");
		vm.setCluster("Default");
		vm.setName("NCIA_CUBRID");
		vm.setDomain("Internal");
		vm.setDescription("NCIA CUBRID DB for Home Page");
		vm.setHost("cloud01.ncia.go.kr");
		vm.setCreationTime("2013-10-21 15:27:03");
		vm.setStartTime("2013-10-22 14:33:19");
		vm.setStatus("Up");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setType("Red Hat VirtIO");
		vm.setTemplate("RHEL6-CUBRID-x86_64");
		vm.setMemory("8192MB");
		vm.setOs("Red Hat Enterprise Linux 6.x x64");
		vm.setCores("8(4 Sockets, 2 Cores per Socket)");
		vm.setDisplay("SPICE");
		vm.setOrigin("RHEV");
		vm.setPriority("HIGH");
		list.add(vm);
		
		
		// NetworkDTO
		NetworkDto nic = new NetworkDto();
		nic.setName("nic1");
		nic.setNetworkName("Public");
		nic.setType("Red Hat VirtIO");
		nic.setMacAddress("00:1a:4a:14:40:07");
		nic.setSpeed("1000");
		nics.add(nic);
		
		nic = new NetworkDto();
		nic.setName("nic2");
		nic.setNetworkName("NFS");
		nic.setType("Red Hat VirtIO");
		nic.setMacAddress("00:1a:4a:14:40:1a");
		nic.setSpeed("1000");
		nics.add(nic);
		
		nic = new NetworkDto();
		nic.setName("nic3");
		nic.setNetworkName("rhevm");
		nic.setType("Red Hat VirtIO");
		nic.setMacAddress("00:1a:4a:14:40:1c");
		nic.setSpeed("1000");
		nics.add(nic);
	}

	public List<VMDto> getVirtualList(VMDto vms) {
		return list;
	}

	public VMDto getVirtualMachine(VMDto vm) {
		// TODO Auto-generated method stub
		if( vm.getVmId() != null && vm.getVmId().equals("ncia-1")) {
			return (VMDto) list.get(0);
		} else if( vm.getVmId() != null && vm.getVmId().equals("ncia-2")) {
			return (VMDto) list.get(1);
		} else if( vm.getVmId() != null && vm.getVmId().equals("ncia-3")) {
			return (VMDto) list.get(2);
		}
		return vm;
	}
	
	public List<NetworkDto> getNIC(VMDto vm) {
		return nics;
	}

}
