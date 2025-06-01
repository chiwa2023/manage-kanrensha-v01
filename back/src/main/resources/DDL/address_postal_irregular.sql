CREATE TABLE `address_postal_irregular` (
  `address_postal_irregular_id` int NOT NULL AUTO_INCREMENT,
  `postal1` varchar(10) DEFAULT NULL,
  `postal2` varchar(10) DEFAULT NULL,
  `lg_code` varchar(6) DEFAULT NULL,
  `address_org` text,
  `address_name` text,
  `address_postal` text,
  `address_block` text,
  `is_add_postal` tinyint DEFAULT NULL COMMENT '郵便番号テーブル追加',
  `is_repair_rsdt` tinyint DEFAULT NULL COMMENT '住居テーブル修正可否',
  PRIMARY KEY (`address_postal_irregular_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6508 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
