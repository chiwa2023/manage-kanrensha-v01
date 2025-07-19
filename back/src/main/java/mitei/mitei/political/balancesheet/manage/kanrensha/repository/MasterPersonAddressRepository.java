package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * master_person_address接続用Repository
 */
public interface MasterPersonAddressRepository extends JpaRepository<MasterPersonAddressEntity, Integer> {

}
