-- liquibase formatted sql

-- changeset Liquibase:8
-- comment populate table country
INSERT INTO `country` (`id`, `name`, `initials`) VALUES (1, 'Brasil', 'BR');