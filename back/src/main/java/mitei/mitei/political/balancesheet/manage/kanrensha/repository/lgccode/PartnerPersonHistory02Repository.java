package mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.PartnerCommonInfoDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory02Entity;

/**
 * partner_person_history_02接続用Repository
 */
public interface PartnerPersonHistory02Repository extends JpaRepository<PartnerPersonHistory02Entity, Integer> {

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name      団体名称
     * @param address   住所
     * @param shokugyou 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_person_history_02 WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND person_shokugyou = ?3 AND is_latest=1", nativeQuery = true)
    List<PartnerPersonHistoryBaseEntity> selectByProperty(String name, String address, String shokugyou);

    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<PartnerPersonHistory02Entity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime, boolean isLatest,
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
    Page<PartnerPersonHistory02Entity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);

    /**
     * 履歴を検索条件に全関連者テーブルから取得する
     *
     * @param name 名称
     * @param address 住所
     * @param recognizedKey 認識キー
     * @return 検索結果
     */
    @Query(value = "SELECT  1 AS kanrensha_kbn ,partner_name ,all_address "
            + " ,person_shokugyou AS recognized_key"
            + " ,person_kanrensha_code AS kanrensha_code"
            + "   FROM partner_person_history_02 "
            + "       WHERE partner_name = ?1 AND all_address = ?2 AND person_shokugyou = ?3"
            + "           AND is_latest = 1"
            + " UNION "
            + "SELECT 2 AS kanrensha_kbn ,partner_name ,all_address "
            + " ,corp_delegate AS recognized_key "
            + " ,corp_kanrensha_code AS kanrensha_code "
            + "   FROM partner_corp_history_02 "
            + "       WHERE partner_name = ?1 AND all_address = ?2 AND corp_delegate = ?3"
            + "           AND is_latest = 1"
            + " UNION "
            + "SELECT  3 AS kanrensha_kbn ,partner_name ,all_address "
            + " ,poli_org_delegate AS recognized_key "
            + " ,poli_org_kanrensha_code AS kanrensha_code "
            + "   FROM partner_poli_org_history_02 "
            + "       WHERE partner_name = ?1 AND all_address = ?2 AND poli_org_delegate = ?3"
            + "           AND is_latest = 1"
            , nativeQuery = true)
    List<PartnerCommonInfoDto> findAllKanrensha(String name, String address, String recognizedKey);
}
