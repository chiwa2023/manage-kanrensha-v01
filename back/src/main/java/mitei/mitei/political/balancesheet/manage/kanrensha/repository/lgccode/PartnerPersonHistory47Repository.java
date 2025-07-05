package mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory47Entity;

/**
 * partner_person_history_47接続用Repository
 */
public interface PartnerPersonHistory47Repository extends JpaRepository<PartnerPersonHistory47Entity, Integer> {

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name      団体名称
     * @param address   住所
     * @param shokugyou 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_person_history_47 " + " WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND person_shokugyou = ?3 AND is_latest=1", nativeQuery = true)
    List<PartnerPersonHistoryBaseEntity> selectByProperty(String name, String address, String shokugyou);

}
