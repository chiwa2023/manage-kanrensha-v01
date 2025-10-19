DELETE FROM master_political_organization_base;

INSERT INTO master_political_organization_base (master_political_organization_base_id, poli_org_kanrensha_code, partner_name,  org_name_kana,  org_delegate_code,  is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(801, 'PO00000001', 'テスト政治団体1',  'テストセイジダンタイ1',  'P000000001',  TRUE, 1, NOW(), 1, NOW()),
(802, 'PO00000002', 'テスト政治団体2',  'テストセイジダンタイ2',  'P000000003',  TRUE, 1, NOW(), 1, NOW()),
(803, 'PO00000003', 'テスト政治団体3',  'テストセイジダンタイ3',  'P000000005',  FALSE, 1, NOW(), 1, NOW());
