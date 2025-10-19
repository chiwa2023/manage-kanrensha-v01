DELETE FROM master_corporation_base;

INSERT INTO master_corporation_base (master_corporation_base_id, corp_kanrensha_code, partner_name, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2801, 'C2801', '更新前略称', TRUE, 1, NOW(), 1, NOW());
