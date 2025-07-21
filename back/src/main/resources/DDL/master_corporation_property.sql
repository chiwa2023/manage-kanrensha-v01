CREATE TABLE `master_corporation_property` (
  `master_corporation_property_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `master_corporation_id` int DEFAULT NULL COMMENT '関連者企業・団体Id',
  `corp_kanrensha_code` varchar(30) DEFAULT NULL COMMENT '関連者企業・団体コード',
  `partner_name` varchar(200) DEFAULT NULL COMMENT '関連者企業・団体名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `houjin_sbts` varchar(5) DEFAULT NULL COMMENT '法人種別',
  `is_foreign` tinyint DEFAULT NULL COMMENT '外国籍該否',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`master_corporation_property_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
