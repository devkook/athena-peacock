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
 * Sang-cheon Park	2013. 11. 1.		First Draft.
 */
package com.athena.peacock.controller.web.as;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.athena.peacock.controller.common.component.RHEVMRestTemplate;
import com.athena.peacock.controller.web.common.model.SimpleJsonResponse;
import com.redhat.rhevm.api.model.VMs;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
@Controller
@RequestMapping("/as")
public class AutoScalingController {

    protected final Logger logger = LoggerFactory.getLogger(AutoScalingController.class);

	@Inject
	@Named("restTemplate")
	private RHEVMRestTemplate restTemplate;

	@RequestMapping("/test")
	public @ResponseBody SimpleJsonResponse test(SimpleJsonResponse jsonRes, String api) throws Exception {
		VMs vms = restTemplate.submit(api, VMs.class);
		
		// new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(vms)
		
		jsonRes.setData(vms);
		
		return jsonRes;
	}

	@RequestMapping("/xml")
	public void xml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//response.setContentType("application/xml");
		response.setContentType("text/xml");
		
		try {
			File file = new File(getClass().getResource("/spring/context-common.xml").getFile());
			BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
			BufferedOutputStream outs = new BufferedOutputStream(response.getOutputStream());
			
			int read = 0;
			while ((read = fin.read()) != -1) {
				outs.write(read);
			}
			
			IOUtils.closeQuietly(fin);
			IOUtils.closeQuietly(outs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
//end of AutoScalingController.java