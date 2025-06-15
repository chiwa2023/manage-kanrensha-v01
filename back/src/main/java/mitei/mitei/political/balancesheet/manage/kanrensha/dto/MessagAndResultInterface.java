package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

/**
 * メッセージと処理結果の成否を格納するInterface
 */
public interface MessagAndResultInterface {

    /**
     * メッセージを取得する
     *
     * @return メッセージ
     */
    String getMessage();

    /**
     * メッセージを設定する
     *
     * @param message メッセージ
     */
    void setMessage(String message);

    /**
     * 処理結果フラグを取得する
     *
     * @return 処理結果フラグ
     */
    Boolean getIsFailure();

    /**
     * 処理結果フラグを設定する
     *
     * @param isFailure 処理結果フラグ
     */
    void setIsFailure(Boolean isFailure);

}
