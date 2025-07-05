package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpPlusHojinNoEntity;

/**
 * wk_tbl_partner_corp_plus_hojin_no接続用Repository
 */
public interface WkTblPartnerCorpPlusHojinNoRepository
        extends JpaRepository<WkTblPartnerCorpPlusHojinNoEntity, Integer> {

    /**
     * ユーザが同一であるデータを削除する
     *
     * @param userCode ユーザコード
     * @return 削除行数
     */
    int deleteByInsertUserCode(Integer userCode);

    /**
     * 操作者のコードで検索する
     *
     * @param userCode ユーザコード
     * @param pageable ページング条件
     * @return 検索結果
     */
    Page<WkTblPartnerCorpPlusHojinNoEntity> findByInsertUserCodeAndIsLatest(Integer userCode, boolean isLatest,
            Pageable pageable);

}
