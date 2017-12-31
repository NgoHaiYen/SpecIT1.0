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
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `Branch_id` int(10) NOT NULL AUTO_INCREMENT,
  `branch_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `leader_id` int(11) NOT NULL,
  PRIMARY KEY (`Branch_id`),
  KEY `Branch_id` (`Branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (1,'IT Hà Nội',1),(2,'IT Đà Nẵng',2);
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `request_id` int(10) NOT NULL,
  `employee_id` int(10) NOT NULL,
  `content` text,
  `type` tinyint(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `fk_tickets_ticket_thread` (`request_id`),
  KEY `fk_ticket_thread_employee` (`employee_id`),
  KEY `comment_id` (`comment_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `employee_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `phone` int(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `Branch_id` int(11) NOT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `team_id` (`team_id`),
  KEY `role` (`role_id`),
  KEY `employee_id` (`employee_id`),
  KEY `role_id` (`role_id`),
  KEY `team_id_2` (`team_id`),
  KEY `Branch_id` (`Branch_id`),
  CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`team_id`) REFERENCES `branch` (`Branch_id`),
  CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23445 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Xuan',1234,'HaNoi','Xuan','827ccb0eea8a706c4c34a16891f84e7b',1,2,1),(2,'Admin',1234,'Hanoi','admin1','e10adc3949ba59abbe56e057f20f883e',2,1,2),(3,'admin2',125,'Hanoi','admin2','e10adc3949ba59abbe56e057f20f883e',3,2,1),(4,'Yến',1235454,'Hưng Yên','yen','e10adc3949ba59abbe56e057f20f883e',3,1,1),(123,'Admin',1234,'Hanoi','admin3','e10adc3949ba59abbe56e057f20f883e',3,2,2),(23444,'admin2',125,'Hanoi','admin4','e10adc3949ba59abbe56e057f20f883e',2,1,1);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `request_id` int(10) NOT NULL,
  `url_image` varchar(255) NOT NULL,
  KEY `request_id` (`request_id`),
  CONSTRAINT `image_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `isread`
--

DROP TABLE IF EXISTS `isread`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `isread` (
  `request_id` int(10) NOT NULL,
  `reader_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`request_id`,`reader_id`),
  KEY `fk_ticket_read_employee` (`reader_id`),
  CONSTRAINT `isread_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `isread_ibfk_2` FOREIGN KEY (`reader_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `isread`
--

LOCK TABLES `isread` WRITE;
/*!40000 ALTER TABLE `isread` DISABLE KEYS */;
/*!40000 ALTER TABLE `isread` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `priorities`
--

DROP TABLE IF EXISTS `priorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priorities` (
  `priority_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`priority_id`)
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
-- Table structure for table `relater`
--

DROP TABLE IF EXISTS `relater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `relater` (
  `request_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `created_at` date NOT NULL,
  KEY `request_id` (`request_id`),
  KEY `employee_id` (`employee_id`),
  CONSTRAINT `relater_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relater`
--

LOCK TABLES `relater` WRITE;
/*!40000 ALTER TABLE `relater` DISABLE KEYS */;
/*!40000 ALTER TABLE `relater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `request_id` int(10) NOT NULL AUTO_INCREMENT,
  `subject` varchar(255) NOT NULL,
  `content` text,
  `created_by` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `priority` tinyint(1) NOT NULL,
  `deadline` date NOT NULL,
  `assigned_to` int(11) DEFAULT NULL,
  `rating` tinyint(1) DEFAULT NULL,
  `team_id` int(10) NOT NULL,
  `branch_id` int(10) NOT NULL,
  `resolved_at` datetime DEFAULT NULL,
  `closed_at` datetime DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `deleted_at` datetime DEFAULT NULL,
  PRIMARY KEY (`request_id`),
  KEY `fk_tickets_employee` (`created_by`),
  KEY `fk_ticket_employees` (`assigned_to`),
  KEY `fk_tickets_team` (`team_id`),
  KEY `request_id` (`request_id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`assigned_to`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (23,'Sửa màn hình','Màn hình bị vỡ',4,1,1,'2018-01-02',NULL,NULL,2,0,NULL,NULL,'2017-12-31 18:21:06',NULL,NULL),(24,'Sửa màn hình','Màn hình bị hỏng',4,1,1,'2017-12-25',NULL,NULL,1,0,NULL,NULL,'2017-12-31 18:25:24',NULL,NULL);
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
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
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
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
  `status_id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) COLLATE utf8_vietnamese_ci NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'New'),(2,'Inprogress'),(3,'Resolved'),(4,'Feedback'),(5,'Closed'),(6,'Cancelled');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `team_id` int(11) NOT NULL AUTO_INCREMENT,
  `team_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `teamleader_id` int(11) NOT NULL,
  `Branch_id` int(11) NOT NULL,
  PRIMARY KEY (`team_id`),
  KEY `team_id` (`team_id`),
  KEY `Branch_id` (`Branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,'Team Hà Nội',4,1),(2,'Team Đà Nẵng',123,2);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-31 19:16:41
