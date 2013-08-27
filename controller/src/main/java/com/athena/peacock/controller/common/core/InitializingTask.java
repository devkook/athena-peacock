/* Copyright (c) 2011 IS Acts.
 * All right reserved.
 *
 * This software is the confidential and proprietary information of IS Acts.
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement
 * you entered into with IS Acts.
 *
 * Revision History
 * Author              Date             Description
 * ------------------  --------------   ------------------
 * Sang-cheon Park     2011. 12. 22.     First Draft.
 */
package com.athena.peacock.controller.common.core;

/**
 * <pre>
 * 시스템 초기화 작업을 담당하는 인터페이스
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public interface InitializingTask {
    
    public void init();
    
}//end of InitializingTask.java