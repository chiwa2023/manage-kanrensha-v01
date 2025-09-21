package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminAddressEntity;

/**
 * riyousha_admin_address接続用Repository
 */
public interface RiyoushaAdminAddressRepository extends JpaRepository<RiyoushaAdminAddressEntity, Integer> {

    /**
     * 利用者仲間IDと最新フラグで検索する
     *
     * @param riyoushaComradeId 利用者仲間ID
     * @param isLatest          最新フラグ
     * @return 検索結果
     */
    List<RiyoushaAdminAddressEntity> findByRiyoushaAdminIdAndIsLatest(Integer riyoushaComradeId, boolean isLatest);

}
