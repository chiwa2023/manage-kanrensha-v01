DELETE FROM `master_person_address`;
ALTER TABLE `master_person_address` auto_increment = 0;
-- CallForEditMasterPersonAddressEntityLogicTest test data
-- master_person_address
INSERT INTO master_person_address (master_person_address_id, master_person_id, person_kanrensha_code, partner_name, is_latest, address_postal, address_block, address_building, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, rsdt2_id, is_postal_edit, is_block_edit, is_building_edit, is_postal_accept, is_block_accept, is_building_accept, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(101, 1, 'P0001', 'テスト 一郎', true, '100-0001', '千代田区千代田１−１', '宮殿', '100', '0001', '131016', '0001000', '001', '001', '002', false, false, false, true, true, true, 1, 1, 'test-user', '2023-01-01 10:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(102, 2, 'P0002', 'テスト 二郎', true, '163-8001', '新宿区西新宿２−８−１', '東京都庁', '163', '8001', '131041', '0002000', '008', '001', '', true, true, false, false, false, true, 1, 1, 'test-user', '2023-01-01 11:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(103, 3, 'P0003', 'テスト 三郎', false, '111-0032', '台東区浅草２−３−１', '雷門', '111', '0032', '131067', '0032000', '003', '001', '', false, false, false, true, true, true, 1, 1, 'test-user', '2023-01-01 12:00:00', 0, 0, '', '1948-07-29 00:00:00');
