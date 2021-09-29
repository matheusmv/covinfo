-- liquibase formatted sql

-- changeset Liquibase:7
-- comment create table password_token
CREATE TABLE IF NOT EXISTS `password_token` (
    `id` BIGINT NOT NULL,
    `token` CHAR(50) NOT NULL UNIQUE,
    `created_at` TIMESTAMP NOT NULL,
    `expires_at` TIMESTAMP NOT NULL,

    PRIMARY KEY (`id`)
);

ALTER TABLE `password_token` ADD CONSTRAINT `fk_password_token_user` FOREIGN KEY (`id`) REFERENCES `user` (`id`);