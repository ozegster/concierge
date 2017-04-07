DROP TABLE IF EXISTS `concierge`.`room_type_feature`;
CREATE TABLE `concierge`.`room_type_feature` (
  `room_type_id` int(11) NOT NULL,
  `feature_id` int(11) NOT NULL,
  FOREIGN KEY (`room_type_id`) REFERENCES `concierge`.`room_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  FOREIGN KEY (`feature_id`) REFERENCES `concierge`.`feature` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
