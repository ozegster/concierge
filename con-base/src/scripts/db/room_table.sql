DROP TABLE IF EXISTS `concierge`.`room`;
CREATE TABLE `concierge`.`room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_people` tinyint(4) NOT NULL,
  `number_of_kids` tinyint(4) NOT NULL,
  `size` tinyint(4) NOT NULL,
  `image` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bed_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`bed_id`) REFERENCES `concierge`.`bed_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;