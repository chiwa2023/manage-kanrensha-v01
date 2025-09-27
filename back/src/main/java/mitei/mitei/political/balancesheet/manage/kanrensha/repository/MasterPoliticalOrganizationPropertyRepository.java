package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * master_political_organization_property接続用Repository
 */
public interface MasterPoliticalOrganizationPropertyRepository
        extends JpaRepository<MasterPoliticalOrganizationPropertyEntity, Integer> {

    /**
     * 関連者コードからテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationPropertyEntity> findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationPropertyIdDesc(
            String kanrenshaCode);

}
