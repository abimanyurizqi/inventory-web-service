
INSERT INTO `user` (`id`, `username`, `password`) VALUES (1, 'admin', '{bcrypt}$2b$10$ZyXdMneocKuinYNqQHnZPubwqRJl.o07svmFNiYUGjl/d77T2xa4S');
INSERT INTO `user` (`id`, `username`, `password`) VALUES (2, 'user', '{bcrypt}$2b$10$.Yht75Oo4eRo7xVxF7E34OQDEEvwogJj9W1d2MH2tc.jWls6NbjwW');

INSERT INTO `role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'USER');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO  `user_role` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);


INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (1,'2020-03-04 07:32:44.591244',NULL,'Beras');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (2,'2020-03-04 07:32:44.614264',NULL,'Gula');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (3,'2020-03-04 07:32:44.621245',NULL,'Garam');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (4,'2020-03-04 07:32:44.626484',NULL,'Susu');

INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`, `description`) VALUES (1,'2020-03-04 07:32:44.633580',NULL,'kg', 'kilogram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`, `description` ) VALUES (2,'2020-03-04 07:32:44.639674',NULL,'g', 'gram');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`, `description`) VALUES (3,'2020-03-04 07:32:44.645228',NULL,'L', 'Liter');

INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (1,'2020-03-04 07:32:44.649539',NULL,200,1,1);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (2,'2020-03-04 07:32:44.682498',NULL,6200,2,2);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (3,'2020-03-04 07:32:44.691086',NULL,400,4,3);

