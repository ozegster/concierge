CREATE TABLE IF NOT EXISTS `concierge`.`hotel` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `rating` TINYINT(4) NOT NULL,
  `address` VARCHAR(128) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `zip` VARCHAR(5) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `city` VARCHAR(64) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `country_id` INT(11) NOT NULL,
  `phone` VARCHAR(45) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `fax` VARCHAR(45) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `email` VARCHAR(45) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `website` VARCHAR(45) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `description` VARCHAR(500) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `check_in` TIME(0) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `check_out` TIME(0) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hotel_country` (`country_id` ASC),
  CONSTRAINT `fk_hotel_country`
    FOREIGN KEY (`country_id`)
    REFERENCES `concierge`.`country` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;