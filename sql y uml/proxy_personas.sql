CREATE DATABASE proxy_personas;
USE proxy_personas;

CREATE TABLE personas (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE telefonos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(20) NOT NULL,
    idPersona INT NOT NULL,
    FOREIGN KEY (idPersona) REFERENCES personas(id)
);
INSERT INTO personas (id, nombre)
VALUES (1, 'Juan Perez');

INSERT INTO telefonos (numero, idPersona)
VALUES 
('2920-123456', 1),
('2920-654321', 1),
('299-111222', 1);