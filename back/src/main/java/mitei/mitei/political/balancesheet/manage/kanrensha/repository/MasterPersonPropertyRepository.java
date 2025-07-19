package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * master_person_property接続用Repository
 */
public interface MasterPersonPropertyRepository extends JpaRepository<MasterPersonPropertyEntity, Integer> {

}
