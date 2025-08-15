package mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.PartnerCombineOrg2025Entity;

/**
 * partner_combine_org_2025接続用Repository
 */
public interface PartnerCombineOrg2025Repository extends JpaRepository<PartnerCombineOrg2025Entity, Integer> {

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PartnerCombineOrg2025Entity> findFirstByOrderByPartnerCombineOrgCodeDesc();

}
