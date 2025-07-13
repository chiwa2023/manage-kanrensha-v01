package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * master_political_organization接続用Repository
 */
public interface MasterPoliticalOrganizationRepository
        extends JpaRepository<MasterPoliticalOrganizationEntity, Integer> {

    /**
     * 最新かつ関連者コードと比較用名称リストを取得する
     *
     * @param code     関連者コード
     * @param nameText 比較用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationEntity> findByPoliOrgKanrenshaCodeAndCompareNameTextAndIsLatest(String code,
            String nameText, Boolean isLatest);


    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<MasterPoliticalOrganizationEntity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime, boolean isLatest,
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
    Page<MasterPoliticalOrganizationEntity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

}
