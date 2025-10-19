package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;

/**
 * 関連者個人Dtoを個人マスタ変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人マスタEntity
     */
    public MasterPersonBaseEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        final String BLANK = "";

        MasterPersonBaseEntity baseEntity = new MasterPersonBaseEntity();
        // 個人姓名を複写
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputPersonNameDto(), baseEntity);
        // 個人職業を複写
        BeanUtils.copyProperties(kanrenshaPersonDto.getInputShokugyouDto(), baseEntity);
        baseEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        baseEntity.setIsShokyouEdit(!BLANK.equals(kanrenshaPersonDto.getInputShokugyouDto().getShokugyouUserWrite()));
        baseEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());

        return baseEntity;
    }

}
