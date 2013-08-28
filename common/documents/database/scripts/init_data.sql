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