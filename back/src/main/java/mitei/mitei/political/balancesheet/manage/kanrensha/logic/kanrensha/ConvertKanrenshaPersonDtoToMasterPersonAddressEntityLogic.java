package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;

/**
 * 関連者個人Dtoを個人住所マスタ変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人住所マスタEntity
     */
    public MasterPersonAddressEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        MasterPersonAddressEntity addressEntity = new MasterPersonAddressEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAddressDto(), addressEntity);
        
        addressEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        addressEntity.setPostal1(kanrenshaPersonDto.getInputAddressDto().getPostalcode1());
        addressEntity.setPostal2(kanrenshaPersonDto.getInputAddressDto().getPostalcode2());
        addressEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());

        return addressEntity;
    }

}
