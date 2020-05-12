CREATE DATABASE  IF NOT EXISTS `moviedb`;
USE `moviedb`;
--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;

 SET character_set_client = utf8mb4 ;
CREATE TABLE `movies` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `actors` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `director` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `type` varchar(20) DEFAULT NULL,
  `year` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

INSERT INTO `movies` VALUES (1,'Rajini','India','karthik SUBBURAJ','tamil','petta','MOVIE','2019-01-02'),(2,'Leonardo DiCaprio, Kate Winslet','America','James Cameron','English','Titanic','MOVIE','1997-01-02'),(3,'Vijay','India','Lokesh','Tamil','Master','SERIES','2020-01-02');