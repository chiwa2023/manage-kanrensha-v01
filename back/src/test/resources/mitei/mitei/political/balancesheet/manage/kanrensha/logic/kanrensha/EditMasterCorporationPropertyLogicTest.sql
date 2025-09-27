DELETE FROM master_corporation_property;

INSERT INTO master_corporation_property (master_corporation_property_id, corp_kanrensha_code, houjin_sbts, is_latest, insert_user_id, insert_timestamp,  delete_user_id, delete_timestamp)
VALUES
(2901, 'C2901', '101', TRUE, 1, NOW(), 1, NOW());
