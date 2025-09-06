package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.HoujinShubetsuConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;

/**
 * 関連者個人Dtoマスタ政治団体属性Entity変換Logic
 */
@Component
public class ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic {

    /**
     * 処理を行う
     *
     * @param kanrenshaCorpDto 関連者企業団体Dto
     * @return マスタ政治団体属性Entity
     */
    public MasterCorporationPropertyEntity practice(final KanrenshaCorpDto kanrenshaCorpDto) {

        MasterCorporationPropertyEntity propertyEntity = new MasterCorporationPropertyEntity();
        BeanUtils.copyProperties(kanrenshaCorpDto, propertyEntity);

        // 法人種別が外国会社401であれば外国籍フラグを立てる
        propertyEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        propertyEntity.setIsForeign(HoujinShubetsuConstants.GAIKOKU_KAISHA.equals(propertyEntity.getHoujinSbts()));

        return propertyEntity;
    }
}
