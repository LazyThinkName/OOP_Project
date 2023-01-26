-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 26, 2023 at 11:08 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `store`
--

-- --------------------------------------------------------

--
-- Table structure for table `drink`
--

CREATE TABLE `drink` (
  `drinkID` varchar(5) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  `arrivalDate` varchar(10) DEFAULT NULL,
  `expiredDate` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drink`
--

INSERT INTO `drink` (`drinkID`, `name`, `type`, `arrivalDate`, `expiredDate`, `price`, `quantity`) VALUES
('D-1', 'Pepsi', 'Drink', '02/01/2023', '30/01/2023', 2, 50),
('D-2', 'Coca-Cola', 'Drink', '04/01/2023', '14/02/2023', 2.2, 60),
('D-3', '100-Plus', 'Drink', '06/01/2023', '15/02/2023', 2.5, 15),
('D-4', 'Green Tea', 'Drink', '12/01/2023', '22/02/2023', 2.5, 30),
('D-5', 'Lemon Tea', 'Drink', '06/01/2023', '15/02/2023', 10.5, 35);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `foodID` varchar(5) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  `arrivalDate` varchar(10) DEFAULT NULL,
  `expiredDate` varchar(10) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`foodID`, `name`, `type`, `arrivalDate`, `expiredDate`, `price`, `quantity`) VALUES
('F-1', 'Bread', 'Food', '02/01/2023', '28/01/2023', 4.5, 30),
('F-2', 'Lolipop', 'Food', '07/01/2023', '28/03/2023', 0.5, 50),
('F-3', 'Biscuit', 'Food', '04/01/2023', '25/01/2023', 1.5, 33);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `drink`
--
ALTER TABLE `drink`
  ADD PRIMARY KEY (`drinkID`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`foodID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
