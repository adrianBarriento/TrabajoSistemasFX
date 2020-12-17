-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-12-2020 a las 21:03:39
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bbdd_sistemas`
--
CREATE DATABASE IF NOT EXISTS `bbdd_sistemas` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bbdd_sistemas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE IF NOT EXISTS `clientes` (
  `Id_Cliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(60) NOT NULL,
  `Apellidos` varchar(150) NOT NULL,
  `Email` varchar(70) NOT NULL,
  `cod_postal` bigint(20) NOT NULL,
  `Id_Producto` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Id_Cliente`, `Nombre`, `Apellidos`, `Email`, `cod_postal`, `Id_Producto`) VALUES
(2, 'Cliente', '', '', 0, NULL),
(3, 'Cliente', 'ccecee', 'smcosmcs', 0, NULL),
(4, 'pepe', 'pepe', 'ddsdsdsds', 28922, NULL),
(5, 'nuevo', 'nuevo', 'dsdsdsd', 28923, NULL),
(6, 'sdsddsdssd', 'sdd', 'dsds', 28923, NULL),
(7, 'paco', 'pa', 'asasss', 28923, NULL),
(8, 'paco', 'gonza', 'ncidnfidf', 28923, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargos`
--

CREATE TABLE IF NOT EXISTS `encargos` (
  `idFactura` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_personal` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`idFactura`),
  KEY `FKproductoEncargo` (`id_producto`),
  KEY `FKpersonalEncargo` (`id_personal`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `encargos`
--

INSERT INTO `encargos` (`idFactura`, `id_cliente`, `id_producto`, `id_personal`, `cantidad`) VALUES
(3, 4, 1, 6, 5),
(5, 4, 1, 6, 3),
(6, 4, 1, 6, 4),
(7, 4, 1, 6, 3),
(8, 4, 1, 6, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE IF NOT EXISTS `personal` (
  `Id_Personal` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Apellidos` varchar(150) NOT NULL,
  `NumSegSocial` bigint(20) NOT NULL,
  `Sueldo` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `IdProducto` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Personal`),
  KEY `FKproductos` (`IdProducto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`Id_Personal`, `Nombre`, `Apellidos`, `NumSegSocial`, `Sueldo`, `DNI`, `IdProducto`) VALUES
(2, 'Paco ', 'Perez', 121222, 1200, '48208786G', NULL),
(3, 'pepe', 'pep', 1022222, 1022, '12345678j', NULL),
(4, 'pepe', 'pep', 10222, 1022, '12345678j', NULL),
(5, 'paco', 'PA', 120222, 1022, '12345678l', NULL),
(6, 'adri', 'aaa', 122122, 1022, '12345678p', NULL),
(7, 'pppp', 'ppp', 12222348337, 1022, '12345678m', NULL),
(8, 'w', 'D', 1234567891012861234, 1022, '12345678K', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblaciones`
--

CREATE TABLE IF NOT EXISTS `poblaciones` (
  `cod_postal` int(11) NOT NULL,
  `poblacion` varchar(50) NOT NULL,
  `provincia` varchar(50) NOT NULL,
  PRIMARY KEY (`cod_postal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `poblaciones`
--

INSERT INTO `poblaciones` (`cod_postal`, `poblacion`, `provincia`) VALUES
(28922, 'Alcorcon', 'Madrid');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `Id_Producto` int(11) NOT NULL AUTO_INCREMENT,
  `Nuevo` tinyint(1) NOT NULL,
  `TipoProducto` enum('Movil','Ordenador','Componente') NOT NULL,
  `Stock` int(11) NOT NULL,
  `Marca` varchar(50) NOT NULL,
  `Modelo` varchar(150) NOT NULL,
  `Precio` int(11) NOT NULL,
  `Encargo` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id_Producto`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`Id_Producto`, `Nuevo`, `TipoProducto`, `Stock`, `Marca`, `Modelo`, `Precio`, `Encargo`) VALUES
(1, 0, 'Movil', 12, 'mxkismxisxmsixs', ' xsjnxjs', 111, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_constituir`
--

CREATE TABLE IF NOT EXISTS `productos_constituir` (
  `id_producto` int(11) NOT NULL,
  `id_escandallo` int(11) NOT NULL,
  `unidades_componente` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`,`id_escandallo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proovedores`
--

CREATE TABLE IF NOT EXISTS `proovedores` (
  `Id_Proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) NOT NULL,
  `Articulo` varchar(60) NOT NULL,
  `Id_Producto` int(11) NOT NULL,
  PRIMARY KEY (`Id_Proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `encargos`
--
ALTER TABLE `encargos`
  ADD CONSTRAINT `FKclienteEncargo` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`Id_Cliente`),
  ADD CONSTRAINT `FKpersonalEncargo` FOREIGN KEY (`id_personal`) REFERENCES `personal` (`Id_Personal`),
  ADD CONSTRAINT `FKproductoEncargo` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`Id_Producto`);

--
-- Filtros para la tabla `personal`
--
ALTER TABLE `personal`
  ADD CONSTRAINT `FKproductos` FOREIGN KEY (`IdProducto`) REFERENCES `productos` (`Id_Producto`);

--
-- Filtros para la tabla `productos_constituir`
--
ALTER TABLE `productos_constituir`
  ADD CONSTRAINT `FKcomponente` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`Id_Producto`);

--
-- Filtros para la tabla `proovedores`
--
ALTER TABLE `proovedores`
  ADD CONSTRAINT `FKproovedores` FOREIGN KEY (`Id_Producto`) REFERENCES `productos` (`Id_Producto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
