package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * master_political_organization_property接続用Repository
 */
public interface MasterPoliticalOrganizationPropertyRepository
        extends JpaRepository<MasterPoliticalOrganizationPropertyEntity, Integer> {

}
