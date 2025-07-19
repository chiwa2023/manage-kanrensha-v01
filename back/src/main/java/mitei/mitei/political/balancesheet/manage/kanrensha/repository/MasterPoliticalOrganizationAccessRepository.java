package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * master_political_organization_access接続用Repository
 */
public interface MasterPoliticalOrganizationAccessRepository
        extends JpaRepository<MasterPoliticalOrganizationAccessEntity, Integer> {

}
