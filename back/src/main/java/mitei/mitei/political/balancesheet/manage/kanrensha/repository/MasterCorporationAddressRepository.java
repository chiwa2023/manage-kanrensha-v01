package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * master_corporation_address接続用Repository
 */
public interface MasterCorporationAddressRepository extends JpaRepository<MasterCorporationAddressEntity, Integer> {

}
