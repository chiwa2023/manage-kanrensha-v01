package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * master_person接続用Repository
 */
public interface MasterPersonRepository  extends JpaRepository<MasterPersonEntity, Integer>{

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPersonEntity> findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(String code, String nameText,
            Boolean isLatest);

}
