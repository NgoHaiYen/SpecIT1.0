-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 30, 2017 at 06:02 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `calllogit2`
--
DROP TABLE IF EXISTS branch,comment,employees,image,information,isread,priorities,relater,request,role,status,team;
-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `Branch_id` int(10) NOT NULL,
  `branch_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `leader_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`Branch_id`, `branch_name`, `leader_id`) VALUES
(1, 'IT Hà Nội', 1),
(2, 'IT Đà Nẵng', 2);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `comment_id` int(10) NOT NULL,
  `request_id` int(10) NOT NULL,
  `employee_id` int(10) NOT NULL,
  `content` text,
  `type` tinyint(1) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` int(20) NOT NULL,
  `email` varchar(200) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `Branch_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `name`, `phone`, `email`, `username`, `password`, `role_id`, `team_id`, `Branch_id`) VALUES
(1, 'Xuan', 1234, 'HaNoi', 'Xuan', '827ccb0eea8a706c4c34a16891f84e7b', 1, 2, 1),
(2, 'Admin', 1234, 'Hanoi', 'admin1', 'e10adc3949ba59abbe56e057f20f883e', 2, 1, 2),
(3, 'admin2', 125, 'Hanoi', 'admin2', 'e10adc3949ba59abbe56e057f20f883e', 3, 2, 1),
(4, 'Yến', 1235454, 'Hưng Yên', 'yen', 'e10adc3949ba59abbe56e057f20f883e', 3, 1, 1),
(123, 'Admin', 1234, 'Hanoi', 'admin3', 'e10adc3949ba59abbe56e057f20f883e', 3, 2, 2),
(23444, 'admin2', 125, 'Hanoi', 'admin4', 'e10adc3949ba59abbe56e057f20f883e', 2, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `request_id` int(10) NOT NULL,
  `url_image` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `information`
--

CREATE TABLE `information` (
  `request_id` int(10) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `prioriry` varchar(255) DEFAULT NULL,
  `rating` varchar(255) DEFAULT NULL,
  `reopened` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `isread`
--

CREATE TABLE `isread` (
  `request_id` int(10) NOT NULL,
  `reader_id` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `priorities`
--

CREATE TABLE `priorities` (
  `priority_id` int(11) NOT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `priorities`
--

INSERT INTO `priorities` (`priority_id`, `name`) VALUES
(1, 'Khẩn cấp'),
(2, 'Cao'),
(3, 'Bình thường'),
(4, 'Thấp');

-- --------------------------------------------------------

--
-- Table structure for table `relater`
--

CREATE TABLE `relater` (
  `relation_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `created_at` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `request_id` int(10) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `content` text,
  `created_by` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `prioriry` tinyint(1) NOT NULL,
  `deadlline` datetime NOT NULL,
  `assigned_to` int(11) DEFAULT NULL,
  `rating` tinyint(1) DEFAULT NULL,
  `team_id` int(10) NOT NULL,
  `branch_id` int(10) NOT NULL,
  `resolved_at` datetime DEFAULT NULL,
  `closed_at` datetime NOT NULL,
  `create_at` datetime NOT NULL,
  `updated_at` datetime NOT NULL,
  `deleted_at` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `role_id` int(10) NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `description` text CHARACTER SET utf8 COLLATE utf8_vietnamese_ci
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `description`) VALUES
(1, 'Member', 'all member'),
(2, 'Sub-Lead', 'medium, can manage some member'),
(3, 'Leader', 'the biggest, can manage members and the staff of the company');

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `status_id` int(11) NOT NULL,
  `status_name` varchar(20) COLLATE utf8_vietnamese_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`status_id`, `status_name`) VALUES
(1, 'New'),
(2, 'Inprogress'),
(3, 'Resolved'),
(4, 'Feedback'),
(5, 'Closed'),
(6, 'Cancelled');

-- --------------------------------------------------------

--
-- Table structure for table `team`
--

CREATE TABLE `team` (
  `team_id` int(11) NOT NULL,
  `team_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_vietnamese_ci NOT NULL,
  `teamleader_id` int(11) NOT NULL,
  `Branch_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `team`
--

INSERT INTO `team` (`team_id`, `team_name`, `teamleader_id`, `Branch_id`) VALUES
(1, 'Team Hà Nội', 4, 1),
(2, 'Team Đà Nẵng', 123, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`Branch_id`),
  ADD KEY `Branch_id` (`Branch_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `fk_tickets_ticket_thread` (`request_id`),
  ADD KEY `fk_ticket_thread_employee` (`employee_id`),
  ADD KEY `comment_id` (`comment_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `team_id` (`team_id`),
  ADD KEY `role` (`role_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `team_id_2` (`team_id`),
  ADD KEY `Branch_id` (`Branch_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD KEY `request_id` (`request_id`);

--
-- Indexes for table `information`
--
ALTER TABLE `information`
  ADD PRIMARY KEY (`request_id`);

--
-- Indexes for table `isread`
--
ALTER TABLE `isread`
  ADD PRIMARY KEY (`request_id`,`reader_id`),
  ADD KEY `fk_ticket_read_employee` (`reader_id`);

--
-- Indexes for table `priorities`
--
ALTER TABLE `priorities`
  ADD PRIMARY KEY (`priority_id`);

--
-- Indexes for table `relater`
--
ALTER TABLE `relater`
  ADD PRIMARY KEY (`relation_id`),
  ADD KEY `relation_id` (`relation_id`),
  ADD KEY `request_id` (`request_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`request_id`),
  ADD KEY `fk_tickets_employee` (`created_by`),
  ADD KEY `fk_ticket_employees` (`assigned_to`),
  ADD KEY `fk_tickets_team` (`team_id`),
  ADD KEY `request_id` (`request_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`status_id`);

--
-- Indexes for table `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`team_id`),
  ADD KEY `team_id` (`team_id`),
  ADD KEY `Branch_id` (`Branch_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `branch`
--
ALTER TABLE `branch`
  MODIFY `Branch_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `comment_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23445;
--
-- AUTO_INCREMENT for table `information`
--
ALTER TABLE `information`
  MODIFY `request_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `priorities`
--
ALTER TABLE `priorities`
  MODIFY `priority_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `request_id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `status_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `team`
--
ALTER TABLE `team`
  MODIFY `team_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `information`
--
ALTER TABLE `information`
  ADD CONSTRAINT `information_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `isread`
--
ALTER TABLE `isread`
  ADD CONSTRAINT `isread_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `isread_ibfk_2` FOREIGN KEY (`reader_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `relater`
--
ALTER TABLE `relater`
  ADD CONSTRAINT `relater_ibfk_1` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `relater_ibfk_2` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_1` FOREIGN KEY (`assigned_to`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_2` FOREIGN KEY (`created_by`) REFERENCES `employees` (`employee_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
