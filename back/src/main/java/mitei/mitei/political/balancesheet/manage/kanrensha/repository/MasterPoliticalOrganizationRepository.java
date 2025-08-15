package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
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
    Page<MasterPoliticalOrganizationEntity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime,
            boolean isLatest, Pageable pageable);
    /**
     * 団体名で検索する
     *
     * @param nameText 団体名自然検索用名称
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationEntity> findByCompareNameTextAndIsLatest(String nameText, Boolean isLatest);

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

    /**
     * 関連者コードからマスタデータを取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    @Query(value = "SELECT  1 AS kanrensha_kbn ,partner_name ,all_address " + " ,person_shokugyou AS recognized_key "
            + " ,person_kanrensha_code AS kanrensha_code "
            + "   FROM  master_person  WHERE person_kanrensha_code = ?1 AND is_latest = 1 " + "UNION "
            + "SELECT 2 AS kanrensha_kbn  ,partner_name ,all_address " + " ,corp_delegate AS recognized_key "
            + " ,corp_kanrensha_code AS kanrensha_code "
            + "   FROM  master_corporation WHERE corp_kanrensha_code =  ?1 AND is_latest = 1 " + "UNION "
            + "SELECT 3 AS kanrensha_kbn ,partner_name ,all_address " + " ,poli_org_delegate AS recognized_key "
            + " ,poli_org_kanrensha_code AS kanrensha_code "
            + "   FROM  master_political_organization  WHERE poli_org_kanrensha_code = ?1 AND is_latest = 1 ", nativeQuery = true)
    List<PartnerCommonInfoDto> findKanrenshaCode(String kanrenshaCode);

    /**
     * 該当コードかつ最新該否でデータを取得する
     *
     * @param code     関連者コード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    Optional<MasterPoliticalOrganizationEntity> findFirstByPoliOrgKanrenshaCodeAndIsLatest(String code,
            Boolean isLatest);

}
