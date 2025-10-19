
DELETE FROM `user_person`;
ALTER TABLE `user_person` auto_increment = 0;
DELETE FROM riyousha_admin;
ALTER TABLE `user_person` auto_increment = 0;
DELETE FROM riyousha_manager;
ALTER TABLE `user_person` auto_increment = 0;
DELETE FROM riyousha_comrade;
ALTER TABLE `user_person` auto_increment = 0;
DELETE FROM riyousha_invite_new;
ALTER TABLE `riyousha_invite_new` auto_increment = 0;

INSERT INTO `riyousha_admin` (`riyousha_admin_id`,`riyousha_admin_code`,`riyousha_admin_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (467,193,'管理者　花子',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_manager` (`riyousha_manager_id`,`riyousha_manager_code`,`riyousha_manager_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (313,310,'運営者　花子',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `riyousha_comrade` (`riyousha_comrade_id`,`riyousha_comrade_code`,`riyousha_comrade_name`,`is_latest`,`is_not_org`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) 
VALUES (227,220,'APIユーザ　花子',1,1,213,190,'ユーザ','2022-12-05 12:34:56',0,0,'ユーザ','1948-07-29 00:00:00');

INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
(88,80,'aaa',0,'aaa@bbb.net',0,0,'a','2022-12-05 00:00:00',0,0,'a','2022-12-05 00:00:00');
INSERT INTO `user_person` (`user_person_id`,`user_person_code`,`user_person_name`,`is_latest`,`email`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`) VALUES 
(89,81,'たろー',1,'aaa@politician.balanse.report.net',89,81,'たろー','2025-05-30 06:19:32',0,0,'','1948-07-28 23:00:00');
