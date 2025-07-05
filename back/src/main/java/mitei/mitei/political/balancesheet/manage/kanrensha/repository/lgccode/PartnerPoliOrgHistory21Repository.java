package mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory21Entity;

/**
 * partner_poli_org_history_21接続用Repository
 */
public interface PartnerPoliOrgHistory21Repository extends JpaRepository<PartnerPoliOrgHistory21Entity, Integer> {

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_poli_org_history_21 WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND poli_org_delegate = ?3 AND is_latest=1", nativeQuery = true)
    List<PartnerPoliOrgHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

}
