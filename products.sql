-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2024 at 05:30 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `products`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `anime`
--

CREATE TABLE `anime` (
  `id_a` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `opinion` int(11) NOT NULL,
  `nsfw` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `anime`
--

INSERT INTO `anime` (`id_a`, `name`, `type`, `opinion`, `nsfw`) VALUES
(1, 'Naruto', 'action', 8, 0),
(2, 'Naruto Shippūden', 'action', 10, 0),
(3, 'Death Note', 'mystery', 8, 0),
(4, 'Classroom Of The Elite', 'psychological', 10, 0),
(5, 'One Piece', 'action', 8, 0),
(6, 'Attack on Titan', 'action', 8, 0),
(7, 'One-Punch Man', 'comedy', 8, 0),
(8, 'Monster', 'drama', 8, 0),
(9, 'Cyberpunk Edgerunners', 'sci-fi', 8, 0),
(10, 'High School DxD', 'Harem', 10, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `manga`
--

CREATE TABLE `manga` (
  `id_b` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `opinion` int(11) NOT NULL,
  `nsfw` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `movies`
--

CREATE TABLE `movies` (
  `id_m` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `opinion` int(11) NOT NULL,
  `nsfw` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id_m`, `name`, `type`, `opinion`, `nsfw`) VALUES
(1, 'Interstellar', 'action', 10, 0),
(2, 'Green Mile', 'horror', 10, 0),
(3, '8th Mile', 'musical', 8, 0),
(4, 'American Psycho', 'cryminal', 7, 0),
(5, 'Silent voice', 'Drama', 10, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `series`
--

CREATE TABLE `series` (
  `id_s` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `opinion` int(11) NOT NULL,
  `nsfw` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `series`
--

INSERT INTO `series` (`id_s`, `name`, `type`, `opinion`, `nsfw`) VALUES
(1, 'Invincible', 'action', 8, 0),
(2, 'Hazbin hotel', 'adventure', 10, 1),
(3, 'Breaking Bad', 'criminal', 8, 0),
(4, 'Dr House', 'medical', 10, 0),
(5, 'The Good Doctor', 'medical', 8, 0),
(6, 'Bogdan Boner: Egzorcysta', 'comedy', 8, 0),
(7, 'Peaky Blinders', 'criminal', 8, 0);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `anime`
--
ALTER TABLE `anime`
  ADD PRIMARY KEY (`id_a`);

--
-- Indeksy dla tabeli `manga`
--
ALTER TABLE `manga`
  ADD PRIMARY KEY (`id_b`);

--
-- Indeksy dla tabeli `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id_m`);

--
-- Indeksy dla tabeli `series`
--
ALTER TABLE `series`
  ADD PRIMARY KEY (`id_s`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `anime`
--
ALTER TABLE `anime`
  MODIFY `id_a` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `manga`
--
ALTER TABLE `manga`
  MODIFY `id_b` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id_m` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `series`
--
ALTER TABLE `series`
  MODIFY `id_s` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
