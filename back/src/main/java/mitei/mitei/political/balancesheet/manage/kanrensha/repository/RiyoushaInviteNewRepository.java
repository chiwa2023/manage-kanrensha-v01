package mitei.mitei.political.balancesheet.manage.kanrensha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;

/**
 * riyousha_invite_new接続用Repository
 */
public interface RiyoushaInviteNewRepository extends JpaRepository<RiyoushaInviteNewEntity, Integer> {

    /**
     * ユーザコードが同一かつ最新を取得する
     *
     * @param userCode ユーザコード
     * @param isLatest 最新該否
     * @return 検索結果
     */
    List<RiyoushaInviteNewEntity> findByPersonUserCodeAndIsLatest(Integer userCode, Boolean isLatest);
}
