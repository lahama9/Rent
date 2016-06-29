-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: rentaldb
-- ------------------------------------------------------
-- Server version	5.6.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL,
  `inUse` tinyint(1) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('Al','123456','landlord',1),('Henry@uw.edu','123456','tenant',1),('Jane','123456','landlord',1),('Mickey','123456','tenant',1),('Minnie','123456','tenant',1),('Nick','123456','landlord',1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `landlord`
--

DROP TABLE IF EXISTS `landlord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landlord`
--

LOCK TABLES `landlord` WRITE;
/*!40000 ALTER TABLE `landlord` DISABLE KEYS */;
INSERT INTO `landlord` VALUES (1,'Jane','Goodall','2067876354','Jane@uw.edu',-1,'Jane'),(2,'Nick','Tesla','2063321456','Nick@uw.edu',-1,'Nick'),(3,'Al ','Einstein','2536789876','Al@uw.edu',-1,'Al');
/*!40000 ALTER TABLE `landlord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `property`
--

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;
INSERT INTO `property` VALUES (1,'123 Main St','Seattle','WA','98108','apartment',600,1),(2,'4562 California Ave','Seattle','WA','98136','house',1000,1),(3,'6102 Beach Dr','Seattle','WA','98136','house',1500,2),(4,'632 3rd Ave','Seattle','WA','98108','apartment',700,2),(5,'4624 Commerce Ave','Tacoma','WA','98402','apartment',800,3),(6,'1430 1st Ave','Tacoma','WA','98402','house',1200,3),(7,'1621 L Street','Tacoma','WA','98402','apartment',500,3);
/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,NULL,'Tacoma','WA','98402','Looking for a 1 bedroom or to share a house with a couple other students.  Need parking. ','2015-08-19',1),(2,NULL,'Seattle','WA','98108','Need to move at the end of September.  Looking for a studio.  ','2015-08-19',2),(3,NULL,'Tacoma','WA','98407','Need housing ASAP.','2015-08-19',3);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (1,'Mickey','Mouse','3178972323','Mickey@uw.edu',-1,'Mickey'),(2,'Minnie ','Mouse','3651219809','Minnie@uw.edu',-1,'Minnie'),(3,'Henry','Hanson','2534667654','Henry@uw.edu',-1,'Henry@uw.edu');
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-19 11:13:15
