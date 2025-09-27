package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;

/**
 * master_corporation_property接続用Repository
 */
public interface MasterCorporationPropertyRepository extends JpaRepository<MasterCorporationPropertyEntity, Integer> {

    /**
     * 関連者コードをキーにテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterCorporationPropertyEntity> findByCorpKanrenshaCodeOrderByMasterCorporationPropertyIdDesc(
            String kanrenshaCode);

}
