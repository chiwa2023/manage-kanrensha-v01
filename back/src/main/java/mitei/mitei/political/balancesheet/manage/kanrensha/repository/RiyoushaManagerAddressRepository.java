package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerAddressEntity;

/**
 * riyousha_manager_address接続用Repository
 */
public interface RiyoushaManagerAddressRepository extends JpaRepository<RiyoushaManagerAddressEntity, Integer> {

    /**
     * 利用者仲間IDと最新フラグで検索する
     *
     * @param riyoushaComradeId 利用者仲間ID
     * @param isLatest          最新フラグ
     * @return 検索結果
     */
    List<RiyoushaManagerAddressEntity> findByRiyoushaManagerIdAndIsLatest(Integer riyoushaComradeId, boolean isLatest);

}
