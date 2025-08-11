package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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

}
