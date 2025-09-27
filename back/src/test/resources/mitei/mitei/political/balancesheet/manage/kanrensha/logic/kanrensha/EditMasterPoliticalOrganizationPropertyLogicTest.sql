DELETE FROM master_political_organization_property;

INSERT INTO master_political_organization_property (master_political_organization_property_id, poli_org_kanrensha_code, partner_name, account_mgr_code, account_mgr_name, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(1401, 'PO1401', '属性更新前 政治団体', 'P000000001', '会計管理者1', TRUE, 1, NOW(), 1, NOW());
