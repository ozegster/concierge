CREATE TABLE `concierge`.`bed_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `concierge`.`bed_type` VALUES (1, 'One double bed');
INSERT INTO `concierge`.`bed_type` VALUES (2, 'One single bed');
INSERT INTO `concierge`.`bed_type` VALUES (3, 'Two single beds');
INSERT INTO `concierge`.`bed_type` VALUES (4, 'One queen bed');
INSERT INTO `concierge`.`bed_type` VALUES (5, 'One king bed');

CREATE TABLE `concierge`.`feature` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `concierge`.`feature` VALUES (1,'Air Condition');
INSERT INTO `concierge`.`feature` VALUES (2,'Free WiFi');
INSERT INTO `concierge`.`feature` VALUES (3,'MiniBar');
INSERT INTO `concierge`.`feature` VALUES (4,'Phone');

CREATE TABLE `concierge`.`room_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_people` tinyint(4) NOT NULL,
  `number_of_kids` tinyint(4) NOT NULL,
  `size` tinyint(4) NOT NULL,
  `image` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `bed_id` int(11) NOT NULL,
  UNIQUE (name),
  PRIMARY KEY (`id`),
  FOREIGN KEY (`bed_id`) REFERENCES `concierge`.`bed_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `concierge`.`room_type_feature` (
  `room_type_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL,
  FOREIGN KEY (`room_type_id`) REFERENCES `concierge`.`room_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  FOREIGN KEY (`feature_id`) REFERENCES `concierge`.`feature` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;