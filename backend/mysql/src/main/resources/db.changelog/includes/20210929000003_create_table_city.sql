-- liquibase formatted sql

-- changeset Liquibase:3
-- comment create table city
CREATE TABLE IF NOT EXISTS `city` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `state_id` BIGINT NOT NULL,

    PRIMARY KEY (`id`)
);

ALTER TABLE `city` ADD CONSTRAINT `fk_city_state` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`);