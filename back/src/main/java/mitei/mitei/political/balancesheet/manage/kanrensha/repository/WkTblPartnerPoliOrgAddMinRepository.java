package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.LockModeType;
import mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min.PartnerPoliOrgMasterUniquekeyDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;

/**
 * wk_tbl_partner_poli_org_add_min接続用Repository
 */
public interface WkTblPartnerPoliOrgAddMinRepository  extends JpaRepository<WkTblPartnerPoliOrgAddMinEntity, Integer>{


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
    Optional<WkTblPartnerPoliOrgAddMinEntity> findFirstByOrderByWkTblPartnerPoliOrgAddMinCodeDesc();


    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblPartnerPoliOrgAddMinEntity> findByInsertUserCodeAndIsLatestAndIsAffected(Integer userCode, boolean isLatest,
            boolean isAffected, Pageable pageable);

    /**
     * 重複キーを検出する
     *
     * @param userCode ユーザコード
     * @return 検索結果
     */
    @Query(value = "SELECT distinct partner_name, all_address, poli_org_delegate FROM wk_tbl_partner_poli_org_add_min WHERE insert_user_code = ?1 "
            + "GROUP BY partner_name, all_address, poli_org_delegate HAVING count(*) >1", nativeQuery = true)
    List<PartnerPoliOrgMasterUniquekeyDto> findDuplicateUniqueKey(Integer userCode);

    /**
     * 全項目が合致するリストを取得する(重複除去用)
     *
     * @param partnerName  関連者名称
     * @param allAddress   全住所
     * @param personShokugyou 個人職業
     * @param userCode     ユーザコード
     * @return 検索結果
     */
    List<WkTblPartnerPoliOrgAddMinEntity> findByPartnerNameAndAllAddressAndPoliOrgDelegateAndInsertUserCodeOrderByWkTblPartnerPoliOrgAddMinIdAsc( // NOPMD
            String partnerName, String allAddress, String personShokugyou, Integer userCode);

}
