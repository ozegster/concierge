DROP TABLE IF EXISTS `concierge`.`feature`;

CREATE TABLE `concierge`.`feature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `concierge`.`feature` VALUES (1,'Air Condition');
INSERT INTO `concierge`.`feature` VALUES (2,'Free WiFi');
INSERT INTO `concierge`.`feature` VALUES (3,'MiniBar');
INSERT INTO `concierge`.`feature` VALUES (4,'Phone');