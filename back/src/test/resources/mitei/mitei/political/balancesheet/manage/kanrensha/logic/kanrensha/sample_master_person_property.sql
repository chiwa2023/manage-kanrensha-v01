DELETE FROM `master_person_property`;
ALTER TABLE `master_person_property` auto_increment = 0;

-- is_latest=trueが1件の正常系データ
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (1, 1, 'code-normal', 1);
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (2, 1, 'code-normal', 0);

-- is_latest=trueが複数件の異常系データ
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (3, 2, 'code-duplicate', 1);
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (4, 2, 'code-duplicate', 1);

-- is_latest=trueが0件の正常系データ(履歴のみ)
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (5, 3, 'code-history', 0);
INSERT INTO `master_person_property` (`master_person_property_id`, `master_person_id`, `person_kanrensha_code`, `is_latest`) VALUES (6, 3, 'code-history', 0);

