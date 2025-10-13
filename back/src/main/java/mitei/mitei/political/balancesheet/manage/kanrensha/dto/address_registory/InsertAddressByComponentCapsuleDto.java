package mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;

/**
 * コンポーネントから直接住所挿入Dto
 */
public class InsertAddressByComponentCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 住所編集コンポーネントDto */
    private InputAddressDto inputAddressDto = new InputAddressDto();

    /**
     * 住所編集コンポーネントDtoを取得する
     *
     * @return 住所編集コンポーネントDto
     */
    public InputAddressDto getInputAddressDto() {
        return inputAddressDto;
    }

    /**
     * 住所編集コンポーネントDtoを設定する
     *
     * @param inputAddressDto 住所編集コンポーネントDto
     */
    public void setInputAddressDto(final InputAddressDto inputAddressDto) {
        this.inputAddressDto = inputAddressDto;
    }

}
