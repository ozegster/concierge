DROP TABLE IF EXISTS `concierge`.`facility`;

CREATE TABLE IF NOT EXISTS `concierge`.`facility` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `facility_name` VARCHAR(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `floor` SMALLINT(2) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` VARCHAR(400) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` VARCHAR(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `facility_type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facility_facility_type1_idx` (`facility_type_id` ASC),
  CONSTRAINT `fk_facility_facility_type1`
    FOREIGN KEY (`facility_type_id`)
    REFERENCES `concierge`.`facility_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;