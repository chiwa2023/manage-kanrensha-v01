package mitei.mitei.political.balancesheet.manage.kanrensha.dto.z_force;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 強制データダンプCapslueDto
 */
public class ForceDumpCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 初期データ(LocalcDate) */
    private static final LocalDate INIT_LocalDate = LocalDate.of(1948, 7, 29);

    /** 企業団体処理該否 */
    private Boolean isExecuteCorp = INIT_Boolean;

    /** 個人処理該否 */
    private Boolean isExecutePerson = INIT_Boolean;

    /** 政治団体処理該否 */
    private Boolean isExecutePoliOrg = INIT_Boolean;

    /** 開始日時 */
    private LocalDate dateStart = INIT_LocalDate;

    /** 終了日時 */
    private LocalDate dateEnd = INIT_LocalDate;

    /**
     * 企業団体処理該否を取得する
     *
     * @return 企業団体処理該否
     */
    public Boolean getIsExecuteCorp() {
        return isExecuteCorp;
    }

    /**
     * 企業団体処理該否を設定する
     *
     * @param isExecuteCorp 企業団体処理該否
     */
    public void setIsExecuteCorp(final Boolean isExecuteCorp) {
        this.isExecuteCorp = isExecuteCorp;
    }

    /**
     * 個人処理該否を取得する
     *
     * @return 個人処理該否
     */
    public Boolean getIsExecutePerson() {
        return isExecutePerson;
    }

    /**
     * 個人処理該否を設定する
     *
     * @param isExecutePerson 個人処理該否
     */
    public void setIsExecutePerson(final Boolean isExecutePerson) {
        this.isExecutePerson = isExecutePerson;
    }

    /**
     * 政治団体処理該否を取得する
     *
     * @return 政治団体処理該否
     */
    public Boolean getIsExecutePoliOrg() {
        return isExecutePoliOrg;
    }

    /**
     * 政治団体処理該否を設定する
     *
     * @param isExecutePoliOrg 政治団体処理該否
     */
    public void setIsExecutePoliOrg(final Boolean isExecutePoliOrg) {
        this.isExecutePoliOrg = isExecutePoliOrg;
    }

    /**
     * 開始日時を取得するを設定する
     *
     * @return 開始日時
     */
    public LocalDate getDateStart() {
        return dateStart;
    }

    /**
     * 開始日時を設定する
     *
     * @param dateStart 開始日時
     */
    public void setDateStart(final LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    /**
     * 終了日時を取得する
     *
     * @return 終了日時
     */
    public LocalDate getDateEnd() {
        return dateEnd;
    }

    /**
     * 終了日時を設定する
     *
     * @param dateEnd 終了日時
     */
    public void setDateEnd(final LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

}
