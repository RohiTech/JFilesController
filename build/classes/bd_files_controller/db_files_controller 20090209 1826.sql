-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bd_files_controller
--

CREATE DATABASE IF NOT EXISTS bd_files_controller;
USE bd_files_controller;

--
-- Definition of table `detalle_unidad`
--

DROP TABLE IF EXISTS `detalle_unidad`;
CREATE TABLE `detalle_unidad` (
  `Num_Archivo` int(10) unsigned NOT NULL auto_increment,
  `Nombre` varchar(150) NOT NULL,
  `Directorio` varchar(300) default NULL,
  `Etiqueta` varchar(50) default NULL,
  PRIMARY KEY  (`Num_Archivo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `detalle_unidad`
--

/*!40000 ALTER TABLE `detalle_unidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_unidad` ENABLE KEYS */;


--
-- Definition of table `unidad`
--

DROP TABLE IF EXISTS `unidad`;
CREATE TABLE `unidad` (
  `Etiqueta` varchar(50) NOT NULL,
  `Cantidad_Archivos` int(10) unsigned default NULL,
  PRIMARY KEY  (`Etiqueta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unidad`
--

/*!40000 ALTER TABLE `unidad` DISABLE KEYS */;
/*!40000 ALTER TABLE `unidad` ENABLE KEYS */;


--
-- Definition of procedure `sp_ingresar_detalle_unidad`
--

DROP PROCEDURE IF EXISTS `sp_ingresar_detalle_unidad`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ingresar_detalle_unidad`(nom nvarchar(150), dir nvarchar(300), eti nvarchar(50))
BEGIN
insert into detalle_unidad(Nombre,Directorio,Etiqueta) values(nom,dir,eti);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_ingresar_unidad`
--

DROP PROCEDURE IF EXISTS `sp_ingresar_unidad`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_ingresar_unidad`(eti nvarchar(50), cant integer)
BEGIN
insert into unidad(Etiqueta,Cantidad_Archivos) values(eti,cant);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
