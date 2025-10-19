package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgJudgeEntity;

/**
 * wk_tbl_master_poli_org_judge接続用Repository
 */
public interface WkTblMasterPoliOrgJudgeRepository  extends JpaRepository<WkTblMasterPoliOrgJudgeEntity, Integer>{

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterPoliOrgJudgeEntity> findByInsertUserCodeAndIsLatest(Integer userCode, boolean isLatest,
            Pageable pageable);

}
