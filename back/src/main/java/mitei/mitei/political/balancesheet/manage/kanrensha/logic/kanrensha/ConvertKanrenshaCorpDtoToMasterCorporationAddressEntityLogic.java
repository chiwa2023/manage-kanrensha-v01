package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;

/**
 * 関連者企業団体Dtoからマスタ企業団体住所Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return 企業団体住所マスタEntity
     */
    public MasterCorporationAddressEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        MasterCorporationAddressEntity addressEntity = new MasterCorporationAddressEntity();
        BeanUtils.copyProperties(kanrenshaCorpDto.getInputAddressDto(), addressEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        addressEntity.setPostal1(kanrenshaCorpDto.getInputAddressDto().getPostalcode1());
        addressEntity.setPostal2(kanrenshaCorpDto.getInputAddressDto().getPostalcode2());
        addressEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        addressEntity.setCorpKanrenshaCode(kanrenshaCorpDto.getCorpKanrenshaCode());

        // TODO: rsdt2_id のセット処理を追加してください

        return addressEntity;
    }

}
