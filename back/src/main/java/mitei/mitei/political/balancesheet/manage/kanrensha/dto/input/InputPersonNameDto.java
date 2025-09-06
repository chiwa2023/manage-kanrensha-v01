package mitei.mitei.political.balancesheet.manage.kanrensha.dto.input;

import java.io.Serializable;

/**
 * 個人氏名DTO
 */
public class InputPersonNameDto implements Serializable { // NOPMD DataClass
    
    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 姓名の姓 */
    private String lastName = INIT_String;

    /** 姓名の名 */
    private String firstName = INIT_String;

    /** 姓名 */
    private String allName = INIT_String;

    /** 姓名のミドルネーム */
    private String middleName = INIT_String;

    /** 姓名の姓かな */
    private String lastNameKana = INIT_String;

    /** 姓名の名かな */
    private String firstNameKana = INIT_String;

    /** 姓名のミドルネームかな */
    private String middleNameKana = INIT_String;

    /** 姓名かな */
    private String allNameKana = INIT_String;

    /**
     * 姓名の姓を取得する
     *
     * @return 姓名の姓
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * 姓名の姓を設定する
     *
     * @param lastName 姓名の姓
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * 姓名の名を取得する
     *
     * @return 姓名の名
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 姓名の名を設定する
     *
     * @param firstName 姓名の名
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * 姓名を取得する
     *
     * @return 姓名
     */
    public String getAllName() {
        return allName;
    }

    /**
     * 姓名を設定する
     *
     * @param allName 姓名
     */
    public void setAllName(final String allName) {
        this.allName = allName;
    }

    /**
     * 姓名のミドルネームを取得する
     *
     * @return 姓名のミドルネーム
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * 姓名のミドルネームを設定する
     *
     * @param middleName 姓名のミドルネーム
     */
    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    /**
     * 姓名の姓かなを取得する
     *
     * @return 姓名の姓かな
     */
    public String getLastNameKana() {
        return lastNameKana;
    }

    /**
     * 姓名の姓かなを設定する
     *
     * @param lastNameKana 姓名の姓かな
     */
    public void setLastNameKana(final String lastNameKana) {
        this.lastNameKana = lastNameKana;
    }

    /**
     * 姓名の名かなを取得する
     *
     * @return 姓名の名かな
     */
    public String getFirstNameKana() {
        return firstNameKana;
    }

    /**
     * 姓名の名かなを設定する
     *
     * @param firstNameKana 姓名の名かな
     */
    public void setFirstNameKana(final String firstNameKana) {
        this.firstNameKana = firstNameKana;
    }

    /**
     * 姓名のミドルネームかなを取得する
     *
     * @return 姓名のミドルネームかな
     */
    public String getMiddleNameKana() {
        return middleNameKana;
    }

    /**
     * 姓名のミドルネームかなを設定する
     *
     * @param middleNameKana 姓名のミドルネームかな
     */
    public void setMiddleNameKana(final String middleNameKana) {
        this.middleNameKana = middleNameKana;
    }

    /**
     * 姓名かなを取得する
     *
     * @return 姓名かな
     */
    public String getAllNameKana() {
        return allNameKana;
    }

    /**
     * 姓名かなを設定する
     *
     * @param allNameKana 姓名かな
     */
    public void setAllNameKana(final String allNameKana) {
        this.allNameKana = allNameKana;
    }
}
