package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;

/**
 * master_political_organization_address接続用Repository
 */
public interface MasterPoliticalOrganizationAddressRepository
        extends JpaRepository<MasterPoliticalOrganizationAddressEntity, Integer> {

    /**
     * 関連者コードからテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationAddressEntity> findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAddressIdDesc(
            String kanrenshaCode);

}
