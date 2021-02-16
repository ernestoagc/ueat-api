-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 16-02-2021 a las 13:13:32
-- Versión del servidor: 8.0.20
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbUeat`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `APPLICATION_FORM`
--

CREATE TABLE `APPLICATION_FORM` (
  `ID` bigint NOT NULL,
  `COMMENT` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `POSITION` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `APPLICATION_FORM`
--

INSERT INTO `APPLICATION_FORM` (`ID`, `COMMENT`, `POSITION`) VALUES
(7, 'these developer come from Peru', 'java developer'),
(10, 'he has been developer since 2013', 'fullstack developer');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ROLE`
--

CREATE TABLE `ROLE` (
  `ID` bigint NOT NULL,
  `CODE` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `ROLE`
--

INSERT INTO `ROLE` (`ID`, `CODE`) VALUES
(1, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER`
--

CREATE TABLE `USER` (
  `ID` bigint NOT NULL,
  `EMAIL` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `USER`
--

INSERT INTO `USER` (`ID`, `EMAIL`, `PASSWORD`) VALUES
(1, 'alonso@google.com', '$2a$10$DKIJPQA7cAlnk93UOfnhienddoqokKEmurmUSmZrSqoyDzka.HFIK');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USER_ROLE`
--

CREATE TABLE `USER_ROLE` (
  `USER` bigint NOT NULL,
  `ROLE` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `USER_ROLE`
--

INSERT INTO `USER_ROLE` (`USER`, `ROLE`) VALUES
(1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `APPLICATION_FORM`
--
ALTER TABLE `APPLICATION_FORM`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `ROLE`
--
ALTER TABLE `ROLE`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `USER`
--
ALTER TABLE `USER`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `USER_ROLE`
--
ALTER TABLE `USER_ROLE`
  ADD KEY `FKor43olgu9wwodwqvm2me93icd` (`ROLE`),
  ADD KEY `FKp05nd9aec94mxjg3tnt1gptsv` (`USER`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `APPLICATION_FORM`
--
ALTER TABLE `APPLICATION_FORM`
  MODIFY `ID` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ROLE`
--
ALTER TABLE `ROLE`
  MODIFY `ID` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `USER`
--
ALTER TABLE `USER`
  MODIFY `ID` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `USER_ROLE`
--
ALTER TABLE `USER_ROLE`
  ADD CONSTRAINT `FKor43olgu9wwodwqvm2me93icd` FOREIGN KEY (`ROLE`) REFERENCES `ROLE` (`ID`),
  ADD CONSTRAINT `FKp05nd9aec94mxjg3tnt1gptsv` FOREIGN KEY (`USER`) REFERENCES `USER` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
