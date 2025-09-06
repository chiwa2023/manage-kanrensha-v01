package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;

/**
 * 関連者個人Dtoマスタ個人属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return マスタ個人属性Entity
     */
    public MasterPersonPropertyEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        MasterPersonPropertyEntity propertyEntity = new MasterPersonPropertyEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto, propertyEntity);

        propertyEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());

        return propertyEntity;
    }
}
