CREATE TABLE `wk_tbl_partner_corp_plus_hojin_no` (
  `wk_tbl_partner_corp_plus_hojin_no_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `partner_name` varchar(100) DEFAULT NULL COMMENT '企業・団体名',
  `all_address` varchar(100) DEFAULT NULL COMMENT '企業・団体全住所',
  `corp_delegate` varchar(100) DEFAULT NULL COMMENT '企業・団体代表者',
  `houjin_no` varchar(300) DEFAULT NULL COMMENT '法人番号',
  `is_foreign_corp` tinyint DEFAULT NULL COMMENT '外国籍企業該否',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`wk_tbl_partner_corp_plus_hojin_no_id`)
) ENGINE=InnoDB AUTO_INCREMENT=634 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
