package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.PartnerCorpUniquekeyDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpHistoryEntity;

/**
 * wk_tbl_partner_corp_hisotry接続用Repository
 */
public interface WkTblPartnerCorpHistoryRepository extends JpaRepository<WkTblPartnerCorpHistoryEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 最大コードを取得する
     *
     * @return 最大コードをもつEntity
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WkTblPartnerCorpHistoryEntity> findFirstByOrderByWkPartnerCorpHistoryCodeDesc();

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblPartnerCorpHistoryEntity> findByInsertUserCodeAndIsLatest(Integer userCode, boolean isLatest,
            Pageable pageable);

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct partner_name, all_address, corp_delegate, corp_kanrensha_code FROM wk_tbl_partner_corp_history WHERE insert_user_code = ?1 "
            + "GROUP BY partner_name, all_address, corp_delegate, corp_kanrensha_code HAVING count(*) >1", nativeQuery = true)
    List<PartnerCorpUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 全項目が合致するリストを取得する(重複除去用)
     *
     * @param partnerName   関連者名称
     * @param allAddress    全住所
     * @param corpDelegate  企業・団体代表者
     * @param kanrenshaCode 関連者コード
     * @param userCode      ユーザコード
     * @return 検索結果
     */
    List<WkTblPartnerCorpHistoryEntity> findByPartnerNameAndAllAddressAndCorpDelegateAndCorpKanrenshaCodeAndInsertUserCodeOrderByWkPartnerCorpHistoryIdAsc( // NOPMD
            String partnerName, String allAddress, String corpDelegate, String kanrenshaCode, Integer userCode);

}
