package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgManagerEntity;

/**
 * riyousha_org_manager接続用Repository
 */
public interface RiyoushaOrgManagerRepository extends JpaRepository<RiyoushaOrgManagerEntity, Integer> {

    /**
     * コードが一致する最新を取得する
     *
     * @param code     コード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<RiyoushaOrgManagerEntity> findByRiyoushaOrgCodeAndIsLatest(Integer code, Boolean isLatest);

}
