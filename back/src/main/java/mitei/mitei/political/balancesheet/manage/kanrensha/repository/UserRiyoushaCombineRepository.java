package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRiyoushaCombineEntity;

/**
 * user_riyousha_combine接続用Repository
 */
public interface UserRiyoushaCombineRepository extends JpaRepository<UserRiyoushaCombineEntity, Integer> {

    /**
     * ユーザと利用者API日野月リストを取得する
     *
     * @param userCode ユーザコード
     * @param role     権限
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<UserRiyoushaCombineEntity> findByUseUserCodeAndRoleAndIsLatest(Integer userCode, String role,
            Boolean isLatest);
}
