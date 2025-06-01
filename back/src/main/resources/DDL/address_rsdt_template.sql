CREATE TABLE `address_rsdt_template` (
  `address_rsdt_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `lg_code` varchar(8) DEFAULT NULL COMMENT '地方自治体コード',
  `postal_code` varchar(10) DEFAULT NULL COMMENT '郵便番号',
  `machiaza_id` varchar(7) DEFAULT NULL COMMENT '町字コード',
  `parcel_rsdt_id` varchar(17) DEFAULT NULL COMMENT '街区コード',
  `address_block` text COMMENT '街区住所',
  `address_building` text COMMENT '住所建物',
  `effect_date` date DEFAULT NULL COMMENT '適用開始日',
  PRIMARY KEY (`address_rsdt_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
