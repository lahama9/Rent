/* Account Table */
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `inUse` tinyint(1) NOT NULL,
  PRIMARY KEY (`name`)
)

/* Account Data */
INSERT INTO `account` VALUES ('Al','123456','landlord',1),('Henry@uw.edu','123456','tenant',1),('Jane','123456','landlord',1),('Mickey','123456','tenant',1),('Minnie','123456','tenant',1),('Nick','123456','landlord',1);

/* Landlord Table */
DROP TABLE IF EXISTS `landlord`;
CREATE TABLE `landlord` (
  `landlordID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `phoneNum` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rating` tinyint(4) DEFAULT '-1',
  `accountName` varchar(20) NOT NULL,
  PRIMARY KEY (`landlordID`),
  KEY `accountName_idx` (`accountName`),
  CONSTRAINT `landlordAccount` FOREIGN KEY (`accountName`) REFERENCES `account` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

/* Landlord Values */
INSERT INTO `landlord` VALUES (1,'Jane','Goodall','2067876354','Jane@uw.edu',-1,'Jane'),(2,'Nick','Tesla','2063321456','Nick@uw.edu',-1,'Nick'),(3,'Al ','Einstein','2536789876','Al@uw.edu',-1,'Al');

/* Property Table */
DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `propertyID` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  `type` varchar(10) NOT NULL,
  `rate` double NOT NULL,
  `landlordID` int(11) NOT NULL,
  PRIMARY KEY (`propertyID`),
  KEY `landlordID_idx` (`landlordID`),
  CONSTRAINT `landlordID` FOREIGN KEY (`landlordID`) REFERENCES `landlord` (`landlordID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 

/* Property Values */
INSERT INTO `property` VALUES (1,'123 Main St','Seattle','WA','98108','apartment',600,1),(2,'4562 California Ave','Seattle','WA','98136','house',1000,1),(3,'6102 Beach Dr','Seattle','WA','98136','house',1500,2),(4,'632 3rd Ave','Seattle','WA','98108','apartment',700,2),(5,'4624 Commerce Ave','Tacoma','WA','98402','apartment',800,3),(6,'1430 1st Ave','Tacoma','WA','98402','house',1200,3),(7,'1621 L Street','Tacoma','WA','98402','apartment',500,3);

/* Request Table */
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `requestID` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(50) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zipcode` varchar(5) NOT NULL,
  `detail` varchar(200) DEFAULT NULL,
  `postDate` date NOT NULL,
  `tenantID` int(11) NOT NULL,
  PRIMARY KEY (`requestID`),
  KEY `tenantID_idx` (`tenantID`),
  CONSTRAINT `tenantID` FOREIGN KEY (`tenantID`) REFERENCES `tenant` (`tenantID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) 

/* Request Data */
INSERT INTO `request` VALUES (1,NULL,'Tacoma','WA','98402','Looking for a 1 bedroom or to share a house with a couple other students.  Need parking. ','2015-08-19',1),(2,NULL,'Seattle','WA','98108','Need to move at the end of September.  Looking for a studio.  ','2015-08-19',2),(3,NULL,'Tacoma','WA','98407','Need housing ASAP.','2015-08-19',3);

/* Tenant Table */
DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `tenantID` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `phoneNum` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `rating` tinyint(4) DEFAULT '-1',
  `accountName` varchar(20) NOT NULL,
  PRIMARY KEY (`tenantID`),
  UNIQUE KEY `accountName_UNIQUE` (`accountName`),
  KEY `accountName_idx` (`accountName`),
  CONSTRAINT `tenantAccount` FOREIGN KEY (`accountName`) REFERENCES `account` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION
)

/* Tenant Data */
INSERT INTO `tenant` VALUES (1,'Mickey','Mouse','3178972323','Mickey@uw.edu',-1,'Mickey'),(2,'Minnie ','Mouse','3651219809','Minnie@uw.edu',-1,'Minnie'),(3,'Henry','Hanson','2534667654','Henry@uw.edu',-1,'Henry@uw.edu');
