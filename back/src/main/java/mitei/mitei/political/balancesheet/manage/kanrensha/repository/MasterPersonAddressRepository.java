package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * master_person_address接続用Repository
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
public interface MasterPersonAddressRepository extends JpaRepository<MasterPersonAddressEntity, Integer> {

    /**
     * 関連者個人コードをキーにテーブルIdの降順で取得する
     *
     * @param personKanrenshaCode 関連者個人コード
     * @return 住所リスト
     */
    List<MasterPersonAddressEntity> findByPersonKanrenshaCodeOrderByMasterPersonAddressIdDesc(
            String personKanrenshaCode);

}
