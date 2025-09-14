package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;

/**
 * riyousha_admin_address接続用Repository
 */
public interface RiyoushaAdminAddressRepository extends JpaRepository<RiyoushaAdminAddressEntity, Integer> {

//    //TODO マスタ系のテーブルでは名称検索が要求されることが多いので、事前に自動生成する。不要な場合は削除する
//    /**
//     * 名称を検索対象として全文検索をする
//     *
//     * @param searchWords 検索語
//     * @return 検索結果
//     */
//    @Query(value = "SELECT * FROM riyousha_admin_address WHERE saishin_kbn= 1 AND MATCH(riyousha_admin_address_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    List<RiyoushaAdminAddressEntity> findFullText(String searchWords);
}
