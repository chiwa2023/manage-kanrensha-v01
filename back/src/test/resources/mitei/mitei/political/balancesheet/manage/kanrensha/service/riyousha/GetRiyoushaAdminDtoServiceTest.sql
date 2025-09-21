-- Test data for GetRiyoushaAdminDtoServiceTest
DELETE FROM riyousha_admin_name;
DELETE FROM riyousha_admin_address;
DELETE FROM riyousha_admin_access;
DELETE FROM riyousha_admin;

-- For testPractice_Person (riyousha_admin_id = 1)
INSERT INTO riyousha_admin (riyousha_admin_id, riyousha_id, is_not_org, version) VALUES (1, 'test-user', true, 1);

INSERT INTO riyousha_admin_access (access_id, riyousha_admin_id, is_latest, tel, mail) 
VALUES (1, 1, true, '123-4567-8901', 'test@example.com');

INSERT INTO riyousha_admin_address (address_id, riyousha_admin_id, is_latest, postal1, postal2, address_postal, address_block, address_building) 
VALUES (1, 1, true, '100', '0001', '東京都千代田区', '千代田１－１', 'テストビル１Ｆ');

INSERT INTO riyousha_admin_name (name_id, riyousha_admin_id, is_latest, last_name, first_name, middle_name, last_name_kana, first_name_kana, middle_name_kana, org_name, org_name_kana) 
VALUES (1, 1, true, '山田', '太郎', '', 'ヤマダ', 'タロウ', '', "団体名", "だんたいめい");


-- For testPractice_Org (riyousha_admin_id = 2)
INSERT INTO riyousha_admin (riyousha_admin_id, riyousha_id, is_not_org, version) VALUES (2, 'test-org', false, 1);

INSERT INTO riyousha_admin_access (access_id, riyousha_admin_id, is_latest, tel, mail) 
VALUES (2, 2, true, '03-1111-2222', 'org@example.com');

INSERT INTO riyousha_admin_address (address_id, riyousha_admin_id, is_latest, postal1, postal2, address_postal, address_block, address_building) 
VALUES (2, 2, true, '100', '0002', '東京都中央区', '八重洲２－２', 'テストタワー２０Ｆ');

INSERT INTO riyousha_admin_name (name_id, riyousha_admin_id, is_latest, last_name, first_name, middle_name, last_name_kana, first_name_kana, middle_name_kana, org_name, org_name_kana) 
VALUES (2, 2, true, "1", "2", "3", "4", "5", "6", 'テスト株式会社', 'テストカブシキガイシャ');
