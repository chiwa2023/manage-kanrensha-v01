DELETE FROM `partner_person_history_01`;
ALTER TABLE `partner_person_history_01` auto_increment = 0;
INSERT INTO `partner_person_history_01` (`partner_person_history_id`,`is_latest`,`partner_name`,`all_address`,`person_shokugyou`,`person_kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (1,1,'a','b','c','w',219,190,'代表者　太郎','2025-06-28 07:52:27',1,1,'ユーザ','1947-07-29 00:00:00');
