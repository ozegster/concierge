DROP SCHEMA IF EXISTS `concierge`;
CREATE SCHEMA `concierge` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;


CREATE TABLE `concierge`.`country` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=247;

INSERT INTO `concierge`.`country` VALUES (1,'Andorra');
INSERT INTO `concierge`.`country` VALUES (2,'Albania');
INSERT INTO `concierge`.`country` VALUES (3,'Austria');
INSERT INTO `concierge`.`country` VALUES (4,'Ăland');
INSERT INTO `concierge`.`country` VALUES (5,'Bosnia and Herzegovina');
INSERT INTO `concierge`.`country` VALUES (6,'Belgium');
INSERT INTO `concierge`.`country` VALUES (7,'Bulgaria');
INSERT INTO `concierge`.`country` VALUES (8,'Belarus');
INSERT INTO `concierge`.`country` VALUES (9,'Switzerland');
INSERT INTO `concierge`.`country` VALUES (10,'Czech Republic');
INSERT INTO `concierge`.`country` VALUES (11,'Germany');
INSERT INTO `concierge`.`country` VALUES (12,'Denmark');
INSERT INTO `concierge`.`country` VALUES (13,'Estonia');
INSERT INTO `concierge`.`country` VALUES (14,'Spain');
INSERT INTO `concierge`.`country` VALUES (15,'Finland');
INSERT INTO `concierge`.`country` VALUES (16,'Faroe Islands');
INSERT INTO `concierge`.`country` VALUES (17,'France');
INSERT INTO `concierge`.`country` VALUES (18,'United Kingdom');
INSERT INTO `concierge`.`country` VALUES (19,'Guernsey');
INSERT INTO `concierge`.`country` VALUES (20,'Gibraltar');
INSERT INTO `concierge`.`country` VALUES (21,'Greece');
INSERT INTO `concierge`.`country` VALUES (22,'Croatia');
INSERT INTO `concierge`.`country` VALUES (23,'Hungary');
INSERT INTO `concierge`.`country` VALUES (24,'Ireland');
INSERT INTO `concierge`.`country` VALUES (25,'Isle of Man');
INSERT INTO `concierge`.`country` VALUES (26,'Iceland');
INSERT INTO `concierge`.`country` VALUES (27,'Italy');
INSERT INTO `concierge`.`country` VALUES (28,'Jersey');
INSERT INTO `concierge`.`country` VALUES (29,'Liechtenstein');
INSERT INTO `concierge`.`country` VALUES (30,'Lithuania');
INSERT INTO `concierge`.`country` VALUES (31,'Luxembourg');
INSERT INTO `concierge`.`country` VALUES (32,'Latvia');
INSERT INTO `concierge`.`country` VALUES (33,'Monaco');
INSERT INTO `concierge`.`country` VALUES (34,'Moldova');
INSERT INTO `concierge`.`country` VALUES (35,'Montenegro');
INSERT INTO `concierge`.`country` VALUES (36,'Macedonia');
INSERT INTO `concierge`.`country` VALUES (37,'Malta');
INSERT INTO `concierge`.`country` VALUES (38,'Netherlands');
INSERT INTO `concierge`.`country` VALUES (39,'Norway');
INSERT INTO `concierge`.`country` VALUES (40,'Poland');
INSERT INTO `concierge`.`country` VALUES (41,'Portugal');
INSERT INTO `concierge`.`country` VALUES (42,'Romania');
INSERT INTO `concierge`.`country` VALUES (43,'Serbia');
INSERT INTO `concierge`.`country` VALUES (44,'Russian Federation');
INSERT INTO `concierge`.`country` VALUES (45,'Sweden');
INSERT INTO `concierge`.`country` VALUES (46,'Slovenia');
INSERT INTO `concierge`.`country` VALUES (47,'Svalbard and Jan Mayen Islands');
INSERT INTO `concierge`.`country` VALUES (48,'Slovakia');
INSERT INTO `concierge`.`country` VALUES (49,'San Marino');
INSERT INTO `concierge`.`country` VALUES (50,'Ukraine');
INSERT INTO `concierge`.`country` VALUES (51,'Vatican City');


CREATE TABLE `concierge`.`hotel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `rating` tinyint(4) NOT NULL,
  `address` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `zip` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
  `city` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `country_id` int(11) NOT NULL,
  `phone` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fax` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `website` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_hotel_country` FOREIGN KEY (`country_id`) REFERENCES `concierge`.`country` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE IF NOT EXISTS `concierge`.`facility_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `facility_type` varchar(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;

INSERT INTO `concierge`.`facility_type` VALUES (1,'Restaurants');
INSERT INTO `concierge`.`facility_type` VALUES (2,'Bars');
INSERT INTO `concierge`.`facility_type` VALUES (3,'Recreations');
INSERT INTO `concierge`.`facility_type` VALUES (4,'Wellness');


CREATE TABLE IF NOT EXISTS `concierge`.`facility` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `facility_name` VARCHAR(80) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `description` VARCHAR(400) COLLATE 'utf8mb4_unicode_ci' NOT NULL,
  `facility_type_id` BLOB NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_facility_facility_type1_idx` (`facility_type_id` ASC),
  CONSTRAINT `fk_facility_facility_type1` FOREIGN KEY (`facility_type_id`) REFERENCES `concierge`.`facility_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE = InnoDB;