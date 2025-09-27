DELETE FROM master_political_organization_access;

INSERT INTO master_political_organization_access (master_political_organization_access_id, poli_org_kanrensha_code, partner_name, phon1, phon2, phon3, email, my_portal_url, sns_service_name, sns_portal_url, sns_account, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(1101, 'PO00000001', 'テスト政治団体', '012', '345', '6789', 'org@example.com', 'https://example.com/org', 'テストSNS', 'https://sns.example.com', '@org', TRUE, 1, NOW(), 1, NOW());
