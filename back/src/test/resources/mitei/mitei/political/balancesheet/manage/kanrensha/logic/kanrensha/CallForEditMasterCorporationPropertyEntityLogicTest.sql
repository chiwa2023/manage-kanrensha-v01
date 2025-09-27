DELETE FROM master_corporation_property;

INSERT INTO master_corporation_property (master_corporation_property_id, corp_kanrensha_code, partner_name, houjin_sbts, is_foreign, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2401, 'C00000001', 'テスト法人1', '101', FALSE, TRUE, 1, NOW(), 1, NOW()),
(2402, 'C00000002', 'テスト法人2', '101', FALSE, TRUE, 1, NOW(), 1, NOW()),
(2403, 'C00000003', 'テスト法人3', '401', TRUE, FALSE, 1, NOW(), 1, NOW());
