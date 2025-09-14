package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;

/**
 * 関連者政治団体Dtoからマスタ政治団体基本Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return 政治団体基本マスタEntity
     */
    public MasterPoliticalOrganizationBaseEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        MasterPoliticalOrganizationBaseEntity baseEntity = new MasterPoliticalOrganizationBaseEntity();
        BeanUtils.copyProperties(kanrenshaPoliOrgDto.getInputOrgNameDto(), baseEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        baseEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        baseEntity.setOrgDelegateCode(kanrenshaPoliOrgDto.getOrgDelegateLeastDto().getPersonKanrenshaCode());
        baseEntity.setPoliOrgKanrenshaCode(kanrenshaPoliOrgDto.getPoliOrgKanrenshaCode());

        return baseEntity;
    }

}
