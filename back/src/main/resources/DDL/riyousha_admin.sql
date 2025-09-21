CREATE TABLE `riyousha_admin` (
  `riyousha_admin_id` int NOT NULL AUTO_INCREMENT COMMENT '利用者管理者Id',
  `riyousha_admin_code` int DEFAULT NULL COMMENT '利用者管理者コード',
  `riyousha_admin_name` varchar(200) DEFAULT NULL COMMENT '利用者管理者名称',
  `is_not_org` tinyint DEFAULT NULL COMMENT '団体該否',
  `is_latest` tinyint DEFAULT NULL COMMENT '最新該否',
  `insert_user_id` int DEFAULT NULL COMMENT '挿入ユーザId',
  `insert_user_code` int DEFAULT NULL COMMENT '挿入ユーザコード',
  `insert_user_name` varchar(200) DEFAULT NULL COMMENT '挿入ユーザ名称',
  `insert_timestamp` datetime DEFAULT NULL COMMENT '挿入日時',
  `delete_user_id` int DEFAULT NULL COMMENT '無効ユーザId',
  `delete_user_code` int DEFAULT NULL COMMENT '無効ユーザコード',
  `delete_user_name` varchar(200) DEFAULT NULL COMMENT '無効ユーザ名称',
  `delete_timestamp` datetime DEFAULT NULL COMMENT '無効日時',
  PRIMARY KEY (`riyousha_admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=468 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
