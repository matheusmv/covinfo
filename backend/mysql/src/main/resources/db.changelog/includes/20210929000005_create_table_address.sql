-- liquibase formatted sql

-- changeset Liquibase:5
-- comment create table address
CREATE TABLE IF NOT EXISTS `address` (
    `id` BIGINT NOT NULL,
    `city_id` BIGINT NOT NULL,
    `zip` VARCHAR(10) NOT NULL,
    `neighborhood` VARCHAR(50) NOT NULL,
    `street` VARCHAR(50) NOT NULL,

    PRIMARY KEY (`id`)
);

ALTER TABLE `address` ADD CONSTRAINT `fk_address_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`);
ALTER TABLE `address` ADD CONSTRAINT `fk_address_city` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);