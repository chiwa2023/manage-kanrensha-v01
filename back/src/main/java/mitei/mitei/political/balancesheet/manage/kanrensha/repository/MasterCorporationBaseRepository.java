package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;

/**
 * master_corporation_base接続用Repository
 */
public interface MasterCorporationBaseRepository extends JpaRepository<MasterCorporationBaseEntity, Integer> {

    /**
     * 関連者コードをキーにテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterCorporationBaseEntity> findByCorpKanrenshaCodeOrderByMasterCorporationBaseIdDesc(String kanrenshaCode);

}
