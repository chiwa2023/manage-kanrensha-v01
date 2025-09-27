DELETE FROM `master_person_base`;
ALTER TABLE `master_person_base` auto_increment = 0;

-- EditMasterPersonBaseLogicTest test data
-- master_person_base
INSERT INTO master_person_base (master_person_base_id, master_person_id, person_kanrensha_code, partner_name, is_latest, last_name, first_name, middle_name, last_name_kana, first_name_kana, middle_name_kana, gyoushu, yakushoku, shokugyou_user_write, corp_no, corp_address, corp_name, is_shokyou_edit, is_shokyou_accept, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(701, 1, 'P0701', '基本更新前 一郎', true, '基本姓', '基本名', '', 'キホンセイ', 'キホンメイ', '', 'IT', 'エンジニア', '自営業', '1234567890123', '東京都千代田区', 'ベース株式会社', false, true, 1, 1, 'test-user', '2023-01-07 10:00:00', 0, 0, '', '1948-07-29 00:00:00');
