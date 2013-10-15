use peacock;

INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_UNIT,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_001','IDLE_CPU','%','Percentage of time that the CPU or CPUs were not processing any commands and the system did not have an outstanding disk I/O request.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_UNIT,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_002','COMBINED_CPU','%','Percentage of CPU utilization while executing at all(system, kernel, user, application).',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_UNIT,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_003','TOTAL_MEMORY','KB','Total system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_UNIT,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_004','FREE_MEMORY','KB','Total free system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_UNIT,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_005','USED_MEMORY','KB','Total used system memory',1,NOW(),1,NOW());

INSERT INTO peacock.software_repo_tbl (SOFTWARE_ID,SOFTWARE_NAME,SOFTWARE_VERSION,SOFTWARE_VENDOR,FILE_LOCATION,FILE_NAME,DESCRIPTION,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (1,'Apache HTTP Daemon','2.2.25','Apache Software Foundation','/repo/apache/2.2.25/','httpd-2.2.25.tar.gz','Apache HTTP Daemon',1,NOW(),1,NOW());
INSERT INTO peacock.software_repo_tbl (SOFTWARE_ID,SOFTWARE_NAME,SOFTWARE_VERSION,SOFTWARE_VENDOR,FILE_LOCATION,FILE_NAME,DESCRIPTION,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (2,'MySQL Community Server','5.5.34','Oracle Corporation','/repo/mysql/5.5.34/','MySQL-server.rpm,MySQL-client.rpm','MySQL 5.5.34',1,NOW(),1,NOW());
INSERT INTO peacock.software_repo_tbl (SOFTWARE_ID,SOFTWARE_NAME,SOFTWARE_VERSION,SOFTWARE_VENDOR,FILE_LOCATION,FILE_NAME,DESCRIPTION,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (3,'JBoss EAP','5.2.0','Red Hat,Inc.','/repo/jboss/','jboss-eap-5.2.0.zip,jboss-cluster-template-5.2.0.zip','JBoss EAP 5.2.0',1,NOW(),1,NOW());
INSERT INTO peacock.software_repo_tbl (SOFTWARE_ID,SOFTWARE_NAME,SOFTWARE_VERSION,SOFTWARE_VENDOR,FILE_LOCATION,FILE_NAME,DESCRIPTION,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (4,'Apache Tomcat','6.0.37','Apache Software Foundation','/repo/tomcat/','apache-tomcat-6.0.37.zip,tomcat-template-6.0.37.zip','Apache Tomcat 6.0.37',1,NOW(),1,NOW());

INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (1,1,'/repo/apache/2.2.25/conf/','${INSTALL_LOCATION}/conf/','httpd.conf','ServerRoot,Port,ServerName',1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (2,1,'/repo/apache/2.2.25/conf/','${INSTALL_LOCATION}/conf/extra/','httpd-mpm.conf',NULL,1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (3,1,'/repo/apache/2.2.25/conf/','${INSTALL_LOCATION}/conf/','mod-jk.conf',NULL,1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (4,1,'/repo/apache/2.2.25/conf/','${INSTALL_LOCATION}/conf/','uriworkermap.properties',NULL,1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (5,1,'/repo/apache/2.2.25/conf/','${INSTALL_LOCATION}/conf/','workers.properties',NULL,1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (6,2,'/repo/mysql/5.5.34/','/etc/','my.cnf','mysql.datadir,mysql.port',1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (7,3,'/repo/jboss/datasource/','${INSTALL_LOCATION}/','${db_type}-ds.xml','databaseType,jndiName,connectionUrl,userName,password,minPoolSize,maxPoolSize',1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (8,3,NULL,'${INSTALL_LOCATION}/bin/','env.sh','jbossHome,serverHome,serverName,partitionName,bindAddress,bindPort',1,NOW(),1,NOW());
INSERT INTO peacock.config_file_tbl (CONFIG_FILE_ID,SOFTWARE_ID,CONFIG_FILE_SOURCE_LOCATION,CONFIG_FILE_TARGET_LOCATION,CONFIG_FILE_NAME,PROPERTIES,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT) 
VALUES (9,4,NULL,'${INSTALL_LOCATION}/bin/','env.sh','javaHome,serverName,catalinaHome,catalinaBase,portOffset,compUser',1,NOW(),1,NOW());

INSERT INTO `peacock`.`roles_tbl` (`ROLE_ID`, `ROLE_NAME`, `PERMISSION`, `REG_DT`) VALUES ('1', 'admin', 'all', NOW());
INSERT INTO `peacock`.`roles_tbl` (`ROLE_ID`, `ROLE_NAME`, `PERMISSION`, `REG_DT`) VALUES ('2', 'role2', 'write', NOW());

INSERT INTO `peacock`.`users_tbl` (`USER_ID`, `ROLE_ID`, `LOGIN_ID`, `HASHED_PASSWD`, `USER_NAME`, `DEPT_NAME`, `EMAIL`, `IS_ADMIN`, `STATUS`, `REG_DT`) 
VALUES ('1', '1', 'idkbj', '1234', '권봉진', '개발팀', 'idkbj@naver.com', '1', '1', NOW());
INSERT INTO `peacock`.`users_tbl` (`USER_ID`, `ROLE_ID`, `LOGIN_ID`, `HASHED_PASSWD`, `USER_NAME`, `DEPT_NAME`, `EMAIL`, `IS_ADMIN`, `STATUS`, `REG_DT`) 
VALUES ('2', '1', 'abcd11', '1111', '홍길동', '개발팀', '111@naver.com', '0', '1', NOW());
INSERT INTO `peacock`.`users_tbl` (`USER_ID`, `ROLE_ID`, `LOGIN_ID`, `HASHED_PASSWD`, `USER_NAME`, `DEPT_NAME`, `EMAIL`, `IS_ADMIN`, `STATUS`, `REG_DT`) 
VALUES ('3', '1', 'test', '1111', '김철수', '개발팀', 'test@naver.com', '0', '1', NOW());
INSERT INTO `peacock`.`users_tbl` (`USER_ID`, `ROLE_ID`, `LOGIN_ID`, `HASHED_PASSWD`, `USER_NAME`, `DEPT_NAME`, `EMAIL`, `IS_ADMIN`, `STATUS`, `REG_DT`) 
VALUES ('4', '1', '1111', '1111', '1111', '개발팀', '111@naver.com', '0', '1', NOW());


INSERT INTO `peacock`.`user_group_tbl` (`GROUP_ID`, `GROUP_NAME`, `DESCRIPTION`, `REG_DT`) VALUES ('1', 'osc-employee', 'Open Source Consulting', NOW());
INSERT INTO `peacock`.`user_group_tbl` (`GROUP_ID`, `GROUP_NAME`, `DESCRIPTION`, `REG_DT`) VALUES ('2', 'ncia', '...', NOW());
