DROP TABLE IF EXISTS `concierge`.`bed_type`;
CREATE TABLE `concierge`.`bed_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `concierge`.`bed_type` VALUES (1, "One double bed");
INSERT INTO `concierge`.`bed_type` VALUES (2, "One single bed");
INSERT INTO `concierge`.`bed_type` VALUES (3, "Two single beds");
INSERT INTO `concierge`.`bed_type` VALUES (4, "One queen bed");
INSERT INTO `concierge`.`bed_type` VALUES (5, "One king bed");
