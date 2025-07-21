package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * master_political_organization_base接続用Repository
 */
public interface MasterPoliticalOrganizationBaseRepository
        extends JpaRepository<MasterPoliticalOrganizationBaseEntity, Integer> {

}
