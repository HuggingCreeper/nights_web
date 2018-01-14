-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2017 at 05:27 PM
-- Server version: 10.1.29-MariaDB
-- PHP Version: 7.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nights`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `ID` int(11) NOT NULL,
  `street` varchar(100) NOT NULL,
  `number` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`ID`, `street`, `number`) VALUES
(1, 'Erlenweg', '7');

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(100) NOT NULL,
  `latitude` double(15,6) NOT NULL,
  `longitude` double(15,6) NOT NULL,
  `price` double(9,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `event`
--

INSERT INTO `event` (`ID`, `name`, `date`, `description`, `latitude`, `longitude`, `price`) VALUES
(1, 'Boy', '2017-12-21 19:00:00', 'Henge mit de Beste', 8.366808, 47.442652, 0.00),
(5, 'Test', '2017-12-21 20:00:00', 'Test', 8.366808, 47.442652, 0.00),
(10, 'Test', '2017-12-21 20:00:00', 'Test', 8.366808, 47.442652, 0.00);

-- --------------------------------------------------------

--
-- Table structure for table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1),
(1);

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE `participant` (
  `ID` int(11) NOT NULL,
  `event` int(11) DEFAULT NULL,
  `user` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`ID`, `event`, `user`, `status`) VALUES
(1, 1, 8, 1),
(2, 1, 12, 2),
(11, 10, 8, 1);

-- --------------------------------------------------------

--
-- Table structure for table `placeofresidence`
--

CREATE TABLE `placeofresidence` (
  `ID` int(11) NOT NULL,
  `place` varchar(50) NOT NULL,
  `plz` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `placeofresidence`
--

INSERT INTO `placeofresidence` (`ID`, `place`, `plz`) VALUES
(1, 'Würenlos', '5436');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `ID` int(11) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`ID`, `type`) VALUES
(1, 'Admin'),
(2, 'Pending'),
(3, 'Accepted'),
(4, 'Declined');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `birthday` date NOT NULL,
  `address` int(11) DEFAULT NULL,
  `place` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`ID`, `email`, `username`, `password`, `firstname`, `lastname`, `birthday`, `address`, `place`) VALUES
(1, 'jojo@gmail.com', 'joshy', 'yaboy', '', '', '3899-03-19', 1, 1),
(8, 'java@hotmail.com', 'java', '9BD77177E21618598BA8A0C8D548A67F', 'Joshua', 'Kern', '2017-12-20', 1, 1),
(10, 'fa@gmail.com', '39863B0650D9E282E3D4F0C1A47785A1', 'fa', 'joshua', 'kern', '1999-02-19', 1, 1),
(11, 'ga@gmail.com', 'goy', '32322F4E049C3AB6CB51E206349FE071', 'joshua', 'kern', '1999-02-19', 1, 1),
(12, 'juan@hotmail.com', 'Juan', '9BD77177E21618598BA8A0C8D548A67F', 'Juan', 'Boy', '1999-02-19', 1, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `participant`
--
ALTER TABLE `participant`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `event` (`event`),
  ADD KEY `user` (`user`),
  ADD KEY `status` (`status`);

--
-- Indexes for table `placeofresidence`
--
ALTER TABLE `placeofresidence`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `place` (`place`),
  ADD KEY `address` (`address`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `event`
--
ALTER TABLE `event`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `participant`
--
ALTER TABLE `participant`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `placeofresidence`
--
ALTER TABLE `placeofresidence`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`address`);

--
-- Constraints for table `participant`
--
ALTER TABLE `participant`
  ADD CONSTRAINT `FKapsedrdsj619sbmig5ta0w9uw` FOREIGN KEY (`user`) REFERENCES `user` (`ID`),
  ADD CONSTRAINT `FKl8el941si4hkq3nqb4qe2c42y` FOREIGN KEY (`event`) REFERENCES `event` (`ID`),
  ADD CONSTRAINT `FKqmmrj4k8ety6v1i87r3c24xhc` FOREIGN KEY (`status`) REFERENCES `status` (`ID`),
  ADD CONSTRAINT `participant_ibfk_1` FOREIGN KEY (`status`) REFERENCES `status` (`ID`),
  ADD CONSTRAINT `participant_ibfk_2` FOREIGN KEY (`event`) REFERENCES `event` (`ID`),
  ADD CONSTRAINT `participant_ibfk_3` FOREIGN KEY (`user`) REFERENCES `user` (`ID`);

--
-- Constraints for table `placeofresidence`
--
ALTER TABLE `placeofresidence`
  ADD CONSTRAINT `placeofresidence_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `user` (`place`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKi371i1vdmyh0slejk03x1ihe7` FOREIGN KEY (`address`) REFERENCES `address` (`ID`),
  ADD CONSTRAINT `FKj8lk8wwm7qx7qbgjx3okjobbc` FOREIGN KEY (`place`) REFERENCES `placeofresidence` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
