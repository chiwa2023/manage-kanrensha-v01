package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgJudgeEntity;

/**
 * wk_tbl_partner_poli_org_judge接続用Repository
 */
public interface WkTblPartnerPoliOrgJudgeRepository extends JpaRepository<WkTblPartnerPoliOrgJudgeEntity, Integer> {


    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除件数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblPartnerPoliOrgJudgeEntity> findByInsertUserCode(Integer userCode ,Pageable pageable);

}
