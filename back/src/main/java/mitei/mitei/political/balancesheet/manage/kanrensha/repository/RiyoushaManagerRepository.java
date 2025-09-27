package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionIntegerDto;
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


    /**
     * コードに合致かつ最新を取得する
     *
     * @param code     コード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<RiyoushaManagerEntity> findByRiyoushaManagerCodeAndIsLatest(Integer code, Boolean isLatest);

    /**
     * 個人コードから紐づく組織をselectbox項目選択肢形式で取得する
     *
     * @param personCode 個人コード
     * @return 組織選択肢
     */
    @Query(value = "SELECT riyousha_manager_id AS value,riyousha_manager_name AS text "
            + "        FROM riyousha_manager WHERE riyousha_manager_code IN ("
            + "            SELECT riyousha_org_code FROM riyousha_org_manager"
            + "            WHERE riyousha_person_code = ?1 AND is_latest = 1"
            + "        ) AND is_latest =1;", nativeQuery = true)
    List<SelectOptionIntegerDto> findByUserCodeOrgOptions(Integer personCode);

    /**
     * 組織コードから紐づく個人マスタリストを取得する
     *
     * @param orgCode 組織コード
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM riyousha_manager WHERE riyousha_manager_code IN ("
            + "             SELECT riyousha_person_code FROM riyousha_org_manager"
            + "                 WHERE riyousha_org_code = ?1 AND is_latest =1"
            + "        ) AND  is_latest =1;", nativeQuery = true)
    List<RiyoushaManagerEntity> findCombinePerson(Integer orgCode);

}
