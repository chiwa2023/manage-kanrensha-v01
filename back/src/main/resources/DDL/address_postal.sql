CREATE TABLE `address_postal` (
  `address_postal_id` int NOT NULL AUTO_INCREMENT,
  `postal1` varchar(10) DEFAULT NULL,
  `postal2` varchar(10) DEFAULT NULL,
  `lg_code` varchar(6) DEFAULT NULL,
  `address_org` text,
  `address_name` text,
  `is_gyoseiku_data` tinyint DEFAULT NULL,
  PRIMARY KEY (`address_postal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=125682 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
