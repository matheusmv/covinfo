-- liquibase formatted sql

-- changeset Liquibase:6
-- comment create table confirmation_token
CREATE TABLE IF NOT EXISTS `confirmation_token` (
    `id` BIGINT NOT NULL,
    `token` CHAR(50) NOT NULL UNIQUE,
    `created_at` TIMESTAMP NOT NULL,
    `expires_at` TIMESTAMP NOT NULL,
    `confirmed_at` TIMESTAMP,

    PRIMARY KEY (`id`)
);

ALTER TABLE `confirmation_token` ADD CONSTRAINT `fk_confirmation_token_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`);