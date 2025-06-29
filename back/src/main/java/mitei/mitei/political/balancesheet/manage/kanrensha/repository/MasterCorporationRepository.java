package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * master_corporation接続用Repository
 */
public interface MasterCorporationRepository extends JpaRepository<MasterCorporationEntity, Integer> {

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterCorporationEntity> findByCorpKanrenshaCodeAndCompareNameTextAndIsLatest(String code, String nameText,
            Boolean isLatest);

    /**
     * 団体名で検索する
     *
     * @param nameText 団体名自然検索用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterCorporationEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

}
