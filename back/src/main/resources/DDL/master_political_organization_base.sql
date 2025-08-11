CREATE TABLE `master_political_organization_base` (
  `master_political_organization_base_id` int NOT NULL AUTO_INCREMENT COMMENT 'テーブルId',
  `master_political_organization_id` int DEFAULT NULL COMMENT '関連者政治団体Id',
  `poli_org_kanrensha_code` varchar(30) DEFAULT NULL COMMENT '関連者政治団体コード',
  `partner_name` varchar(200) DEFAULT NULL COMMENT '関連者政治団体名称',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `org_name_kana` varchar(200) DEFAULT NULL COMMENT '関連者団体名称かな',
  `org_delegate_code` varchar(200) DEFAULT NULL COMMENT '団体代表者関連者コード',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`master_political_organization_base_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
