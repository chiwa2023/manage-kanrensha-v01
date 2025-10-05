DELETE FROM master_corporation_address;
DELETE FROM `master_person_address`;
DELETE FROM master_political_organization_address;
ALTER TABLE `master_person_address` auto_increment = 0;
ALTER TABLE `master_corporation_address` auto_increment = 0;
ALTER TABLE `master_political_organization_address` auto_increment = 0;


INSERT INTO master_political_organization_address (master_political_organization_address_id, poli_org_kanrensha_code, partner_name, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, address_postal, address_block, address_building, is_postal_edit, is_block_edit, is_building_edit, is_postal_accept, is_block_accept, is_building_accept, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(701, 'PO00000001', 'テスト政治団体1', '100', '0001', '131016', '0001000', '001', '001', '100-0001', '千代田区千代田１−１', '宮殿', FALSE, true, FALSE, FALSE, FALSE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(702, 'PO00000002', 'テスト政治団体2', '163', '8001', '131041', '0002000', '008', '001', '163-8001', '新宿区西新宿２−８−１', '東京都庁', TRUE, TRUE, FALSE, true, TRUE, true, TRUE, 1, NOW(), 1, NOW()),
(703, 'PO00000003', 'テスト政治団体3', '460', '8501', '231002', '0001000', '001', '001', '460-8501', '名古屋市中区三の丸３−１−２', '愛知県庁', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 1, NOW(), 1, NOW());

INSERT INTO master_person_address (master_person_address_id, master_person_id, person_kanrensha_code, partner_name, is_latest, address_postal, address_block, address_building, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, rsdt2_id, is_postal_edit, is_block_edit, is_building_edit, is_postal_accept, is_block_accept, is_building_accept, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(101, 1, 'P0001', 'テスト 一郎', true, '100-0001', '千代田区千代田１−１', '宮殿', '100', '0001', '131016', '0001000', '001', '001', '002', false, true, false, false, false, false, 1, 1, 'test-user', '2023-01-01 10:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(102, 2, 'P0002', 'テスト 二郎', true, '163-8001', '新宿区西新宿２−８−１', '東京都庁', '163', '8001', '131041', '0002000', '008', '001', '', true, true, false, true, true, true, 1, 1, 'test-user', '2023-01-01 11:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(103, 3, 'P0003', 'テスト 三郎', false, '111-0032', '台東区浅草２−３−１', '雷門', '111', '0032', '131067', '0032000', '003', '001', '', false, false, false, true, true, true, 1, 1, 'test-user', '2023-01-01 12:00:00', 0, 0, '', '1948-07-29 00:00:00');

INSERT INTO master_corporation_address (master_corporation_address_id, corp_kanrensha_code, partner_name, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, address_postal, address_block, address_building, is_postal_edit, is_block_edit, is_building_edit, is_postal_accept, is_block_accept, is_building_accept , is_latest, insert_user_id, insert_timestamp, delete_user_id, delete_timestamp)
VALUES
(2201, 'C00000001', 'テスト法人1', '100', '0001', '131016', '0001000', '001', '001', '100-0001', '千代田区千代田１−１', 'テストビル1', FALSE, TRUE, FALSE, true, true, true, TRUE, 1, NOW(), 1, NOW()),
(2202, 'C00000002', 'テスト法人2', '163', '8001', '131041', '0002000', '008', '001', '163-8001', '新宿区西新宿２−８−１', 'テストビル2', TRUE, TRUE, FALSE, FALSE, FALSE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(2203, 'C00000003', 'テスト法人3', '460', '8501', '231002', '0001000', '001', '001', '460-8501', '名古屋市中区三の丸３−１−２', 'テストビル3', FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, FALSE, 1, NOW(), 1, NOW());
