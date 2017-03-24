-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 24, 2017 at 09:14 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sobakaisti`
--

-- --------------------------------------------------------

--
-- Table structure for table `AUTHOR`
--

CREATE TABLE `AUTHOR` (
  `ID` int(11) NOT NULL,
  `FIRST_NAME` varchar(30) DEFAULT NULL,
  `LAST_NAME` varchar(30) DEFAULT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `BIRTHPLACE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `SHORT_BIO` varchar(255) DEFAULT NULL,
  `PROFESSION` varchar(50) DEFAULT NULL,
  `AVATAR_PATH` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `AUTHOR`
--

INSERT INTO `AUTHOR` (`ID`, `FIRST_NAME`, `LAST_NAME`, `DATE_OF_BIRTH`, `BIRTHPLACE`, `EMAIL`, `WEBSITE`, `SHORT_BIO`, `PROFESSION`, `AVATAR_PATH`) VALUES
(1, 'Andrea', 'Kane', '1988-10-10', 'Milano, Italia', 'andrea.kane@sobakaisti.org', 'andreakane.sobakaisti.org', '', 'Pesnik / blogger', 'avatar_path_value'),
(2, 'Nemnaj', 'djsndjn', NULL, '', '', '', '', 'nsdjnjdns', 'avatar_path_value'),
(16, 'Nemanja', 'Jelesijevic', NULL, 'Gornji Milanovac', 'jelles86@yahoo.co.yu', '', '', 'programer', 'avatar_path_value');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AUTHOR`
--
ALTER TABLE `AUTHOR`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
