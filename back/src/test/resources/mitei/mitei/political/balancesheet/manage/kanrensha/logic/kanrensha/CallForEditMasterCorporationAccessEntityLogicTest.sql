DELETE FROM master_corporation_access;

INSERT INTO master_corporation_access (master_corporation_access_id, corp_kanrensha_code, partner_name, phon1, phon2, phon3, email, my_portal_url, sns_service_name, sns_portal_url, sns_account, is_latest, insert_user_id, insert_timestamp, delete_user_id, delete_timestamp)
VALUES
(2101, 'C00000001', 'テスト法人1', '012', '345', '6789', 'corp1@example.com', 'https://example.com/corp1', 'テストSNS', 'https://sns.example.com', '@corp1', TRUE, 1, NOW(), 1, NOW()),
(2102, 'C00000002', 'テスト法人2', '011', '222', '3333', 'corp2@example.com', 'https://example.com/corp2', 'テストSNS', 'https://sns.example.com', '@corp2', TRUE, 1, NOW(), 1, NOW()),
(2103, 'C00000003', 'テスト法人3', '044', '555', '6666', 'corp3@example.com', 'https://example.com/corp3', 'テストSNS', 'https://sns.example.com', '@corp3', FALSE, 1, NOW(), 1, NOW());
