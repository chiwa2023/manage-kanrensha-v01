package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;

/**
 * houjin_no_latest接続用Repository
 */
public interface HoujinNoLatestRepository extends JpaRepository<HoujinNoLatestEntity, Long> {

    /**
     * 自然検索用カラムを検索対象として全文検索をする
     *
     * @param searchWords 検索語
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM houjin_no_latest WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    List<HoujinNoLatestEntity> findFullText(String searchWords, Pageable pageable);
    
    @Query(value = "SELECT COUNT(*) FROM houjin_no_latest WHERE MATCH(search_text) AGAINST (?1 IN BOOLEAN MODE)", nativeQuery = true)
    Long getCountByFullText(String searchWords);
    

    @Query(value = "SELECT COUNT(*) FROM houjin_no_latest WHERE corporate_number LIKE ?1 ", nativeQuery = true)
    Long getCountByHoujinNumber(String searchWords);

    @Query(value = "SELECT * FROM houjin_no_latest WHERE corporate_number LIKE ?1 ORDER BY corporate_number ASC", nativeQuery = true)
    List<HoujinNoLatestEntity> findByHoujinNumber(String searchWords,Pageable pageable);

    
    /**
     * 法人番号が同一かつ最新のデータを取得する
     *
     * @param houjinNo 法人番号
     * @param latest 最新
     * @return 検索結果
     */
    List<HoujinNoLatestEntity> findByCorporateNumberAndLatest(String houjinNo, boolean latest);

    /**
     * 履歴テーブルに存在するが最新テーブルから見削除データを抽出する
     *
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM houjin_no_latest WHERE houjin_no_latest_id IN ("
            + "    SELECT houjin_no_latest_id FROM houjin_no_history WHERE houjin_no_latest_id <> 0)", nativeQuery = true)
    List<HoujinNoLatestEntity> findRestLatestData();

}
