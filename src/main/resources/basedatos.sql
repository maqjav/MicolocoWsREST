-- Tabla de estaciones
CREATE TABLE ESTACION (
	ID INTEGER IDENTITY NOT NULL,
	NOMBRE VARCHAR(25)
);

-- Tabla de setas
CREATE TABLE SETA (
	ID INTEGER IDENTITY NOT NULL,
	NOMBRE VARCHAR(25),
	DESCRIPCION VARCHAR(250),
	IDESTACION INTEGER,
	COMESTIBLE BOOLEAN,
	CONFIRMADA BOOLEAN
);

-- Tabla de ubicaciones
CREATE TABLE UBICACION (
	ID INTEGER IDENTITY NOT NULL,
	IDSETA INTEGER NOT NULL,
	FECHA TIMESTAMP,
	LONGITUD VARCHAR(25),
	LATITUD VARCHAR(25),
	DESCRIPCION VARCHAR(250),
	BUSCADOR VARCHAR(100)
);

-- Tabla de imagenes
CREATE TABLE IMAGEN (
	ID INTEGER IDENTITY NOT NULL,
	IDSETA INTEGER NOT NULL,
	CONTENIDO BLOB,
	NOMBRE VARCHAR(25)
);

-- Tabla de usuarios
CREATE TABLE USUARIO (
	ID INTEGER IDENTITY NOT NULL,
	EMAIL VARCHAR(25) NOT NULL,
	PASSWORD VARCHAR(8)
);

-- Indices
ALTER TABLE SETA ADD FOREIGN KEY(IDESTACION) REFERENCES ESTACION(ID);
ALTER TABLE UBICACION ADD FOREIGN KEY(IDSETA) REFERENCES SETA(ID);
ALTER TABLE IMAGEN ADD FOREIGN KEY(IDSETA) REFERENCES SETA(ID);

------------------
-- INSERCCIONES --
------------------

-- Tabla de estaciones
INSERT INTO ESTACION (ID, NOMBRE) VALUES (1, 'Invierno');
INSERT INTO ESTACION (ID, NOMBRE) VALUES (2, 'Primavera');
INSERT INTO ESTACION (ID, NOMBRE) VALUES (3, 'Verano');
INSERT INTO ESTACION (ID, NOMBRE) VALUES (4, 'Otoño');

-- Tabla de setas
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (1, 'Amanita caesarea', 'La Amanita caesarea es una seta que bla bla bla', 1, true, true);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (2, 'Lactarius deliciosus', 'La Lactarius deliciosus es una seta que bla bla bla', 2, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (3, 'Boletos aereus', 'El Boletos aereus es una seta que bla bla bla', 2, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (4, 'Amanita muscaria', 'La Amanita muscaria es una seta que bla bla bla', 4, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (5, 'Clathrus ruber', 'Clathrus ruber es una seta que bla bla bla', 3, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (6, 'Clitocybe dealbata', 'La Clitocybe dealbata es una seta que bla bla bla', 3, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (7, 'Lepista nuda', 'La Lepista nuda es una seta que bla bla bla', 3, true, false);
INSERT INTO SETA (ID, NOMBRE, DESCRIPCION, IDESTACION, COMESTIBLE, CONFIRMADA) VALUES (8, 'Morchella vulgaris', 'La Morchella vulgaris es una seta que bla bla bla', 4, true, false);

-- Tabla de ubicaciones
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (1, 1, '2016-01-31 12:00:05', '40.2699283', '2.4213452', 'La ubicacion 1 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (2, 1, '2016-01-31 12:10:24', '41.2699283', '1.2312452', 'La ubicacion 2 es muy bla bla bla', 'test2@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (3, 2, '2016-03-04 12:51:20', '42.2699283', '2.1112352', 'La ubicacion 3 es muy bla bla bla', 'test3@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (4, 2, '2016-04-02 16:43:16', '43.2699283', '3.9999452', 'La ubicacion 4 es muy bla bla bla', 'test4@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (5, 3, '2016-03-04 12:51:20', '44.2699283', '4.1226122', 'La ubicacion 5 es muy bla bla bla', 'test5@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (6, 3, '2016-04-02 16:43:16', '45.2699283', '5.5123452', 'La ubicacion 6 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (7, 4, '2016-10-06 20:23:30', '44.2699283', '4.1223452', 'La ubicacion 7 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (8, 4, '2016-10-21 18:41:46', '45.2699283', '5.1229023', 'La ubicacion 8 es muy bla bla bla', 'test2@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (9, 5, '2016-06-01 15:33:02', '46.2699283', '8.1223000', 'La ubicacion 9 es muy bla bla bla', 'test2@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (10, 5, '2016-06-15 09:17:03', '47.2699283', '9.1223452', 'La ubicacion 10 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (11, 6, '2016-07-20 23:10:00', '46.2671283', '8.1778152', 'La ubicacion 11 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (12, 6, '2016-06-30 06:38:46', '47.2111283', '10.1981452', 'La ubicacion 12 es muy bla bla bla', 'test5@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (13, 7, '2016-10-15 13:31:53', '47.2612283', '9.1223452', 'La ubicacion 13 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (14, 7, '2016-11-01 13:26:50', '49.2121283', '8.8812152', 'La ubicacion 14 es muy bla bla bla', 'test1@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (15, 7, '2016-10-06 12:07:06', '47.2111283', '10.8121452', 'La ubicacion 15 es muy bla bla bla', 'test3@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (16, 8, '2016-11-01 20:56:51', '49.2991283', '8.8812152', 'La ubicacion 16 es muy bla bla bla', 'test2@mail.com');
INSERT INTO UBICACION (ID, IDSETA, FECHA, LONGITUD, LATITUD, DESCRIPCION, BUSCADOR) VALUES (17, 8, '2016-10-06 19:00:02', '47.2000083', '10.1231452', 'La ubicacion 17 es muy bla bla bla', 'test3@mail.com');

-- Tabla de usuarios
INSERT INTO USUARIO (ID, EMAIL, PASSWORD) VALUES (1, 'test1@mail.com', '12345678');
INSERT INTO USUARIO (ID, EMAIL, PASSWORD) VALUES (2, 'test2@mail.com', '12345678');
INSERT INTO USUARIO (ID, EMAIL, PASSWORD) VALUES (3, 'test3@mail.com', '12345678');
INSERT INTO USUARIO (ID, EMAIL, PASSWORD) VALUES (4, 'test4@mail.com', '12345678');
INSERT INTO USUARIO (ID, EMAIL, PASSWORD) VALUES (5, 'test5@mail.com', '12345678');