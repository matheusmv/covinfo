INSERT INTO `country` (`id`, `name`, `initials`) VALUES (1, 'Brasil', 'BR');

INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (1, 'Acre', 'AC', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (2, 'Alagoas', 'AL', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (3, 'Amazonas', 'AM', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (4, 'Amapá', 'AP', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (5, 'Bahia', 'BA', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (6, 'Ceará', 'CE', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (7, 'Distrito Federal', 'DF', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (8, 'Espírito Santo', 'ES', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (9, 'Goiás', 'GO', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (10, 'Maranhão', 'MA', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (11, 'Minas Gerais', 'MG', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (12, 'Mato Grosso do Sul', 'MS', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (13, 'Mato Grosso', 'MT', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (14, 'Pará', 'PA', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (15, 'Paraíba', 'PB', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (16, 'Pernambuco', 'PE', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (17, 'Piauí', 'PI', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (18, 'Paraná', 'PR', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (19, 'Rio de Janeiro', 'RJ', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (20, 'Rio Grande do Norte', 'RN', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (21, 'Rondônia', 'RO', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (22, 'Roraima', 'RR', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (23, 'Rio Grande do Sul', 'RS', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (24, 'Santa Catarina', 'SC', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (25, 'Sergipe', 'SE', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (26, 'São Paulo', 'SP', 1);
INSERT INTO `state` (`id`, `name`, `initials`, `country_id`) VALUES (27, 'Tocantins', 'TO', 1);

INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (1, 'Rio Branco', 1);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (2, 'Maceió', 2);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (3, 'Manaus', 3);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (4, 'Macapá', 4);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (5, 'Salvador', 5);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (6, 'Fortaleza', 6);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (7, 'Brasília', 7);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (8, 'Vitória', 8);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (9, 'Goiânia', 9);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (10, 'São Luís', 10);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (11, 'Belo Horizonte', 11);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (12, 'Campo Grande', 12);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (13, 'Cuiabá', 13);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (14, 'Belém', 14);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (15, 'João Pessoa', 15);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (16, 'Recife', 16);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (17, 'Teresina', 17);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (18, 'Curitiba', 18);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (19, 'Rio de Janeiro', 19);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (20, 'Natal', 20);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (21, 'Porto Velho', 21);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (22, 'Boa Vista', 22);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (23, 'Porto Alegre', 23);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (24, 'Florianópolis', 24);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (25, 'Aracaju', 25);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (26, 'São Paulo', 26);
INSERT INTO `city` (`id`, `name`, `state_id`) VALUES (27, 'Palmas', 27);

-- password = password
INSERT INTO `user` (`id`, `full_name`, `email`, `password`, `locked`, `enabled`, `created_at`, `role`) VALUES (1, 'Pedro Alcantara', 'pedro@email.com', '$2a$10$6SBtKRzkY3lLUzfIVy8HgOOaMo3CQ4d2bj1prPRfD2J6GKF9M1QcG', false, true, NOW(), 'ADMIN');
-- password = password
INSERT INTO `user` (`id`, `full_name`, `email`, `password`, `locked`, `enabled`, `created_at`, `role`) VALUES (2, 'José Almeida', 'jose@email.com', '$2a$10$fkC188l3Ueac1TeDwyrCYeVT47Q2OCX317eRcqQYYfjeb4C49z98u', false, true, NOW(), 'USER');
-- password = password
INSERT INTO `user` (`id`, `full_name`, `email`, `password`, `locked`, `enabled`, `created_at`, `role`) VALUES (3, 'Maria Alves', 'maria@email.com', '$2a$10$gCIDrDOhhW3DPj6Zt1JBCeJ8Ty2Sws3NtSI.C5bDVUHeJg6bFAKri', false, true, NOW(), 'CONTENT_MANAGER');

INSERT INTO `address` (`id`, `city_id`, `zip`, `neighborhood`, `street`) VALUES (1, 26, '04852-410', 'Jardim Jaú (Zona Sul)', 'Rua 3');
INSERT INTO `address` (`id`, `city_id`, `zip`, `neighborhood`, `street`) VALUES (2, 26, '08344-010', 'Jardim da Conquista (Zona Leste)', 'Travessa A Banda');
INSERT INTO `address` (`id`, `city_id`, `zip`, `neighborhood`, `street`) VALUES (3, 15, '58042-959', 'Tambauzinho', 'Rua Abdias Gomes de Almeida');
