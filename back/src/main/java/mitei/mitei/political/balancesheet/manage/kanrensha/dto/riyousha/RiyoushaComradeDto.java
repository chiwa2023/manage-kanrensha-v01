package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;

/**
 * API利用ユーザDto
 */
public class RiyoushaComradeDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** API接続利用者Id */
    private Integer riyoushaComradeId = INIT_Integer;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /**
     * API接続利用者Idを取得する
     *
     * @return API接続利用者Id
     */
    public Integer getRiyoushaComradeId() {
        return riyoushaComradeId;
    }

    /**
     * API接続利用者Idを設定する
     *
     * @param riyoushaComradeId API接続利用者Id
     */
    public void setRiyoushaComradeId(final Integer riyoushaComradeId) {
        this.riyoushaComradeId = riyoushaComradeId;
    }

    /** API接続利用者コード */
    private Integer riyoushaComradeCode = INIT_Integer;

    /**
     * API接続利用者コードを取得する
     *
     * @return API接続利用者コード
     */
    public Integer getRiyoushaComradeCode() {
        return riyoushaComradeCode;
    }

    /**
     * API接続利用者コードを設定する
     *
     * @param riyoushaComradeCode API接続利用者コード
     */
    public void setRiyoushaComradeCode(final Integer riyoushaComradeCode) {
        this.riyoushaComradeCode = riyoushaComradeCode;
    }

    /** API接続利用者名称 */
    private String riyoushaComradeName = INIT_String;

    /**
     * API接続利用者名称を取得する
     *
     * @return API接続利用者名称
     */
    public String getRiyoushaComradeName() {
        return riyoushaComradeName;
    }

    /**
     * API接続利用者名称を設定する
     *
     * @param riyoushaComradeName API接続利用者名称
     */
    public void setRiyoushaComradeName(final String riyoushaComradeName) {
        this.riyoushaComradeName = riyoushaComradeName;
    }

    /** 個人姓名入力 */
    private InputPersonNameDto inputPersonNameDto = new InputPersonNameDto();

    /** 団体名称入力 */
    private InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();

    /** 住所入力 */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先入力 */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /** 組織非該当 */
    private Boolean isNotOrg = INIT_Boolean;

    /**
     * 組織非該当を取得する
     *
     * @return 組織非該当
     */
    public Boolean getIsNotOrg() {
        return isNotOrg;
    }

    /**
     * 組織非該当を設定する
     *
     * @param isNotOrg 組織非該当
     */
    public void setIsNotOrg(final Boolean isNotOrg) {
        this.isNotOrg = isNotOrg;
    }

    /**
     * 個人姓名入力を取得する
     *
     * @return 個人姓名入力
     */
    public InputPersonNameDto getInputPersonNameDto() {
        return inputPersonNameDto;
    }

    /**
     * 個人姓名入力を設定する
     *
     * @param inputPersonNameDto 個人姓名入力
     */
    public void setInputPersonNameDto(final InputPersonNameDto inputPersonNameDto) {
        this.inputPersonNameDto = inputPersonNameDto;
    }

    /**
     * 団体名称入力を取得する
     *
     * @return 団体名称入力
     */
    public InputOrgNameDto getInputOrgNameDto() {
        return inputOrgNameDto;
    }

    /**
     * 団体名称入力を設定する
     *
     * @param inputOrgNameDto 団体名称入力
     */
    public void setInputOrgNameDto(final InputOrgNameDto inputOrgNameDto) {
        this.inputOrgNameDto = inputOrgNameDto;
    }

    /**
     * 住所入力を取得する
     *
     * @return 住所入力
     */
    public InputAddressDto getInputAddressDto() {
        return inputAddressDto;
    }

    /**
     * 住所入力を設定する
     *
     * @param inputAddressDto 住所入力
     */
    public void setInputAddressDto(final InputAddressDto inputAddressDto) {
        this.inputAddressDto = inputAddressDto;
    }

    /**
     * 連絡先入力を取得する
     *
     * @return 連絡先入力
     */
    public InputAccessDto getInputAccessDto() {
        return inputAccessDto;
    }

    /**
     * 連絡先入力を設定する
     *
     * @param inputAccessDto 連絡先入力
     */
    public void setInputAccessDto(final InputAccessDto inputAccessDto) {
        this.inputAccessDto = inputAccessDto;
    }

}
