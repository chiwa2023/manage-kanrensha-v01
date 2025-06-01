DELETE FROM `user_role`;
ALTER TABLE `user_role` auto_increment = 0;
INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (11,'bb@ccc.net',1,'comrade',0,0,'0','2022-12-05 00:00:00',0,0,'0','2022-12-05 00:00:00');
INSERT INTO `user_role` (`user_role_id`,`email`,`is_latest`,`role`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (12,'aaa@politician.balanse.report.net',1,'manager',0,81,'たろー','2025-05-26 20:51:34',0,0,'','1948-07-28 23:00:00');
