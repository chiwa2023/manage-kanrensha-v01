package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;

/**
 * 関連者個人Dtoからマスタ個人連作先Entity変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人住所マスタEntity
     */
    public MasterPersonAccessEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        MasterPersonAccessEntity accsessEntity = new MasterPersonAccessEntity();
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputAccessDto(), accsessEntity);

        accsessEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());

        return accsessEntity;
    }

}
