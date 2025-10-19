package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * master_person接続用Repository
 */
public interface MasterPersonRepository extends JpaRepository<MasterPersonEntity, Integer> {

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPersonEntity> findByPersonKanrenshaCodeAndCompareNameTextAndIsLatest(String code, String nameText,
            Boolean isLatest);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<MasterPersonEntity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime, boolean isLatest,
            Pageable pageable);

    /**
     * 基準時間開始以上かつ終了より前の最新を取得する
     *
     * @param dateTimeStart 基準日時開始
     * @param dateTimeEnd   基準日時終了
     * @param isLatest      最新該否
     * @param pageable      ページング条件
     * @return 検索結果
     */
    Page<MasterPersonEntity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

    /**
     * 団体名で検索する
     *
     * @param nameText 団体名自然検索用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPersonEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

    /**
     * 該当コードかつ最新データを取得する
     *
     * @param code     関連者コード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    Optional<MasterPersonEntity> findFirstByPersonKanrenshaCodeAndIsLatest(String code, Boolean isLatest);

    /**
     * 関連者コードを基にテーブルId降順で取得する
     *
     * @param code 関連者コード
     * @return 検索結果
     */
    List<MasterPersonEntity> findByPersonKanrenshaCodeOrderByMasterPersonIdDesc(String code);

    /**
     * 現在利用できるリストをページングで取得する(TODO 自然検索に変更の予定)
     *
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    List<MasterPersonEntity> findByIsLatest(Boolean isLatest, Pageable pageable);

    /**
     * 現在利用できる件数取得する(TODO 自然検索に変更の予定)
     *
     * @param isLatest 最新該否
     * @return 検索件数
     */
    Integer countByIsLatest(Boolean isLatest);

    /**
     * 関連者コードと最新該否から個人マスタリストを取得する
     *
     * @param code     関連者コード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPersonEntity> findByPersonKanrenshaCodeAndIsLatest(String code, boolean isLatest);

}
