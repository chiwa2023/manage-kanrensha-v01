DELETE FROM master_political_organization_property;

INSERT INTO master_political_organization_property (master_political_organization_property_id, poli_org_kanrensha_code, partner_name, account_mgr_code, account_mgr_name, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(901, 'PO00000001', 'テスト政治団体1', 'P000000001', '会計管理者1', TRUE, 1, NOW(), 1, NOW()),
(902, 'PO00000002', 'テスト政治団体2', 'P000000003', '会計管理者2', TRUE, 1, NOW(), 1, NOW()),
(903, 'PO00000003', 'テスト政治団体3', 'P000000005', '会計管理者3', FALSE, 1, NOW(), 1, NOW());
