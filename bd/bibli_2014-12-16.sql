# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.31-MariaDB)
# Database: bibli
# Generation Time: 2014-12-17 04:22:47 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table bi_articles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_articles`;

CREATE TABLE `bi_articles` (
  `ISBN` varchar(17) NOT NULL,
  `TypeArticle` varchar(20) NOT NULL,
  `Titre` varchar(100) NOT NULL,
  `Resume` varchar(500) NOT NULL,
  `PrixUnitaire` decimal(5,2) NOT NULL,
  `IndicateurEnCommande` char(1) NOT NULL,
  `QuantiteEnCommande` decimal(4,0) NOT NULL,
  `DateParution` datetime NOT NULL,
  `MaisonEditionID` decimal(7,0) NOT NULL,
  `Langue` varchar(2) NOT NULL DEFAULT 'FR',
  `ageMinimum` int(11) NOT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `FK_BI_Articles_TypeArticle` (`TypeArticle`),
  KEY `FK_BI_Articles_MaisonEditionID` (`MaisonEditionID`),
  CONSTRAINT `FK_BI_Articles_TypeArticle` FOREIGN KEY (`TypeArticle`) REFERENCES `bi_typearticles` (`TypeArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_articles` WRITE;
/*!40000 ALTER TABLE `bi_articles` DISABLE KEYS */;

INSERT INTO `bi_articles` (`ISBN`, `TypeArticle`, `Titre`, `Resume`, `PrixUnitaire`, `IndicateurEnCommande`, `QuantiteEnCommande`, `DateParution`, `MaisonEditionID`, `Langue`, `ageMinimum`)
VALUES
	('008888528111','JEU','Assassin\'s Creed IV: Black Flag','Assassin\'s Creed 4 pour XBox 360',59.99,'0',0,'2013-10-05 00:00:00',2,'FR',15),
	('978-2-12345-012-1','DVD','Harry Potter et les reliques de la mort - 2e partie','Dans ce dernier opus spectaculaire, le combat entre les forces du bien et du mal dans le monde de la magie s\'intensifie et se transforme en guerre totale. Les enjeux n\'ont jamais été aussi importants et personne n\'est à l\'abri. Mais c\'est Harry Potter qui risque de devoir faire l\'ultime sacrifice au moment de la confrontation cruciale imminente avec Lord Voldemort.',18.99,'1',3,'2011-11-11 00:00:00',1,'FR',18),
	('978-2-70964-192-0','LI','Les neuf cercles','1974. De retour du Vietnam, John Gaines a accepté le poste de shérif de Whytesburg, Mississippi. Une petite ville tranquille jusqu’au jour où l’on découvre, enterré sur les berges de la rivière, le cadavre d’une adolescente. La surprise est de taille : celle-ci n’est autre que Nancy Denton, une jeune fille mystérieusement disparue vingt ans plus tôt, dont le corps a été préservé par la boue. L’autopsie révèle que son cœur a disparu, remplacé par un panier contenant la dépouille d’un serpent. Tra',16.99,'1',5,'2012-08-02 00:00:00',2,'FR',2);

/*!40000 ALTER TABLE `bi_articles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_articlesauteurs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_articlesauteurs`;

CREATE TABLE `bi_articlesauteurs` (
  `AuteurID` decimal(7,0) NOT NULL,
  `ISBN` varchar(17) NOT NULL,
  PRIMARY KEY (`AuteurID`,`ISBN`),
  KEY `FK_ArticlesAuteurs_ISBN` (`ISBN`),
  CONSTRAINT `FK_ArticlesAuteurs_AuteurID` FOREIGN KEY (`AuteurID`) REFERENCES `bi_auteurs` (`AuteurID`),
  CONSTRAINT `FK_ArticlesAuteurs_ISBN` FOREIGN KEY (`ISBN`) REFERENCES `bi_articles` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_articlesauteurs` WRITE;
/*!40000 ALTER TABLE `bi_articlesauteurs` DISABLE KEYS */;

INSERT INTO `bi_articlesauteurs` (`AuteurID`, `ISBN`)
VALUES
	(2,'978-2-12345-012-1'),
	(3,'978-2-70964-192-0');

/*!40000 ALTER TABLE `bi_articlesauteurs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_auteurs
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_auteurs`;

CREATE TABLE `bi_auteurs` (
  `AuteurID` decimal(7,0) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Pays` varchar(50) NOT NULL DEFAULT 'Canada',
  `SiteInternet` varchar(100) DEFAULT NULL,
  `AnneeNaissance` char(4) NOT NULL,
  `AnneeDeces` char(4) DEFAULT NULL,
  PRIMARY KEY (`AuteurID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_auteurs` WRITE;
/*!40000 ALTER TABLE `bi_auteurs` DISABLE KEYS */;

INSERT INTO `bi_auteurs` (`AuteurID`, `Nom`, `Prenom`, `Pays`, `SiteInternet`, `AnneeNaissance`, `AnneeDeces`)
VALUES
	(1,'James','Erika Leonard','Canada','','1963',''),
	(2,'Rowling','J.K.','Angleterre','','1965',''),
	(3,'Ellory','R.J.','Angleterre','http://www.rjellory.com/','1965','');

/*!40000 ALTER TABLE `bi_auteurs` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_commentaires
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_commentaires`;

CREATE TABLE `bi_commentaires` (
  `CommentaireID` decimal(7,0) NOT NULL,
  `EmpruntID` decimal(7,0) NOT NULL,
  `Commentaire` varchar(250) NOT NULL,
  `datetimeCommentaire` datetime NOT NULL,
  PRIMARY KEY (`CommentaireID`),
  KEY `FK_Commentaires_EmpruntID` (`EmpruntID`),
  CONSTRAINT `FK_Commentaires_EmpruntID` FOREIGN KEY (`EmpruntID`) REFERENCES `bi_emprunts` (`EmpruntID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_commentaires` WRITE;
/*!40000 ALTER TABLE `bi_commentaires` DISABLE KEYS */;

INSERT INTO `bi_commentaires` (`CommentaireID`, `EmpruntID`, `Commentaire`, `datetimeCommentaire`)
VALUES
	(1,1,'A perdu un livre.','2012-09-15 00:00:00'),
	(2,1,'A brisé un livre.','2012-09-14 00:00:00');

/*!40000 ALTER TABLE `bi_commentaires` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_copiesarticles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_copiesarticles`;

CREATE TABLE `bi_copiesarticles` (
  `NoArticle` decimal(7,0) NOT NULL,
  `ISBN` varchar(17) NOT NULL,
  `IndicateurDisponible` varchar(1) NOT NULL,
  PRIMARY KEY (`NoArticle`,`ISBN`),
  KEY `FK_CopiesArticles_ISBN` (`ISBN`),
  CONSTRAINT `FK_CopiesArticles_ISBN` FOREIGN KEY (`ISBN`) REFERENCES `bi_articles` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_copiesarticles` WRITE;
/*!40000 ALTER TABLE `bi_copiesarticles` DISABLE KEYS */;

INSERT INTO `bi_copiesarticles` (`NoArticle`, `ISBN`, `IndicateurDisponible`)
VALUES
	(1,'978-2-70964-192-0','1'),
	(2,'978-2-70964-192-0','1'),
	(3,'978-2-12345-012-1','1'),
	(4,'978-2-12345-012-1','1'),
	(5,'008888528111','1');

/*!40000 ALTER TABLE `bi_copiesarticles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_emprunts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_emprunts`;

CREATE TABLE `bi_emprunts` (
  `EmpruntID` decimal(7,0) NOT NULL,
  `NoMembre` decimal(7,0) NOT NULL,
  `NoArticle` decimal(7,0) NOT NULL,
  `dateEmprunt` datetime NOT NULL,
  `dateRetourPrevue` datetime NOT NULL,
  `dateRetour` datetime DEFAULT NULL,
  `NbJoursDeRetard` int(11) DEFAULT NULL,
  `AmendeParJour` decimal(3,2) NOT NULL,
  `IndicateurPerte` char(1) NOT NULL,
  `PrixUnitaire` decimal(5,2) NOT NULL,
  `TotalAmende` decimal(6,2) DEFAULT NULL,
  `ModePaiementCd` varchar(20) DEFAULT NULL,
  `ISBN` varchar(17) NOT NULL,
  PRIMARY KEY (`EmpruntID`),
  KEY `FK_Emprunts_NoMembre` (`NoMembre`),
  KEY `FK_Emprunts_NoArticle_ISBN` (`NoArticle`,`ISBN`),
  KEY `FK_Emprunts_ModePaiementCd` (`ModePaiementCd`),
  CONSTRAINT `FK_Emprunts_ModePaiementCd` FOREIGN KEY (`ModePaiementCd`) REFERENCES `bi_modespaiements` (`ModePaiementCd`),
  CONSTRAINT `FK_Emprunts_NoArticle_ISBN` FOREIGN KEY (`NoArticle`, `ISBN`) REFERENCES `bi_copiesarticles` (`NoArticle`, `ISBN`),
  CONSTRAINT `FK_Emprunts_NoMembre` FOREIGN KEY (`NoMembre`) REFERENCES `bi_membres` (`NoMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_emprunts` WRITE;
/*!40000 ALTER TABLE `bi_emprunts` DISABLE KEYS */;

INSERT INTO `bi_emprunts` (`EmpruntID`, `NoMembre`, `NoArticle`, `dateEmprunt`, `dateRetourPrevue`, `dateRetour`, `NbJoursDeRetard`, `AmendeParJour`, `IndicateurPerte`, `PrixUnitaire`, `TotalAmende`, `ModePaiementCd`, `ISBN`)
VALUES
	(1,1,2,'2012-01-01 00:00:00','2012-01-07 00:00:00','2014-12-16 22:07:48',NULL,2.00,'0',0.00,NULL,NULL,'978-2-70964-192-0'),
	(2,2,2,'2012-01-03 00:00:00','2012-01-04 00:00:00','2014-12-16 22:09:57',NULL,3.00,'1',0.00,NULL,NULL,'978-2-70964-192-0'),
	(3,2,1,'2012-01-03 00:00:00','2012-01-04 00:00:00','2014-12-16 22:10:15',NULL,3.00,'1',0.00,NULL,NULL,'978-2-70964-192-0'),
	(4,2,1,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:10:00',NULL,3.00,'1',0.00,NULL,NULL,'978-2-70964-192-0'),
	(5,3,4,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:13:29',NULL,3.00,'0',0.00,NULL,NULL,'978-2-12345-012-1'),
	(6,3,4,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:10:02',NULL,3.00,'0',0.00,NULL,NULL,'978-2-12345-012-1'),
	(7,3,3,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:10:45',NULL,3.00,'0',0.00,NULL,NULL,'978-2-12345-012-1'),
	(8,1,4,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:10:03',NULL,1.00,'1',0.00,NULL,NULL,'978-2-12345-012-1'),
	(9,2,3,'2012-09-05 00:00:00','2012-09-09 00:00:00','2014-12-16 22:13:31',NULL,1.00,'0',0.00,NULL,NULL,'978-2-12345-012-1'),
	(10,2,5,'2014-09-05 00:00:00','2014-09-09 00:00:00','2014-12-16 22:10:48',NULL,1.25,'0',0.00,NULL,NULL,'008888528111'),
	(11,1,4,'2014-12-16 21:31:38','2014-12-30 21:31:38','2014-12-16 22:15:21',NULL,0.00,'0',0.00,NULL,NULL,'978-2-12345-012-1'),
	(12,1,5,'2014-12-16 21:35:35','2014-12-30 21:35:35','2014-12-16 22:15:22',NULL,0.00,'0',0.00,NULL,NULL,'008888528111'),
	(13,4,5,'2014-12-16 22:15:55','2014-12-30 22:15:55','2014-12-16 22:19:36',NULL,0.00,'0',0.00,NULL,NULL,'008888528111'),
	(14,4,2,'2014-12-16 22:17:15','2014-12-30 22:17:15','2014-12-16 22:43:38',NULL,0.00,'0',0.00,NULL,NULL,'978-2-70964-192-0');

/*!40000 ALTER TABLE `bi_emprunts` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_maisonseditions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_maisonseditions`;

CREATE TABLE `bi_maisonseditions` (
  `MaisonEditionID` decimal(10,0) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Adresse` varchar(200) NOT NULL,
  `Ville` varchar(50) NOT NULL DEFAULT 'Québec',
  `CdPostal` char(7) NOT NULL,
  `ProvCode` char(2) NOT NULL,
  `Pays` varchar(50) NOT NULL DEFAULT 'Canada',
  `NoTel` char(14) NOT NULL,
  `NoFax` char(14) DEFAULT NULL,
  `Email` varchar(100) NOT NULL,
  `SiteInternet` varchar(100) DEFAULT NULL,
  `Contact` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MaisonEditionID`),
  KEY `FK_MaisonsEditions_ProvCode` (`ProvCode`),
  CONSTRAINT `FK_MaisonsEditions_ProvCode` FOREIGN KEY (`ProvCode`) REFERENCES `bi_provinces` (`ProvCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_maisonseditions` WRITE;
/*!40000 ALTER TABLE `bi_maisonseditions` DISABLE KEYS */;

INSERT INTO `bi_maisonseditions` (`MaisonEditionID`, `Nom`, `Adresse`, `Ville`, `CdPostal`, `ProvCode`, `Pays`, `NoTel`, `NoFax`, `Email`, `SiteInternet`, `Contact`)
VALUES
	(1,'Belle-oeuvre','192 rue de la chapelle','Québec','G0S 1N0','QC','Canada','(418) 333-4434','(418) 454-1212','belleoeuvre@hotmail.com','www.belle-oeuvre.com','Gérard'),
	(2,'Belle-horizon','99 rue des zombies','Québec','G4S 3N0','QC','Canada','(418) 222-4344','(418) 555-1122','bellehorizon@hotmail.com','www.belle-horizon.com','Roger');

/*!40000 ALTER TABLE `bi_maisonseditions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_membres
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_membres`;

CREATE TABLE `bi_membres` (
  `NoMembre` decimal(7,0) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `TypeMembre` char(1) NOT NULL,
  `Salutation` varchar(20) NOT NULL,
  `Addresse` varchar(100) NOT NULL,
  `Ville` varchar(50) NOT NULL DEFAULT 'Québec',
  `CodePostal` char(7) NOT NULL,
  `ProvCode` char(2) NOT NULL,
  `Pays` varchar(50) NOT NULL DEFAULT 'Canada',
  `NoTel` char(14) NOT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Login` varchar(20) NOT NULL,
  `MotPasse` varchar(255) NOT NULL DEFAULT '',
  `DernierLogin` date NOT NULL,
  `DateActivation` date NOT NULL,
  `QuestionSecrete` varchar(255) NOT NULL DEFAULT '',
  `ReponseSecrete` varchar(255) NOT NULL DEFAULT '',
  `EstActif` tinyint(1) NOT NULL,
  `DateNaissance` date NOT NULL,
  PRIMARY KEY (`NoMembre`),
  KEY `FK_Membres_TypeMembre` (`TypeMembre`),
  KEY `FK_Membres_ProvCode` (`ProvCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

LOCK TABLES `bi_membres` WRITE;
/*!40000 ALTER TABLE `bi_membres` DISABLE KEYS */;

INSERT INTO `bi_membres` (`NoMembre`, `Nom`, `Prenom`, `TypeMembre`, `Salutation`, `Addresse`, `Ville`, `CodePostal`, `ProvCode`, `Pays`, `NoTel`, `Email`, `Login`, `MotPasse`, `DernierLogin`, `DateActivation`, `QuestionSecrete`, `ReponseSecrete`, `EstActif`, `DateNaissance`)
VALUES
	(1,'Filion','Jean','1','M.','123 rue des Sapins','Québec','G4S 4H8','QC','Canada','(418) 222-6666','jean_filion@hotmail.com','Jean','65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5','2014-11-30','2014-11-30','Question','Réponse',1,'2013-11-30'),
	(2,'Lemay','Nicole','1','Mme.','1 rue des Peupliers','Québec','G2D 4H6','QC','Canada','(418) 332-4344','nicole_lemay@hotmail.com','Nicole','65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5','2014-11-30','2014-11-30','Question','Réponse',1,'1994-11-30'),
	(3,'Nadeau','Olivier','1','M.','76 rue des Pins','Québec','G1D 7J8','QC','Canada','(418) 123-4567','onadeau@cegepgarneau.ca','Olivier','65e84be33532fb784c48129675f9eff3a682b27168c0ea744b2cf58ee02337c5','2014-11-30','2014-11-30','Question','Réponse',1,'1994-11-30'),
	(4,'Garneau','Garneau','2','M.','cégep garneau','Québec','G1G 1G1','QC','Canada','(819) 123-1233','g@cegepgarneau.ca','garneau','daaad6e5604e8e17bd9f108d91e26afe6281dac8fda0091040a7a6d7bd9b43b5','2014-11-30','2014-11-30','Question','Réponse',1,'1994-11-30');

/*!40000 ALTER TABLE `bi_membres` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_modespaiements
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_modespaiements`;

CREATE TABLE `bi_modespaiements` (
  `ModePaiementCd` varchar(20) NOT NULL,
  `CdDescFr` varchar(50) NOT NULL,
  `CdDescEn` varchar(50) NOT NULL,
  PRIMARY KEY (`ModePaiementCd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_modespaiements` WRITE;
/*!40000 ALTER TABLE `bi_modespaiements` DISABLE KEYS */;

INSERT INTO `bi_modespaiements` (`ModePaiementCd`, `CdDescFr`, `CdDescEn`)
VALUES
	('C','Comptant','Cash'),
	('V','Visa','Visa');

/*!40000 ALTER TABLE `bi_modespaiements` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_produits
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_produits`;

CREATE TABLE `bi_produits` (
  `CodeProduit` decimal(7,0) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Description` varchar(200) NOT NULL,
  `PrixUnitaire` decimal(5,2) NOT NULL,
  `IndicateurTaxable` char(1) NOT NULL,
  `QteEnInventaire` decimal(5,0) NOT NULL,
  `NiveauRuptureStock` decimal(5,0) NOT NULL,
  `QteACommander` decimal(5,0) NOT NULL,
  PRIMARY KEY (`CodeProduit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_produits` WRITE;
/*!40000 ALTER TABLE `bi_produits` DISABLE KEYS */;

INSERT INTO `bi_produits` (`CodeProduit`, `Nom`, `Description`, `PrixUnitaire`, `IndicateurTaxable`, `QteEnInventaire`, `NiveauRuptureStock`, `QteACommander`)
VALUES
	(1,'Skor','Barre de chocolat Skor',3.00,'1',25,5,0),
	(2,'Ruffles Crème sure et oignon','Chips Ruffles à saveur de crème sure et oignon',2.00,'1',5,5,15),
	(3,'Dentyne aux fraises','Gomme DEntyne à saveur de fraise',2.50,'1',5,5,15);

/*!40000 ALTER TABLE `bi_produits` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_provinces
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_provinces`;

CREATE TABLE `bi_provinces` (
  `ProvCode` char(2) NOT NULL,
  `ProvDescFr` varchar(50) NOT NULL,
  `ProvDescEn` varchar(50) NOT NULL,
  `PcTaxeProv` decimal(3,3) NOT NULL,
  `PcTaxeFed` decimal(3,3) NOT NULL,
  PRIMARY KEY (`ProvCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_provinces` WRITE;
/*!40000 ALTER TABLE `bi_provinces` DISABLE KEYS */;

INSERT INTO `bi_provinces` (`ProvCode`, `ProvDescFr`, `ProvDescEn`, `PcTaxeProv`, `PcTaxeFed`)
VALUES
	('ON','Ontario','Ontario',0.080,0.070),
	('QC','Québec','Quebec',0.095,0.050);

/*!40000 ALTER TABLE `bi_provinces` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_reservation
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_reservation`;

CREATE TABLE `bi_reservation` (
  `IdReservation` decimal(10,0) NOT NULL,
  `NoMembre` decimal(7,0) NOT NULL,
  `NoArticles` varchar(17) NOT NULL,
  `dateReservation` date NOT NULL,
  PRIMARY KEY (`IdReservation`),
  UNIQUE KEY `IdReservation` (`IdReservation`),
  KEY `NoMembre` (`NoMembre`),
  KEY `NoMembre_2` (`NoMembre`),
  KEY `NoArticles` (`NoArticles`),
  CONSTRAINT `FK_NOARTICLE` FOREIGN KEY (`NoArticles`) REFERENCES `bi_articles` (`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_NOMEMBRE` FOREIGN KEY (`NoMembre`) REFERENCES `bi_membres` (`NoMembre`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_reservation` WRITE;
/*!40000 ALTER TABLE `bi_reservation` DISABLE KEYS */;

INSERT INTO `bi_reservation` (`IdReservation`, `NoMembre`, `NoArticles`, `dateReservation`)
VALUES
	(1,1,'978-2-12345-012-1','2014-12-16'),
	(2,1,'008888528111','2014-12-16'),
	(3,1,'008888528111','2014-12-16'),
	(4,1,'008888528111','2014-12-16');

/*!40000 ALTER TABLE `bi_reservation` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_typearticles
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_typearticles`;

CREATE TABLE `bi_typearticles` (
  `TypeArticle` varchar(20) NOT NULL,
  `TypeArticleDescFr` varchar(80) NOT NULL,
  `TypeArticleDescEn` varchar(80) NOT NULL,
  `AmendeParJour` decimal(3,2) NOT NULL,
  PRIMARY KEY (`TypeArticle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_typearticles` WRITE;
/*!40000 ALTER TABLE `bi_typearticles` DISABLE KEYS */;

INSERT INTO `bi_typearticles` (`TypeArticle`, `TypeArticleDescFr`, `TypeArticleDescEn`, `AmendeParJour`)
VALUES
	('BLU','Film Blu-ray','Blu-ray Movie',1.00),
	('DVD','Film DVD','DVD Movie',1.00),
	('JEU','Jeu vidéo','Video game',1.25),
	('LI','Livre','Book',0.20);

/*!40000 ALTER TABLE `bi_typearticles` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_typesmembres
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_typesmembres`;

CREATE TABLE `bi_typesmembres` (
  `TypeMembre` char(1) NOT NULL,
  `TypeDescFr` varchar(50) NOT NULL,
  `TypeDescEn` varchar(50) NOT NULL,
  `NbJoursSurEmprunt` decimal(2,0) NOT NULL,
  PRIMARY KEY (`TypeMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_typesmembres` WRITE;
/*!40000 ALTER TABLE `bi_typesmembres` DISABLE KEYS */;

INSERT INTO `bi_typesmembres` (`TypeMembre`, `TypeDescFr`, `TypeDescEn`, `NbJoursSurEmprunt`)
VALUES
	('1','Résident','Resident',7),
	('2','Entreprise','Entreprise',15),
	('3','Étudiant','Student',15);

/*!40000 ALTER TABLE `bi_typesmembres` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_ventes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_ventes`;

CREATE TABLE `bi_ventes` (
  `VenteID` decimal(7,0) NOT NULL,
  `NoMembre` decimal(7,0) NOT NULL,
  `ModePaiementCd` varchar(20) NOT NULL,
  `dateVente` datetime NOT NULL,
  `TotalVente` decimal(5,2) NOT NULL,
  `TaxeProvCourante` decimal(8,2) DEFAULT NULL,
  `TaxeFedCourante` decimal(8,2) DEFAULT NULL,
  `TotalTaxes` decimal(8,2) DEFAULT NULL,
  `GrandTotalVente` decimal(8,2) DEFAULT NULL,
  PRIMARY KEY (`VenteID`),
  KEY `FK_Ventes_NoMembre` (`NoMembre`),
  KEY `FK_Ventes_ModePaiementCd` (`ModePaiementCd`),
  CONSTRAINT `FK_Ventes_ModePaiementCd` FOREIGN KEY (`ModePaiementCd`) REFERENCES `bi_modespaiements` (`ModePaiementCd`),
  CONSTRAINT `FK_Ventes_NoMembre` FOREIGN KEY (`NoMembre`) REFERENCES `bi_membres` (`NoMembre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_ventes` WRITE;
/*!40000 ALTER TABLE `bi_ventes` DISABLE KEYS */;

INSERT INTO `bi_ventes` (`VenteID`, `NoMembre`, `ModePaiementCd`, `dateVente`, `TotalVente`, `TaxeProvCourante`, `TaxeFedCourante`, `TotalTaxes`, `GrandTotalVente`)
VALUES
	(1,1,'V','2012-01-02 00:00:00',6.00,NULL,NULL,NULL,NULL),
	(2,1,'C','2012-01-02 00:00:00',2.00,NULL,NULL,NULL,NULL),
	(3,1,'C','2012-09-03 00:00:00',3.00,NULL,NULL,NULL,NULL),
	(4,1,'C','2012-08-20 00:00:00',11.00,NULL,NULL,NULL,NULL),
	(5,1,'C','2012-08-20 00:00:00',2.50,NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `bi_ventes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bi_ventesproduits
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bi_ventesproduits`;

CREATE TABLE `bi_ventesproduits` (
  `VenteID` decimal(7,0) NOT NULL,
  `CodeProduit` decimal(7,0) NOT NULL,
  `IndicateurTaxable` char(1) NOT NULL,
  `QteAchetee` decimal(5,0) NOT NULL,
  `PrixUnitaire` decimal(6,2) NOT NULL,
  `TotalAchatProduit` decimal(6,2) NOT NULL,
  PRIMARY KEY (`VenteID`,`CodeProduit`),
  KEY `FK_VentesProduits_CodProd` (`CodeProduit`),
  CONSTRAINT `FK_VentesProduits_CodProd` FOREIGN KEY (`CodeProduit`) REFERENCES `bi_produits` (`CodeProduit`),
  CONSTRAINT `FK_VentesProduits_VenteID` FOREIGN KEY (`VenteID`) REFERENCES `bi_ventes` (`VenteID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `bi_ventesproduits` WRITE;
/*!40000 ALTER TABLE `bi_ventesproduits` DISABLE KEYS */;

INSERT INTO `bi_ventesproduits` (`VenteID`, `CodeProduit`, `IndicateurTaxable`, `QteAchetee`, `PrixUnitaire`, `TotalAchatProduit`)
VALUES
	(1,1,'1',2,3.00,6.00),
	(2,2,'1',1,2.00,2.00),
	(3,1,'1',1,3.00,3.00),
	(4,2,'1',2,2.00,6.00),
	(4,3,'1',2,2.50,5.00),
	(5,3,'1',1,2.50,2.50);

/*!40000 ALTER TABLE `bi_ventesproduits` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
