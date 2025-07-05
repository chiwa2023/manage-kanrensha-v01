package mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory04Entity;

/**
 * partner_corp_history_04接続用Repository
 */
public interface PartnerCorpHistory04Repository extends JpaRepository<PartnerCorpHistory04Entity, Integer> {

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_corp_history_04 " + " WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND corp_delegate = ?3 AND is_latest=1", nativeQuery = true)
    List<PartnerCorpHistoryBaseEntity> selectByProperty(String name, String address, String delegate);

}
