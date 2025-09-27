DELETE FROM `master_person`;
ALTER TABLE `master_person` auto_increment = 0;

-- EditMasterPersonLogicTest test data
-- master_person
INSERT INTO master_person (master_person_id, person_kanrensha_code, is_latest, partner_name, all_address, person_shokugyou, compare_name_text, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(501, 'P0501', true, '更新前 太郎', '更新前住所', '更新前職業', 'コウシンマエタロウ', 1, 1, 'test-user', '2023-01-05 10:00:00', 0, 0, '', '1948-07-29 00:00:00');
