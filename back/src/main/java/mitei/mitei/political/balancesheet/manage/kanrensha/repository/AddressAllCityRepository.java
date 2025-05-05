package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

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

}
