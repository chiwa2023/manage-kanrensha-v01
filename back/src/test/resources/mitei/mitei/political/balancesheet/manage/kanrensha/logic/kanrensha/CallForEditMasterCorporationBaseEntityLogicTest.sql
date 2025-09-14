DELETE FROM master_corporation_base;

INSERT INTO master_corporation_base (master_corporation_base_id, corp_kanrensha_code, partner_name, org_name_kana,  org_delegate_code, is_shiten, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2301, 'C00000001', 'テスト法人1', 'テストホウジンイチ',  'P000000001', FALSE, TRUE, 1, NOW(), 1, NOW()),
(2302, 'C00000002', 'テスト法人2', 'テストホウジンニ',  'P000000003', FALSE, TRUE, 1, NOW(), 1, NOW()),
(2303, 'C00000003', 'テスト法人3', 'テストホウジンサン', 'P000000005', TRUE, FALSE, 1, NOW(), 1, NOW());
