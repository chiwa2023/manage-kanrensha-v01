package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * master_person_base接続用Repository
 */
public interface MasterPersonBaseRepository extends JpaRepository<MasterPersonBaseEntity, Integer> {

}
