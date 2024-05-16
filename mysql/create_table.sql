/* Query per a poder crear la base de dades i la tabla de dades de contactes */
CREATE DATABASE IF NOT EXISTS agendaJAVA;
USE agendaJAVA;
CREATE TABLE IF NOT EXISTS agenda (id INT AUTO_INCREMENT PRIMARY KEY, nom VARCHAR(50), cognom VARCHAR(100), telefon INT(15), direccio VARCHAR(150));