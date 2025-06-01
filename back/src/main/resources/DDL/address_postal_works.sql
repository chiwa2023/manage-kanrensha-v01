CREATE TABLE `address_postal_works` (
  `address_postal_works_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `address_postal_irregular_id` int DEFAULT NULL COMMENT '郵便番号不規則データId',
  `lg_code` varchar(8) DEFAULT NULL,
  `postal1` varchar(10) DEFAULT NULL,
  `postal2` varchar(6) DEFAULT NULL,
  `is_add_postal` tinyint DEFAULT NULL COMMENT '郵便番号テーブル追加',
  `is_repair_rsdt` tinyint DEFAULT NULL COMMENT '住居テーブル修正',
  `address_org` text,
  `address_name` text COMMENT '住所名',
  `address_postal` text COMMENT '住所郵便番号まで',
  `address_block` text,
  PRIMARY KEY (`address_postal_works_id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
