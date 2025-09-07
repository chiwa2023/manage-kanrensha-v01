DELETE FROM `master_person`;
ALTER TABLE `master_person` auto_increment = 0;

-- CallForEditMasterPersonMasterEntityLogic test data
-- master_person
INSERT INTO master_person (master_person_id, person_kanrensha_code, is_latest, partner_name, all_address, person_shokugyou, compare_name_text, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(401, 'P0001', true, 'マスター 一郎', '東京都千代田区', '議員', 'マスターイチロウ', 1, 1, 'test-user', '2023-01-04 10:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(402, 'P0002', true, 'マスター 二郎', '大阪府大阪市', '会社役員', 'マスタニロウ', 1, 1, 'test-user', '2023-01-04 11:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(403, 'P0003', false, 'マスター 三郎', '愛知県名古屋市', '無職', 'マスターサブロウ', 1, 1, 'test-user', '2023-01-04 12:00:00', 0, 0, '', '1948-07-29 00:00:00');
