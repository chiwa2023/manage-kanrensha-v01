DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;
INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (88,80,'aaa',0,'aaa@bbb.net',0,0,'a','2022-12-05 00:00:00',0,0,'a','2022-12-05 00:00:00');
