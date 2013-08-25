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
 * Sang-cheon Park	2013. 8. 25.		First Draft.
 */
package com.athena.peacock.controller.common.provider;

import org.springframework.context.ApplicationContext;

/**
 * <pre>
 * 
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public class AppContext {
    
    private static ApplicationContext ctx;  
    
    public static void setApplicationContext(ApplicationContext applicationContext) {  
        ctx = applicationContext;  
    }  
  
    public static ApplicationContext getApplicationContext() {  
        return ctx;  
    }

	public static Object getBean(String value) {
		return ctx.getBean(value);
	}  

	public static <T> T getBean(Class<T> clazz) {
		return clazz.cast(ctx.getBean(clazz));
	}  

	public static <T> T getBean(String value, Class<T> clazz) {
		return clazz.cast(ctx.getBean(value));
	}  
}//end of AppContext.java