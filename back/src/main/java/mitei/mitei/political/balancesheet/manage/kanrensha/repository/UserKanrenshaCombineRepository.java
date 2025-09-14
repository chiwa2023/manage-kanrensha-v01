package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserKanrenshaCombineEntity;

/**
 * user_kanrensha_combine接続用Repository
 */
public interface UserKanrenshaCombineRepository extends JpaRepository<UserKanrenshaCombineEntity, Integer> {

    /**
     * ユーザコードが一致し最新データを取得する
     *
     * @param userCode ユーザコード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<UserKanrenshaCombineEntity> findByUseUserCodeAndIsLatest(Integer userCode, Boolean isLatest);
}
