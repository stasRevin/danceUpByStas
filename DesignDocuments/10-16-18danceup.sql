-- MySQL dump 10.13  Distrib 5.7.21, for macos10.13 (x86_64)
--
-- Host: localhost    Database: danceup
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `Dance`
--

DROP TABLE IF EXISTS `Dance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Dance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dance`
--

LOCK TABLES `Dance` WRITE;
/*!40000 ALTER TABLE `Dance` DISABLE KEYS */;
INSERT INTO `Dance` VALUES (2,'Rumba','romantic, slow, easy'),(3,'Waltz','Slow and elegant'),(4,'Cha Cha','fast and flirtatious');
/*!40000 ALTER TABLE `Dance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Notification`
--

DROP TABLE IF EXISTS `Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message` varchar(45) NOT NULL,
  `isRead` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Notification`
--

LOCK TABLES `Notification` WRITE;
/*!40000 ALTER TABLE `Notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `Notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Role`
--

DROP TABLE IF EXISTS `Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Role`
--

LOCK TABLES `Role` WRITE;
/*!40000 ALTER TABLE `Role` DISABLE KEYS */;
INSERT INTO `Role` VALUES (1,'instructor'),(2,'student');
/*!40000 ALTER TABLE `Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Notification`
--

DROP TABLE IF EXISTS `User_Notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Notification` (
  `notification_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`notification_id`,`user_id`),
  KEY `fk_User_Notification_User1_idx` (`user_id`),
  CONSTRAINT `fk_User_Notification_Notification1` FOREIGN KEY (`notification_id`) REFERENCES `Notification` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Notification_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Notification`
--

LOCK TABLES `User_Notification` WRITE;
/*!40000 ALTER TABLE `User_Notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_Notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_Role`
--

DROP TABLE IF EXISTS `User_Role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_User_Role_User1_idx` (`user_id`),
  CONSTRAINT `fk_User_Role_Role1` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Role_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_Role`
--

LOCK TABLES `User_Role` WRITE;
/*!40000 ALTER TABLE `User_Role` DISABLE KEYS */;
INSERT INTO `User_Role` VALUES (2,5),(2,6),(2,9),(2,11),(2,12),(2,14),(2,16),(2,18),(1,19),(1,20),(1,21),(1,22),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41),(1,42),(1,43);
/*!40000 ALTER TABLE `User_Role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `location_id` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Lesson_Location1` (`location_id`),
  CONSTRAINT `fk_Lesson_Location1` FOREIGN KEY (`location_id`) REFERENCES `Location` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,'17:00:00','18:00:00',1,'2018-10-01'),(2,'17:00:00','18:00:00',1,'2018-10-02'),(3,'17:00:00','18:00:00',1,'2018-10-03'),(4,'17:00:00','18:00:00',1,'2018-10-04'),(5,'17:00:00','18:00:00',1,'2018-10-05'),(6,'17:00:00','18:00:00',1,'2018-09-27'),(7,'19:00:00','18:00:00',2,'2018-10-01'),(8,'18:00:00','19:00:00',1,'2018-08-01'),(9,'18:00:00','19:00:00',1,'2018-08-02'),(10,'18:00:00','19:00:00',1,'2018-11-01'),(11,'18:00:00','19:00:00',1,'2018-11-02');
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) DEFAULT NULL,
  `address1` varchar(120) NOT NULL,
  `address2` varchar(120) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(2) NOT NULL,
  `lat` decimal(9,6) DEFAULT NULL,
  `lon` decimal(9,6) DEFAULT NULL,
  `postalcode` varchar(16) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'Star Ballroom','588 Main St',NULL,'Madison','WI',NULL,NULL,'53704'),(2,'My Ballroom','123 Main St',NULL,'Madison','WI',NULL,NULL,'53705'),(3,'Elite Dance','124 Main St',NULL,'Madison','WI',NULL,NULL,'53705');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `user_id` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`,`user_id`),
  KEY `fk_Schedule_User1_idx` (`user_id`),
  CONSTRAINT `fk_Schedule_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (69,'08:00:00','20:00:00',34,'2018-10-08'),(71,'08:00:00','20:00:00',34,'2018-10-22'),(72,'08:00:00','20:00:00',34,'2018-10-29');
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(450) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `address1` varchar(120) NOT NULL,
  `address2` varchar(120) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(2) NOT NULL,
  `postalcode` varchar(16) NOT NULL,
  `lat` decimal(9,6) DEFAULT NULL,
  `lon` decimal(9,6) DEFAULT NULL,
  `pay_rate` decimal(5,2) DEFAULT NULL,
  `photo_name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'test1','abc',0,'John','Kuba','124 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(3,'test2','abc',0,'Mike','Johnson','123 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(4,'test3','abc',0,'Mike','Johnson','123 Main St','','Madison','WI','53750',NULL,NULL,0.00,''),(5,'test4','abc',0,'Mike','Peterson','123 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(6,'fs','',0,'','','','','','','',NULL,NULL,0.00,''),(7,'fsadg','',0,'','','','','','','',NULL,NULL,0.00,''),(9,'fsa','',0,'','','','','','','',NULL,NULL,0.00,''),(11,'test5','abc',0,'Mike','Simpson','123 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(12,'test6','abc',0,'Senior','George','123 Main St','','Madison','WI','31431',NULL,NULL,0.00,''),(14,'test7','',0,'Senior','George','123 Main St','','Madison','WI','31431',NULL,NULL,0.00,''),(16,'test10','abc',0,'Senior','George','123 Main St','','Madison','WI','31431',NULL,NULL,0.00,''),(18,'test11','abc',0,'Mike','Simpson','123 Main','','Madison','WI','53705',NULL,NULL,0.00,'belitapic.jpg'),(19,'dancer1','abc',0,'Maria','Gomez','134 Main St','','Madison','WI','53705',NULL,NULL,0.00,'susannahpic.jpg'),(20,'dancer2','abc',0,'Marco','Lopez','134 Main','','Madison','WI','53706',NULL,NULL,0.00,'paulpic.jpg'),(21,'dancer3','abc',0,'Marco','Lopez','134 Main','','Madison','WI','53706',NULL,NULL,0.00,'jasonpic.jpg'),(22,'fads','',0,'fs','fs','','','','','',NULL,NULL,0.00,''),(24,'hgyt','',0,'fs','fs','','','','','',NULL,NULL,0.00,''),(25,'aaa','',0,'fs','fs','','','','','',NULL,NULL,0.00,'susannahpic.jpg'),(26,'martini','abc',0,'Martin','Mara','124 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(27,'martini1','abc',0,'Martin','Mara','124 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(28,'martini2','abc',0,'Martin','Mara','124 Main St','','Madison','WI','53705',NULL,NULL,0.00,''),(29,'martini3','abc',0,'Martin','Mara','124 Main St','','Madison','WI','53705',NULL,NULL,0.00,'alexpic.jpg'),(30,'bobby1','abc',0,'Bob','Somers','','','','','',NULL,NULL,0.00,'nevilpic.jpg'),(31,'bobby2','abc',0,'Bob','August','','','','','',NULL,NULL,0.00,'dierdrepic.jpg'),(32,'bobby3','abc',0,'Bob','July','','','','','',NULL,NULL,0.00,'johanpic.jpg'),(33,'bobby4','abc',0,'Bob','Septemberish','123 Main St','','Madison','WI','53704',NULL,NULL,50.00,'susannahpic.jpg'),(34,'Bobby22','ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad',0,'Sam','Cash','135 Sheridan','','Jefferson','AR','53704',NULL,NULL,50.00,'susannahpic.jpg'),(35,'RobertM','abc',0,'Robert','Martin','134 Main St','','Madison','WI','53705',NULL,NULL,34.00,'belitasscore.gif'),(36,'Jessy1','>6Gj>%qxPl?',0,'Jessy','Jackson','134 Main St','','Madison','WI','53705',NULL,NULL,60.00,'oscarpic.jpg'),(37,'Jessy3','f8c1d87006fbf7e5cc4b026c3138bc046883dc71',0,'Jessy','Jackson','3412 Main St','','Madison','WI','54705',NULL,NULL,50.00,''),(38,'Jessy4','abc',0,'Jessy','Johnson','134 Main St','','Madison','WI','53705',NULL,NULL,50.00,'nevilpic.jpg'),(39,'Jessy5','a9993e364706816aba3e25717850c26c9cd0d89d',0,'Jessy','Simpson','134 Main St','','Madison','WI','53705',NULL,NULL,50.00,'johanpic.jpg'),(40,'Jessy6','f8c1d87006fbf7e5cc4b026c3138bc046883dc71',0,'Jessy','Franklin','3342 Main St','','Madison','WI','53705',NULL,NULL,50.00,'oscarpic.jpg'),(41,'Jessy7','~*G>=YES\'~',0,'Jessy','Milford','234 Main St','','Madison','WI','53705',NULL,NULL,50.00,'susannahpic.jpg'),(42,'Jessy9','~*G>=YES\'~',0,'Jessy','Sammy','134 Main St','','Madison','WI','53705',NULL,NULL,50.00,'nevilpic.jpg'),(43,'Jessy10','ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad',0,'Jessy','Jackson','1234 Main St','','Madison','WI','53705',NULL,NULL,40.00,'sidneypic.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_dance`
--

DROP TABLE IF EXISTS `user_dance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_dance` (
  `experience_years` int(11) DEFAULT NULL,
  `learning_proficiency` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `dance_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`dance_id`),
  KEY `fk_User_Dance_User1_idx` (`user_id`),
  KEY `fk_User_Dance_Dance1_idx` (`dance_id`),
  CONSTRAINT `fk_User_Dance_Dance1` FOREIGN KEY (`dance_id`) REFERENCES `Dance` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Dance_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_dance`
--

LOCK TABLES `user_dance` WRITE;
/*!40000 ALTER TABLE `user_dance` DISABLE KEYS */;
INSERT INTO `user_dance` VALUES (2,NULL,33,2),(4,NULL,33,3),(5,NULL,33,4),(5,NULL,34,2),(7,NULL,34,3),(5,NULL,34,4);
/*!40000 ALTER TABLE `user_dance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_lesson`
--

DROP TABLE IF EXISTS `user_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_lesson` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` int(11) NOT NULL,
  `time_scheduled` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`lesson_id`),
  KEY `fk_User_Lesson_Lesson1_idx` (`lesson_id`),
  KEY `fk_user_lesson_role_id` (`role_id`),
  CONSTRAINT `fk_User_Lesson_Lesson1` FOREIGN KEY (`lesson_id`) REFERENCES `Lesson` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Lesson_User1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_lesson_role_id` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_lesson`
--

LOCK TABLES `user_lesson` WRITE;
/*!40000 ALTER TABLE `user_lesson` DISABLE KEYS */;
INSERT INTO `user_lesson` VALUES (34,1,'2018-10-07 17:36:41',1),(34,2,'2018-10-07 17:36:42',1),(34,8,'2018-10-09 21:33:55',1),(34,9,'2018-10-09 21:33:55',1),(34,10,'2018-10-09 21:40:12',1),(34,11,'2018-10-09 21:40:12',1);
/*!40000 ALTER TABLE `user_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `user_role_view`
--

DROP TABLE IF EXISTS `user_role_view`;
/*!50001 DROP VIEW IF EXISTS `user_role_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `user_role_view` AS SELECT 
 1 AS `username`,
 1 AS `role_name`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `user_role_view`
--

/*!50001 DROP VIEW IF EXISTS `user_role_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `user_role_view` AS select `u`.`username` AS `username`,`r`.`name` AS `role_name` from ((`user` `u` join `user_role` `ur` on((`u`.`id` = `ur`.`user_id`))) join `role` `r` on((`ur`.`role_id` = `r`.`id`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-16 22:15:22
