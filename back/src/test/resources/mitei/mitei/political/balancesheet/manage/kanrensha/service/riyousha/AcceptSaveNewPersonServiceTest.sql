DELETE FROM riyousha_org_manager;
ALTER TABLE `riyousha_org_manager` auto_increment = 0;
DELETE FROM riyousha_org_comrade;
ALTER TABLE `riyousha_org_comrade` auto_increment = 0;
DELETE FROM riyousha_invite_new;
ALTER TABLE `riyousha_invite_new` auto_increment = 0;

INSERT INTO `riyousha_invite_new` (`riyousha_invite_new_id`,`is_latest`,`mail_address`,`regist_code`,`person_user_id`,`person_user_code`,`person_user_name`,`dantai_role`,`riyousha_dantai_id`,`riyousha_dantai_code`,`riyousha_dantai_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (101,1,'aaa@politician.balanse.report.net','1a2b3c4d-5e6f7g',89,81,'たろー','ROLE_comrade',467,193,'管理者　花子',196,190,'管理人　太郎','2025-09-23 05:55:11',0,0,'','1948-07-28 23:00:00');

  INSERT INTO `riyousha_invite_new` (`riyousha_invite_new_id`,`is_latest`,`mail_address`,`regist_code`,`person_user_id`,`person_user_code`,`person_user_name`,`dantai_role`,`riyousha_dantai_id`,`riyousha_dantai_code`,`riyousha_dantai_name`,`insert_user_id`,`insert_user_code`,`insert_user_name`,`insert_timestamp`,`delete_user_id`,`delete_user_code`,`delete_user_name`,`delete_timestamp`)
  VALUES (201,1,'bbb@politician.balanse.report.net','9z8y7x-6v5w',50,46,'たろー','ROLE_manager',303,294,'管理者　花子',196,190,'管理人　太郎','2025-09-23 05:55:11',0,0,'','1948-07-28 23:00:00');
