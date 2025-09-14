package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;

/**
 * 関連者個人Dtoを個人マスタ変換Logic
 */
@Component
public class ConvertKanrenshaPersonDtoToMasterPersonEntityLogic {

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 処理を行う
     *
     * @param kanrenshaPersonDto 関連者個人Dto
     * @return 個人マスタEntity
     */
    public MasterPersonEntity practice(final KanrenshaPersonDto kanrenshaPersonDto) {

        MasterPersonEntity personEntity = new MasterPersonEntity();
        personEntity.setAllAddress(kanrenshaPersonDto.getInputAddressDto().getAddressAll());
        personEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        personEntity.setPersonShokugyou(kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());
        personEntity.setPersonKanrenshaCode(kanrenshaPersonDto.getPersonKanrenshaCode());
        personEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(personEntity.getPartnerName()));

        return personEntity;
    }

}
