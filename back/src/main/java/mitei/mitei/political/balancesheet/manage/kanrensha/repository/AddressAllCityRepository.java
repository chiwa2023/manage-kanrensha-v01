package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * アドレス・ベース・レジストリ市区町村Repository
 */
public interface AddressAllCityRepository extends JpaRepository<AddressAllCityEntity, Integer> {

    /**
     * 地方公共団体コードを取得する
     *
     * @param pageable ページング
     * @return 検索結果
     */
    @Query(value = "SELECT lg_code FROM address_all_city ", nativeQuery = true)
    List<String> findLgCode(Pageable pageable);

    /**
     * 地方公共団体コード前方一致条件で取得する
     *
     * @param lgStarts 地方公共団体コードの一部
     * @return 検索結果
     */
    List<AddressAllCityEntity> findByLgCodeStartingWith(String lgStarts);

    /**
     * 住所部分一致検索件数を取得する
     *
     * @param addressWoreds 検索語
     * @param now           現在日付
     * @return 検索結果
     */
    Integer countByAddressNameContainingAndEffectDateLessThanEqual(String addressWoreds, LocalDate now);

    /**
     * 住所部分一致検索すう
     *
     * @param addressWoreds 検索語
     * @param now           現在日付
     * @param pageable      ページング条件
     * @return 検索結果
     */
    List<AddressAllCityEntity> findByAddressNameContainingAndEffectDateLessThanEqual(String addressWoreds,
            LocalDate now, Pageable pageable);

}
