package mitei.mitei.political.balancesheet.manage.kanrensha.entity;

import java.time.LocalDateTime;

/**
 * テーブル履歴を管理するためのInterface
 */
public interface AllTabeDataHistoryInterface {

    /**
     * 最新区分を取得する
     *
     * @return 最新区分
     */
    Short getLatestKbn();

    /**
     * 最新区分を設定する
     *
     * @param latestKbn 最新区分
     */
    void setLatestKbn(Short latestKbn);

    /**
     * 挿入ユーザIdを取得する
     *
     * @return 挿入ユーザId
     */
    Long getInsertUserId();

    /**
     * 挿入ユーザIdを設定する
     *
     * @param insertUserId 挿入ユーザId
     */
    void setInsertUserId(Long insertUserId);

    /**
     * 挿入ユーザ同一識別コードを取得する
     *
     * @return 挿入ユーザ同一識別コード
     */
    Integer getInsertUserCode();

    /**
     * 挿入ユーザ同一識別コードを設定する
     *
     * @param insertUserCode 挿入ユーザ同一識別コード
     */
    void setInsertUserCode(Integer insertUserCode);

    /**
     * 挿入ユーザ名称を取得するを設定する
     *
     * @return 挿入ユーザ名称
     */
    String getInsertUserName();

    /**
     * 挿入ユーザ名称を設定する
     *
     * @param insertUserName 挿入ユーザ名称
     */
    void setInsertUserName(String insertUserName);

    /**
     * 挿入日時を取得する
     *
     * @return 挿入日時
     */
    LocalDateTime getInsertTimestamp();

    /**
     * 挿入日時を設定する
     *
     * @param insertTimestamp 挿入日時
     */
    void setInsertTimestamp(LocalDateTime insertTimestamp);

    /**
     * 更新ユーザIdを取得する
     *
     * @return 更新ユーザId
     */
    Long getUpdateUserId();

    /**
     * 更新ユーザIdを設定する
     *
     * @param updatetUserId 更新ユーザId
     */
    void setUpdateUserId(Long updatetUserId);

    /**
     * 更新ユーザ同一識別コードを取得する
     *
     * @return 更新ユーザ同一識別コード
     */
    Integer getUpdateUserCode();

    /**
     * 更新ユーザ同一識別コードを設定する
     *
     * @param updateUserCode 更新ユーザ同一識別コード
     */
    void setUpdateUserCode(Integer updateUserCode);

    /**
     * 更新ユーザ名称を取得する
     *
     * @return 更新ユーザ名称
     */
    String getUpdateUserName();

    /**
     * 更新ユーザ名称を設定する
     *
     * @param updateUserName 更新ユーザ名称
     * 
     */
    void setUpdateUserName(String updateUserName);

    /**
     * 更新日時を取得する
     *
     * @return 更新日時
     */
    LocalDateTime getUpdateTimestamp();

    /**
     * 更新日時を設定する
     *
     * @param updateTimestamp 更新日時
     */
    void setUpdateTimestamp(LocalDateTime updateTimestamp);
}
