-- MySQL dump 10.13  Distrib 5.7.12, for osx10.9 (x86_64)
--
-- Host: 127.0.0.1    Database: MobyRx_3
-- ------------------------------------------------------
-- Server version	5.7.13

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
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `specialization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (1,'Audiologist','Audiologist'),(2,'Allergist','Allergist'),(3,'Anesthesiologist','Anesthesiologist'),(4,'Cardiologist','Cardiologist'),(5,'Dentist','Dentist'),(6,'Dermatologist','Dermatologist'),(7,'Endocrinologist','Endocrinologist'),(8,'Epidemiologist','Epidemiologist'),(9,'Gynaecologist','Gynaecologist'),(10,'General Physician','General Physician'),(11,'Immunologist','Immunologist'),(12,'Infectious Disease Specialist','Infectious Disease Specialist'),(13,'Internal Medicine Specialist','Internal Medicine Specialist'),(14,'Medical Geneticist','Medical Geneticist'),(15,'Microbiologist','Microbiologist'),(16,'Neonatologist','Neonatologist'),(17,'Neurologist','Neurologist'),(18,'Neurosurgeon','Neurosurgeon'),(19,'Obstetrician','Obstetrician'),(20,'Oncologist','Oncologist'),(21,'Orthopedic Surgeon','Orthopedic Surgeon'),(22,'ENT Specialist','ENT Specialist'),(23,'Pediatrician','Pediatrician'),(24,'Physiologist','Physiologist'),(25,'Plastic Surgeon','Plastic Surgeon'),(26,'Podiatrist','Podiatrist'),(27,'Psychiatrist','Psychiatrist'),(28,'Radiologist','Radiologist'),(29,'Rheumatologist','Rheumatologist'),(30,'Surgeon','Surgeon'),(31,'Urologist','Urologist');
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-29 21:20:48
