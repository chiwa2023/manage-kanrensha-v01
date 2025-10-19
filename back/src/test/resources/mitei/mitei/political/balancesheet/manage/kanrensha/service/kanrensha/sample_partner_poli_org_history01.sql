DELETE FROM `partner_poli_org_history_01`;
ALTER TABLE `partner_poli_org_history_01` auto_increment = 0;
INSERT INTO `partner_poli_org_history_01` (`partner_poli_org_history_id`,`is_latest`,`partner_name`,`all_address`,`poli_org_delegate`,`poli_org_kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (402,1,'ちゃらんぽらん政治団体','北海道架空市','代表者　太郎','12345',253,210,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
