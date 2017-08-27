DROP DATABASE agenda;
CREATE DATABASE agenda;
USE agenda;
CREATE TABLE Localidades
(  idLocalidad int(11) NOT NULL AUTO_INCREMENT,
   nombre_Localidad varchar(45) ,
   PRIMARY KEY (idLocalidad)
);
CREATE TABLE Etiquetas(
    idEtiqueta int(11) NOT NULL AUTO_INCREMENT,
	nombre_Etiqueta varchar(15) ,
    PRIMARY KEY (idEtiqueta)
);

CREATE TABLE Personas
(
    idPersona int(11) NOT NULL AUTO_INCREMENT,
  Nombre varchar(45) NOT NULL,
  Telefono varchar(20) ,
  Calle varchar(45) ,
  Altura varchar(11) ,
  Piso varchar(11) ,
  Departamento varchar(45) ,
  Email varchar(45) ,
  fechaNacimiento varchar(10) ,
  idLocalidad int(11),
  idEtiqueta int(11) ,

  PRIMARY KEY (idPersona),
  FOREIGN KEY (idLocalidad) REFERENCES Localidades(idLocalidad),
  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)

);

CREATE USER 'user'@'%' IDENTIFIED BY 'mypass';
GRANT RELOAD,PROCESS ON *.* TO 'user'@'%';

GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;
