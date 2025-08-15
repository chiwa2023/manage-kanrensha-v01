package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org.PartnerCombineOrgUniquekeyDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;

/**
 * wk_tbl_partner_combine_org接続用Repository
 */
public interface WkTblPartnerCombineOrgRepository extends JpaRepository<WkTblPartnerCombineOrgEntity, Integer> {

    /**
     * 編集用に検索を行う
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @param pageable   ページング
     * @return 検索結果
     */
    List<WkTblPartnerCombineOrgEntity> findByInsertUserCodeAndKanrenshaKbnAndIsLatestInAndIsAffectedInAndIsFinishIn(
            Integer userCode, Short kasnrenshaKbn, List<Boolean> listLatest, List<Boolean> isAffected,
            List<Boolean> listFinish, Pageable pageable);

    /**
     * 編集用に検索を行う際の該当件数を返却する
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @return 件数
     */
    Integer countByInsertUserCodeAndKanrenshaKbnAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode,
            Short kasnrenshaKbn, List<Boolean> listLatest, List<Boolean> isAffected, List<Boolean> listFinish);

    /**
     * 処理可能データを取得する
     *
     * @param userCode   ユーザコード
     * @param isLatest   最新該否
     * @param isAffected 影響有無
     * @param isFinish   終了該否
     * @param pageable   ページング
     * @return 検索結果
     */
    Page<WkTblPartnerCombineOrgEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            Boolean isLatest, Boolean isAffected, Boolean isFinish, Pageable pageable);

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct kanrensha_kbn,person_kanrensha_code,org_kanrensha_code,year_array_text "
            + "       FROM wk_tbl_partner_combine_org WHERE insert_user_code = 190"
            + "          GROUP BY kanrensha_kbn,person_kanrensha_code,org_kanrensha_code,year_array_text"
            + "          HAVING count(*) >1", nativeQuery = true)
    List<PartnerCombineOrgUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 全項目が合致するリストを取得する(重複除去用)
     *
     * @param personCode   個人関連者コード
     * @param orgCode      団体関連者コード
     * @param yearText     年配列
     * @param kanrenshaKbn 関連者区分
     * @param userCode     ユーザコード
     * @return 検索結果
     */
    List<WkTblPartnerCombineOrgEntity> findByPersonKanrenshaCodeAndOrgKanrenshaCodeAndYearArrayTextAndKanrenshaKbnAndInsertUserCodeOrderByWkTblPartnerCombineOrgIdAsc(
            String personCode, String orgCode, String yearText, Short kanrenshaKbn, Integer userCode);

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblPartnerCombineOrgEntity> findFirstByOrderByWkTblPartnerCombineOrgCodeDesc();

}
