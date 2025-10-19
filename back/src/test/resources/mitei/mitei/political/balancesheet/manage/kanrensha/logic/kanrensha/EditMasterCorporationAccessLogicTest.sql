DELETE FROM master_corporation_access;

INSERT INTO master_corporation_access (master_corporation_access_id, corp_kanrensha_code, partner_name, sns_account, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2601, 'C00000001', 'テスト法人', '@corp', TRUE, 1, NOW(), 1, NOW());
