-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: khonggianvipdata
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `kgvinformation`
--

DROP TABLE IF EXISTS `kgvinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kgvinformation` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name_res` varchar(250) CHARACTER SET utf8 DEFAULT NULL,
  `add_res` text CHARACTER SET utf8,
  `tell` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `logo` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `gioithieu` text COLLATE utf8_unicode_ci,
  `tuyendung` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kgvinformation`
--

LOCK TABLES `kgvinformation` WRITE;
/*!40000 ALTER TABLE `kgvinformation` DISABLE KEYS */;
INSERT INTO `kgvinformation` VALUES (1,'KHÔNG GIAN VIP','34 Nguyễn Hữu Thọ','0511 3 789 887','tolo.balap@gmail.com','img631.png','<p>Kh&ocirc;ng Gian vip</p>\r\n\r\n<p style=\"text-align:center\"><img alt=\"chef\" src=\"/KhongGianVip/View/Image/Imagestore/img967.png	\" style=\"border-style:solid; border-width:1px; height:245px; margin:10px; width:300px\" /></p>\r\n','<h1 style=\"text-align:center\">Tuy&ecirc;n dụng th&aacute;ng 7</h1>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p style=\"text-align:center\"><img alt=\"chef\" src=\"/KhongGianVip/View/Image/Imagestore/img556.png\" style=\"border-style:solid; border-width:1px; height:111px; margin:1px; width:136px\" /></p>\r\n');
/*!40000 ALTER TABLE `kgvinformation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_danhmuc`
--

DROP TABLE IF EXISTS `tb_danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_danhmuc` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `sp_id` int(3) DEFAULT NULL,
  `content_danhmuc` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_danhmuc`
--

LOCK TABLES `tb_danhmuc` WRITE;
/*!40000 ALTER TABLE `tb_danhmuc` DISABLE KEYS */;
INSERT INTO `tb_danhmuc` VALUES (1,'KHÁCH LẺ',0,''),(2,'VIP',0,NULL),(3,'KHÁCH ĐOÀN',0,NULL),(4,'TIỆC CƯỚI',0,NULL),(5,'KHAI VỊ',1,NULL),(6,'BÒ',1,NULL),(7,'CÁ',1,NULL);
/*!40000 ALTER TABLE `tb_danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_imagestore`
--

DROP TABLE IF EXISTS `tb_imagestore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_imagestore` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name_image` varchar(250) DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_imagestore`
--

LOCK TABLES `tb_imagestore` WRITE;
/*!40000 ALTER TABLE `tb_imagestore` DISABLE KEYS */;
INSERT INTO `tb_imagestore` VALUES (17,'img80.png','l');
/*!40000 ALTER TABLE `tb_imagestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `id_menu` int(3) NOT NULL AUTO_INCREMENT,
  `title_menu` varchar(200) DEFAULT NULL,
  `sp_id_menu` int(3) DEFAULT NULL,
  `type_menu` int(1) DEFAULT NULL,
  `content_menu` text,
  PRIMARY KEY (`id_menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_news`
--

DROP TABLE IF EXISTS `tb_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_news` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `title` varchar(250) DEFAULT NULL,
  `content` text,
  `time_news` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news` VALUES (1,'muong khai truong','chi chi do Kênh Âm nhạc giáo dục VNtoday sẽ giúp bạn học tập tốt hơn, nâng cao trí tuệ, tinh thần tập trung và trí nhớ tốt với những bản nhạc Baroque ','may gioi do'),(2,'dsd','Kênh Âm nhạc giáo dục VNtoday sẽ giúp bạn học tập tốt hơn, nâng cao trí tuệ, tinh thần tập trung và trí nhớ tốt với những bản nhạc Baroque Kênh Âm nhạc giáo dục VNtoday sẽ giúp bạn học tập tốt hơn, nâng cao trí tuệ, tinh thần tập trung và trí nhớ tốt với những bản nhạc Baroque ','2');
/*!40000 ALTER TABLE `tb_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `id_pr` int(3) NOT NULL AUTO_INCREMENT,
  `sp_pr` int(3) DEFAULT NULL,
  `image_pr` varchar(100) DEFAULT NULL,
  `price` varchar(100) DEFAULT NULL,
  `title_pr` varchar(200) DEFAULT NULL,
  `content_menu` text,
  PRIMARY KEY (`id_pr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_slider`
--

DROP TABLE IF EXISTS `tb_slider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_slider` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `image_slider` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_slider`
--

LOCK TABLES `tb_slider` WRITE;
/*!40000 ALTER TABLE `tb_slider` DISABLE KEYS */;
INSERT INTO `tb_slider` VALUES (4,NULL,'img450.png'),(9,NULL,'img798.png'),(10,NULL,'img66.png'),(12,'','img337.png'),(17,'','img567.png');
/*!40000 ALTER TABLE `tb_slider` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-24  9:52:11
