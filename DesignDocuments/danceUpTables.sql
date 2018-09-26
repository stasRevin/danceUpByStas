-- MySQL Script generated by MySQL Workbench
-- Fri Sep 14 10:50:47 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(450) NULL,
  `is_deleted` VARCHAR(1) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `address1` VARCHAR(120) NOT NULL,
  `address2` VARCHAR(120) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `postalcode` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Schedule`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Schedule` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `user_id`),
  INDEX `fk_Schedule_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_Schedule_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `address1` VARCHAR(120) NOT NULL,
  `address2` VARCHAR(120) NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(2) NOT NULL,
  `lat` DECIMAL(9,6) NULL,
  `lon` DECIMAL(9,6) NULL,
  `postalcode` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LessonDao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Lesson` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NOT NULL,
  `location_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Lesson_Location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `Location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Notification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(45) NOT NULL,
  `isRead` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Notification`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Notification` (
  `notification_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`notification_id`, `user_id`),
  INDEX `fk_User_Notification_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_User_Notification_Notification1`
    FOREIGN KEY (`notification_id`)
    REFERENCES `Notification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Notification_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Dance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Dance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Dance`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Dance` (
  `experience_years` INT NULL,
  `learning_proficiency` INT NULL,
  `user_id` INT NOT NULL,
  `dance_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `dance_id`),
  INDEX `fk_User_Dance_User1_idx` (`user_id` ASC),
  INDEX `fk_User_Dance_Dance1_idx` (`dance_id` ASC),
  CONSTRAINT `fk_User_Dance_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Dance_Dance1`
    FOREIGN KEY (`dance_id`)
    REFERENCES `Dance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Role` (
  `role_id` INT,
  `user_id` INT,
  PRIMARY KEY (`role_id`, `user_id`),
  INDEX `fk_User_Role_User1_idx` (`user_id` ASC),
  CONSTRAINT `fk_User_Role_Role1`
    FOREIGN KEY (`role_id`)
    REFERENCES `Role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User_Lesson`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `User_Lesson` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `lesson_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `lesson_id`),
  INDEX `fk_User_Lesson_Lesson1_idx` (`lesson_id` ASC),
  CONSTRAINT `fk_User_Lesson_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Lesson_Lesson1`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `Lesson` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


create view user_role_view
as select u.username, r.name as role_name
from User u JOIN User_Role ur on u.id = ur.user_id
JOIN Role r on ur.role_id = r.id;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
