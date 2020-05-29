# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.5.5-10.4.13-MariaDB-1:10.4.13+maria~bionic)
# Database: notice-board
# Generation Time: 2020-05-29 09:22:34 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table BOARD_LIST
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BOARD_LIST`;

CREATE TABLE `BOARD_LIST` (
  `ID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL DEFAULT '',
  `CONTENTS` varchar(1000) NOT NULL DEFAULT '',
  `IMGPATH` varchar(300) DEFAULT NULL,
  `REGDATE` date NOT NULL,
  `CHGDATE` date NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `BOARD_LIST` WRITE;
/*!40000 ALTER TABLE `BOARD_LIST` DISABLE KEYS */;

INSERT INTO `BOARD_LIST` (`ID`, `TITLE`, `CONTENTS`, `IMGPATH`, `REGDATE`, `CHGDATE`)
VALUES
	(23,'q','q',NULL,'2020-05-27','2020-05-27'),
	(24,'q','q',NULL,'2020-05-27','2020-05-27'),
	(26,'33','33',NULL,'2020-05-27','2020-05-27'),
	(27,'1','1',NULL,'2020-05-28','2020-05-28'),
	(29,'안녕안녕','그랭~','ba90fcae-d843-4d50-9cd5-4ebed709e3f3/98.PDF','2020-05-28','2020-05-29'),
	(30,'fff','ffff',NULL,'2020-05-28','2020-05-28'),
	(31,'1','1',NULL,'2020-05-28','2020-05-29'),
	(32,'121','123',NULL,'2020-05-28','2020-05-29'),
	(33,'날아올라','고양이',NULL,'2020-05-29','2020-05-29'),
	(34,'왕문곰','왕문곰jpg','85adb9cd-efac-4eb7-83dd-86ecb6ffa80e/wangmungom.jpg','2020-05-29','2020-05-29'),
	(35,'2','2','ff376e03-d464-473f-8c08-786a77e934db/8.png','2020-05-29','2020-05-29'),
	(36,'왕문곰','22',NULL,'2020-05-29','2020-05-29'),
	(37,'isEmpty','isEmpty',NULL,'2020-05-29','2020-05-29'),
	(38,'img','img',NULL,'2020-05-29','2020-05-29'),
	(39,'d','d','94c69ece-e309-42b9-a89d-38ea487a867e/비_웅덩이_배경화면.jpg','2020-05-29','2020-05-29'),
	(40,'고양이','고야잉','c903430b-2412-4ffc-90fb-2800b69f8768/2날아올라.png','2020-05-29','2020-05-29');

/*!40000 ALTER TABLE `BOARD_LIST` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
