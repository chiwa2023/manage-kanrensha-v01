package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;

/**
 * master_corporation_base接続用Repository
 */
public interface MasterCorporationBaseRepository extends JpaRepository<MasterCorporationBaseEntity, Integer> {

}
