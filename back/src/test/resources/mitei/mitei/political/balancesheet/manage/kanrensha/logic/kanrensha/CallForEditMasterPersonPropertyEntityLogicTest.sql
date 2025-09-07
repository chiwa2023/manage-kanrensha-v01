DELETE FROM `master_person_property`;
ALTER TABLE `master_person_property` auto_increment = 0;

-- CallForEditMasterPersonPropertyEntityLogicTest test data
-- master_person_property
INSERT INTO master_person_property (master_person_property_id, master_person_id, person_kanrensha_code, partner_name, is_latest, is_foreign, insert_user_id, insert_user_code, insert_user_name, insert_timestamp, delete_user_id, delete_user_code, delete_user_name, delete_timestamp) VALUES
(201, 1, 'P0001', 'プロパティ 一郎', true, false, 1, 1, 'test-user', '2023-01-02 10:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(202, 2, 'P0002', 'プロパティ 二郎', true, true, 1, 1, 'test-user', '2023-01-02 11:00:00', 0, 0, '', '1948-07-29 00:00:00'),
(203, 3, 'P0003', 'プロパティ 三郎', false, false, 1, 1, 'test-user', '2023-01-02 12:00:00', 0, 0, '', '1948-07-29 00:00:00');
