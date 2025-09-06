package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;

/**
 * 関連者個人Dtoマスタ個人属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return マスタ政治団体属性Entity
     */
    public MasterPoliticalOrganizationPropertyEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        MasterPoliticalOrganizationPropertyEntity propertyEntity = new MasterPoliticalOrganizationPropertyEntity();

        InputKanrenshaPersonLeastDto accountMgr = kanrenshaPoliOrgDto.getAccounrMgrLeastDto();
        propertyEntity.setAccountMgrCode(accountMgr.getPersonKanrenshaCode());
        propertyEntity.setAccountMgrName(accountMgr.getPersonName());

        propertyEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        
        return propertyEntity;
    }
}
