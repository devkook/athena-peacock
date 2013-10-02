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
