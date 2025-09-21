package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * riyousha_manager接続用Repository
 */
public interface RiyoushaManagerRepository extends JpaRepository<RiyoushaManagerEntity, Integer> {

    // //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
    // /**
    // * 名称を検索対象として全文検索をする
    // *
    // * @param searchWords 検索語
    // * @return 検索結果
    // */
    // @Query(value = "SELECT * FROM riyousha_manager WHERE saishin_kbn= 1 AND
    // MATCH(riyousha_manager_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)",
    // nativeQuery = true)
    // List<RiyoushaManagerEntity> findFullText(String searchWords);

    /**
     * 最大コードをもつEntityを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RiyoushaManagerEntity> findFirstByOrderByRiyoushaManagerCodeDesc();

    /**
     * 最新リストを取得する
     *
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<RiyoushaManagerEntity> findByIsLatest(Boolean isLatest);

}
