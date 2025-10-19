DELETE FROM `master_political_organization`;
ALTER TABLE `master_political_organization` auto_increment = 0;
DELETE FROM `master_political_organization_access`;
ALTER TABLE `master_political_organization_access` auto_increment = 0;
DELETE FROM `master_political_organization_address`;
ALTER TABLE `master_political_organization_address` auto_increment = 0;
DELETE FROM `master_political_organization_base`;
ALTER TABLE `master_political_organization_base` auto_increment = 0;
DELETE FROM `master_political_organization_property`;
ALTER TABLE `master_political_organization_property` auto_increment = 0;

INSERT INTO `master_political_organization` (`master_political_organization_id`,`poli_org_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`poli_org_delegate`,`compare_name_text`,`dantai_kbn`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (724,'12-345-ABCCDEF',1,'陰謀論政治団体','和歌山県架空市実在町','代表者　太郎','陰謀論政治団体','05',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `master_political_organization_access` (`master_political_organization_access_id`,`master_political_organization_id`,`poli_org_kanrensha_code`,`partner_name`,`is_latest`,`phon1`,`phon2`,`phon3`,`email`,`my_portal_url`,`sns_service_id`,`sns_service_code`,`sns_service_name`,`sns_portal_url`,`sns_account`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (601,0,'12-345-ABCCDEF','テスト政治団体1',1,'012','345','6789','org1@example.com','https://example.com/org1',0,0,'テストSNS','https://sns.example.com','@org1',213,190,'ユーザ','2025-09-13 05:25:03',0,0,'ユーザ','2025-09-13 05:25:03');

INSERT INTO `master_political_organization_address` (`master_political_organization_address_id`,`master_political_organization_id`,`poli_org_kanrensha_code`,`partner_name`,`is_latest`,`address_postal`,`address_block`,`address_building`,`postal1`,`postal2`,`lg_code`,`machiaza_id`,`blk_id`,`rsdt_id`,`rsdt2_id`,`is_postal_edit`,`is_block_edit`,`is_building_edit`,`is_postal_accept`,`is_block_accept`,`is_building_accept`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (701,0,'12-345-ABCCDEF','テスト政治団体1',1,'100-0001','千代田区千代田１−１','宮殿','100','0001','131016','0001000','001','001','0',0,0,0,0,0,0,213,190,'ユーザ','2025-09-13 05:27:52',0,0,'ユーザ','2025-09-13 05:27:52');

INSERT INTO `master_political_organization_base` (`master_political_organization_base_id`,`master_political_organization_id`,`poli_org_kanrensha_code`,`partner_name`,`is_latest`,`org_name_kana`,`org_delegate_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (801,0,'12-345-ABCCDEF','テスト政治団体1',1,'テストセイジダンタイ1','P000000007',213,190,'ユーザ','2025-09-13 05:37:04',0,0,'ユーザ','2025-09-13 05:37:04');

INSERT INTO `master_political_organization_property` (`master_political_organization_property_id`,`master_political_organization_id`,`poli_org_kanrensha_code`,`partner_name`,`is_latest`,`account_mgr_code`,`account_mgr_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (901,0,'12-345-ABCCDEF','テスト政治団体1',1,'P000000001','会計管理者1',213,190,'ユーザ','2025-09-13 05:40:10',0,0,'ユーザ','2025-09-13 05:40:10');
