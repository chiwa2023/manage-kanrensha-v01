package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;

/**
 * master_corporation_property接続用Repository
 */
public interface MasterCorporationPropertyRepository extends JpaRepository<MasterCorporationPropertyEntity, Integer> {

}
