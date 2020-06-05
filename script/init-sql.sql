DROP DATABASE IF EXISTS `test_trace_db`;
CREATE DATABASE `test_trace_db`;
USE `test_trace_db`;

DROP TABLE IF EXISTS `message_record`;
CREATE TABLE `message_record` (
   `id` BIGINT PRIMARY KEY auto_increment,
   `message_key` VARCHAR(255) DEFAULT null,
   `message_value` VARCHAR(255) DEFAULT null,
   `update_time` datetime,
   INDEX `message_idx`(`message_key`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;