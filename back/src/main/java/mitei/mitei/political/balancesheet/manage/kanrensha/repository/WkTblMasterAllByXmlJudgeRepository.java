package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlJudgeEntity;

/**
 * wk_tbl_master_all_by_xml_judge接続用Repository
 */
public interface WkTblMasterAllByXmlJudgeRepository extends JpaRepository<WkTblMasterAllByXmlJudgeEntity, Integer> {

    /**
     * 最新かつ該当ユーザデータを抽出する
     *
     * @param userCode ユーザコード
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterAllByXmlJudgeEntity> findByInsertUserCodeAndIsLatest(Integer userCode, Boolean isLatest,
            Pageable pageable);
}
