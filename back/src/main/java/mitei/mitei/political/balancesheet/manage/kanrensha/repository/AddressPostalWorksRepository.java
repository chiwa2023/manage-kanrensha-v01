package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * 郵便番号作業Repository
 */
public interface AddressPostalWorksRepository extends JpaRepository<AddressPostalWorksEntity, Integer> {

}
