package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;

/**
 * 関連者政治団体Dtoを政治団体マスタ変換Logic
 */
@Component
public class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return 政治団体マスタEntity
     */
    public MasterPoliticalOrganizationEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        MasterPoliticalOrganizationEntity poliOrgEntity = new MasterPoliticalOrganizationEntity();
        poliOrgEntity.setAllAddress(kanrenshaPoliOrgDto.getInputAddressDto().getAddressAll());
        poliOrgEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        poliOrgEntity.setPoliOrgKanrenshaCode(kanrenshaPoliOrgDto.getPoliOrgKanrenshaCode());
        poliOrgEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(poliOrgEntity.getPartnerName()));

        return poliOrgEntity;
    }

}
