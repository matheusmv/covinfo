-- liquibase formatted sql

-- changeset Liquibase:1
-- comment create table country
CREATE TABLE IF NOT EXISTS `country` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `initials` VARCHAR(5) NOT NULL,

    PRIMARY KEY (`id`)
);