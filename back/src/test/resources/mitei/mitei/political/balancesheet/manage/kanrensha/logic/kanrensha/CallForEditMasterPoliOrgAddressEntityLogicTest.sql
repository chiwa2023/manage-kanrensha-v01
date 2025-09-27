DELETE FROM master_political_organization_address;

INSERT INTO master_political_organization_address (master_political_organization_address_id, poli_org_kanrensha_code, partner_name, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, address_postal, address_block, address_building, is_postal_edit, is_block_edit, is_building_edit, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(701, 'PO00000001', 'テスト政治団体1', '100', '0001', '131016', '0001000', '001', '001', '100-0001', '千代田区千代田１−１', '宮殿', FALSE, FALSE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(702, 'PO00000002', 'テスト政治団体2', '163', '8001', '131041', '0002000', '008', '001', '163-8001', '新宿区西新宿２−８−１', '東京都庁', TRUE, TRUE, FALSE, TRUE, 1, NOW(), 1, NOW()),
(703, 'PO00000003', 'テスト政治団体3', '460', '8501', '231002', '0001000', '001', '001', '460-8501', '名古屋市中区三の丸３−１−２', '愛知県庁', FALSE, FALSE, FALSE, FALSE, 1, NOW(), 1, NOW());
