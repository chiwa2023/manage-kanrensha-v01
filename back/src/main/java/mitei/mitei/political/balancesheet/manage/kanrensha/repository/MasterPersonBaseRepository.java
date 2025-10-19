package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * master_person_base接続用Repository
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
public interface MasterPersonBaseRepository extends JpaRepository<MasterPersonBaseEntity, Integer> {

    /**
     * 関連者個人コードをキーにテーブルIdの降順で取得する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return 住所リスト
     */
    List<MasterPersonBaseEntity> findByPersonKanrenshaCodeOrderByMasterPersonBaseIdDesc(String personKanrenshaCode);

    /**
     * 職業承認作業リストを取得する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listIsEdit    作業終了フラグリスト
     * @param isAccept      作業承認フラグ
     * @param isLatest      最新フラグ
     * @param pageable      ページング
     * @return 検索結果
     */
    List<MasterPersonBaseEntity> findByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(
            LocalDateTime startDatetime, LocalDateTime endDatetime, List<Boolean> listIsEdit, Boolean isAccept,
            Boolean isLatest, Pageable pageable);

    /**
     * 作業承認対象件数を取得する
     *
     * @param startDatetime 検索開始日時
     * @param endDatetime   検索終了日時
     * @param listIsEdit    作業終了フラグリスト
     * @param isAccept      作業承認
     * @param isLatest      最新フラグ
     * @return 検索件数
     */
    Integer countByInsertTimestampBetweenAndIsShokyouAcceptInAndIsShokyouEditAndIsLatest(LocalDateTime startDatetime,
            LocalDateTime endDatetime, List<Boolean> listIsEdit, Boolean isAccept, Boolean isLatest);
}
