-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 03, 2025 lúc 11:28 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `spring_database`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `authorities`
--

CREATE TABLE `authorities` (
  `username` varchar(255) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `authorities`
--

INSERT INTO `authorities` (`username`, `authority`) VALUES
('hung', 'ROLE_ADMIN'),
('hung', 'ROLE_MANAGER'),
('template', 'ROLE_ADMIN'),
('template', 'ROLE_MANAGER'),
('template', 'ROLE_STUDENT'),
('template_25', 'ROLE_ADMIN'),
('template_25', 'ROLE_MANAGER'),
('template_25', 'ROLE_STUDENT'),
('template_28', 'ROLE_ADMIN'),
('template_28', 'ROLE_MANAGER'),
('template_28', 'ROLE_STUDENT'),
('trong', 'ROLE_ADMIN'),
('trong', 'ROLE_MANAGER'),
('trong', 'ROLE_STUDENT'),
('tru', 'ROLE_STUDENT'),
('truong', 'ROLE_MANAGER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `name_of_company` varchar(255) NOT NULL,
  `full_postal_address` text NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `name_of_company`, `full_postal_address`, `city`, `region`, `zip_code`, `country`, `address`) VALUES
(9, 'gia dinh', 'ss', 'hcm', 'binh duong', '011111', 'UK', NULL),
(10, 'gia dinh', 'ss', 'hcm', 'binh duong', '12233', 'Vietnam', NULL),
(11, 'gia dinh', 'ss', 'dS', 'ffffff', '011111', 'Vietnam', NULL),
(12, 'trong', 'bd', 'hcm', 'binh duong', '011111', 'Vietnam', NULL),
(13, 'gia dinh', 'ss', 'hcm', 'binh duong', '12345', 'USA', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `name_of_company` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `zip_code` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `customers`
--

INSERT INTO `customers` (`id`, `address`, `city`, `country`, `name_of_company`, `region`, `zip_code`) VALUES
(2, 'binh dinh', 'hcm', 'HaNoi', 'gia dinh', 'binh duong', 11111),
(3, 'binh dinh', 'hcm', 'HCM', 'gia dinh 123', 'cscss', 12222),
(4, 'binh dinh', 'dS', 'DaNang', 'gia dinh 123', 'ffffff', 11111),
(6, 'đưa', 'dS', 'HCM', 'fawf', 'ffffff', 111123),
(7, 'đưa', 'hcm', 'HaNoi', 'gia dinh', 'binh duong', 12222);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`) VALUES
(1, 'Leslie', 'Andrews', 'leslie@luv2code.com'),
(2, 'Emma', 'Baumgarten', 'emma@luv2code.com'),
(3, 'Avani', 'Gupta', 'avani@luv2code.com'),
(4, 'Yuri', 'Petrov', 'yuri@luv2code.com'),
(5, 'Juan', 'Vega', 'juan@luv2code.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `sku` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock_quantity` int(11) NOT NULL DEFAULT 0 CHECK (`stock_quantity` >= 0),
  `image` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `sku`, `name`, `description`, `price`, `stock_quantity`, `image`, `is_active`) VALUES
(18, 'sách1', 'Cheeseburger', 'Juicy beef patty with melted cheese, lettuce, and tomato in a sesame bun', 8.9, 12, '/default/images/c54e1872-c7a9-4931-b257-cf5756f48fa7.jpg', NULL),
(21, 'sach 2', 'Caesar Salad', 'Crisp romaine lettuce with Caesar dressing, croutons, and parmesan.', 7.5, 23, '/default/images/b2c5ba91-f24e-467d-8768-105babf914a5.jpg', NULL),
(22, 'sach3', 'BBQ Ribs', 'Slow-cooked ribs glazed with smoky BBQ sauce', 31, 54, '/default/images/f3eda73b-a91a-4d5d-8431-853dd98c972f.jpg', NULL),
(23, 'sach4', 'Tacos al Pastor', 'Mexican-style tacos with marinated pork, pineapple, and cilantro', 28, 30, '/default/images/92c18eb6-0406-4f7f-baf0-13907a524c58.jpg', NULL),
(29, 'sách 6', 'gai 19', 'đẹp', 100, 1, '/default/images/adfb47fc-490f-425a-86ea-a132c5c818a0.jpg', NULL),
(30, 'sách 11', 'sách', 'đẹp hấp dẫn', 1000, 1, '/default/images/6979fac4-d15a-42f3-b411-0492717b1723.jpg', NULL),
(31, 'noi that', 'noi that', 'ghees', 200, 100, '/default/images/5e20264e-69e8-403d-87a5-5a79597b9fb8.jpg', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

CREATE TABLE `student` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `student`
--

INSERT INTO `student` (`id`, `first_name`, `last_name`, `email`) VALUES
(12, 'Truong', 'Tran', 'truongtran@gmail.com'),
(14, 'Vu', 'Hoang\r\n', 'vuhoang@gmail.com'),
(20, 'Quynh', 'Lan', 'quynhlan@gmail.com'),
(21, 'Bao', 'Khoa', 'baokhoa@gmail.com'),
(22, 'abc', 'aaa', 'abc@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES
('hung', '{bcrypt}$2a$12$FQI4JHUDGpYnCwnaYKtz3eRId0haiDsZ3bxkqMXooLBU21.hLuQF6', b'1'),
('template', '{bcrypt}$2a$10$xTsaK.o.KV3pdWxDxFQoDeAWYsF/TtMlSke1OvcHihxSEj5vlcjBy', b'1'),
('template_25', '{bcrypt}$2a$10$dQh6vfmZ4KZYWaI7CYdfl./ThJ9K957Xbao7T9NYoux/85SLWIYCu', b'1'),
('template_28', '{bcrypt}$2a$10$6b/GW8waX8DKikIUrbH.rekNpldKNEQN/TNFNAFB3dAKwQNNIjjOq', b'1'),
('trong', '{bcrypt}$2a$10$cvFvuVnW6hIiJ1PhAhVCFuI/XqFz.u4k5cM/p5qDs5EPlediXrYRe', b'1'),
('tru', '{bcrypt}$2a$12$oIfgqsAvfCe/lHi/daLTquyErn0i0.9zWhwkFj1vHkpe4o5h//Y76', b'1'),
('truong', '{bcrypt}$2a$10$vYIKRo9xMSxFmAk4N8sE2Ov96ReSTb/7CtF9KdsEdZ6f7lsv9RtB2', b'1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `authorities`
--
ALTER TABLE `authorities`
  ADD UNIQUE KEY `authorities_idx_1` (`username`,`authority`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `sku` (`sku`);

--
-- Chỉ mục cho bảng `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `customers`
--
ALTER TABLE `customers`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT cho bảng `student`
--
ALTER TABLE `student`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
