DROP TABLE IF EXISTS `concierge`.`facility_type`;

CREATE TABLE IF NOT EXISTS `concierge`.`facility_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `facility_type` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

INSERT INTO `concierge`.`facility_type` VALUES (1,'Restaurant');
INSERT INTO `concierge`.`facility_type` VALUES (2,'Bar');
INSERT INTO `concierge`.`facility_type` VALUES (3,'Recreation');
INSERT INTO `concierge`.`facility_type` VALUES (4,'Wellnes');