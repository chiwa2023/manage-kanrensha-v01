package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;

/**
 * 関連者政治団体Dtoからマスタ政治団体住所Entity変換Logic
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Component
public class ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaPoliOrgDto 関連者政治団体Dto
     * @return 政治団体住所マスタEntity
     */
    public MasterPoliticalOrganizationAddressEntity practice(final KanrenshaPoliOrgDto kanrenshaPoliOrgDto) {

        MasterPoliticalOrganizationAddressEntity addressEntity = new MasterPoliticalOrganizationAddressEntity();
        BeanUtils.copyProperties(kanrenshaPoliOrgDto.getInputAddressDto(), addressEntity);

        // 意味内容が同じで、フィールド名が異なっているものを追加で複写
        addressEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        addressEntity.setPostal1(kanrenshaPoliOrgDto.getInputAddressDto().getPostalcode1());
        addressEntity.setPostal2(kanrenshaPoliOrgDto.getInputAddressDto().getPostalcode2());
        addressEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        addressEntity.setPoliOrgKanrenshaCode(kanrenshaPoliOrgDto.getPoliOrgKanrenshaCode());

        // TODO: rsdt2_id のセット処理を追加してください

        return addressEntity;
    }

}
