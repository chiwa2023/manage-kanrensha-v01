package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;

/**
 * master_political_organization_address接続用Repository
 */
public interface MasterPoliticalOrganizationAddressRepository
        extends JpaRepository<MasterPoliticalOrganizationAddressEntity, Integer> {

}
