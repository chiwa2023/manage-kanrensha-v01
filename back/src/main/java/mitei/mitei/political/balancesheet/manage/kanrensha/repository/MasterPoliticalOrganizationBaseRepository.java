package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * master_political_organization_base接続用Repository
 */
public interface MasterPoliticalOrganizationBaseRepository
        extends JpaRepository<MasterPoliticalOrganizationBaseEntity, Integer> {

    /**
     * 関連者コードをキーにテーブルid降順で取得する
     *
     * @param kanrenshaCode 関連者コード
     * @return 検索結果
     */
    List<MasterPoliticalOrganizationBaseEntity> findByPoliOrgKanrenshaCodeOrderByMasterPoliticalOrganizationBaseIdDesc(
            String kanrenshaCode);
}
