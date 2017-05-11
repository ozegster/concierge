CREATE TABLE `concierge`.`facility_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `facility_type` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `concierge`.`facility_type` VALUES (1,'Restaurants');
INSERT INTO `concierge`.`facility_type` VALUES (2,'Bars');
INSERT INTO `concierge`.`facility_type` VALUES (3,'Recreations');
INSERT INTO `concierge`.`facility_type` VALUES (4,'Wellness');


CREATE TABLE `concierge`.`facility` (
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