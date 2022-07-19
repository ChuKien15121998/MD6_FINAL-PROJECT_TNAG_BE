CREATE DATABASE  IF NOT EXISTS `tnag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `tnag`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: tnag
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_address` varchar(255) DEFAULT NULL,
  `address_category_id` bigint DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5npb2nxnmfife5wk94gla2xn2` (`address_category_id`),
  KEY `FK93c3js0e22ll1xlu21nvrhqgg` (`customer_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'Hoa Lâm - Long Biên - Hà Nội',1,1),(2,'Mỹ Đình - Nam Từ Liêm - Hà Nội',2,1);
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_categories`
--

DROP TABLE IF EXISTS `address_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_categories`
--

LOCK TABLES `address_categories` WRITE;
/*!40000 ALTER TABLE `address_categories` DISABLE KEYS */;
INSERT INTO `address_categories` VALUES (1,'Nhà'),(2,'Cơ quan'),(3,'Khác');
/*!40000 ALTER TABLE `address_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKra1cb3fu95r1a0m7aksow0nk4` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'phong','+84333258456',2);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_categories`
--

DROP TABLE IF EXISTS `food_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_categories`
--

LOCK TABLES `food_categories` WRITE;
/*!40000 ALTER TABLE `food_categories` DISABLE KEYS */;
INSERT INTO `food_categories` VALUES (1,'Cơm'),(2,'Bún/Phở'),(3,'Đồ ăn vặt'),(4,'Đồ uống');
/*!40000 ALTER TABLE `food_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foods`
--

DROP TABLE IF EXISTS `foods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foods` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `image` longtext,
  `is_delete` tinyint(1) DEFAULT '1',
  `name` varchar(255) DEFAULT NULL,
  `price_discount` double NOT NULL,
  `recommend` bit(1) DEFAULT NULL,
  `sold` bigint DEFAULT '0',
  `food_category_id` bigint DEFAULT NULL,
  `merchant_id` bigint DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8kpsrgxfhc4wjdmd7vs1j4n60` (`food_category_id`),
  KEY `FKgg2bwcyhve5wxixnduwrsxhvp` (`merchant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foods`
--

LOCK TABLES `foods` WRITE;
/*!40000 ALTER TABLE `foods` DISABLE KEYS */;
INSERT INTO `foods` VALUES (1,'Thịt bê ăn cùng rau sống  càng nhai thì càng như tan trong miệng. Vị ngọt của thịt hòa cùng vị đậm đà của mắm sẽ làm cho bạn thử một lần và nhớ mãi','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/be-thui.jpg?alt=media&token=6394ba10-4469-4cc0-9a73-4c748159bd1a',1,'Bê thui',155000,NULL,0,1,1,160000),(2,'Mọi người yêu thích thịt cá bống một phần do thịt dai dai nên khi kho cùng tiêu, ớt thì xen lẫn thêm vị cay cay nhẹ càng thêm hấp dẫn.','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/ca-bong.jpg?alt=media&token=00a509cb-af61-444a-b696-dcf4f6cdb1b4',1,'Cá bống',275000,NULL,0,1,1,300000),(3,'Gà nướng lá chanh','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/ga-nuong-la-chanh.jpg?alt=media&token=29ac17f2-394c-4e6e-b5ab-2f385523b82c',1,'Gà nướng lá chanh',220000,NULL,0,1,1,250000),(4,'Dê núi','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/de-nui.jpg?alt=media&token=257e6884-6730-4fb2-a2ef-811ede3af4ab',1,'Dê núi',230000,NULL,0,1,1,250000),(5,'Bánh đa cua','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/banh-da-cua-hai-phong.jpg?alt=media&token=2dea6e8d-f479-4c91-9c8e-2c07fc6947c2',1,'Bánh đa cua Hải Phòng',45000,NULL,0,2,2,50000),(6,'Phở','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/pho.jpg?alt=media&token=9a75ca61-57a9-4411-8cd8-8dc5dda31256',1,'Phở',35000,NULL,0,2,2,35000),(7,'Phở chua','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/pho-chua-lang-son.jpg?alt=media&token=449c6d0b-64cd-4850-b772-370c912be29f',1,'Phở chua Lạng Sơn',25000,NULL,0,2,2,30000),(8,'Bánh khoái','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/banh-khoai.jpg?alt=media&token=814b16b9-b313-48e8-96df-b238a5317d33',1,'Bánh khoái',20000,NULL,0,3,3,25000),(9,'Bánh xèo','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/banh-xeo.jpg?alt=media&token=ca916734-9ccb-4051-8583-ee1cb3ac7002',1,'Bánh xèo',15000,NULL,0,3,3,18000),(10,'Cơm tấm','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/com-tam-sai-gon.jpg?alt=media&token=f3db3682-9dc7-4105-969c-2b8e2be7aac3',1,'Cơm tấm Sài Gòn',45000,NULL,0,1,3,60000),(11,'Hủ tiếu','https://firebasestorage.googleapis.com/v0/b/md5-angular.appspot.com/o/hu-tieu-my-tho.jpg?alt=media&token=4f94519b-925f-48e5-bb24-b3b8e106186d',1,'Hủ tiếu Mỹ Tho',55000,NULL,0,2,3,70000);
/*!40000 ALTER TABLE `foods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `merchant` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `safe_food_license` longtext,
  `address` varchar(255) DEFAULT NULL,
  `close_time` time DEFAULT NULL,
  `gold` bit(1) DEFAULT NULL,
  `is_accept` tinyint(1) DEFAULT '0',
  `is_active` tinyint(1) DEFAULT '1',
  `open_time` time DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm7nft5f7y6le9crtlpe558ktp` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'phong@gmail.com','Quán Cây Khế',NULL,'Long Biên - Hà Nội',NULL,NULL,0,1,NULL,NULL,2),(2,'dung@gmail.com','Bún Bòa',NULL,'Nam Từ Liêm - Hà Nội',NULL,NULL,0,1,NULL,NULL,3),(3,'hung@gmail.com','Bánh Bèo',NULL,'Nam Từ Liêm - Hà Nội',NULL,NULL,0,1,NULL,NULL,4);
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderstatus`
--

DROP TABLE IF EXISTS `orderstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderstatus` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name_order_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderstatus`
--

LOCK TABLES `orderstatus` WRITE;
/*!40000 ALTER TABLE `orderstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nb4h0p6txrmfc0xbrd1kglp9t` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER'),(2,'ROLE_MERCHANT'),(3,'ROLE_ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,3),(2,1),(2,2),(3,2),(4,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `avatar` longtext,
  `email` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'kien@gmail.com','kien','$2a$10$3XAZwndB3D8xE89aC.4K2uD2EMhoQXSw7XaeNw7pHYfUDwbqFsKpK','kien@gmail.com'),(2,NULL,'phong@gmail.com','phong','$2a$10$3XAZwndB3D8xE89aC.4K2uD2EMhoQXSw7XaeNw7pHYfUDwbqFsKpK','phong@gmail.com'),(3,NULL,'dung@gmail.com','dung','$2a$10$3XAZwndB3D8xE89aC.4K2uD2EMhoQXSw7XaeNw7pHYfUDwbqFsKpK','dung@gmail.com'),(4,NULL,'hung@gmail.com','hung','$2a$10$3XAZwndB3D8xE89aC.4K2uD2EMhoQXSw7XaeNw7pHYfUDwbqFsKpK','hung@gmail.com');
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

-- Dump completed on 2022-07-19  8:20:33
