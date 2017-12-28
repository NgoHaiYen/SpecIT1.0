-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: calllogit
-- ------------------------------------------------------
-- Server version	5.7.20-0ubuntu0.16.04.1

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` int(20) NOT NULL,
  `address` varchar(200) NOT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  `role` int(11) NOT NULL,
  `itteam` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `itteam` (`itteam`),
  KEY `role` (`role`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`itteam`) REFERENCES `itteam` (`id`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`role`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23445 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Xuan',1234,'HaNoi','Xuan','123456',1,2),(2,'Admin',1234,'Hanoi','admin1','123456',2,1),(3,'admin2',125,'Hanoi','admin2','123456',3,2),(123,'Admin',1234,'Hanoi','admin3','123456',3,2),(23444,'admin2',125,'Hanoi','admin4','123456',2,1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itteam`
--

DROP TABLE IF EXISTS `itteam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itteam` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itteam`
--

LOCK TABLES `itteam` WRITE;
/*!40000 ALTER TABLE `itteam` DISABLE KEYS */;
INSERT INTO `itteam` VALUES (1,'IT Hà Nội'),(2,'IT Đà Nẵng');
/*!40000 ALTER TABLE `itteam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `priorities`
--

DROP TABLE IF EXISTS `priorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priorities`
--

LOCK TABLES `priorities` WRITE;
/*!40000 ALTER TABLE `priorities` DISABLE KEYS */;
INSERT INTO `priorities` VALUES (1,'Khẩn cấp'),(2,'Cao'),(3,'Bình thường'),(4,'Thấp');
/*!40000 ALTER TABLE `priorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `content` text,
  `created_by` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `prioriry` tinyint(1) NOT NULL,
  `deadlline` datetime NOT NULL,
  `assigned_to` int(11) DEFAULT NULL,
  `ratting` tinyint(1) DEFAULT NULL,
  `team_id` int(10) NOT NULL,
  `resolved_at` datetime DEFAULT NULL,
  `closed_at` datetime NOT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tickets_employee` (`created_by`),
  KEY `fk_ticket_employees` (`assigned_to`),
  KEY `fk_tickets_team` (`team_id`),
  CONSTRAINT `fk_ticket_employees` FOREIGN KEY (`assigned_to`) REFERENCES `employees` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_employee` FOREIGN KEY (`created_by`) REFERENCES `employees` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_team` FOREIGN KEY (`team_id`) REFERENCES `itteam` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Member','all member'),(2,'Sub-Lead','medium, can manage some member'),(3,'Leader','the biggest, can manage members and the staff of the company');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_attributes`
--

DROP TABLE IF EXISTS `ticket_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_attributes` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `prioriry` varchar(255) DEFAULT NULL,
  `ratting` varchar(255) DEFAULT NULL,
  `reopened` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_attributes`
--

LOCK TABLES `ticket_attributes` WRITE;
/*!40000 ALTER TABLE `ticket_attributes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_images`
--

DROP TABLE IF EXISTS `ticket_images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_images` (
  `id_ticket` int(10) NOT NULL,
  `url_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_images`
--

LOCK TABLES `ticket_images` WRITE;
/*!40000 ALTER TABLE `ticket_images` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_reads`
--

DROP TABLE IF EXISTS `ticket_reads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_reads` (
  `ticket_id` int(10) NOT NULL,
  `reader_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticket_id`,`reader_id`),
  KEY `fk_ticket_read_employee` (`reader_id`),
  CONSTRAINT `fk_ticket_read_employee` FOREIGN KEY (`reader_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_reads`
--

LOCK TABLES `ticket_reads` WRITE;
/*!40000 ALTER TABLE `ticket_reads` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_reads` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket_threads`
--

DROP TABLE IF EXISTS `ticket_threads`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ticket_threads` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(10) NOT NULL,
  `employee_id` int(10) NOT NULL,
  `content` text,
  `type` tinyint(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_tickets_ticket_thread` (`ticket_id`),
  KEY `fk_ticket_thread_employee` (`employee_id`),
  CONSTRAINT `fk_ticket_thread_employee` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_ticket_thread` FOREIGN KEY (`ticket_id`) REFERENCES `request` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket_threads`
--

LOCK TABLES `ticket_threads` WRITE;
/*!40000 ALTER TABLE `ticket_threads` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket_threads` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-28 16:47:06
