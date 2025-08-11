CREATE TABLE `partner_person_history_12` (
  `partner_person_history_id` int NOT NULL AUTO_INCREMENT COMMENT '関連者個人履歴Id',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `partner_name` varchar(100) DEFAULT NULL COMMENT '個人名',
  `all_address` varchar(100) DEFAULT NULL COMMENT '個人全住所',
  `person_shokugyou` varchar(100) DEFAULT NULL COMMENT '個人職業',
  `person_kanrensha_code` varchar(30) DEFAULT NULL COMMENT '個人関連者コード',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`partner_person_history_id`),
  UNIQUE KEY `unique_data` (`partner_name`,`all_address`,`person_shokugyou`,`person_kanrensha_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
