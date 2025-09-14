DELETE FROM master_corporation_address;

INSERT INTO master_corporation_address (master_corporation_address_id, corp_kanrensha_code, partner_name, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, address_postal, address_block, address_building, is_postal_edit, is_block_edit, is_building_edit, is_latest, insert_user_id, insert_timestamp, delete_user_id, delete_timestamp)
VALUES
(2201, 'C00000001', 'テスト法人1', '100', '0001', '131016', '0001000', '001', '001', '100-0001', '千代田区千代田１−１', 'テストビル1', FALSE, FALSE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(2202, 'C00000002', 'テスト法人2', '163', '8001', '131041', '0002000', '008', '001', '163-8001', '新宿区西新宿２−８−１', 'テストビル2', TRUE, TRUE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(2203, 'C00000003', 'テスト法人3', '460', '8501', '231002', '0001000', '001', '001', '460-8501', '名古屋市中区三の丸３−１−２', 'テストビル3', FALSE, FALSE, FALSE, FALSE, 1, NOW(), 1, NOW());
