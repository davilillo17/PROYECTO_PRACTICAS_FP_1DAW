DROP DATABASE PORTAL_EVENTOS_SOSTENIBLES;
CREATE DATABASE PORTAL_EVENTOS_SOSTENIBLES;
USE PORTAL_EVENTOS_SOSTENIBLES;

CREATE TABLE USUARIOS (
ID_USUARIO INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE_USUARIO VARCHAR(100),
EMAIL_USUARIO VARCHAR(100),
CONTRASEÑA_USUARIO VARCHAR(100)
);

CREATE TABLE ORGANIZADORES (
ID_ORGANIZADOR INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE_ORGANIZADOR VARCHAR(100),
EMAIL_ORGANIZADOR VARCHAR(100),
TELEFONO_ORGANIZADOR VARCHAR(20),
EVENTOS_CREADOS INT
);

CREATE TABLE CATEGORIAS (
ID_CATEGORIA INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE_CATEGORIA VARCHAR(100)
);

CREATE TABLE UBICACION (
ID_UBICACION INT AUTO_INCREMENT PRIMARY KEY,
TIPO_UBICACION VARCHAR(100),
DIRECCION_UBICACION VARCHAR(250),
ENLACE_UBICACION VARCHAR(250)
);

CREATE TABLE EVENTOS (
ID_EVENTO INT AUTO_INCREMENT PRIMARY KEY,
NOMBRE_EVENTO VARCHAR(100),
DESCRIPCION_EVENTO TEXT,
FECHA_EVENTO DATE,
DURACION_EVENTO DOUBLE,
ID_CATEGORIA INT,
ID_UBICACION INT,
ID_ORGANIZADOR INT,
FOREIGN KEY (ID_CATEGORIA) REFERENCES CATEGORIAS(ID_CATEGORIA),
FOREIGN KEY (ID_UBICACION) REFERENCES UBICACION(ID_UBICACION)
);

CREATE TABLE INSCRIPCIONES (
ID_USUARIO INT,
ID_EVENTO INT,
ESTADO_INSCRIPCION ENUM ('ACTIVA', 'CANCELADA') DEFAULT ('ACTIVA'),
FECHA_INSCRIPCION DATE,
FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS(ID_USUARIO),
FOREIGN KEY (ID_EVENTO) REFERENCES EVENTOS(ID_EVENTO)
);


INSERT INTO USUARIOS (ID_USUARIO, NOMBRE_USUARIO, EMAIL_USUARIO, CONTRASEÑA_USUARIO) VALUES
(1, 'David Colorado', 'david@gmail.com', 'david123'),
(2, 'Jorge Morales', 'jorge@gmail.com', 'jorge456'),
(3, 'Jose Andre', 'jose@gmail.com', 'jose789'),
(4, 'Adrián Espada', 'adrian@gmail.com', 'adrian123'),
(5, 'Lucía Martín', 'lucia@gmail.com', 'lucia456'),
(6, 'Manuel Pérez', 'manuel@gmail.com', 'manuel789'),
(7, 'Marta López', 'marta@gmail.com', 'marta123');

INSERT INTO ORGANIZADORES (ID_ORGANIZADOR, NOMBRE_ORGANIZADOR, EMAIL_ORGANIZADOR, TELEFONO_ORGANIZADOR, EVENTOS_CREADOS) VALUES
(1, 'David', 'david@ecomundo.com', '11111111', 2),
(2, 'Javier', 'javier@ecomundo.com', '22222222', 1),
(3, 'Iker', 'iker@ecomundo.com', '33333333', 1),
(4, 'Carlos', 'carlos@ecomundo.com', '44444444', 1),
(5, 'Sergio', 'sergio@ecomundo.com', '55555555', 1),
(6, 'Mario', 'mario@ecomundo.com', '66666666', 0),
(7, 'Dario', 'dario@ecomundo.es', '77777777', 1);

INSERT INTO CATEGORIAS (ID_CATEGORIA, NOMBRE_CATEGORIA) VALUES
(1, 'Ecoinnovación'),
(2, 'Conferencia'),
(3, 'Actividad ecológica'),
(4, 'Moda sostenible'),
(5, 'Agricultura ecológica'),
(6, 'Charla'),
(7, 'Educación ambiental');

INSERT INTO UBICACION (ID_UBICACION, TIPO_UBICACION, DIRECCION_UBICACION, ENLACE_UBICACION) VALUES
(1, 'presencial', 'Av. Ecología 101, Madrid', NULL),
(2, 'presencial', 'Calle Verde 23, Sevilla', NULL),
(3, 'online', NULL, 'https://evento1.eco'),
(4, 'online', NULL, 'https://evento2.sostenible'),
(5, 'presencial', 'Plaza verde 12, Valencia', NULL),
(6, 'online', NULL, 'https://evento3.com'),
(7, 'presencial', 'Paseo verde 45, Bilbao', NULL);

INSERT INTO EVENTOS (ID_EVENTO, NOMBRE_EVENTO, DESCRIPCION_EVENTO, FECHA_EVENTO, DURACION_EVENTO, ID_CATEGORIA, ID_UBICACION) VALUES
(1, 'Innovación Verde 2025', 'Evento sobre nuevas tecnologías sostenibles.', '2025-06-01', 3.5, 1, 1),
(2, 'Conferencia Ambiental Global', 'Expertos internacionales debaten el cambio climático.', '2025-06-03', 3, 2, 3),
(3, 'Día de Acción Ecológica', 'Voluntariado para limpiar espacios naturales.', '2025-06-05', 2, 3, 2),
(4, 'Pasarela EcoModa', 'Desfile de ropa hecha con materiales reciclados.', '2025-06-10', 1.5, 4, 5),
(5, 'Agricultura Ecológica 10', 'Formación básica para cultivar sin químicos.', '2025-06-12', 2.5, 5, 7),
(6, 'Charla con EcoExpertos', 'Mesa redonda sobre el futuro sostenible.', '2025-06-15', 2.5, 6, 4),
(7, 'Jornada Educativa Verde', 'Actividades escolares sobre medio ambiente.', '2025-06-18', 6, 7, 6);

INSERT INTO INSCRIPCIONES (ID_USUARIO, ID_EVENTO, ESTADO_INSCRIPCION, FECHA_INSCRIPCION) VALUES
(1, 1, 'ACTIVA', '2025-05-10'),
(2, 2, 'ACTIVA', '2025-05-12'),
(3, 3, 'CANCELADA', '2025-05-15'),
(4, 4, 'ACTIVA', '2025-05-20'),
(5, 5, 'ACTIVA', '2025-05-22'),
(6, 6, 'CANCELADA', '2025-05-25'),
(7, 7, 'ACTIVA', '2025-05-30');