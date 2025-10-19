package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;

/**
 * master_person_access接続用Repository
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
public interface MasterPersonAccessRepository extends JpaRepository<MasterPersonAccessEntity, Integer> {

    /**
     * 関連者個人コードで検索する（テーブルId降順）
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return 検索結果
     */
    List<MasterPersonAccessEntity> findByPersonKanrenshaCodeOrderByMasterPersonAccessIdDesc(String personKanrenshaCode);

}
