CREATE TABLE `concierge`.`room_booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `last_name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` VARCHAR(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `check_in` DATE COLLATE utf8mb4_unicode_ci NOT NULL,
  `check_out` DATE COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_people` tinyint(4) NOT NULL,
  `number_of_kids` tinyint(4) NOT NULL,
  `password` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_room_booking_room`
    FOREIGN KEY (`room_id`)
    REFERENCES `concierge`.`room` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

