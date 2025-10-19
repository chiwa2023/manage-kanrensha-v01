DELETE FROM `master_person_address`;
ALTER TABLE `master_person_address` auto_increment = 0;

-- EditMasterPersonAddressLogicTest test data
-- master_person_address
INSERT INTO master_person_address (master_person_address_id, master_person_id, person_kanrensha_code, partner_name, is_latest, address_postal, address_block, address_building, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, rsdt2_id, is_postal_edit, is_block_edit, is_building_edit, is_postal_accept, is_block_accept, is_building_accept, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(601, 1, 'P0601', '住所更新前 一郎', true, '100-0001', '千代田区千代田１−１', '宮殿', '100', '0001', '131016', '0001000', '001', '001', '002', false, false, false, true, true, true, 1, 1, 'test-user', '2023-01-06 10:00:00', 0, 0, '', '1948-07-29 00:00:00');
