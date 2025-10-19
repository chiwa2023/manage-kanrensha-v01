DELETE FROM `master_person_base`;
ALTER TABLE `master_person_base` auto_increment = 0;

-- CallForEditMasterPersonBaseEntityLogicTest test data
-- master_person_base
INSERT INTO master_person_base (master_person_base_id, master_person_id, person_kanrensha_code, partner_name, is_latest, last_name, first_name, middle_name, last_name_kana, first_name_kana, middle_name_kana, gyoushu, yakushoku, shokugyou_user_write, corp_no, corp_address, corp_name, is_shokyou_edit, is_shokyou_accept, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(301, 1, 'P0001', 'ベース 一郎', true, 'ベース', '一郎', '', 'ベース', 'イチロウ', '', 'IT', 'エンジニア', '自営業', '1234567890123', '東京都千代田区', 'ベース株式会社', false, true, 1, 1, 'test-user', '2023-01-03 10:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(302, 2, 'P0002', 'ベース 二郎', true, 'ベース', '二郎', '', 'ベース', 'ジロウ', '', '製造業', '工場長', '', '', '', '', true, false, 1, 1, 'test-user', '2023-01-03 11:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(303, 3, 'P0003', 'ベース 三郎', false, 'ベース', '三郎', '', 'ベース', 'サブロウ', '', 'サービス業', '店長', '', '', '', '', false, false, 1, 1, 'test-user', '2023-01-03 12:00:00', 0, 0, '', '1948-07-29 00:00:00');
