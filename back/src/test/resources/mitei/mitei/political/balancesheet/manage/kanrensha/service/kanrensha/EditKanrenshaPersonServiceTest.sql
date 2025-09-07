DELETE FROM `master_person`;
ALTER TABLE `master_person` auto_increment = 0;
DELETE FROM `master_person_access`;
ALTER TABLE `master_person_access` auto_increment = 0;
DELETE FROM `master_person_address`;
ALTER TABLE `master_person_address` auto_increment = 0;
DELETE FROM `master_person_base`;
ALTER TABLE `master_person_base` auto_increment = 0;
DELETE FROM `master_person_property`;
ALTER TABLE `master_person_property` auto_increment = 0;

-- EditKanrenshaPersonServiceTest test data

-- master_person (ID 901)
INSERT INTO master_person (master_person_id, person_kanrensha_code, is_latest, partner_name, all_address, person_shokugyou, compare_name_text, insert_user_id, insert_user_code, insert_user_name, insert_timestamp) VALUES
(901, 'P0900', true, 'サービス太郎', 'サービス住所', 'サービス職業', 'サービスタロウ', 1, 1, 'test-user', '2023-01-09 10:00:00');

-- master_person_access (ID 902)
INSERT INTO master_person_access (master_person_access_id, master_person_id, person_kanrensha_code, partner_name, is_latest, phon1, phon2, phon3, email, my_portal_url, sns_service_name, sns_portal_url, sns_account, insert_user_id, insert_user_code, insert_user_name, insert_timestamp) VALUES
(902, 901, 'P0900', 'サービス太郎', true, '090', '1111', '2222', 'access@example.com', '', '', '', '', 1, 1, 'test-user', '2023-01-09 10:00:00');

-- master_person_address (ID 903)
INSERT INTO master_person_address (master_person_address_id, master_person_id, person_kanrensha_code, partner_name, is_latest, address_postal, address_block, address_building, postal1, postal2, insert_user_id, insert_user_code, insert_user_name, insert_timestamp) VALUES
(903, 901, 'P0900', 'サービス太郎', true, '123-4567', 'サービス町1-2-3', 'サービスビル', '123', '4567', 1, 1, 'test-user', '2023-01-09 10:00:00');

-- master_person_base (ID 904)
INSERT INTO master_person_base (master_person_base_id, master_person_id, person_kanrensha_code, partner_name, is_latest, last_name, first_name, last_name_kana, first_name_kana, gyoushu, yakushoku, insert_user_id, insert_user_code, insert_user_name, insert_timestamp) VALUES
(904, 901, 'P0900', 'サービス太郎', true, 'サービス', '太郎', 'サービス', 'タロウ', 'IT', '部長', 1, 1, 'test-user', '2023-01-09 10:00:00');

-- master_person_property (ID 905)
INSERT INTO master_person_property (master_person_property_id, master_person_id, person_kanrensha_code, partner_name, is_latest, is_foreign, insert_user_id, insert_user_code, insert_user_name, insert_timestamp) VALUES
(905, 901, 'P0900', 'サービス太郎', true, false, 1, 1, 'test-user', '2023-01-09 10:00:00');
