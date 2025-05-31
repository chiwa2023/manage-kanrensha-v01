package mitei.mitei.political.balancesheet.manage.kanrensha.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoHistoryEntity;

/**
 * houjin_no_latest接続用Repository
 */
public interface HoujinNoHistoryRepository  extends JpaRepository<HoujinNoHistoryEntity, Long>{

//    /**
//     * 自然検索用カラムを検索対象として全文検索をする
//     *
//     * @param searchWords 検索語
//     * @return 検索結果
//     */
//    @Query(value = "SELECT * FROM houjin_no_latest WHERE latest= 1 AND MATCH(houjin_no_latest_name) AGAINST (?1 IN NATURAL LANGUAGE MODE)", nativeQuery = true)
//    List<HoujinNoLatestEntity> findFullText(String searchWords,Pageable pageable);

}
