package mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;

/**
 * ユーザ運営者Dto
 */
public class RiyoushaManagerDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 初期データ(Integer) */
    private static final Integer INIT_Integer = 0;

    /** 初期データ(Boolean) */
    private static final Boolean INIT_Boolean = false;

    /** 利用者運営者Id */
    private Integer riyousharManagerId = INIT_Integer;

    /**
     * 利用者運営者Idを取得する
     *
     * @return 利用者運営者Id
     */
    public Integer getRiyousharManagerId() {
        return riyousharManagerId;
    }

    /**
     * 利用者運営者Idを設定する
     *
     * @param riyousharManagerId 利用者運営者Id
     */
    public void setRiyousharManagerId(final Integer riyousharManagerId) {
        this.riyousharManagerId = riyousharManagerId;
    }

    /** 利用者運営者コード */
    private Integer riyoushaManagerCode = INIT_Integer;

    /**
     * 利用者運営者コードを取得する
     *
     * @return 利用者運営者コード
     */
    public Integer getRiyoushaManagerCode() {
        return riyoushaManagerCode;
    }

    /**
     * 利用者運営者コードを設定する
     *
     * @param riyoushaManagerCode 利用者運営者コード
     */
    public void setRiyoushaManagerCode(final Integer riyoushaManagerCode) {
        this.riyoushaManagerCode = riyoushaManagerCode;
    }

    /** 利用者運営者名称 */
    private String riyoushaManagerName = INIT_String;

    /**
     * 利用者運営者名称を取得する
     *
     * @return 利用者運営者名称
     */
    public String getRiyoushaManagerName() {
        return riyoushaManagerName;
    }

    /**
     * 利用者運営者名称を設定する
     *
     * @param riyoushaManagerName 利用者運営者名称
     */
    public void setRiyoushaManagerName(final String riyoushaManagerName) {
        this.riyoushaManagerName = riyoushaManagerName;
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
