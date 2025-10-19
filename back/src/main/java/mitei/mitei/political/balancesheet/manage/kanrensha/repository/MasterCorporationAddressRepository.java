package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * master_corporation_address接続用Repository
 */
public interface MasterCorporationAddressRepository extends JpaRepository<MasterCorporationAddressEntity, Integer> {

    /**
     * 関連者コードをキーにId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterCorporationAddressEntity> findByCorpKanrenshaCodeOrderByMasterCorporationAddressIdDesc(
            String kanrenshaCode);

}
