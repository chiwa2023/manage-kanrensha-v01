package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;

/**
 * 関連者企業団体Dtoからマスタ企業団体基本Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return 企業団体基本マスタEntity
     */
    public MasterCorporationBaseEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        MasterCorporationBaseEntity baseEntity = new MasterCorporationBaseEntity();
        BeanUtils.copyProperties(kanrenshaCorpDto.getInputOrgNameDto(), baseEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        baseEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        baseEntity.setIsShiten(kanrenshaCorpDto.getIsShiten());
        baseEntity.setOrgDelegateCode(kanrenshaCorpDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());

        return baseEntity;
    }

}
