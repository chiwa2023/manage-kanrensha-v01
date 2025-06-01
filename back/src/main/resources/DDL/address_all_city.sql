CREATE TABLE `address_all_city` (
  `address_all_city_id` int NOT NULL AUTO_INCREMENT,
  `lg_code` varchar(6) DEFAULT NULL,
  `address_name` varchar(300) DEFAULT NULL,
  `address_name_kana` varchar(300) DEFAULT NULL,
  `effect_date` date DEFAULT NULL,
  PRIMARY KEY (`address_all_city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1919 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
