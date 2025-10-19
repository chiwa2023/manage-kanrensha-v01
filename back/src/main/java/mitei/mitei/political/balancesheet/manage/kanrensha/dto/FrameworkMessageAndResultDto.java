package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

import java.io.Serializable;

/**
 * メッセージと処理結果フラグを持つ処理結果のみ返却Dto
 */
public class FrameworkMessageAndResultDto // NOPMD DataClass
        implements Serializable, MessagAndResultInterface {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 出力メッセージ */
    private String message = INIT_String;

    /** 処理失敗フラグ */
    private Boolean isFailure = INIT_Boolean;

    /**
     * メッセージを取得する
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * メッセージを設定
     */
    @Override
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * 処理結果失敗フラグを取得する
     */
    @Override
    public Boolean getIsFailure() {
        return isFailure;
    }

    /**
     * 処理結果失敗フラグを設定する
     */
    @Override
    public void setIsFailure(final Boolean isFailure) {
        this.isFailure = isFailure;
    }

}
