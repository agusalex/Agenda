DROP DATABASE agenda;
CREATE DATABASE agenda;
USE agenda;
CREATE TABLE Localidades
(  idLocalidad int(11) NOT NULL AUTO_INCREMENT,
   nombre_Localidad varchar(45) NOT NULL,
   PRIMARY KEY (idLocalidad)
);
CREATE TABLE Etiquetas
(
    idEtiqueta int(11) NOT NULL AUTO_INCREMENT,
	nombre_Etiqueta varchar(15) NOT NULL,
    PRIMARY KEY (idEtiqueta)
);

CREATE TABLE Personas
(
  idPersona int(11) NOT NULL AUTO_INCREMENT,
  Nombre varchar(45) NOT NULL,
  Telefono varchar(20) NOT NULL,
  Calle varchar(45) NOT NULL,
  Altura int(11) NOT NULL,
  Piso int(11) NOT NULL,
  Departamento varchar(45) NOT NULL,
  Email varchar(45) NOT NULL,
  fechaNacimiento Date NOT NULL,
  idLocalidad int(11)NOT NULL,
  idEtiqueta int(11) NOT NULL,

  PRIMARY KEY (idPersona),
  FOREIGN KEY (idLocalidad) REFERENCES Localidades(idLocalidad),
  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)

);
CREATE USER 'user'@'%' IDENTIFIED BY 'mypass';
GRANT RELOAD,PROCESS ON *.* TO 'user'@'%';

GRANT ALL PRIVILEGES ON *.* TO 'user'@'%' WITH GRANT OPTION;
