-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 24, 2020 at 10:59 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pekarna`
--

-- --------------------------------------------------------

--
-- Table structure for table `narudzba`
--

CREATE TABLE `narudzba` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Cijena` float NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `Iznos` float NOT NULL,
  `id_racuna` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `narudzba`
--

INSERT INTO `narudzba` (`ID`, `Naziv`, `Cijena`, `Kolicina`, `Iznos`, `id_racuna`) VALUES
(60, 'Burek', 3, 1, 3, 28),
(59, 'Zeljanica', 2, 4, 8, 28),
(58, 'Sirnica', 2, 2, 4, 28),
(57, 'Zeljanica', 2, 7, 14, 28),
(56, 'Sirnica', 2, 7, 14, 28),
(55, 'Kroasan', 1, 5, 5, 28),
(54, 'Sirnica', 2, 2, 4, 28),
(53, 'Zeljanica', 2, 2, 4, 28),
(52, 'Kifla', 0.25, 4, 1, 28);

-- --------------------------------------------------------

--
-- Table structure for table `osoba`
--

CREATE TABLE `osoba` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(50) COLLATE utf8_croatian_ci NOT NULL,
  `Prezime` varchar(50) COLLATE utf8_croatian_ci NOT NULL,
  `JMBG` int(11) NOT NULL,
  `Adresa` varchar(50) COLLATE utf8_croatian_ci NOT NULL,
  `Telefon` int(11) NOT NULL,
  `Korisnicko_ime` varchar(50) COLLATE utf8_croatian_ci NOT NULL,
  `Lozinka` varchar(50) COLLATE utf8_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `osoba`
--

INSERT INTO `osoba` (`ID`, `Ime`, `Prezime`, `JMBG`, `Adresa`, `Telefon`, `Korisnicko_ime`, `Lozinka`) VALUES
(4, 'Ana', 'Bilos', 26541, 'Zagomila', 2152, 'admin', 'admin'),
(5, 'Slavica', 'Bilos', 16532, 'Zagomila', 2156, 'prodavac', 'prodavac'),
(8, 'Blagica', 'Bilos', 31235, 'Zagomila', 23165, 'blaga', 'blaga'),
(10, 'Andrija', 'Bilos', 11032, 'Zagomila', 564656, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `proizvod`
--

CREATE TABLE `proizvod` (
  `ID` int(11) NOT NULL,
  `Naziv` varchar(50) COLLATE utf8_croatian_ci NOT NULL,
  `Cijena` float NOT NULL,
  `Kolicina` int(11) NOT NULL,
  `ID_Vrsta_Proizvoda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `proizvod`
--

INSERT INTO `proizvod` (`ID`, `Naziv`, `Cijena`, `Kolicina`, `ID_Vrsta_Proizvoda`) VALUES
(15, 'Burek', 3, 1, 1),
(16, 'Zeljanica', 2, 1, 1),
(17, 'Sirnica', 2, 1, 1),
(18, 'Kroasan', 1, 1, 2),
(19, 'Kifla', 0.3, 1, 2),
(22, 'Lisnato visnja', 1, 1, 2),
(23, 'Struca', 1, 1, 2),
(24, 'Razeni kruh', 2, 1, 2),
(25, 'Pizza', 3, 1, 2),
(26, 'Coko kifla', 1, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `racun`
--

CREATE TABLE `racun` (
  `id` int(11) NOT NULL,
  `ukupno` float NOT NULL,
  `datum` text COLLATE utf8_unicode_ci NOT NULL,
  `id_konobara` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `racun`
--

INSERT INTO `racun` (`id`, `ukupno`, `datum`, `id_konobara`) VALUES
(32, 11, '24/02/2020', 5),
(31, 4, '24/02/2020', 5),
(30, 28, '24/02/2020', 5),
(29, 5, '24/02/2020', 5),
(28, 9, '24/02/2020', 5);

-- --------------------------------------------------------

--
-- Table structure for table `vrsta_proizvoda`
--

CREATE TABLE `vrsta_proizvoda` (
  `ID` int(11) NOT NULL,
  `Vrsta` varchar(50) COLLATE utf8_croatian_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_croatian_ci;

--
-- Dumping data for table `vrsta_proizvoda`
--

INSERT INTO `vrsta_proizvoda` (`ID`, `Vrsta`) VALUES
(1, 'Pita'),
(2, 'Pecivo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `narudzba`
--
ALTER TABLE `narudzba`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `osoba`
--
ALTER TABLE `osoba`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `ID_Vrsta_Proizvoda` (`ID_Vrsta_Proizvoda`);

--
-- Indexes for table `racun`
--
ALTER TABLE `racun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `vrsta_proizvoda`
--
ALTER TABLE `vrsta_proizvoda`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `narudzba`
--
ALTER TABLE `narudzba`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `osoba`
--
ALTER TABLE `osoba`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `proizvod`
--
ALTER TABLE `proizvod`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `racun`
--
ALTER TABLE `racun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `vrsta_proizvoda`
--
ALTER TABLE `vrsta_proizvoda`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `proizvod`
--
ALTER TABLE `proizvod`
  ADD CONSTRAINT `Proizvod_Vrsta_proizvoda` FOREIGN KEY (`ID_Vrsta_Proizvoda`) REFERENCES `vrsta_proizvoda` (`ID`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
