SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `peacock` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `peacock` ;

-- -----------------------------------------------------
-- Table `peacock`.`machine_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`machine_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`machine_tbl` (
  `MACHINE_ID` INT(11)  NOT NULL ,
  `MACHINE_MAC_ADDR` VARCHAR(20) NULL ,
  `IS_VM` CHAR(1) NULL ,
  `OS_NAME` VARCHAR(50) NULL ,
  `OS_VER` VARCHAR(20) NULL ,
  `OS_ARCH` VARCHAR(20) NULL ,
  `CPU_CLOCK` VARCHAR(20) NULL ,
  `CPU_NUM` INT NULL ,
  `MEM_SIZE` VARCHAR(20) NULL ,
  `IP_ADDR` VARCHAR(15) NULL ,
  `HOST_NAME` VARCHAR(255) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NOT NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`MACHINE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`software_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`software_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`software_tbl` (
  `MACHINE_ID` INT(11)  NOT NULL ,
  `SOFTWARE_ID` INT NOT NULL ,
  `INSTALL_LOCATION` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(255) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NOT NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`MACHINE_ID`, `SOFTWARE_ID`) ,
  INDEX `fk_SOFTWARE_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  CONSTRAINT `fk_SOFTWARE_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`mon_factor_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`mon_factor_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`mon_factor_tbl` (
  `MON_FACTOR_ID` INT(11)  NOT NULL ,
  `MON_FACTOR_NAME` VARCHAR(45) NOT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`MON_FACTOR_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`mon_data_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`mon_data_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`mon_data_tbl` (
  `MACHINE_ID` INT(11)  NOT NULL ,
  `MON_DATA_ID` INT(11)  NOT NULL ,
  `MON_FACTOR_ID` INT(11)  NOT NULL ,
  `MON_DATA_VALUE` INT(11)  NOT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`MACHINE_ID`, `MON_DATA_ID`, `MON_FACTOR_ID`) ,
  INDEX `fk_MON_DATA_TBL_MON_FACTOR_TBL1_idx` (`MON_FACTOR_ID` ASC) ,
  INDEX `fk_MON_DATA_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  CONSTRAINT `fk_MON_DATA_TBL_MON_FACTOR_TBL1`
    FOREIGN KEY (`MON_FACTOR_ID` )
    REFERENCES `peacock`.`mon_factor_tbl` (`MON_FACTOR_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MON_DATA_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`os_package_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`os_package_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`os_package_tbl` (
  `PKG_ID` INT(11)  NOT NULL ,
  `MACHINE_ID` INT(11)  NOT NULL ,
  `PKG_NAME` VARCHAR(100) NULL ,
  `PKG_ARCH` VARCHAR(20) NULL ,
  `VERSION` VARCHAR(20) NULL ,
  `RELEASE` VARCHAR(20) NULL ,
  `SIZE` VARCHAR(20) NULL ,
  `INSTALL_DATE` VARCHAR(45) NULL ,
  `SUMMARY` VARCHAR(255) NULL ,
  `DESCRIPTION` VARCHAR(45) NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`PKG_ID`, `MACHINE_ID`) ,
  INDEX `fk_OS_PACKAGE_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  CONSTRAINT `fk_OS_PACKAGE_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`roles_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`roles_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`roles_tbl` (
  `ROLE_ID` INT(11)  NOT NULL ,
  `ROLE_NAME` VARCHAR(30) NULL ,
  `PERMISSION` VARCHAR(45) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`ROLE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`users_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`users_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`users_tbl` (
  `USER_ID` INT(11)  NOT NULL ,
  `ROLE_ID` INT(11)  NOT NULL ,
  `LOGIN_ID` VARCHAR(30) NULL ,
  `HASHED_PASSWD` VARCHAR(50) NULL ,
  `USER_NAME` VARCHAR(20) NULL ,
  `DEPT_NAME` VARCHAR(45) NULL ,
  `EMAIL` VARCHAR(60) NULL ,
  `IS_ADMIN` TINYINT(1) NULL ,
  `STATUS` INT NULL DEFAULT 1 ,
  `LAST_LOGON` DATETIME NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`USER_ID`) ,
  INDEX `fk_USERS_TBL_ROLES_TBL1_idx` (`ROLE_ID` ASC) ,
  CONSTRAINT `fk_USERS_TBL_ROLES_TBL1`
    FOREIGN KEY (`ROLE_ID` )
    REFERENCES `peacock`.`roles_tbl` (`ROLE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`user_machine_map_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`user_machine_map_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`user_machine_map_tbl` (
  `USER_ID` INT(11)  NOT NULL ,
  `MACHINE_ID` INT(11)  NOT NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_DT` DATETIME NULL ,
  INDEX `fk_USER_MACHINE_MAP_TBL_USERS_TBL1_idx` (`USER_ID` ASC) ,
  INDEX `fk_USER_MACHINE_MAP_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  PRIMARY KEY (`USER_ID`, `MACHINE_ID`) ,
  CONSTRAINT `fk_USER_MACHINE_MAP_TBL_USERS_TBL1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `peacock`.`users_tbl` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_MACHINE_MAP_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`user_group_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`user_group_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`user_group_tbl` (
  `GROUP_ID` INT(11)  NOT NULL ,
  `GROUP_NAME` VARCHAR(20) NOT NULL ,
  `DESC` VARCHAR(100) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`GROUP_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`user_group_map_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`user_group_map_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`user_group_map_tbl` (
  `GROUP_ID` INT(11)  NOT NULL ,
  `USER_ID` INT(11)  NOT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`GROUP_ID`, `USER_ID`) ,
  INDEX `fk_USER_GROUP_MAP_TBL_USERS_TBL1_idx` (`USER_ID` ASC) ,
  CONSTRAINT `fk_USER_GROUP_MAP_TBL_USER_GROUP_TBL1`
    FOREIGN KEY (`GROUP_ID` )
    REFERENCES `peacock`.`user_group_tbl` (`GROUP_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_GROUP_MAP_TBL_USERS_TBL1`
    FOREIGN KEY (`USER_ID` )
    REFERENCES `peacock`.`users_tbl` (`USER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`as_launch_config_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`as_launch_config_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`as_launch_config_tbl` (
  `LAUNCH_CONFIG_ID` VARCHAR(20) NOT NULL ,
  `LAUNCH_IMG_ID` VARCHAR(45) NULL ,
  `INSTANCE_TYPE` VARCHAR(20) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`LAUNCH_CONFIG_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`as_group_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`as_group_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`as_group_tbl` (
  `AS_GROUP_ID` INT(11)  NOT NULL ,
  `AS_GROU_NAME` VARCHAR(45) NULL ,
  `MIN_SIZE` INT(11)  NULL ,
  `MAX_SIZE` INT(11)  NULL ,
  `LAUNCH_CONFIG_ID` VARCHAR(20) NOT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`AS_GROUP_ID`, `LAUNCH_CONFIG_ID`) ,
  INDEX `fk_AS_GROUP_AS_LAUNCH_CONFIG_TBL1_idx` (`LAUNCH_CONFIG_ID` ASC) ,
  CONSTRAINT `fk_AS_GROUP_AS_LAUNCH_CONFIG_TBL1`
    FOREIGN KEY (`LAUNCH_CONFIG_ID` )
    REFERENCES `peacock`.`as_launch_config_tbl` (`LAUNCH_CONFIG_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`as_policy_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`as_policy_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`as_policy_tbl` (
  `POLICY_ID` INT(11)  NOT NULL ,
  `AS_GROUP_ID` INT(11)  NOT NULL ,
  `POLICY_NAME` VARCHAR(45) NULL ,
  `ADJUST_SIZE` INT(11)  NULL ,
  `CHECK_TYPE` INT(1) NULL ,
  `PERIOD` INT(11)  NULL ,
  `INCREASE_BASE` INT(3) NULL ,
  `DECREASE_BASE` INT(3) NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`POLICY_ID`, `AS_GROUP_ID`) ,
  INDEX `fk_AS_POLICY_AS_GROUP1_idx` (`AS_GROUP_ID` ASC) ,
  CONSTRAINT `fk_AS_POLICY_AS_GROUP1`
    FOREIGN KEY (`AS_GROUP_ID` )
    REFERENCES `peacock`.`as_group_tbl` (`AS_GROUP_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`load_balancer_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`load_balancer_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`load_balancer_tbl` (
  `LOAD_BALANCER_ID` INT(11)  NOT NULL ,
  `LOAD_BALANCER_NAME` VARCHAR(45) NULL ,
  `LB_IP_ADDR` VARCHAR(45) NULL ,
  `AS_GROUP_ID` INT(11)  NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`LOAD_BALANCER_ID`) ,
  INDEX `fk_LOAD_BALANCER_TBL_AS_GROUP_TBL1_idx` (`AS_GROUP_ID` ASC) ,
  CONSTRAINT `fk_LOAD_BALANCER_TBL_AS_GROUP_TBL1`
    FOREIGN KEY (`AS_GROUP_ID` )
    REFERENCES `peacock`.`as_group_tbl` (`AS_GROUP_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`lb_machine_map_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`lb_machine_map_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`lb_machine_map_tbl` (
  `LOAD_BALANCER_ID` INT(11)  NOT NULL ,
  `MACHINE_ID` INT(11)  NOT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  INDEX `fk_LB_MACHINE_MAP_TBL_LOAD_BALANCER_TBL1_idx` (`LOAD_BALANCER_ID` ASC) ,
  INDEX `fk_LB_MACHINE_MAP_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  CONSTRAINT `fk_LB_MACHINE_MAP_TBL_LOAD_BALANCER_TBL1`
    FOREIGN KEY (`LOAD_BALANCER_ID` )
    REFERENCES `peacock`.`load_balancer_tbl` (`LOAD_BALANCER_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_LB_MACHINE_MAP_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`machine_package_map_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`machine_package_map_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`machine_package_map_tbl` (
  `MACHINE_ID` INT(11)  NOT NULL ,
  `PKG_ID` INT(11)  NOT NULL ,
  INDEX `fk_MACHINE_PACKAGE_MAP_TBL_MACHINE_TBL1_idx` (`MACHINE_ID` ASC) ,
  INDEX `fk_MACHINE_PACKAGE_MAP_TBL_OS_PACKAGE_TBL1_idx` (`PKG_ID` ASC) ,
  CONSTRAINT `fk_MACHINE_PACKAGE_MAP_TBL_MACHINE_TBL1`
    FOREIGN KEY (`MACHINE_ID` )
    REFERENCES `peacock`.`machine_tbl` (`MACHINE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MACHINE_PACKAGE_MAP_TBL_OS_PACKAGE_TBL1`
    FOREIGN KEY (`PKG_ID` )
    REFERENCES `peacock`.`os_package_tbl` (`PKG_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`software_repo_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`software_repo_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`software_repo_tbl` (
  `SOFTWARE_ID` INT NOT NULL ,
  `SOFTWARE_NAME` VARCHAR(45) NULL ,
  `REPO_LOCATION` VARCHAR(45) NULL ,
  `VERION` VARCHAR(45) NULL ,
  `FILE_NAME` VARCHAR(200) NULL ,
  `VENDOR_NAME` VARCHAR(40) NULL ,
  `DESCRIPTION` TEXT NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  `UPD_DT` DATETIME NULL ,
  PRIMARY KEY (`SOFTWARE_ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `peacock`.`config_file_info_tbl`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `peacock`.`config_file_info_tbl` ;

CREATE  TABLE IF NOT EXISTS `peacock`.`config_file_info_tbl` (
  `SOFTWARE_ID` INT NOT NULL ,
  `CONFIG_FILE_ID` INT NOT NULL ,
  `CONFIG_FILE_NAME` VARCHAR(45) NOT NULL ,
  `CONFIG_FILE_LOCATION` VARCHAR(200) NULL ,
  `REG_DT` DATETIME NULL ,
  `UPD_DT` DATETIME NULL ,
  `REG_USER_ID` INT(11)  NULL ,
  `UPD_USER_ID` INT(11)  NULL ,
  INDEX `fk_CONFIG_FILE_INFO_TBL_SOFTWARE_REPO_TBL1_idx` (`SOFTWARE_ID` ASC) ,
  PRIMARY KEY (`SOFTWARE_ID`, `CONFIG_FILE_ID`) ,
  CONSTRAINT `fk_CONFIG_FILE_INFO_TBL_SOFTWARE_REPO_TBL1`
    FOREIGN KEY (`SOFTWARE_ID` )
    REFERENCES `peacock`.`software_repo_tbl` (`SOFTWARE_ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;