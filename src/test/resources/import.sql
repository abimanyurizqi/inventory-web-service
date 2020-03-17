
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (1,'2020-03-04 07:32:44.591244',NULL,'Beras');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (2,'2020-03-04 07:32:44.614264',NULL,'Gula');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (3,'2020-03-04 07:32:44.621245',NULL,'Garam');
INSERT INTO `item` (`id`, `created_date`, `modified_date`, `name`) VALUES (4,'2020-03-04 07:32:44.626484',NULL,'Susu');

INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`) VALUES (1,'2020-03-04 07:32:44.633580',NULL,'kg');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`) VALUES (2,'2020-03-04 07:32:44.639674',NULL,'g');
INSERT INTO `unit` (`id`, `created_date`, `modified_date`, `unit_name`) VALUES (3,'2020-03-04 07:32:44.645228',NULL,'Liter');

INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (1,'2020-03-04 07:32:44.649539',NULL,200,1,1);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (2,'2020-03-04 07:32:44.682498',NULL,6200,2,2);
INSERT INTO `stock` (`id`, `created_date`, `modified_date`, `quantity`, `item_id`, `unit_id`) VALUES (3,'2020-03-04 07:32:44.691086',NULL,400,4,3);

