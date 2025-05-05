DELETE FROM `address_postal_works`;
ALTER TABLE `address_postal_works` auto_increment = 0;
INSERT INTO `address_postal_works` (`address_postal_works_id`,`address_postal_irregular_id`,`lg_code`,`postal1`,`postal2`,`is_add_postal`,`is_repair_rsdt`,`address_org`,`address_name`,`address_postal`,`address_block`) VALUES (1,2,'3','4','5',1,1,'a','b','c','d');
