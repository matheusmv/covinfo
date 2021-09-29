-- liquibase formatted sql

-- changeset Liquibase:2
-- comment create table state
CREATE TABLE IF NOT EXISTS `state` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `initials` VARCHAR(5) NOT NULL,
    `country_id` BIGINT NOT NULL,

    PRIMARY KEY (`id`)
);

ALTER TABLE `state` ADD CONSTRAINT `fk_state_country` FOREIGN KEY (`country_id`) REFERENCES `country` (`id`);