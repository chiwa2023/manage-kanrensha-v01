package mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory46Entity;

/**
 * partner_poli_org_history_01接続用Repository
 */
public interface PartnerPoliOrgHistory46Repository extends JpaRepository<PartnerPoliOrgHistory46Entity, Integer> {

    /**
     * 企業・団体の属性でリスト取得する
     *
     * @param name     団体名称
     * @param address  住所
     * @param delegate 代表者名
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM partner_poli_org_history_46 WHERE partner_name = ?1 AND all_address = ?2 "
            + "   AND poli_org_delegate = ?3 AND is_latest=1", nativeQuery = true)
    List<PartnerPoliOrgHistoryBaseEntity> selectByProperty(String name, String address, String delegate);


    /**
     * 基準時間より前の最新データを取得する
     *
     * @param dateTime 基準日時開始
     * @param isLatest 最新該否
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<PartnerPoliOrgHistory46Entity> findByInsertTimestampLessThanAndIsLatest(LocalDateTime dateTime, boolean isLatest,
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
    Page<PartnerPoliOrgHistory46Entity> findByInsertTimestampGreaterThanEqualAndInsertTimestampLessThanAndIsLatest(
            LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd, boolean isLatest, Pageable pageable);
}
