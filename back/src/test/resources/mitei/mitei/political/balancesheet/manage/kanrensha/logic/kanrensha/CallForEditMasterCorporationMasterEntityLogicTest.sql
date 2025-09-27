DELETE FROM master_corporation;

INSERT INTO master_corporation (master_corporation_id, corp_kanrensha_code, partner_name, all_address, compare_name_text, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2001, 'C00000001', 'テスト法人1', '東京都千代田区', 'テストホウジンイチ', TRUE, 1, NOW(), 1, NOW()),
(2002, 'C00000002', 'テスト法人2', '大阪府大阪市', 'テストホウジンニ', TRUE, 1, NOW(), 1, NOW()),
(2003, 'C00000003', 'テスト法人3', '愛知県名古屋市', 'テストホウジンサン', FALSE, 1, NOW(), 1, NOW());
