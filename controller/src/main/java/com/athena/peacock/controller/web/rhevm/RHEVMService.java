package com.athena.peacock.controller.web.rhevm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;



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

	public int getVirtualMachineListCnt(VMDto vms) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<?> getVirtualList(VMDto vms) {
		
		List<VMDto> list = new ArrayList<VMDto>();
		
		VMDto vm1 = new VMDto();
		vm1.setCluster("Default");
		vm1.setName("NCIA_WebServer");
		vm1.setDomain("Internal");
		vm1.setDescription("NCIA Web Server for Home Page");
		vm1.setHost("cloud01.ncia.go.kr");
		vm1.setCreationTime("2013-10-10 14:30:25");
		vm1.setStartTime("2013-10-11 12:32:23");
		vm1.setStatus("Up");
		vm1.setOs("Red Hat Enterprise Linux 6.x x64");
		vm1.setType("Red Hat VirtIO");
		list.add(vm1);
		
		VMDto vm2 = new VMDto();
		vm2.setCluster("Default");
		vm2.setName("NCIA_JBoss");
		vm2.setDomain("Internal");
		vm2.setDescription("NCIA Middleware for Home Page");
		vm2.setHost("cloud01.ncia.go.kr");
		vm2.setCreationTime("2013-10-21 14:30:25");
		vm2.setStartTime("2013-10-21 14:33:19");
		vm2.setStatus("Down");
		vm2.setOs("Red Hat Enterprise Linux 6.x x64");
		vm2.setType("Red Hat VirtIO");
		list.add(vm2);
		
		return list;
	}

}
