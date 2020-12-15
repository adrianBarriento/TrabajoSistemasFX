-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-12-2020 a las 11:09:24
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `Id_Cliente` int(11) NOT NULL,
  `Nombre` varchar(60) NOT NULL,
  `Apellidos` varchar(150) NOT NULL,
  `Direccion` varchar(100) NOT NULL,
  `Poblacion` varchar(50) NOT NULL,
  `Email` varchar(70) NOT NULL,
  `Id_Producto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`Id_Cliente`, `Nombre`, `Apellidos`, `Direccion`, `Poblacion`, `Email`, `Id_Producto`) VALUES
(1, 'Ete', 'Sech', 'Mi compa', 'RD', 'ete@sech.com', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `encargos`
--

CREATE TABLE `encargos` (
  `id_cliente` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_personal` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `encargos`
--

INSERT INTO `encargos` (`id_cliente`, `id_producto`, `id_personal`, `cantidad`) VALUES
(1, 1, 3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `Id_Personal` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellidos` varchar(150) NOT NULL,
  `NumSegSocial` bigint(20) NOT NULL,
  `Sueldo` int(11) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `IdProducto` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`Id_Personal`, `Nombre`, `Apellidos`, `NumSegSocial`, `Sueldo`, `DNI`, `IdProducto`) VALUES
(2, 'Paco ', 'Perez', 121222, 1200, '48208786G', NULL),
(3, 'Juan', 'Pepe', 123456789, 9000, '09138804F', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `Id_Producto` int(11) NOT NULL,
  `Nuevo` tinyint(1) NOT NULL,
  `TipoProducto` enum('Movil','Ordenador','Componente') NOT NULL,
  `Stock` int(11) NOT NULL,
  `Marca` varchar(50) NOT NULL,
  `Modelo` varchar(150) NOT NULL,
  `Precio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`Id_Producto`, `Nuevo`, `TipoProducto`, `Stock`, `Marca`, `Modelo`, `Precio`) VALUES
(1, 1, 'Ordenador', 15, 'Asus', 'Rog', 12),
(2, 1, 'Ordenador', 15, 'Asus', 'Rog', 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos_constituir`
--

CREATE TABLE `productos_constituir` (
  `id_producto` int(11) NOT NULL,
  `id_escandallo` int(11) NOT NULL,
  `unidades_componente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proovedores`
--

CREATE TABLE `proovedores` (
  `Id_Proveedor` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Articulo` varchar(60) NOT NULL,
  `Id_Producto` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`Id_Cliente`),
  ADD KEY `FKclientes` (`Id_Producto`);

--
-- Indices de la tabla `encargos`
--
ALTER TABLE `encargos`
  ADD PRIMARY KEY (`id_cliente`,`id_producto`),
  ADD KEY `FKproductoEncargo` (`id_producto`),
  ADD KEY `FKpersonalEncargo` (`id_personal`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`Id_Personal`),
  ADD KEY `FKproductos` (`IdProducto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`Id_Producto`);

--
-- Indices de la tabla `productos_constituir`
--
ALTER TABLE `productos_constituir`
  ADD PRIMARY KEY (`id_producto`,`id_escandallo`);

--
-- Indices de la tabla `proovedores`
--
ALTER TABLE `proovedores`
  ADD PRIMARY KEY (`Id_Proveedor`),
  ADD KEY `FKproovedores` (`Id_Producto`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `Id_Cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `Id_Personal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `Id_Producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `proovedores`
--
ALTER TABLE `proovedores`
  MODIFY `Id_Proveedor` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `FKclientes` FOREIGN KEY (`Id_Producto`) REFERENCES `productos` (`Id_Producto`);

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
