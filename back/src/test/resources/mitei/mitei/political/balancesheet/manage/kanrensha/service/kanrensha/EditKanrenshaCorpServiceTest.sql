DELETE FROM master_corporation;
DELETE FROM `master_corporation_access`;
ALTER TABLE `master_corporation_access` auto_increment = 0;
DELETE FROM `master_corporation_address`;
ALTER TABLE `master_corporation_address` auto_increment = 0;
DELETE FROM `master_corporation_base`;
ALTER TABLE `master_corporation_base` auto_increment = 0;
DELETE FROM `master_corporation_property`;
ALTER TABLE `master_corporation_property` auto_increment = 0;

INSERT INTO `master_corporation` (`master_corporation_id`,`corp_kanrensha_code`,`houjin_no`,`is_latest`,`partner_name`,`all_address`,`corp_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (191,'111-222-3333','1-22-33',1,'ぼったくり企業','和歌山県実在市山麓町','代表者　太郎','ぼったくり企業',1,1,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `master_corporation_access` (`master_corporation_access_id`,`master_corporation_id`,`corp_kanrensha_code`,`partner_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2601,NULL,'111-222-3333','テスト法人',1,'012','345','6789','aaa@example.com','https://example.com/blog',0,0,'弱小SNS','https://example.com/?acount=222','@corp',213,190,'管理人　太郎','2025-09-13 05:40:46',0,0,'ユーザ','1948-07-28 23:00:00');

INSERT INTO `master_corporation_address` (`master_corporation_address_id`,`master_corporation_id`,`corp_kanrensha_code`,`partner_name`,`is_latest`,`address_postal`,`address_block`,`address_building`,`postal1`,`postal2`,`lg_code`,`machiaza_id`,`blk_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2201,0,'111-222-3333','テスト法人1',1,'100-0001','千代田区千代田１−１','テストビル1','100','0001','131016','0001000','001','001',NULL,0,0,0,0,0,0,1,1,'ユーザ','2025-09-13 05:14:03',0,0,'ユーザ','1948-07-29 12:34:56');

INSERT INTO `master_corporation_base` (`master_corporation_base_id`,`master_corporation_id`,`corp_kanrensha_code`,`partner_name`,`is_latest`,`org_name_kana`,`is_shiten`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2301,0,'111-222-3333','テスト法人1',1,'テストホウジンイチ',0,'P000000001',213,190,'ユーザ','2025-09-13 05:18:10',0,0,'ユーザ','2025-09-13 05:18:10');

INSERT INTO `master_corporation_property` (`master_corporation_property_id`,`master_corporation_id`,`corp_kanrensha_code`,`partner_name`,`is_latest`,`houjin_sbts`,`is_foreign`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2401,0,'111-222-3333','テスト法人1',1,'101',0,213,190,'ユーザ','2025-09-13 05:19:05',0,0,'ユーザ','2025-09-13 05:19:05');
