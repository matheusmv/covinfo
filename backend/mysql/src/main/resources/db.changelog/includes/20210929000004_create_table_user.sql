-- liquibase formatted sql

-- changeset Liquibase:4
-- comment create table user
CREATE TABLE IF NOT EXISTS `user` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `full_name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) UNIQUE NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `locked` BOOLEAN NOT NULL,
    `enabled` BOOLEAN NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `role` VARCHAR(20) NOT NULL,

    PRIMARY KEY (`id`)
);