DELETE FROM master_corporation;

INSERT INTO master_corporation (master_corporation_id, corp_kanrensha_code, partner_name, all_address, compare_name_text, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2501, 'C2501', '更新前 法人', '更新前住所', 'コウシンマエホウジン', TRUE, 1, NOW(), 1, NOW());
