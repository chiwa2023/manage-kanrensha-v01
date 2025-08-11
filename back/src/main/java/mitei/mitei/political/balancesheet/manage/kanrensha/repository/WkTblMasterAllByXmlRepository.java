package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;

/**
 * wk_tbl_master_all_by_xml接続用Repository
 */
public interface WkTblMasterAllByXmlRepository extends JpaRepository<WkTblMasterAllByXmlEntity, Integer> {

    /**
     * 登録すべきデータを抽出する
     *
     * @param userCode   ユーザコード
     * @param isLatest   最新該否
     * @param isAffected 反映有無
     * @param isFinish   終了有無
     * @param pageable   ページング条件
     * @return 検索結果
     */
    Page<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(Integer userCode,
            Boolean isLatest, Boolean isAffected, Boolean isFinish, Pageable pageable);

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
    List<WkTblMasterAllByXmlEntity> findByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode,
            List<Boolean> listLatest, List<Boolean> isAffected, List<Boolean> listFinish, Pageable pageable);

    /**
     * 編集用に検索を行う際の該当件数を返却する
     *
     * @param userCode   ユーザコード
     * @param listLatest 検索条件履歴
     * @param isAffected 検索条件反映行
     * @param listFinish 検索条件勝利完了
     * @return 件数
     */
    Integer countByInsertUserCodeAndIsLatestInAndIsAffectedInAndIsFinishIn(Integer userCode, List<Boolean> listLatest,
            List<Boolean> isAffected, List<Boolean> listFinish);

}
