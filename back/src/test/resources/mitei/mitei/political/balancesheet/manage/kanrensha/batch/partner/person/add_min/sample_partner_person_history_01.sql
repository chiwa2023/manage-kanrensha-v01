DELETE FROM `partner_person_history_01`;
ALTER TABLE `partner_person_history_01` auto_increment = 0;
INSERT INTO `partner_person_history_01` (`partner_person_history_id`,`is_latest`,`partner_name`,`all_address`,`person_shokugyou`,`person_kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (325,1,'迂回献金　太郎','和歌山県架空市山麓町','医師','12-3456',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ',NULL);
