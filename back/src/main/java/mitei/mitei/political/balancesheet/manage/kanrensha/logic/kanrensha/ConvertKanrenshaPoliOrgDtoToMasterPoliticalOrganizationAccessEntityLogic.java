package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;

/**
 * 関連者政治団体Dtoからマスタ政治団体連絡先Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return 政治団体連絡先マスタEntity
     */
    public MasterPoliticalOrganizationAccessEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        MasterPoliticalOrganizationAccessEntity accessEntity = new MasterPoliticalOrganizationAccessEntity();
        BeanUtils.copyProperties(kanrenshaPoliOrgDto.getInputAccessDto(), accessEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        accessEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        accessEntity.setPoliOrgKanrenshaCode(kanrenshaPoliOrgDto.getPoliOrgKanrenshaCode());

        return accessEntity;
    }

}
