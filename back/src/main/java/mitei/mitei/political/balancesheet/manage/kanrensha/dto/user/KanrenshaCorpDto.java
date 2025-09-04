package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;

/**
 * 関連者企業団体Dto
 */
public class KanrenshaCorpDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 団体名称入力Dto */
    private InputOrgNameDto inputOrgNameDto = new InputOrgNameDto();

    /** 住所入力Dto */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /** 連絡先Dto */
    private InputAccessDto inputAccessDto = new InputAccessDto();

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

}
