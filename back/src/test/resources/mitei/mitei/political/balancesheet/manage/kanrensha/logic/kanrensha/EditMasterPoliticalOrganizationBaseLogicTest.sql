DELETE FROM master_political_organization_base;

INSERT INTO master_political_organization_base (master_political_organization_base_id, poli_org_kanrensha_code, partner_name, org_name, org_name_kana, org_short_name, org_delegate_code, dantai_kbn, keirishi_code, keirishi_name, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(1301, 'PO1301', '基本更新前 政治団体', '基本更新前 政治団体', 'キホンコウシンマエセイジダンタイ', '更新前略称', 'P000000001', 1, 'P000000002', '経理担当', TRUE, 1, NOW(), 1, NOW());
