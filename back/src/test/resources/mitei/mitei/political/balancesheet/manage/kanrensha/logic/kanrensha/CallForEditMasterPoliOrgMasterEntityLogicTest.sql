DELETE FROM master_political_organization;

INSERT INTO master_political_organization (master_political_organization_id, poli_org_kanrensha_code, partner_name, all_address, compare_name_text, is_latest, insert_user_id, insert_timestamp, update_user_id, update_timestamp,  delete_user_id, delete_timestamp)
VALUES
(501, 'PO00000001', 'テスト政治団体1', '東京都千代田区', 'テストセイジダンタイ1', TRUE, 1, NOW(), 1, NOW(), NULL, NULL),
(502, 'PO00000002', 'テスト政治団体2', '大阪府大阪市', 'テストセイジダンタイ2', TRUE, 1, NOW(), 1, NOW(), NULL, NULL),
(503, 'PO00000003', 'テスト政治団体3', '愛知県名古屋市', 'テストセイジダンタイ3', FALSE, 1, NOW(), 1, NOW(), NULL, NULL);
