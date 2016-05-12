-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: se2project
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `actions`
--

DROP TABLE IF EXISTS `actions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actions` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `actionid` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `userid` (`userid`),
  CONSTRAINT `actions_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actions`
--

LOCK TABLES `actions` WRITE;
/*!40000 ALTER TABLE `actions` DISABLE KEYS */;
INSERT INTO `actions` VALUES (1,1,1,'make action'),(3,4,35,'Save Place 35'),(7,1,9,'3Make checkin9'),(8,1,1,'4Make like  to checkin num 1'),(9,1,2,'5Make Comment to 1'),(10,1,3,'5Make Comment to checkin 1'),(11,2,8,'1 Add new place8'),(12,2,36,'2Save Place 36');
/*!40000 ALTER TABLE `actions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkin`
--

DROP TABLE IF EXISTS `checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `place_id` int(11) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `like_num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkin`
--

LOCK TABLES `checkin` WRITE;
/*!40000 ALTER TABLE `checkin` DISABLE KEYS */;
INSERT INTO `checkin` VALUES (1,5,1,'welcome new study year',0),(2,8,1,'back to school',0),(3,0,0,NULL,0),(4,18,3,'khalifa',0),(5,1,3,'wwww',0),(6,1,4,'wwww',0),(7,18,4,'wwww',0),(8,18,5,'wwww',0),(9,1,2,'nearby',0);
/*!40000 ALTER TABLE `checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_checkin`
--

DROP TABLE IF EXISTS `comment_checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `checkinid` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `comment` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `checkinid` (`checkinid`),
  KEY `userid` (`userid`),
  CONSTRAINT `comment_checkin_ibfk_1` FOREIGN KEY (`checkinid`) REFERENCES `checkin` (`id`),
  CONSTRAINT `comment_checkin_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_checkin`
--

LOCK TABLES `comment_checkin` WRITE;
/*!40000 ALTER TABLE `comment_checkin` DISABLE KEYS */;
INSERT INTO `comment_checkin` VALUES (1,1,1,'verygood'),(2,1,1,'very good'),(3,1,1,'very nice');
/*!40000 ALTER TABLE `comment_checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `follow` (
  `follower` varchar(255) NOT NULL,
  `followed` varchar(255) NOT NULL,
  PRIMARY KEY (`follower`,`followed`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES ('m.samir','mhmdsamir92@gmail.com'),('mhmdsamir1@gmail.com','mhmdsamir92@gmail.com'),('mhmdsamir1@gmail.com','youtube'),('mhmdsamir91@gmail.com','youtube'),('mhmdsamir@gmail.com','youtube'),('youtube',''),('youtube','m.samir'),('youtube','mhmdsamir92@gmail.com'),('youtube','Ramadan');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_checkin`
--

DROP TABLE IF EXISTS `like_checkin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_checkin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `checkinid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `checkinid` (`checkinid`),
  CONSTRAINT `like_checkin_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `like_checkin_ibfk_2` FOREIGN KEY (`checkinid`) REFERENCES `checkin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_checkin`
--

LOCK TABLES `like_checkin` WRITE;
/*!40000 ALTER TABLE `like_checkin` DISABLE KEYS */;
INSERT INTO `like_checkin` VALUES (1,1,1);
/*!40000 ALTER TABLE `like_checkin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification` (
  `nid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `checkinid` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`nid`),
  KEY `userid` (`userid`),
  KEY `checkinid` (`checkinid`),
  CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `users` (`id`),
  CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`checkinid`) REFERENCES `checkin` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,1,2,'has comment'),(2,1,1,'make comment on post'),(3,1,1,'Check in num  1 has Comment');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `places` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `description` text NOT NULL,
  `lat` double NOT NULL,
  `long` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
INSERT INTO `places` VALUES (1,'Cairo','is very beuitful',23,25),(2,'cairo','verynice',12.5,12),(3,'cairoFci','very good',12.5,12),(4,'Giza comp',' international company',20.5,5.2),(6,'Cairo tower','very good',2.55,2.556),(7,'tower','high',225.5,279.55),(8,'FCICAIRo','Very big',2.55,255.5);
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saveplaces`
--

DROP TABLE IF EXISTS `saveplaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `saveplaces` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `placeid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `placeid` (`placeid`),
  KEY `userid` (`userid`),
  CONSTRAINT `saveplaces_ibfk_1` FOREIGN KEY (`placeid`) REFERENCES `places` (`id`),
  CONSTRAINT `saveplaces_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saveplaces`
--

LOCK TABLES `saveplaces` WRITE;
/*!40000 ALTER TABLE `saveplaces` DISABLE KEYS */;
INSERT INTO `saveplaces` VALUES (2,2,2),(3,2,2),(4,2,2),(5,2,2),(6,2,2),(7,2,2),(8,2,2),(9,2,2),(10,2,2),(11,2,2),(12,2,2),(13,2,2),(14,2,2),(15,2,2),(16,2,1),(17,1,2),(18,1,2),(19,1,2),(20,1,2),(21,1,2),(22,1,2),(23,1,2),(24,1,2),(25,1,2),(26,1,2),(27,1,2),(28,1,2),(29,1,2),(30,1,2),(31,1,2),(32,1,2),(33,1,2),(34,1,2),(35,4,4),(36,2,8);
/*!40000 ALTER TABLE `saveplaces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(300) NOT NULL,
  `email` varchar(500) NOT NULL,
  `password` varchar(300) NOT NULL,
  `lat` double DEFAULT NULL,
  `long` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `last_place_id` (`lat`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'mohamed','mhmdsamir@gmail.com','123',30.0310036,31.2127736),(2,'mohamed','mhmdsamir1@gmail.com','123',0,NULL),(3,'mohamed','mhmdsamir91@gmail.com','123456789',NULL,NULL),(4,'mohamed','mhmdsamir92@gmail.com','123456789',NULL,NULL),(5,'mohamed','m.samir','123456789',30,31),(6,'Omar','omar','123',NULL,NULL),(8,'Ramadan Anaconda','rmadanfci20130197@gmail.com','01276038376',30.145361400000002,31.3188648),(9,'Mohamed Khalid','khalifa@gmail.com','01276038376',NULL,NULL),(10,'facebook','facebook@gmail.com','1111',NULL,NULL),(18,'Ramadan','Ramadan','123',30.145349699999997,31.3188417),(19,'yoytube','youtube','1111',30.145336800000003,31.318835099999994);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-12 22:37:13
