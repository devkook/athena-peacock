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
 * Sang-cheon Park	2013. 8. 2.		First Draft.
 */
package com.athena.peacock.agent.util;

import org.hyperic.sigar.Cpu;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.ProcStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

/**
 * <pre>
 * Sigar를 이용한 Memory, CPU의 상태정보를 수집하는 유틸 클래스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public final class SigarUtil {
	
	private static Sigar sigar;
	
	private SigarUtil() {
		// nothing to do.
	}

	/**
	 * <pre>
	 * sigar 라이브러리를 이용한 시스템 상태정보 수집 시 사용하는 /src/main/resources/sigar 경로의 
	 * 라이브러리 파일들을 java.library.path에 추가한다.
	 * </pre>
	 */
	static {
		String sigarLibPath = SigarUtil.class.getResource("/sigar").getFile();
		String javaLibPath = System.getProperty("java.library.path");
		
		if (javaLibPath.indexOf(sigarLibPath) < 0) {
			System.setProperty("java.library.path", new StringBuilder()
														.append(sigarLibPath)
														.append(":")
														.append(javaLibPath)
														.toString());
		}
	}
	
	/**
	 * <pre>
	 * Sigar의 인스턴스를 반환한다.(Memory, CPU 외의 정보를 직접 조회할 때 사용)
	 * </pre>
	 * @return
	 */
	public static Sigar getInstance() {
		if (sigar == null) {
			sigar = new Sigar();
		}
		
		return sigar;
	}//end of getInstance()

	public static Mem getMem() throws SigarException {
		return getInstance().getMem();
	}
	
	public static Cpu getCpu() throws SigarException {
		return getInstance().getCpu();
	}
	
	public static CpuPerc[] getCpuPercList() throws SigarException {
		return getInstance().getCpuPercList();
	}
	
	public static Swap getSwap() throws SigarException {
		return getInstance().getSwap();
	}
	
	public static ProcStat getasdfasd() throws SigarException {
		return getInstance().getProcStat();
	}
	
}
//end of SigarUtil.java