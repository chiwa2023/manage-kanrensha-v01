package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;

/**
 * 関連者企業団体Dtoを企業団体マスタ変換Logic
 */
@Component
public class ConvertKanrenshaCorpDtoToMasterCorporationEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return 企業団体マスタEntity
     */
    public MasterCorporationEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        MasterCorporationEntity corpEntity = new MasterCorporationEntity();
        corpEntity.setAllAddress(kanrenshaCorpDto.getInputAddressDto().getAddressAll());
        corpEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        corpEntity.setCorpKanrenshaCode(kanrenshaCorpDto.getCorpKanrenshaCode());
        corpEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(corpEntity.getPartnerName()));

        return corpEntity;
    }

}
