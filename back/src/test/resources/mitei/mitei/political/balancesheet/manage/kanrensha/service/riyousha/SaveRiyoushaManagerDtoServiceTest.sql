DELETE FROM `riyousha_manager`;
ALTER TABLE `riyousha_manager` auto_increment = 0;
DELETE FROM `riyousha_manager_access`;
ALTER TABLE `riyousha_manager_access` auto_increment = 0;
DELETE FROM `riyousha_manager_address`;
ALTER TABLE `riyousha_manager_address` auto_increment = 0;
DELETE FROM `riyousha_manager_name`;
ALTER TABLE `riyousha_manager_name` auto_increment = 0;

INSERT INTO `riyousha_manager` (`riyousha_manager_id`,`riyousha_manager_code`,`riyousha_manager_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (467,467,'APIユーザ　花子',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_manager_access` (`riyousha_manager_access_id`,`riyousha_manager_id`,`riyousha_manager_code`,`riyousha_manager_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (623,467,467,'APIユーザ　花子',1,'012','345','678','aaa@seijishikin.jp','https://blog.com/',0,0,'弱小SNS','https://jyakushou.sns.com/?acount=1234','@hanako',213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_manager_address` (`riyousha_manager_address_id`,`riyousha_manager_id`,`riyousha_manager_code`,`riyousha_manager_name`,`is_latest`,`address_postal`,`address_block`,`address_building`,`postal1`,`postal2`,`lg_code`,`machiaza_id`,`blk_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (826,467,467,'APIユーザ　花子',1,'山梨県実在市湖畔町','202番地1','四角ビル','123','4567','212134','111','222','333','444',1,1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_manager_name` (`riyousha_manager_name_id`,`riyousha_manager_id`,`riyousha_manager_code`,`riyousha_manager_name`,`is_latest`,`is_not_org`,`last_name`,`first_name`,`middle_name`,`last_name_kana`,`first_name_kana`,`middle_name_kana`,`org_name`,`org_name_kana`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (316,467,467,'APIユーザ　花子',1,1,'APIユーザ','花子','マリア','えーぴーあいゆーざ','はなこ','まりあ','例外システム株式会社','れいがいしすてむかぶしきかいしゃ',213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');
