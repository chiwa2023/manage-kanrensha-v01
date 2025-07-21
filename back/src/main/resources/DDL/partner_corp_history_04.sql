CREATE TABLE `partner_corp_history_04` (
  `partner_corp_history_id` int NOT NULL AUTO_INCREMENT COMMENT '関連者企業・団体履歴Id',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `partner_name` varchar(100) DEFAULT NULL COMMENT '企業・団体名',
  `all_address` varchar(100) DEFAULT NULL COMMENT '企業・団体全住所',
  `corp_delegate` varchar(100) DEFAULT NULL COMMENT '企業・団体代表者',
  `corp_kanrensha_code` varchar(30) DEFAULT NULL COMMENT '企業・団体関連者コード',
  `org_delegate_code` varchar(30) DEFAULT NULL COMMENT '団体代表者コード',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`partner_corp_history_id`),
  UNIQUE KEY `unique_data` (`partner_name`,`all_address`,`corp_delegate`,`corp_kanrensha_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
