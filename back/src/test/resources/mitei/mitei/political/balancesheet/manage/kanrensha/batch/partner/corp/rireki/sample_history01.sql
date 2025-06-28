DELETE FROM `partner_corp_history_01`;
ALTER TABLE `partner_corp_history_01` auto_increment = 0;

INSERT INTO `partner_corp_history_01` (`partner_corp_history_id`,`is_latest`,`partner_name`,`all_address`,`corp_delegate`,`corp_kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (102,1,'a','b','c','x',200,190,'ユーザ','2022-12-05 12:34:56',1,1,'1','1948-07-28 00:00:00');
