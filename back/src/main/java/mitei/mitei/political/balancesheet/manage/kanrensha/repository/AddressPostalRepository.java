package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionNumberDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 郵便番号Repository
 */
public interface AddressPostalRepository extends JpaRepository<AddressPostalEntity, Integer> {

    /**
     * 不規則データを抽出する
     *
     * @param isSearch 地方自治体住居テーブルを検索する
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalEntity> findByIsGyoseikuData(Boolean isSearch, Pageable pageable);


    /**
     * 地方公共団体コードが前方一致する郵便番号データを抽出する
     *
     * @param lgStarts 地方公共団体コード(県表示部分がほとんど)
     * @return 検索結果
     */
    List<AddressPostalEntity> findByLgCodeStartingWith(String lgStarts);

    /**
     * 郵便番号が同一であるデータを取得する
     *
     * @param postal1 郵便番号(7桁)
     * @return 検索結果
     */
    List<AddressPostalEntity> findByPostal1OrderByAddressNameAsc(String postal1);

    
    /**
     * 郵便番号から住所郵便番号までを取得する
     *
     * @param postal1 郵便番号
     * @return 検索結果
     */
    @Query(value = "SELECT address_postal_id AS value , address_name AS text"
            + " FROM address_postal WHERE postal1 = ?1 AND is_gyoseiku_data = 1", nativeQuery = true)
    List<SelectOptionNumberDto> findByPostalCodeAndSearchGyoseiku(String postal1);
    
    
}
