DROP TABLE IF EXISTS `concierge`.`room_feature`;
CREATE TABLE `concierge`.`room_feature` (
  `room_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL,
  FOREIGN KEY (`room_id`) REFERENCES `concierge`.`room` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  FOREIGN KEY (`feature_id`) REFERENCES `concierge`.`feature` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
