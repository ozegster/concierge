CREATE SCHEMA `concierge` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

DROP TABLE IF EXISTS `concierge`.`continents`;
CREATE TABLE `concierge`.`countries` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=247;

INSERT INTO `concierge`.`countries` VALUES (1,'Andorra');
INSERT INTO `concierge`.`countries` VALUES (2,'Albania');
INSERT INTO `concierge`.`countries` VALUES (3,'Austria');
INSERT INTO `concierge`.`countries` VALUES (4,'Ăland');
INSERT INTO `concierge`.`countries` VALUES (5,'Bosnia and Herzegovina');
INSERT INTO `concierge`.`countries` VALUES (6,'Belgium');
INSERT INTO `concierge`.`countries` VALUES (7,'Bulgaria');
INSERT INTO `concierge`.`countries` VALUES (8,'Belarus');
INSERT INTO `concierge`.`countries` VALUES (9,'Switzerland');
INSERT INTO `concierge`.`countries` VALUES (10,'Czech Republic');
INSERT INTO `concierge`.`countries` VALUES (11,'Germany');
INSERT INTO `concierge`.`countries` VALUES (12,'Denmark');
INSERT INTO `concierge`.`countries` VALUES (13,'Estonia');
INSERT INTO `concierge`.`countries` VALUES (14,'Spain');
INSERT INTO `concierge`.`countries` VALUES (15,'Finland');
INSERT INTO `concierge`.`countries` VALUES (16,'Faroe Islands');
INSERT INTO `concierge`.`countries` VALUES (17,'France');
INSERT INTO `concierge`.`countries` VALUES (18,'United Kingdom');
INSERT INTO `concierge`.`countries` VALUES (19,'Guernsey');
INSERT INTO `concierge`.`countries` VALUES (20,'Gibraltar');
INSERT INTO `concierge`.`countries` VALUES (21,'Greece');
INSERT INTO `concierge`.`countries` VALUES (22,'Croatia');
INSERT INTO `concierge`.`countries` VALUES (23,'Hungary');
INSERT INTO `concierge`.`countries` VALUES (24,'Ireland');
INSERT INTO `concierge`.`countries` VALUES (25,'Isle of Man');
INSERT INTO `concierge`.`countries` VALUES (26,'Iceland');
INSERT INTO `concierge`.`countries` VALUES (27,'Italy');
INSERT INTO `concierge`.`countries` VALUES (28,'Jersey');
INSERT INTO `concierge`.`countries` VALUES (29,'Liechtenstein');
INSERT INTO `concierge`.`countries` VALUES (30,'Lithuania');
INSERT INTO `concierge`.`countries` VALUES (31,'Luxembourg');
INSERT INTO `concierge`.`countries` VALUES (32,'Latvia');
INSERT INTO `concierge`.`countries` VALUES (33,'Monaco');
INSERT INTO `concierge`.`countries` VALUES (34,'Moldova');
INSERT INTO `concierge`.`countries` VALUES (35,'Montenegro');
INSERT INTO `concierge`.`countries` VALUES (36,'Macedonia');
INSERT INTO `concierge`.`countries` VALUES (37,'Malta');
INSERT INTO `concierge`.`countries` VALUES (38,'Netherlands');
INSERT INTO `concierge`.`countries` VALUES (39,'Norway');
INSERT INTO `concierge`.`countries` VALUES (40,'Poland');
INSERT INTO `concierge`.`countries` VALUES (41,'Portugal');
INSERT INTO `concierge`.`countries` VALUES (42,'Romania');
INSERT INTO `concierge`.`countries` VALUES (43,'Serbia');
INSERT INTO `concierge`.`countries` VALUES (44,'Russian Federation');
INSERT INTO `concierge`.`countries` VALUES (45,'Sweden');
INSERT INTO `concierge`.`countries` VALUES (46,'Slovenia');
INSERT INTO `concierge`.`countries` VALUES (47,'Svalbard and Jan Mayen Islands');
INSERT INTO `concierge`.`countries` VALUES (48,'Slovakia');
INSERT INTO `concierge`.`countries` VALUES (49,'San Marino');
INSERT INTO `concierge`.`countries` VALUES (50,'Ukraine');
INSERT INTO `concierge`.`countries` VALUES (51,'Vatican City');


