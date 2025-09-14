DELETE FROM master_political_organization;

INSERT INTO master_political_organization (master_political_organization_id, poli_org_kanrensha_code, partner_name, all_address, compare_name_text, is_latest, insert_user_id, insert_timestamp, update_user_id, update_timestamp,  delete_user_id, delete_timestamp)
VALUES
(1001, 'PO1001', '更新前 政治団体', '更新前住所', 'コウシンマエセイジダンタイ', TRUE, 1, NOW(), 1, NOW(), NULL, NULL);
