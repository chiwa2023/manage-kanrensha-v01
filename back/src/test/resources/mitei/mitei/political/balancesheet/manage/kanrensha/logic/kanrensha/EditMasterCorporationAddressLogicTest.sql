DELETE FROM master_corporation_address;

INSERT INTO master_corporation_address (master_corporation_address_id, corp_kanrensha_code, partner_name, address_building, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2701, 'C2701', '住所更新前 法人', 'テストビル', TRUE, 1, NOW(), 1, NOW());
