package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * master_political_organization_access接続用Repository
 */
public interface MasterPoliticalOrganizationAccessRepository
        extends JpaRepository<MasterPoliticalOrganizationAccessEntity, Integer> {

    /**
     * 関連者コードをテーブルId降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationAccessEntity> findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationAccessIdDesc(
            String kanrenshaCode);
}
