DELETE FROM master_political_organization_access;

INSERT INTO master_political_organization_access (master_political_organization_access_id, poli_org_kanrensha_code, partner_name, phon1, phon2, phon3, email, my_portal_url, sns_service_name, sns_portal_url, sns_account, is_latest, insert_user_id, insert_timestamp, delete_user_id, delete_timestamp)
VALUES
(601, 'PO00000001', 'テスト政治団体1', '012', '345', '6789', 'org1@example.com', 'https://example.com/org1', 'テストSNS', 'https://sns.example.com', '@org1', TRUE, 1, NOW(), 1, NOW()),
(602, 'PO00000002', 'テスト政治団体2', '011', '222', '3333', 'org2@example.com', 'https://example.com/org2', 'テストSNS', 'https://sns.example.com', '@org2', TRUE, 1, NOW(), 1, NOW()),
(603, 'PO00000003', 'テスト政治団体3', '044', '555', '6666', 'org3@example.com', 'https://example.com/org3', 'テストSNS', 'https://sns.example.com', '@org3', FALSE, 1, NOW(), 1, NOW());
