CREATE TABLE Detalle_Unidad (
  Num_Archivo INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(150) NOT NULL,
  Directorio VARCHAR(300) NULL,
  Etiqueta VARCHAR(50) NULL,
  PRIMARY KEY(Num_Archivo)
);

CREATE TABLE Unidad (
  Etiqueta VARCHAR(50) NOT NULL,
  Cantidad_Archivos INTEGER UNSIGNED NULL,
  PRIMARY KEY(Etiqueta)
);

DELIMITER $$

DROP PROCEDURE IF EXISTS `bd_files_controller`.`sp_ingresar_unidad` $$
CREATE PROCEDURE `bd_files_controller`.`sp_ingresar_unidad` (eti nvarchar(50), cant integer)
BEGIN
insert into unidad(Etiqueta,Cantidad_Archivos) values(eti,cant);
END $$

DELIMITER ;

DELIMITER $$

DROP PROCEDURE IF EXISTS `bd_files_controller`.`sp_ingresar_detalle_unidad` $$
CREATE PROCEDURE `bd_files_controller`.`sp_ingresar_detalle_unidad` (nom nvarchar(150), dir nvarchar(300), eti nvarchar(50))
BEGIN
insert into detalle_unidad(Nombre,Directorio,Etiqueta) values(nom,dir,eti);
END $$

DELIMITER ;
