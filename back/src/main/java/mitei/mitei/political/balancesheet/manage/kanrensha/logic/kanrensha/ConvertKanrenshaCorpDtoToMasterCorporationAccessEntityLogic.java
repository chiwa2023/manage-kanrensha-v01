package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;

/**
 * 関連者企業団体Dtoからマスタ企業団体連絡先Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return 企業団体連絡先マスタEntity
     */
    public MasterCorporationAccessEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        MasterCorporationAccessEntity accessEntity = new MasterCorporationAccessEntity();
        BeanUtils.copyProperties(kanrenshaCorpDto.getInputAccessDto(), accessEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        accessEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        accessEntity.setCorpKanrenshaCode(kanrenshaCorpDto.getCorpKanrenshaCode());

        return accessEntity;
    }

}
