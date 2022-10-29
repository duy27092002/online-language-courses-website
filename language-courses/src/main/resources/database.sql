CREATE DATABASE  IF NOT EXISTS `edukate` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `edukate`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: edukate
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
-- Table structure for table `about`
--

DROP TABLE IF EXISTS `about`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `about` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `facebook_link` varchar(255) NOT NULL,
  `favicon` varchar(255) DEFAULT NULL,
  `in_link` varchar(255) NOT NULL,
  `instagram_link` varchar(255) NOT NULL,
  `location` varchar(50) NOT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(10) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `twitter_link` varchar(255) NOT NULL,
  `youtube_link` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `about`
--

LOCK TABLES `about` WRITE;
/*!40000 ALTER TABLE `about` DISABLE KEYS */;
INSERT INTO `about` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-29 15:00:46',1,'edukate@gmail.com','Facebook Link','edukate_logo.ico','LinkedIn','Instagram Link','128A Hồ Tùng Mậu, Cầu Giấy, Hà Nội','edukate_logo.png','Edukate','0387654321','Tiwtter Link','Youtube Link');
/*!40000 ALTER TABLE `about` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `feedback_for_user_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `video_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbo4qsdk9769ho26gw7st8kew3` (`feedback_for_user_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
  KEY `FKbsuh08kv1lyh8v6ivufrollr6` (`video_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `description` text NOT NULL,
  `discount` int DEFAULT NULL,
  `end_discount_time` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `price` varchar(20) NOT NULL,
  `release_time` datetime DEFAULT NULL,
  `start_discount_time` datetime DEFAULT NULL,
  `thumbnail` varchar(1000) NOT NULL,
  `language_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfkhqlhm1awgxvson7yy8ldh16` (`language_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-25 15:12:39',2,'<p>&bull; Ph&aacute;t &acirc;m chuẩn bản ngữ - th&agrave;nh thạo Nghe - N&oacute;i, hết mất gốc Tiếng Anh với kho&aacute; tiếng Anh trực tuyến Elight Online, chỉ 3K/ ng&agrave;y: Học tiếng Anh đơn giản v&agrave; dễ hiều c&ugrave;ng Elight với với playlist tiếng Anh. Xem ngay tại đ&acirc;y tại đ&acirc;y: 1. Lộ tr&igrave;nh học tiếng Anh người mới bắt đầu v&agrave; mất gốc 2. Ph&aacute;t &acirc;m tiếng Anh chuẩn như người bản xứ&nbsp;3. Ngữ ph&aacute;p cơ bản cho người mới bắt đầu v&agrave; mất gốc&nbsp;4. Tiếng Anhh giao tiếp cơ bản cho người mới bắt đầu&nbsp;5. Từ vựng tiếng Anh theo chủ đề th&ocirc;ng dụng&nbsp;6. Luyện nghe tiếng Anh giao tiếp&nbsp;Mỗi ng&agrave;y xem 3-5 video học tiếng anh của elight, kết hợp với kh&oacute;a học tiếng Anh to&agrave;n diện của Elight, tiếng Anh của bạn sẽ cải thiện nhanh ch&oacute;ng. --------------- T&agrave;i liệu tham khảo: - Bảng phi&ecirc;n &acirc;m IPA 44 &acirc;m&nbsp;Để kh&ocirc;ng bỏ lỡ c&aacute;c video b&agrave;i học th&uacute; vị. Subscribe elight ngay tại link n&agrave;y nh&eacute; &nbsp;Bảng phi&ecirc;n &acirc;m tiếng anh hướng dẫn c&aacute;ch ph&aacute;t &acirc;m tiếng Anh chuẩn giọng anh Mỹ&nbsp;Ph&aacute;t &acirc;m tiếng Anh chuẩn l&agrave; yếu tố cần thiết với người học tiếng Anh. Để ph&aacute;t &acirc;m tiếng anh chuẩn, ch&uacute;ng ta cần biết c&aacute;ch đọc 44 &acirc;m IPA, trong đ&oacute; bao gồm 1. Bảng phi&ecirc;n &acirc;m IPA: 12 nguy&ecirc;n &acirc;m đơn 2. Bảng phi&ecirc;n &acirc;m IPA: 8 nguy&ecirc;n &acirc;m đ&ocirc;i (video hướng dẫn chi tiết 8 nguy&ecirc;n &acirc;m đ&ocirc;i sẽ được cập nhật ng&agrave;y 25/2/2017) 3. Bảng phi&ecirc;n &acirc;m IPA: 24 phụ &acirc;m (video hướng dẫn chi tiết 24 phụ &acirc;m sẽ được cập nhật v&agrave;o cuối th&aacute;ng 2- đầu th&aacute;ng 3)</p>',0,NULL,'Tiếng anh cơ bản 1','250,000',NULL,NULL,'https://storage.googleapis.com/edukate-system.appspot.com/e76dcab5-0f55-4372-96f0-e8524534dbc6jpg',1),(3,NULL,NULL,'nvd@gmail.com','2022-10-27 09:15:19',2,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p><p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using &#39;Content here, content here&#39;, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for &#39;lorem ipsum&#39; will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>',0,NULL,'Tiếng anh cơ bản 3','500,000',NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',1),(2,NULL,NULL,'nvd@gmail.com','2022-10-27 09:15:08',2,'<p>&bull; Ph&aacute;t &acirc;m chuẩn bản ngữ - th&agrave;nh thạo Nghe - N&oacute;i, hết mất gốc Tiếng Anh với kho&aacute; tiếng Anh trực tuyến Elight Online, chỉ 3K/ ng&agrave;y: Học tiếng Anh đơn giản v&agrave; dễ hiều c&ugrave;ng Elight với với playlist tiếng Anh. Xem ngay tại đ&acirc;y tại đ&acirc;y: 1. Lộ tr&igrave;nh học tiếng Anh người mới bắt đầu v&agrave; mất gốc 2. Ph&aacute;t &acirc;m tiếng Anh chuẩn như người bản xứ&nbsp;3. Ngữ ph&aacute;p cơ bản cho người mới bắt đầu v&agrave; mất gốc&nbsp;4. Tiếng Anhh giao tiếp cơ bản cho người mới bắt đầu&nbsp;5. Từ vựng tiếng Anh theo chủ đề th&ocirc;ng dụng&nbsp;6. Luyện nghe tiếng Anh giao tiếp&nbsp;Mỗi ng&agrave;y xem 3-5 video học tiếng anh của elight, kết hợp với kh&oacute;a học tiếng Anh to&agrave;n diện của Elight, tiếng Anh của bạn sẽ cải thiện nhanh ch&oacute;ng. --------------- T&agrave;i liệu tham khảo: - Bảng phi&ecirc;n &acirc;m IPA 44 &acirc;m&nbsp;Để kh&ocirc;ng bỏ lỡ c&aacute;c video b&agrave;i học th&uacute; vị. Subscribe elight ngay tại link n&agrave;y nh&eacute; &nbsp;Bảng phi&ecirc;n &acirc;m tiếng anh hướng dẫn c&aacute;ch ph&aacute;t &acirc;m tiếng Anh chuẩn giọng anh Mỹ&nbsp;Ph&aacute;t &acirc;m tiếng Anh chuẩn l&agrave; yếu tố cần thiết với người học tiếng Anh. Để ph&aacute;t &acirc;m tiếng anh chuẩn, ch&uacute;ng ta cần biết c&aacute;ch đọc 44 &acirc;m IPA, trong đ&oacute; bao gồm 1. Bảng phi&ecirc;n &acirc;m IPA: 12 nguy&ecirc;n &acirc;m đơn 2. Bảng phi&ecirc;n &acirc;m IPA: 8 nguy&ecirc;n &acirc;m đ&ocirc;i (video hướng dẫn chi tiết 8 nguy&ecirc;n &acirc;m đ&ocirc;i sẽ được cập nhật ng&agrave;y 25/2/2017) 3. Bảng phi&ecirc;n &acirc;m IPA: 24 phụ &acirc;m (video hướng dẫn chi tiết 24 phụ &acirc;m sẽ được cập nhật v&agrave;o cuối th&aacute;ng 2- đầu th&aacute;ng 3)</p>',20,'2022-11-15 00:00:00','Tiếng anh cơ bản 2','355,000',NULL,'2022-10-24 00:00:00','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',1),(4,NULL,NULL,'nvd@gmail.com','2022-10-27 09:29:06',2,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>',0,NULL,'Tiếng Nhật cơ bản 1','500,000',NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',2),(5,NULL,NULL,'nvd@gmail.com','2022-10-27 09:28:52',2,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>',0,NULL,'Tiếng Nhật cơ bản 2','850,000',NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',2),(6,NULL,NULL,'nvd@gmail.com','2022-10-27 09:28:39',2,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>',0,NULL,'Tiếng Nhật cơ bản 3','1,000,000',NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',2),(7,'nvd@gmail.com','2022-10-27 09:29:51','nvd@gmail.com','2022-10-27 09:29:51',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>',0,NULL,'Tiếng Nhật cơ bản 4','1,500,000',NULL,NULL,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/thumbnail_default.png?alt=media&token=bd4c3805-1176-40fd-af64-f9f5ed566513',2);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_instructor`
--

DROP TABLE IF EXISTS `course_instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_instructor` (
  `course_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKlh4as6v0k90giv65dk18ftjgn` (`user_id`),
  KEY `FKeqej22fgwa29i98ucd9x9ycie` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_instructor`
--

LOCK TABLES `course_instructor` WRITE;
/*!40000 ALTER TABLE `course_instructor` DISABLE KEYS */;
INSERT INTO `course_instructor` VALUES (1,3),(1,2),(2,4),(3,4),(3,3),(4,3),(5,3),(6,3),(7,3);
/*!40000 ALTER TABLE `course_instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_skill`
--

DROP TABLE IF EXISTS `course_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_skill` (
  `course_id` bigint NOT NULL,
  `skill_level_id` bigint NOT NULL,
  KEY `FK2eqq9qa0lt50fmor8dq996igb` (`skill_level_id`),
  KEY `FKn17ep7229hbi0li6eobs1mi6q` (`course_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_skill`
--

LOCK TABLES `course_skill` WRITE;
/*!40000 ALTER TABLE `course_skill` DISABLE KEYS */;
INSERT INTO `course_skill` VALUES (2,6),(2,5),(2,3),(2,2),(1,6),(1,5),(3,4),(3,3),(3,2),(3,1),(4,5),(5,6),(5,5),(6,6),(6,3),(7,1),(7,2),(7,3);
/*!40000 ALTER TABLE `course_skill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_student`
--

DROP TABLE IF EXISTS `course_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_student` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `course_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlmj50qx9k98b7li5li74nnylb` (`course_id`),
  KEY `FK9rbx2um6e21kwknwmkt9glol6` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_student`
--

LOCK TABLES `course_student` WRITE;
/*!40000 ALTER TABLE `course_student` DISABLE KEYS */;
INSERT INTO `course_student` VALUES (1,'binhgold@gmail.com','2022-10-01 00:00:00','binhgold@gmail.com','2022-10-01 00:00:00',1,1,5),(2,'huongno@gmail.com','2022-09-10 00:00:00','huongno@gmail.com','2022-09-10 00:00:00',2,1,6),(3,'minhduxng@gmail.com','2022-10-28 09:14:52','minhduxng@gmail.com','2022-10-28 09:14:52',1,1,8),(4,'huongno@gmail.com','2022-10-28 09:27:23','huongno@gmail.com','2022-10-28 09:27:23',1,2,6),(5,'huongno@gmail.com','2022-10-28 15:06:23','huongno@gmail.com','2022-10-28 15:06:23',1,3,6);
/*!40000 ALTER TABLE `course_student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluated`
--

DROP TABLE IF EXISTS `evaluated`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluated` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `content` varchar(1000) NOT NULL,
  `point` int NOT NULL,
  `course_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKer8412wecrplvs7490io40kmu` (`course_id`),
  KEY `FKno2hnny6udbvlhosod193uoy` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluated`
--

LOCK TABLES `evaluated` WRITE;
/*!40000 ALTER TABLE `evaluated` DISABLE KEYS */;
INSERT INTO `evaluated` VALUES (1,'binhgold@gmail.com','2022-10-25 00:00:00','binhgold@gmail.com','2022-10-25 00:00:00',2,'Khóa học thật tuyệt vời, thời lượng hợp lý, giảng viên dạy rất dễ hiểu',5,1,5),(2,NULL,NULL,'nvd@gmail.com','2022-10-25 20:33:06',1,'Khóa học rất hay và bổ ích',4,1,6),(3,'huongno@gmail.com','2022-10-28 21:06:21','huongno@gmail.com','2022-10-28 21:06:21',0,'Khóa học rất bổ ích. Bài tập bám sát với lý thuyết được dạy. iu Edukate ?',5,2,6);
/*!40000 ALTER TABLE `evaluated` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faqs`
--

DROP TABLE IF EXISTS `faqs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faqs` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `answer` text,
  `question` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faqs`
--

LOCK TABLES `faqs` WRITE;
/*!40000 ALTER TABLE `faqs` DISABLE KEYS */;
INSERT INTO `faqs` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-22 20:43:10',1,'<p>Giảng vi&ecirc;n phụ tr&aacute;ch của kh&oacute;a học</p>','Trong quá trình học theo khóa học thì ai là người giải đáp thắc mắc của học viên?'),(2,'nvd@gmail.com','2022-10-27 08:31:35','nvd@gmail.com','2022-10-27 08:31:35',1,'<p>Cho ph&eacute;p ho&agrave;n trả kh&oacute;a học trong v&ograve;ng 3 ng&agrave;y kể từ ng&agrave;y mua kh&oacute;a học</p>','Vấn đề hoàn trả khóa học');
/*!40000 ALTER TABLE `faqs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `question` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-26 19:33:25',2,'khanhhung@gmail.com','Nguyễn Khánh Hùng','Khi có thắc mắc về nội dung trong khóa học thì ai sẽ là người giải đáp ạ?'),(2,NULL,NULL,'nvd@gmail.com','2022-10-27 08:31:45',2,'quangha@gmail.com','Hà Quang Việt','Có được hoàn trả khóa học không ạ? Và nếu được thì thời gian hoàn trả như thế nào?');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `language` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g8hr207ijpxlwu10pewyo65gv` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'nvd@gmail.com','2022-10-22 14:47:19','nvd@gmail.com','2022-10-22 14:47:19',1,'Tiếng Anh'),(2,'nvd@gmail.com','2022-10-22 14:47:41','nvd@gmail.com','2022-10-22 14:47:41',1,'Tiếng Nhật'),(3,'nvd@gmail.com','2022-10-22 14:47:52','nvd@gmail.com','2022-10-22 14:47:52',1,'Tiếng Trung Quốc'),(4,'nvd@gmail.com','2022-10-22 14:48:04','nvd@gmail.com','2022-10-22 14:48:04',1,'Tiếng Hàn Quốc'),(5,NULL,NULL,'nvd@gmail.com','2022-10-26 09:13:25',0,'Tiếng Nga');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,NULL,NULL,NULL,NULL,1,'ROLE_admin','Admin'),(2,'nvd@gmail.com','2022-10-22 14:54:51','nvd@gmail.com','2022-10-22 14:54:51',1,'ROLE_giang-vien','Giảng viên'),(3,'nvd@gmail.com','2022-10-22 14:55:00','nvd@gmail.com','2022-10-22 14:55:00',1,'ROLE_hoc-vien','Học viên'),(4,'nvd@gmail.com','2022-10-22 14:55:14','nvd@gmail.com','2022-10-22 14:55:14',1,'ROLE_video-editor','Video Editor');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skill_level`
--

DROP TABLE IF EXISTS `skill_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skill_level` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mqhv8a7i1q6ok93gpybm7co7x` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skill_level`
--

LOCK TABLES `skill_level` WRITE;
/*!40000 ALTER TABLE `skill_level` DISABLE KEYS */;
INSERT INTO `skill_level` VALUES (1,'nvd@gmail.com','2022-10-22 14:50:17','nvd@gmail.com','2022-10-22 14:50:17',1,'Nghe'),(2,'nvd@gmail.com','2022-10-22 14:50:29','nvd@gmail.com','2022-10-22 14:50:29',1,'Nói'),(3,'nvd@gmail.com','2022-10-22 14:50:35','nvd@gmail.com','2022-10-22 14:50:35',1,'Đọc'),(4,'nvd@gmail.com','2022-10-22 14:50:42','nvd@gmail.com','2022-10-22 14:50:42',1,'Viết'),(5,'nvd@gmail.com','2022-10-22 14:52:15','nvd@gmail.com','2022-10-22 14:52:15',1,'Nhớ từ vựng cơ bản'),(6,'nvd@gmail.com','2022-10-22 14:53:31','nvd@gmail.com','2022-10-22 14:53:31',1,'Nhớ cú pháp đơn giản');
/*!40000 ALTER TABLE `skill_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `avatar` varchar(500) DEFAULT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `facebook_link` varchar(255) DEFAULT NULL,
  `gender` int NOT NULL,
  `in_link` varchar(255) DEFAULT NULL,
  `instagram_link` varchar(255) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(180) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `twitter_link` varchar(255) DEFAULT NULL,
  `user_name` varchar(50) NOT NULL,
  `youtube_link` varchar(255) DEFAULT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_4bgmpi98dylab6qdvf9xyaxu4` (`phone_number`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-27 09:58:21',1,'https://storage.googleapis.com/edukate-system.appspot.com/920b0a59-c432-4cde-a7a9-f41e67a061e6svg','2002-09-27 00:00:00','nvd@gmail.com','',1,'','','Nguyễn Văn Duy','$2a$10$CadIh2JjqMCwrgKAOvF63ONcYvX1HS7lI8NXc8ZAhVqRZp3Py.HEW','0983018799','','DnV','',1),(2,NULL,NULL,'ddk@gmail.com','2022-10-29 10:25:22',1,'https://storage.googleapis.com/edukate-system.appspot.com/684da568-c8fb-402e-94ea-9a88ac16e189jpg','1999-01-25 00:00:00','ddk@gmail.com','',1,'','','Đặng Duy Khanh','$2a$10$DiMMKPnu6UcnUJne88xXKeKKG.8KiYaXW7FQXzwQRfXELPgsusLKW','0972251389','','Khanh Đặng','',2),(9,'nvd@gmail.com','2022-10-28 23:03:22','nvd@gmail.com','2022-10-28 23:03:22',1,'https://storage.googleapis.com/edukate-system.appspot.com/f53f0abf-3a33-40f9-ba8e-f7a686e94233jpg','1999-07-22 00:00:00','quangling@gmail.com','',1,'','','Phạm Quang Linh','$2a$10$1nnBYi7U5ctUtIh9074XGuYYYPeGiQvXCKKDXIQ3LMp1HQxW9V7y6','0397783569','','Quang Ling','',2),(3,NULL,NULL,'vutuankiet2411@gmail.com','2022-10-26 23:13:26',1,'https://storage.googleapis.com/edukate-system.appspot.com/dfad2776-6f64-4c35-b83a-bf66e619b838svg','2002-03-15 00:00:00','vutuankiet2411@gmail.com','Facebook Link',1,'LinkedIn','Instagram Link','Vũ Tuấn Kiệt','$2a$10$0m0bm44hn4FbtD3RsWN8yupoj9XGUof7EHEFlSc/oia.jB3dOszVm','0385789333','Twitter Link','Kiệt Vũ','Youtube Link',2),(4,'nvd@gmail.com','2022-10-22 14:58:59','nvd@gmail.com','2022-10-22 14:58:59',1,'https://storage.googleapis.com/edukate-system.appspot.com/c8e157a4-5863-427d-88e9-24ef7abfaf7asvg','2000-05-27 00:00:00','ktl@gmail.com','',1,'','','Kiều Trí Lăng','$2a$10$Cf60003jm1k7c9b1z2esPu.tEzkQQzahpXxXogvaJSn4lG3xmH2UK','0382222222','','Trí Lăng','',2),(5,NULL,NULL,'binhkdc@gmail.com','2022-10-29 10:12:41',1,'https://storage.googleapis.com/edukate-system.appspot.com/cf88b092-1fb9-475c-8e95-b5a0853e5e5apng','2002-04-16 00:00:00','binhkdc@gmail.com','',1,'','','Lê Đăng Bình','$2a$10$fVVHjmnxwmFvY6bM/Fu0x.l4gmVahn1Y6usdUNgNMOzQ4hJ95v.sO','0387659989','','Binh-KDC','',3),(6,'nvd@gmail.com','2022-10-25 18:10:36','nvd@gmail.com','2022-10-25 18:10:36',1,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/avatar_default.png?alt=media&token=b493b298-c1ce-4f76-9150-f5b6f6c743a3','2002-11-05 00:00:00','huongno@gmail.com','',0,'','','La Thị Hường','$2a$10$ZHvTPw.WklpZ6iRwdJg1wOiQapRJvf8FTIK9I3C1lq9QrkDRFkG8i','0953321989','','Hường Nỏ','',3),(7,'anonymousUser','2022-10-26 22:12:25','nvd@gmail.com','2022-10-26 22:13:13',0,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/avatar_default.png?alt=media&token=b493b298-c1ce-4f76-9150-f5b6f6c743a3','1999-09-29 00:00:00','thutrang@gmail.com','',0,'','','Phạm Thu Trang','$2a$10$jXCEE/RevBfzdXxLI/0XiOxfYqSg/uR9MmxkMBTZq17GZUVtQZ7PG','0382251956','','Trang Phạm','',3),(8,NULL,NULL,'minhduxng@gmail.com','2022-10-27 10:05:57',1,'https://storage.googleapis.com/edukate-system.appspot.com/3286d0ea-5509-45f0-9194-7b242fcea2e0png','2002-11-01 00:00:00','minhduxng@gmail.com','',1,'','','Hoàng Minh Dũng','$2a$10$G.8WWC1vdQCkyLqGUCo4zOeMEFYwrxA2KEkmfbMjpgZGdadAgc4Fi','0977861955','','Minh duxng','',3),(10,'nvd@gmail.com','2022-10-29 10:27:57','nvd@gmail.com','2022-10-29 10:27:57',1,'https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/avatar_default.png?alt=media&token=b493b298-c1ce-4f76-9150-f5b6f6c743a3','1997-10-21 00:00:00','mailinh@gmail.com','',0,'','','Trần Mai Linh','$2a$10$LcrnOOxkpn3dgK8JuNUMVeSr67fCLw/nKM9ZMlF5GoncLhBQ7rJiO','0973369199','','Linh Trần','',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `type` int DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_by` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` varchar(255) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `status` int NOT NULL,
  `description` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `thumbnail` varchar(500) DEFAULT NULL,
  `video_file` varchar(500) NOT NULL,
  `course_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `index_` int NOT NULL,
  `cmt` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgcpx5x8vhp9j5f9nhxlxqx45m` (`course_id`),
  KEY `FKlvftuhj7tfoq8kigg4lc2ps7p` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,NULL,NULL,'nvd@gmail.com','2022-10-25 09:03:13',2,'<p>M&ocirc; tả video</p>','Bài học số 1','https://storage.googleapis.com/edukate-system.appspot.com/219c690a-7416-4484-ac83-31c49be63dc6png','https://storage.googleapis.com/edukate-system.appspot.com/3c63bfd7-461c-454a-a230-f6be34f85143mp4',1,2,1,'Đạt yêu cầu'),(2,NULL,NULL,'ddk@gmail.com','2022-10-25 10:09:35',0,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 2','https://storage.googleapis.com/edukate-system.appspot.com/0672b93b-a6bd-48cd-857c-c73afa8113ddpng','https://storage.googleapis.com/edukate-system.appspot.com/9b320d70-7985-4602-b45f-f375d7a6df0amp4',1,2,2,'Thumbnail không phù hợp'),(3,NULL,NULL,'ddk@gmail.com','2022-10-25 08:34:42',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 3','https://storage.googleapis.com/edukate-system.appspot.com/ab8b74fe-5512-4b56-9123-222acb80da58png','https://storage.googleapis.com/edukate-system.appspot.com/d4da718e-69a3-4b3e-83a0-c43362cb03b2mp4',1,2,3,''),(4,NULL,NULL,'ddk@gmail.com','2022-10-25 09:53:53',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 4','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/788f615a-8422-4919-9a45-36d7a3f0ce4dmp4',1,2,4,''),(5,'ddk@gmail.com','2022-10-25 09:53:05','ddk@gmail.com','2022-10-25 09:53:05',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 5','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/3c5d049d-fe5e-4610-bf0f-8f0780c40735mp4',1,2,5,NULL),(6,'vutuankiet2411@gmail.com','2022-10-25 14:44:59','vutuankiet2411@gmail.com','2022-10-25 14:44:59',1,'<p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of &quot;de Finibus Bonorum et Malorum&quot; (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, &quot;Lorem ipsum dolor sit amet..&quot;, comes from a line in section 1.10.32.</p><p>The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from &quot;de Finibus Bonorum et Malorum&quot; by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham</p>','Bài học số 6','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/da2e5e30-af76-48f4-a1ea-d245725a8888mp4',1,3,6,NULL),(7,'vutuankiet2411@gmail.com','2022-10-25 14:45:37','vutuankiet2411@gmail.com','2022-10-25 14:45:37',1,'<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using &#39;Content here, content here&#39;, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for &#39;lorem ipsum&#39; will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>','Bài học số 7','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/dd98808f-6485-452c-97da-63931e884005mp4',1,3,7,NULL),(8,'vutuankiet2411@gmail.com','2022-10-25 14:46:48','vutuankiet2411@gmail.com','2022-10-25 14:46:48',1,'<p>It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using &#39;Content here, content here&#39;, making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for &#39;lorem ipsum&#39; will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).</p>','Bài học số 8','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/b0f816bc-09a1-4ee6-8c7d-b1fad62a1f1bmp4',1,3,8,NULL),(9,'vutuankiet2411@gmail.com','2022-10-25 14:47:47','vutuankiet2411@gmail.com','2022-10-25 14:47:47',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 9','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/ba94bde0-a2c6-4b98-9880-1f9c774d6219mp4',1,3,9,NULL),(10,'nvd@gmail.com','2022-10-27 09:17:54','nvd@gmail.com','2022-10-27 09:17:54',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 2.1','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/43e63a92-07cb-42cd-af74-16b5571a3962mp4',2,1,1,NULL),(11,'nvd@gmail.com','2022-10-27 09:18:47','nvd@gmail.com','2022-10-27 09:18:47',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 2.2','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/07889771-7eae-4980-9e6b-2bed602e8595mp4',2,1,2,NULL),(12,'nvd@gmail.com','2022-10-27 09:19:25','nvd@gmail.com','2022-10-27 09:19:25',1,'<p><strong>Lorem Ipsum</strong>&nbsp;is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry&#39;s standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>','Bài học số 3.1','https://firebasestorage.googleapis.com/v0/b/edukate-system.appspot.com/o/video_thumbnail_default.png?alt=media&token=a620226a-f8f9-4ab2-be8b-7773a4424b1b','https://storage.googleapis.com/edukate-system.appspot.com/15bd3e37-afb2-4088-9579-b802fe0fce8dmp4',3,1,1,NULL);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29 15:38:35
