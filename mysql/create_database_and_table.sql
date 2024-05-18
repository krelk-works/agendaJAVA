/* Query per a poder crear la base de dades i la tabla de dades de contactes */
CREATE DATABASE IF NOT EXISTS agendaJAVA;
USE agendaJAVA;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2024 a las 21:26:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `agendajava`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agenda`
--

CREATE TABLE IF NOT EXISTS `agenda` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `cognom` varchar(100) DEFAULT NULL,
  `telefon` bigint(15) DEFAULT NULL,
  `direccio` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `agenda`
--

INSERT IGNORE INTO `agenda` (`id`, `nom`, `cognom`, `telefon`, `direccio`) VALUES
(1, 'María', 'Gómez López', 610988233, 'Calle Mayor 91'),
(2, 'José', 'Rodríguez García', 920191649, 'Avenida Siempre Viva 47'),
(3, 'Carmen', 'Fernández Sánchez', 862163280, 'Calle Mayor 59'),
(4, 'Miguel', 'Gómez Ruiz', 606656289, 'Calle de la Esperanza 19'),
(5, 'Carmen', 'Rodríguez Ruiz', 712138237, 'Calle Falsa 82'),
(6, 'Pedro', 'Ruiz Gómez', 796533445, 'Calle Mayor 57'),
(7, 'Marta', 'García Fernández', 678404974, 'Calle de la Esperanza 90'),
(8, 'Ana', 'González Gómez', 992166312, 'Avenida Siempre Viva 50'),
(9, 'Luis', 'Fernández Pérez', 745797087, 'Camino Real 77'),
(10, 'Miguel', 'Rodríguez Sánchez', 969234334, 'Calle de la Esperanza 84'),
(11, 'Laura', 'García Fernández', 603707499, 'Calle de la Esperanza 85'),
(12, 'Carmen', 'García López', 975046990, 'Avenida Siempre Viva 14'),
(13, 'José', 'López Sánchez', 741417336, 'Calle Mayor 75'),
(14, 'María', 'Sánchez Pérez', 930580549, 'Calle de la Esperanza 61'),
(15, 'Juan', 'Gómez Rodríguez', 840120903, 'Calle Mayor 88'),
(16, 'Pedro', 'López García', 836633640, 'Calle Falsa 65'),
(17, 'José', 'Pérez Gómez', 960229533, 'Avenida Siempre Viva 95'),
(18, 'Laura', 'Martínez López', 832340957, 'Camino Real 74'),
(19, 'Pedro', 'Martínez Martínez', 809408980, 'Calle Mayor 2'),
(20, 'Juan', 'López Fernández', 772573877, 'Camino Real 42'),
(21, 'Marta', 'González Martínez', 844178419, 'Camino Real 88'),
(22, 'Ana', 'Pérez Ruiz', 920353579, 'Avenida Siempre Viva 8'),
(23, 'José', 'López Gómez', 792551187, 'Camino Real 38'),
(24, 'Laura', 'Fernández Gómez', 719871617, 'Calle Falsa 25'),
(25, 'Laura', 'Fernández Ruiz', 980930757, 'Camino Real 55'),
(26, 'José', 'Sánchez Martínez', 702441646, 'Calle de la Esperanza 1'),
(27, 'Pedro', 'Pérez Ruiz', 814343369, 'Calle de la Esperanza 74'),
(28, 'José', 'Sánchez González', 900823899, 'Camino Real 14'),
(29, 'Luis', 'Fernández Pérez', 843603943, 'Calle Falsa 17'),
(30, 'Laura', 'López García', 618373648, 'Calle Mayor 50'),
(31, 'Juan', 'Martínez Gómez', 762221278, 'Calle de la Esperanza 73'),
(32, 'Carmen', 'Pérez Ruiz', 858343797, 'Camino Real 85'),
(33, 'José', 'Pérez Pérez', 631555970, 'Camino Real 89'),
(34, 'Carmen', 'Gómez López', 783267771, 'Avenida Siempre Viva 23'),
(35, 'Ana', 'Ruiz Rodríguez', 741698026, 'Avenida Siempre Viva 99'),
(36, 'Ana', 'García Fernández', 824684577, 'Calle Falsa 84'),
(37, 'Marta', 'Martínez Sánchez', 999430094, 'Calle Mayor 86'),
(38, 'Juan', 'López González', 664083354, 'Avenida Siempre Viva 10'),
(39, 'José', 'Pérez Martínez', 869113953, 'Avenida Siempre Viva 24'),
(40, 'Juan', 'Fernández Fernández', 963301565, 'Calle Falsa 56');
--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agenda`
--
ALTER TABLE `agenda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
