CREATE TABLE `concierge`.`room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(4) NOT NULL,
  `floor_number` tinyint(4) NOT NULL,
  `room_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`room_type_id`) REFERENCES `concierge`.`room_type` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;