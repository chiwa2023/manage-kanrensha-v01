package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;

/**
 * 関連者政治団体Dto
 */
public class KanrenshaPoliOrgDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 名称入力Dto */
    private InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();

    /** 住所入力Dto */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先Dto */
    private InputAccessDto inputAccessDto = new InputAccessDto();

    /** 団体代表者関連者最低限Dto */
    private InputKanrenshaPersonLeastDto orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();

    /** 団体会計責任者関連者最低限Dto */
    private InputKanrenshaPersonLeastDto accounrMgrLeastDto = new InputKanrenshaPersonLeastDto();

    /**
     * 団体名称入力Dtoを取得する
     *
     * @return 団体名称入力Dto
     */
    public InputOrgNameDto getInputOrgNameDto() {
        return inputOrgNameDto;
    }

    /**
     * 団体名称入力Dtoを設定する
     *
     * @param inputOrgNameDto 団体名称入力Dto
     */
    public void setInputOrgNameDto(final InputOrgNameDto inputOrgNameDto) {
        this.inputOrgNameDto = inputOrgNameDto;
    }

    /**
     * 住所入力Dtoを取得する
     *
     * @return 住所入力Dto
     */
    public InputAddressDto getInputAddressDto() {
        return inputAddressDto;
    }

    /**
     * 住所入力Dtoを設定する
     *
     * @param inputAddressDto 住所入力Dto
     */
    public void setInputAddressDto(final InputAddressDto inputAddressDto) {
        this.inputAddressDto = inputAddressDto;
    }

    /**
     * 連絡先Dtoを取得する
     *
     * @return 連絡先Dto
     */
    public InputAccessDto getInputAccessDto() {
        return inputAccessDto;
    }

    /**
     * 連絡先Dtoを設定する
     *
     * @param inputAccessDto 連絡先Dto
     */
    public void setInputAccessDto(final InputAccessDto inputAccessDto) {
        this.inputAccessDto = inputAccessDto;
    }

    /**
     * 団体代表者関連者最低限Dtoを取得する
     *
     * @return 団体代表者関連者最低限Dto
     */
    public InputKanrenshaPersonLeastDto getOrgDelegateLeastDto() {
        return orgDelegateLeastDto;
    }

    /**
     * 団体代表者関連者最低限Dtoを設定する
     *
     * @param orgDelegateLeastDto 団体代表者関連者最低限Dto
     */
    public void setOrgDelegateLeastDto(final InputKanrenshaPersonLeastDto orgDelegateLeastDto) {
        this.orgDelegateLeastDto = orgDelegateLeastDto;
    }

    /**
     * 団体会計責任者関連者最低限Dtoを取得する
     *
     * @return 団体会計責任者関連者最低限Dto
     */
    public InputKanrenshaPersonLeastDto getAccounrMgrLeastDto() {
        return accounrMgrLeastDto;
    }

    /**
     * 団体会計責任者関連者最低限Dtoを設定する
     *
     * @param accounrMgrLeastDto 団体会計責任者関連者最低限Dto
     */
    public void setAccounrMgrLeastDto(final InputKanrenshaPersonLeastDto accounrMgrLeastDto) {
        this.accounrMgrLeastDto = accounrMgrLeastDto;
    }


    /** 政治団体区分 */
    private String dantaiKbn = INIT_String;

    /**
     * 政治団体区分を取得する
     *
     * @return 政治団体区分
     */
    public String getDantaiKbn() {
        return dantaiKbn;
    }

    /**
     * 政治団体区分を設定する
     *
     * @param dantaiKbn 政治団体区分
     */
    public void setDantaiKbn(final String dantaiKbn) {
        this.dantaiKbn = dantaiKbn;
    }


}
