-- CREATE DATABASE "test" ----------------------------------
-- DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test`
  CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `test`;
-- ---------------------------------------------------------


-- CREATE TABLE "account" ----------------------------------
CREATE TABLE IF NOT EXISTS `account` (
  `account_id`               BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `last_password_reset_date` DATETIME     NULL,
  `password`                 VARCHAR(255) NOT NULL,
  `username`                 VARCHAR(255) NOT NULL,
  PRIMARY KEY (`account_id`)
)
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ENGINE = InnoDB;
-- ---------------------------------------------------------


-- CREATE TABLE "account_role" -----------------------------
CREATE TABLE IF NOT EXISTS `account_role` (
  `account_role_id` BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `role`            VARCHAR(255) NOT NULL,
  `account_id`      BIGINT(20)   NOT NULL,
  PRIMARY KEY (`account_role_id`),
  FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
    ON DELETE CASCADE
)
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ENGINE = InnoDB;
-- ---------------------------------------------------------


-- CREATE TABLE "bookmark" ---------------------------------
CREATE TABLE IF NOT EXISTS `bookmark` (
  `bookmark_id` BIGINT(20)   NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL,
  `uri`         VARCHAR(255) NOT NULL,
  `account_id`  BIGINT(20)   NOT NULL,
  PRIMARY KEY (`bookmark_id`),
  FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`)
    ON DELETE CASCADE
)
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ENGINE = InnoDB;
-- ---------------------------------------------------------
