package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgJudgeEntity;

/**
 * wk_tbl_partner_combine_org_judge接続用Repository
 */
public interface WkTblPartnerCombineOrgJudgeRepository
        extends JpaRepository<WkTblPartnerCombineOrgJudgeEntity, Integer> {

    /**
     * 該当ユーザかつ最新データを取得する
     *
     * @param userCode ユーザコード
     * @param isLatest 最新該否
     * @param pageable ページング
     * @return 検索結果
     */
    Page<WkTblPartnerCombineOrgJudgeEntity> findByInsertUserCodeAndIsLatest(Integer userCode, Boolean isLatest,
            Pageable pageable);

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

}
