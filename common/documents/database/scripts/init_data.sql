use peacock;

INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_001','USER_CPU','Percentage of CPU utilization while executing at the user, or application, level.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_002','SYS_CPU','Percentage of CPU utilization while executing at the system, or kernel, level.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_003','IDLE_CPU','Percentage of time that the CPU or CPUs were not processing any commands and the system did not have an outstanding disk I/O request.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_004','WAIT_CPU','Percentage of time that the CPU or CPUs were not processing any commands but during which the system had an outstanding disk I/O request.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_005','NICE_CPU','Percentage of CPU utilization while executing at the user level with nice priority.',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_006','COMBINED_CPU','Sum of User, Sys, Nice, Wait',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_007','ACTUAL_FREE_MEMORY','Actual total free system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_008','ACTUAL_USED_MEMORY','Actual total used system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_009','FREE_MOMORY','Total free system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_010','PREE_PERCENT_MEMORY','Percent total free system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_011','RAM_MOMORY','System Random Access Memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_012','TOTAL_MOMORY','Total system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_013','USED_MOMORY','Total used system memory',1,NOW(),1,NOW());
INSERT INTO peacock.mon_factor_tbl(MON_FACTOR_ID,MON_FACTOR_NAME,MON_FACTOR_DESC,REG_USER_ID,REG_DT,UPD_USER_ID,UPD_DT)
VALUES ('FACTOR_014','USED_PERCENT_MOMORY','Percent total used system memory',1,NOW(),1,NOW());
