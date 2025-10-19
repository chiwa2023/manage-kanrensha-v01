DELETE FROM `master_corporation`;
ALTER TABLE `master_corporation` auto_increment = 0;
DELETE FROM `master_person`;
ALTER TABLE `master_person` auto_increment = 0;
DELETE FROM `master_political_organization`;
ALTER TABLE `master_political_organization` auto_increment = 0;



INSERT INTO `master_person` (`master_person_id`,`person_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (825,'XYZAB-1',1,'迂回献金　太郎1','和歌山県架空市実在町','経営者','迂回献金太郎',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `master_person` (`master_person_id`,`person_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (826,'XYZAB-2',1,'迂回献金　太郎','和歌山県架空市実在町','経営者','迂回献金太郎',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `master_person` (`master_person_id`,`person_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`person_shokugyou`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (827,'XYZAB-2',1,'迂回献金　太郎','和歌山県架空市実在町','経営者','迂回献金太郎',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');


INSERT INTO `master_corporation` (`master_corporation_id`,`corp_kanrensha_code`,`houjin_no`,`is_latest`,`partner_name`,`all_address`,`corp_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (191,'ABCCDEF-1','12-345',1,'ぼったくり企業1','和歌山県架空市実在町','代表者　太郎','ぼったくり企業',1,1,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `master_corporation` (`master_corporation_id`,`corp_kanrensha_code`,`houjin_no`,`is_latest`,`partner_name`,`all_address`,`corp_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (192,'ABCCDEF-2','12-345',1,'ぼったくり企業2','和歌山県架空市実在町','代表者　太郎','ぼったくり企業',1,1,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `master_corporation` (`master_corporation_id`,`corp_kanrensha_code`,`houjin_no`,`is_latest`,`partner_name`,`all_address`,`corp_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (193,'ABCCDEF-3','12-345',1,'ぼったくり企業3','和歌山県架空市実在町','代表者　太郎','ぼったくり企業',1,1,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `master_political_organization` (`master_political_organization_id`,`poli_org_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`poli_org_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (724,'CDEFGH-1',1,'ちゃらんぽらん団体','和歌山県架空市実在町','代表者　太郎','ちゃらんぽらん団体1',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `master_political_organization` (`master_political_organization_id`,`poli_org_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`poli_org_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (725,'CDEFGH-2',1,'ちゃらんぽらん団体','和歌山県架空市実在町','代表者　太郎','ちゃらんぽらん団体2',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `master_political_organization` (`master_political_organization_id`,`poli_org_kanrensha_code`,`is_latest`,`partner_name`,`all_address`,`poli_org_delegate`,`compare_name_text`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (726,'CDEFGH-3',1,'ちゃらんぽらん団体','和歌山県架空市実在町','代表者　太郎','ちゃらんぽらん団体3',215,190,'ユーザ','2022-12-05 12:34:56',1,1,'ユーザ','1948-07-29 00:00:00');


DELETE FROM `user_kanrensha_combine`;
ALTER TABLE `user_kanrensha_combine` auto_increment = 0;

INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (1,1,1,298,'dm-Sp8V2-u5Cx-DLba-qUFIx',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');
INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (2,1,1,298,'dm-Sp8V2-u5Cx-DLba-qUFIx',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');

INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (72,1,1,325,'XYZAB-2',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');


INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (101,1,1,190,'XYZAB-1',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');

INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (102,1,2,190,'ABCCDEF-1',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');
INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (103,1,2,190,'ABCCDEF-2',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');


INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (104,1,3,190,'CDEFGH-1',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');
INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (105,1,3,190,'CDEFGH-2',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');
INSERT INTO `user_kanrensha_combine` (`user_kanrensha_combine_id`,`is_latest`,`kanrensha_kbn`,`use_user_code`,`kanrensha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES (106,1,3,190,'CDEFGH-100',107,13,'aaa-person 太郎','2025-09-14 09:28:33',0,0,'','1948-07-28 23:00:00');



