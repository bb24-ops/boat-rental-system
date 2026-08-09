/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.40 : Database - izdavanje_brodova
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`izdavanje_brodova` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `izdavanje_brodova`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `idAdministrator` bigint NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `brojTelefona` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`idAdministrator`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administrator` */

insert  into `administrator`(`idAdministrator`,`ime`,`prezime`,`brojTelefona`,`username`,`password`) values 
(1,'Lazar','Mijovic','0612787111','laza','laza1'),
(2,'Vuk','Vasiljevic','0641122333','vasilja','vuk123'),
(3,'Luka','Jagers','0631221444','lukas','lukas3');

/*Table structure for table `brod` */

DROP TABLE IF EXISTS `brod`;

CREATE TABLE `brod` (
  `idBrod` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `tip` varchar(50) NOT NULL,
  `kategorija` varchar(50) NOT NULL,
  `cenaPoDanu` double NOT NULL,
  PRIMARY KEY (`idBrod`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `brod` */

insert  into `brod`(`idBrod`,`naziv`,`tip`,`kategorija`,`cenaPoDanu`) values 
(1,'Aurora','JAHTA','KATEGORIJA_C',50000),
(2,'Posejdon','KATAMARAN','KATEGORIJA_B',40000),
(3,'Zlatno Jedro','JEDRILICA','KATEGORIJA_B',30000),
(4,'Plava Laguna','GLISER','KATEGORIJA_A',25000),
(5,'Odisej','JET_SKI','KATEGORIJA_A',10000),
(6,'Argo ','JET_SKI','BEZ_KATEGORIJE',8000),
(14,'Gasssss','GLISER','KATEGORIJA_A',123);

/*Table structure for table `iznajmljivanje` */

DROP TABLE IF EXISTS `iznajmljivanje`;

CREATE TABLE `iznajmljivanje` (
  `idIznajmljivanje` bigint NOT NULL AUTO_INCREMENT,
  `datum` date DEFAULT NULL,
  `ukupanIznos` double NOT NULL,
  `administrator` bigint NOT NULL,
  `korisnik` bigint NOT NULL,
  PRIMARY KEY (`idIznajmljivanje`),
  KEY `administrator` (`administrator`),
  KEY `korisnik` (`korisnik`),
  CONSTRAINT `iznajmljivanje_ibfk_1` FOREIGN KEY (`administrator`) REFERENCES `administrator` (`idAdministrator`),
  CONSTRAINT `iznajmljivanje_ibfk_2` FOREIGN KEY (`korisnik`) REFERENCES `korisnik` (`idKorisnik`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `iznajmljivanje` */

insert  into `iznajmljivanje`(`idIznajmljivanje`,`datum`,`ukupanIznos`,`administrator`,`korisnik`) values 
(4,'2025-08-25',276000,1,1),
(7,'2025-08-23',200000,1,2),
(8,'2025-08-22',232000,1,2),
(9,'2025-08-12',196000,1,1),
(10,'2025-08-23',24000,1,2),
(20,'2025-08-23',33000,1,17),
(21,'2025-08-24',58000,1,2),
(22,'2025-08-24',50123,1,2),
(23,'2025-08-24',25000,1,2),
(24,'2026-12-24',50000,1,2),
(25,'2025-08-24',8000,1,2),
(26,'2025-08-25',50000,1,2),
(27,'2025-08-26',25000,1,2),
(28,'2025-08-26',50000,1,2),
(29,'2025-08-28',25000,1,17),
(30,'2025-08-24',50000,1,2),
(31,'2025-08-24',43000,1,2),
(32,'2025-08-26',58000,1,2);

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `idKorisnik` bigint NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) DEFAULT NULL,
  `prezime` varchar(50) DEFAULT NULL,
  `brojTelefona` varchar(50) DEFAULT NULL,
  `luka` bigint DEFAULT NULL,
  PRIMARY KEY (`idKorisnik`),
  KEY `luka` (`luka`),
  CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`luka`) REFERENCES `luka` (`idLuka`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`idKorisnik`,`ime`,`prezime`,`brojTelefona`,`luka`) values 
(1,'Filip','Zivic','0612787333',3),
(2,'Milos','Milosevic','062111111',1),
(3,'Vojislav','Buduric','0636677888',2),
(8,'Milos','Zurkic','06412351',7),
(17,'Marko','Mikic','06412356',3),
(21,'asdasd','asdasd','224234',3),
(22,'asdasd','adsad','344234',1);

/*Table structure for table `luka` */

DROP TABLE IF EXISTS `luka`;

CREATE TABLE `luka` (
  `idLuka` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  `brMesta` bigint NOT NULL,
  PRIMARY KEY (`idLuka`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `luka` */

insert  into `luka`(`idLuka`,`naziv`,`brMesta`) values 
(1,'Mirna Obala',100),
(2,'Zlatni Zaliv',200),
(3,'Plavi Horizont',400),
(7,'SSSS',123);

/*Table structure for table `skiper` */

DROP TABLE IF EXISTS `skiper`;

CREATE TABLE `skiper` (
  `idSkiper` bigint NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `brojTerminaUkupno` bigint NOT NULL,
  `sertifikat` varchar(100) NOT NULL,
  PRIMARY KEY (`idSkiper`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `skiper` */

insert  into `skiper`(`idSkiper`,`ime`,`brojTerminaUkupno`,`sertifikat`) values 
(1,'Marko',25,'Kraljevska Jedriličarska Asocijacija.'),
(2,'Nikola',50,'Međunarodni Sertifikat o Osposobljenosti.'),
(3,'Boris',10,'Sertifikat za upravljanje vodenim skuterima.'),
(4,'Mirko',5,'Gasiram se Miki jako'),
(36,'Isak',150,'Kraljevska mornaricka brigada');

/*Table structure for table `skiperdez` */

DROP TABLE IF EXISTS `skiperdez`;

CREATE TABLE `skiperdez` (
  `idSkiperDez` bigint NOT NULL AUTO_INCREMENT,
  `administrator` bigint NOT NULL,
  `skiper` bigint NOT NULL,
  `datumDezurstva` date NOT NULL,
  PRIMARY KEY (`idSkiperDez`),
  KEY `administrator` (`administrator`),
  KEY `skiper` (`skiper`),
  CONSTRAINT `skiperdez_ibfk_1` FOREIGN KEY (`administrator`) REFERENCES `administrator` (`idAdministrator`),
  CONSTRAINT `skiperdez_ibfk_2` FOREIGN KEY (`skiper`) REFERENCES `skiper` (`idSkiper`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `skiperdez` */

insert  into `skiperdez`(`idSkiperDez`,`administrator`,`skiper`,`datumDezurstva`) values 
(1,1,4,'2025-01-10'),
(2,2,2,'2025-01-01'),
(3,3,1,'2025-01-19'),
(4,1,2,'2025-08-24'),
(5,1,1,'2025-08-24'),
(6,1,2,'2025-08-26'),
(7,1,4,'2025-08-24'),
(8,1,1,'2025-08-28');

/*Table structure for table `stavkaizn` */

DROP TABLE IF EXISTS `stavkaizn`;

CREATE TABLE `stavkaizn` (
  `rbStavke` bigint NOT NULL AUTO_INCREMENT,
  `iznajmljivanje` bigint NOT NULL,
  `datumIzdavanja` date DEFAULT NULL,
  `datumPovratka` date DEFAULT NULL,
  `brojDana` bigint DEFAULT NULL,
  `brod` bigint DEFAULT NULL,
  `iznosJedneStavke` double DEFAULT NULL,
  PRIMARY KEY (`rbStavke`,`iznajmljivanje`),
  KEY `iznajmljivanje` (`iznajmljivanje`),
  KEY `brod` (`brod`),
  CONSTRAINT `stavkaizn_ibfk_1` FOREIGN KEY (`iznajmljivanje`) REFERENCES `iznajmljivanje` (`idIznajmljivanje`),
  CONSTRAINT `stavkaizn_ibfk_2` FOREIGN KEY (`brod`) REFERENCES `brod` (`idBrod`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaizn` */

insert  into `stavkaizn`(`rbStavke`,`iznajmljivanje`,`datumIzdavanja`,`datumPovratka`,`brojDana`,`brod`,`iznosJedneStavke`) values 
(1,4,'2025-08-22','2025-08-22',1,1,50000),
(1,8,'2025-08-11','2025-08-13',3,1,150000),
(1,9,'2025-08-12','2025-08-13',2,1,100000),
(1,20,'2025-08-23','2025-08-23',1,6,8000),
(1,21,'2025-08-24','2025-08-24',1,1,50000),
(1,22,'2025-08-24','2025-08-24',1,1,50000),
(1,23,'2025-08-24','2025-08-24',1,4,25000),
(1,24,'2025-08-24','2025-08-24',1,1,50000),
(1,25,'2025-08-24','2025-08-24',1,6,8000),
(1,26,'2025-08-25','2025-08-25',1,1,50000),
(1,27,'2025-08-26','2025-08-26',1,4,25000),
(1,28,'2025-08-26','2025-08-26',1,1,50000),
(1,29,'2025-08-24','2025-08-24',1,4,25000),
(1,30,'2025-08-24','2025-08-24',1,1,50000),
(1,31,'2025-08-24','2025-08-24',1,6,8000),
(1,32,'2025-08-26','2025-08-26',1,6,8000),
(2,4,'2025-08-25','2025-08-27',3,5,30000),
(2,7,'2025-08-11','2025-08-15',5,2,200000),
(2,8,'2025-08-11','2025-08-11',1,6,8000),
(2,9,'2025-08-12','2025-08-13',2,2,80000),
(2,10,'2025-08-23','2025-08-25',3,6,24000),
(2,20,'2025-08-23','2025-08-23',1,4,25000),
(2,21,'2025-08-24','2025-08-24',1,6,8000),
(2,22,'2025-08-24','2025-08-24',1,14,123),
(2,31,'2025-08-24','2025-08-24',1,4,25000),
(2,32,'2025-08-26','2025-08-27',2,4,50000),
(3,4,'2025-08-25','2025-08-26',2,6,16000),
(3,8,'2025-08-11','2025-08-13',3,6,24000),
(3,9,'2025-08-12','2025-08-13',2,6,16000),
(3,31,'2025-08-24','2025-08-24',1,5,10000),
(4,4,'2025-08-25','2025-08-30',6,3,180000),
(4,8,'2025-08-22','2025-08-22',1,1,50000);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
