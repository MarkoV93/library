-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.11-log

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'wiev for give to hend'),(2,'wiev for give to reading room'),(3,'give in reading room'),(4,'give to hend'),(5,'refused'),(6,'returned');
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answerua`
--

DROP TABLE IF EXISTS `answerua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answerua` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answerua`
--

LOCK TABLES `answerua` WRITE;
/*!40000 ALTER TABLE `answerua` DISABLE KEYS */;
INSERT INTO `answerua` VALUES (1,'Розгляд про видання на руки'),(2,'Розгляд про видання в читальну залу'),(3,'видано в читальну залу'),(4,'видано на руки'),(5,'вiдмовлено'),(6,'повернено');
/*!40000 ALTER TABLE `answerua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `qty` int(4) DEFAULT NULL,
  `genre_id` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `genre_id` (`genre_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`genre_id`) REFERENCES `genreua` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'kult','Lubko Deresh',3,8),(2,'sidchartkcha','German Gesse',1,11),(3,'Robinzon Kruzo','Daniel Deffo',5,14),(4,'woman','nobody',8,2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Fairy Tale'),(2,'Drama'),(3,'Fable'),(4,'Fantasy'),(5,'Fiction'),(6,'Fiction in Verse '),(7,'Historical Fiction '),(8,'Horror '),(9,'Humor '),(10,'Mystery'),(11,'Mythology'),(12,'Poetry'),(13,'Science Fiction '),(14,'adventure');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genreua`
--

DROP TABLE IF EXISTS `genreua`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genreua` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `genre` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genreua`
--

LOCK TABLES `genreua` WRITE;
/*!40000 ALTER TABLE `genreua` DISABLE KEYS */;
INSERT INTO `genreua` VALUES (1,'казка'),(2,'драма'),(3,'байка'),(4,'фантастика'),(5,'вигадка'),(6,'лiтература в вiршах'),(7,'iсторична проза'),(8,'жах'),(9,'юмор'),(10,'таємниця'),(11,'мiфологiя'),(12,'поезiя'),(13,'наукова фантастика'),(14,'пригода');
/*!40000 ALTER TABLE `genreua` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(4) DEFAULT NULL,
  `book_id` int(4) DEFAULT NULL,
  `answer` int(1) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `book_id` (`book_id`),
  KEY `answer` (`answer`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `reserve_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  CONSTRAINT `reserve_ibfk_3` FOREIGN KEY (`answer`) REFERENCES `answer` (`id`),
  CONSTRAINT `reserve_ibfk_4` FOREIGN KEY (`answer`) REFERENCES `answerua` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,1,1,1,'30.07.2016 11:58:32');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `login` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Marko','Vintoniuk','admin','admin',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-30 12:28:36
