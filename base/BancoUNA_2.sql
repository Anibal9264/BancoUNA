-- MySQL dump 10.13  Distrib 8.0.17, for macos10.14 (x86_64)
--
-- Host: localhost    Database: BancoUNA
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `Cuenta`
--

DROP TABLE IF EXISTS `Cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cuenta` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `saldo` double DEFAULT '0',
  `estado` tinyint(1) DEFAULT '0',
  `descripcion` varchar(45) DEFAULT NULL,
  `interesesG` double DEFAULT '0',
  `limite` double DEFAULT '0',
  `Usuario_cedula` varchar(9) NOT NULL,
  `Moneda_id` varchar(3) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `fk_Cuenta_Usuario_idx` (`Usuario_cedula`),
  KEY `fk_Cuenta_Moneda1_idx` (`Moneda_id`),
  CONSTRAINT `fk_Cuenta_Moneda1` FOREIGN KEY (`Moneda_id`) REFERENCES `moneda` (`id`),
  CONSTRAINT `fk_Cuenta_Usuario` FOREIGN KEY (`Usuario_cedula`) REFERENCES `usuario` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cuenta`
--

LOCK TABLES `Cuenta` WRITE;
/*!40000 ALTER TABLE `Cuenta` DISABLE KEYS */;
INSERT INTO `Cuenta` VALUES (1,5000,1,'Cuenta Corriente',0,2000,'604040087','CRC'),(2,20.532695093086517,1,'Cuenta Dolares',0,10,'604040087','USD'),(3,3003,1,'Cuenta Corriente',0,3000,'123','CRC');
/*!40000 ALTER TABLE `Cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CuentaFavorita`
--

DROP TABLE IF EXISTS `CuentaFavorita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CuentaFavorita` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `Cuenta_numero` int(11) NOT NULL,
  `Usuario_cedula` varchar(9) NOT NULL,
  PRIMARY KEY (`numero`),
  KEY `fk_CuentaFavorita_Cuenta1_idx` (`Cuenta_numero`),
  KEY `fk_CuentaFavorita_Usuario1_idx` (`Usuario_cedula`),
  CONSTRAINT `fk_CuentaFavorita_Cuenta1` FOREIGN KEY (`Cuenta_numero`) REFERENCES `cuenta` (`numero`),
  CONSTRAINT `fk_CuentaFavorita_Usuario1` FOREIGN KEY (`Usuario_cedula`) REFERENCES `usuario` (`cedula`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CuentaFavorita`
--

LOCK TABLES `CuentaFavorita` WRITE;
/*!40000 ALTER TABLE `CuentaFavorita` DISABLE KEYS */;
INSERT INTO `CuentaFavorita` VALUES (1,3,'604040087'),(3,2,'123');
/*!40000 ALTER TABLE `CuentaFavorita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Deposito`
--

DROP TABLE IF EXISTS `Deposito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Deposito` (
  `id` int(11) NOT NULL DEFAULT '0',
  `monto` double DEFAULT '0',
  `motivo` varchar(45) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `nombreDepositante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Deposito`
--

LOCK TABLES `Deposito` WRITE;
/*!40000 ALTER TABLE `Deposito` DISABLE KEYS */;
INSERT INTO `Deposito` VALUES (0,NULL,NULL,NULL,NULL),(1,2000,'prueba','2020-03-23 11:07:15','Maria Perez Seran'),(2,3,'cambio','2020-03-23 11:11:14','Anibal Chaves Badilla');
/*!40000 ALTER TABLE `Deposito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Moneda`
--

DROP TABLE IF EXISTS `Moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Moneda` (
  `id` varchar(3) NOT NULL,
  `tipo_cambio` double DEFAULT NULL,
  `interes` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Moneda`
--

LOCK TABLES `Moneda` WRITE;
/*!40000 ALTER TABLE `Moneda` DISABLE KEYS */;
INSERT INTO `Moneda` VALUES ('CRC',566.14,1.5),('EUR',0.89,2.3),('USD',1,2.8);
/*!40000 ALTER TABLE `Moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Movimiento`
--

DROP TABLE IF EXISTS `Movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Movimiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT NULL,
  `detalle` varchar(45) DEFAULT NULL,
  `Deposito_id` int(11) NOT NULL,
  `Retiro_id` int(11) NOT NULL,
  `Cuenta_numero` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Movimiento_Deposito1_idx` (`Deposito_id`),
  KEY `fk_Movimiento_Retiro1_idx` (`Retiro_id`),
  KEY `fk_Movimiento_Cuenta1_idx` (`Cuenta_numero`),
  CONSTRAINT `fk_Movimiento_Cuenta1` FOREIGN KEY (`Cuenta_numero`) REFERENCES `cuenta` (`numero`),
  CONSTRAINT `fk_Movimiento_Deposito1` FOREIGN KEY (`Deposito_id`) REFERENCES `deposito` (`id`),
  CONSTRAINT `fk_Movimiento_Retiro1` FOREIGN KEY (`Retiro_id`) REFERENCES `retiro` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Movimiento`
--

LOCK TABLES `Movimiento` WRITE;
/*!40000 ALTER TABLE `Movimiento` DISABLE KEYS */;
INSERT INTO `Movimiento` VALUES (1,'2020-03-23 11:07:15','prueba',0,1,3),(2,'2020-03-23 11:07:15','prueba',1,0,2),(3,'2020-03-23 11:11:14','cambio',0,2,2),(4,'2020-03-23 11:11:14','cambio',2,0,3);
/*!40000 ALTER TABLE `Movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Retiro`
--

DROP TABLE IF EXISTS `Retiro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Retiro` (
  `id` int(11) NOT NULL DEFAULT '0',
  `monto` double DEFAULT '0',
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Retiro`
--

LOCK TABLES `Retiro` WRITE;
/*!40000 ALTER TABLE `Retiro` DISABLE KEYS */;
INSERT INTO `Retiro` VALUES (0,NULL,NULL),(1,2000,'2020-03-23 11:07:15'),(2,3,'2020-03-23 11:11:14');
/*!40000 ALTER TABLE `Retiro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Usuario` (
  `cedula` varchar(9) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido1` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `is` tinyint(1) DEFAULT '0',
  `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cedula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES ('123','Maria','Perez','Seran','123',0,'88888888'),('604040087','Anibal','Chaves','Badilla','1509',1,'60193037');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-23 11:33:06
