package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;

/**
 * master_person_access接続用Repository
 */
public interface MasterPersonAccessRepository extends JpaRepository<MasterPersonAccessEntity, Integer> {

}
