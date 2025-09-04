package mitei.mitei.political.balancesheet.manage.kanrensha.dto.input;

import java.io.Serializable;

/**
 * 職業入力Dto
 */
public class InputShokugyouDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 職業全表記 */
    private String allShokugyou = INIT_String;

    /** 職業の業種 */
    private String gyoushu = INIT_String;

    /** 職業の役職 */
    private String yakushoku = INIT_String;

    /** ユーザ記述の職業 */
    private String shokugyouUserWrite = INIT_String;

    /** 企業番号 */
    private String corpNo = INIT_String;

    /** 企業所在地 */
    private String corpAddress = INIT_String;

    /** 企業名 */
    private String corpName = INIT_String;

    /**
     * 職業全表記を取得する
     *
     * @return 職業全表記
     */
    public String getAllShokugyou() {
        return allShokugyou;
    }

    /**
     * 職業全表記を設定する
     *
     * @param allShokugyou 職業全表記
     */
    public void setAllShokugyou(final String allShokugyou) {
        this.allShokugyou = allShokugyou;
    }

    /**
     * 職業の業種を取得する
     *
     * @return 職業の業種
     */
    public String getGyoushu() {
        return gyoushu;
    }

    /**
     * 職業の業種を設定する
     *
     * @param gyoushu 職業の業種
     */
    public void setGyoushu(final String gyoushu) {
        this.gyoushu = gyoushu;
    }

    /**
     * 職業の役職を取得する
     *
     * @return 職業の役職
     */
    public String getYakushoku() {
        return yakushoku;
    }

    /**
     * 職業の役職を設定する
     *
     * @param yakushoku 職業の役職
     */
    public void setYakushoku(final String yakushoku) {
        this.yakushoku = yakushoku;
    }

    /**
     * ユーザ記述の職業を取得する
     *
     * @return ユーザ記述の職業
     */
    public String getShokugyouUserWrite() {
        return shokugyouUserWrite;
    }

    /**
     * ユーザ記述の職業を設定する
     *
     * @param shokugyouUserWrite ユーザ記述の職業
     */
    public void setShokugyouUserWrite(final String shokugyouUserWrite) {
        this.shokugyouUserWrite = shokugyouUserWrite;
    }

    /**
     * 企業番号を取得する
     *
     * @return 企業番号
     */
    public String getCorpNo() {
        return corpNo;
    }

    /**
     * 企業番号を設定する
     *
     * @param corpNo 企業番号
     */
    public void setCorpNo(final String corpNo) {
        this.corpNo = corpNo;
    }

    /**
     * 企業所在地を取得する
     *
     * @return 企業所在地
     */
    public String getCorpAddress() {
        return corpAddress;
    }

    /**
     * 企業所在地を設定する
     *
     * @param corpAddress 企業所在地
     */
    public void setCorpAddress(final String corpAddress) {
        this.corpAddress = corpAddress;
    }

    /**
     * 企業名を取得する
     *
     * @return 企業名
     */
    public String getCorpName() {
        return corpName;
    }

    /**
     * 企業名を設定する
     *
     * @param corpName 企業名
     */
    public void setCorpName(final String corpName) {
        this.corpName = corpName;
    }

}
