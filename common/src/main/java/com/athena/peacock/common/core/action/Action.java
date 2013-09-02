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
 * Sang-cheon Park	2013. 9. 2.		First Draft.
 */
package com.athena.peacock.common.core.action;

import java.io.Serializable;

/**
 * <pre>
 * Task unit for provisioning.
 * </pre>
 * @author Sang-cheon Park
 * @version 1.0
 */
public interface Action extends Serializable {
    public <T> T perform();
}
//end of Action.java