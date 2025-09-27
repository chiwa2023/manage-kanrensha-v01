DELETE FROM master_political_organization_address;

INSERT INTO master_political_organization_address (master_political_organization_address_id, poli_org_kanrensha_code, partner_name, postal1, postal2, lg_code, machiaza_id, blk_id, rsdt_id, address_postal, address_block, address_building, is_postal_edit, is_block_edit, is_building_edit, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(1201, 'PO1201', '住所更新前 政治団体', '100', '0001', '131016', '0001000', '001', '001', '100-0001', '千代田区千代田１−１', 'テストビル', FALSE, FALSE, FALSE, TRUE, 1, NOW(), 1, NOW());
