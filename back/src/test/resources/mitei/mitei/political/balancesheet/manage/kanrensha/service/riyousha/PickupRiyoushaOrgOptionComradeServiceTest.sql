DELETE FROM `user_riyousha_combine`;
ALTER TABLE `user_riyousha_combine` auto_increment = 0;

DELETE FROM `riyousha_comrade`;
ALTER TABLE `riyousha_comrade` auto_increment = 0;

DELETE FROM `riyousha_org_comrade`;
ALTER TABLE `riyousha_org_comrade` auto_increment = 0;


INSERT INTO `user_riyousha_combine` (`user_riyousha_combine_id`,`is_latest`,`role`,`use_user_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (102,1,'ROLE_comrade',190,220,196,190,'管理人　太郎','2025-09-14 08:30:49',0,0,'','1948-07-28 23:00:00');

INSERT INTO `user_riyousha_combine` (`user_riyousha_combine_id`,`is_latest`,`role`,`use_user_code`,`riyousha_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (202,1,'ROLE_comrade',813,880,196,190,'管理人　太郎','2025-09-14 08:30:49',0,0,'','1948-07-28 23:00:00');



INSERT INTO `riyousha_comrade` (`riyousha_comrade_id`,`riyousha_comrade_code`,`riyousha_comrade_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (227,220,'APIユーザ　花子',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_comrade` (`riyousha_comrade_id`,`riyousha_comrade_code`,`riyousha_comrade_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (888,880,'サンプルユーザ',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_comrade` (`riyousha_comrade_id`,`riyousha_comrade_code`,`riyousha_comrade_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (323,313,'組織1',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `riyousha_comrade` (`riyousha_comrade_id`,`riyousha_comrade_code`,`riyousha_comrade_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (431,421,'組織2',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_org_comrade` (`riyousha_org_comrade_id`,`is_latest`,`riyousha_person_code`,`riyousha_org_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (413,1,880,313,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');
INSERT INTO `riyousha_org_comrade` (`riyousha_org_comrade_id`,`is_latest`,`riyousha_person_code`,`riyousha_org_code`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
VALUES (424,1,880,421,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');
