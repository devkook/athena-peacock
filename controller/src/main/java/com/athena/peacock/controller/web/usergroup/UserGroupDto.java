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
 * Bong-Jin Kwon	2013. 10. 2.		First Draft.
 */
package com.athena.peacock.controller.web.usergroup;

import java.util.Date;

/**
 * <pre>
 * 
 * </pre>
 * @author Bong-Jin Kwon
 * @version 1.0
 */
public class UserGroupDto {
	
	private int group_id;
	private String group_name;
	private String description;
	private int reg_user_id;
	private Date reg_dt;
	private int upd_user_id;
	private Date upd_dt;
	
	private int users;

	public int getUsers() {
		return users;
	}

	public void setUsers(int users) {
		this.users = users;
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	public UserGroupDto() {
		// TODO Auto-generated constructor stub
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReg_user_id() {
		return reg_user_id;
	}

	public void setReg_user_id(int reg_user_id) {
		this.reg_user_id = reg_user_id;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public int getUpd_user_id() {
		return upd_user_id;
	}

	public void setUpd_user_id(int upd_user_id) {
		this.upd_user_id = upd_user_id;
	}

	public Date getUpd_dt() {
		return upd_dt;
	}

	public void setUpd_dt(Date upd_dt) {
		this.upd_dt = upd_dt;
	}

}
//end of UserGroupDto.java