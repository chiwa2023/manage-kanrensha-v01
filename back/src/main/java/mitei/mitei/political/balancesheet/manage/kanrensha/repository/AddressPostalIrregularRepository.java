package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.SelectOptionNumberDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * 郵便番号不規則データRepository
 */
public interface AddressPostalIrregularRepository extends JpaRepository<AddressPostalIrregularEntity, Integer> {

    /**
     * 波文字を含み、カンマ文字がない(範囲が1項目)を取得する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param nami     波ダッシュ文字
     * @param comma    カンマ文字
     * @param pageable ページング
     * @return 検索結果
     */
    Page<AddressPostalIrregularEntity> findByLgCodeStartingWithAndAddressOrgLikeAndAddressOrgNotLike(String lgCode,
            String nami, String comma, Pageable pageable);

    /**
     * 地名(地名)といった単一地域を抽出する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM address_postal_irregular WHERE lg_code LIKE ?1" + " AND address_org NOT LIKE '%〜%'"
            + " AND address_org NOT LIKE '%、%' AND address_org NOT LIKE '%階）%'"
            + " AND address_org NOT LIKE '%階層不明）%' AND address_org NOT LIKE '%（その他）%'"
            + " AND address_org NOT LIKE '%（次のビルを除く）%' AND address_org NOT LIKE '%番地%'"
            + " AND address_org NOT LIKE '%丁目%'", nativeQuery = true)
    Page<AddressPostalIrregularEntity> findSingleAddress(String lgCode, Pageable pageable);

    /**
     * （その他）を抽出する
     *
     * @param lgCode   地方公共団体コード(県部分)
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT * FROM address_postal_irregular WHERE lg_code LIKE ?1 AND (address_org LIKE '%その他%' OR address_org LIKE '%次のビルを除く%')", nativeQuery = true)
    Page<AddressPostalIrregularEntity> findOtherAddress(String lgCode, Pageable pageable);

    
    /**
     * 郵便番号から住所を取得する
     *
     * @param postal1 郵便番号
     * @return 検索結果
     */
    @Query(value = "SELECT address_postal_irregular_id AS value , address_postal AS text"
            + " FROM address_postal_irregular WHERE postal1 = ?1", nativeQuery = true)
    List<SelectOptionNumberDto> findByPostalCode(String postal1);
    
}
