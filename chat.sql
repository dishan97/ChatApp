-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 04, 2021 at 08:20 AM
-- Server version: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chat`
--

-- --------------------------------------------------------

--
-- Table structure for table `chatcreated`
--

DROP TABLE IF EXISTS `chatcreated`;
CREATE TABLE IF NOT EXISTS `chatcreated` (
  `chatid` int(6) NOT NULL AUTO_INCREMENT,
  `chatname` varchar(50) NOT NULL,
  `datecreated` date NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`chatid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chatcreated`
--

INSERT INTO `chatcreated` (`chatid`, `chatname`, `datecreated`, `status`) VALUES
(1, 'University', '2021-01-31', 0),
(3, 'FOT', '2021-01-31', 0);

-- --------------------------------------------------------

--
-- Table structure for table `subscribers`
--

DROP TABLE IF EXISTS `subscribers`;
CREATE TABLE IF NOT EXISTS `subscribers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `chatid` char(6) NOT NULL,
  `userid` char(15) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `userid` int(15) NOT NULL AUTO_INCREMENT,
  `username` char(30) NOT NULL,
  `nickname` varchar(20) NOT NULL,
  `usertype` varchar(10) NOT NULL,
  `pwd` varchar(30) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userid`, `username`, `nickname`, `usertype`, `pwd`) VALUES
(10, 'jithmi', 'sdsfdsfsdf', 'client', '123'),
(1, 'admin', '123', 'admin', '123'),
(2, 'dishan', 'lal', 'client', '123'),
(11, 'asdsad', 'asdsa', 'client', '123'),
(12, 'supun Chalaka', 'supu', 'client', 'asd'),
(13, 'supun', 'supcha', 'client', 'asd');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
