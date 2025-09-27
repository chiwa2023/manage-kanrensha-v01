package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPersonAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPersonAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPersonBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPersonPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaPersonDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallMasterPersonAccessEntityLogic callMasterPersonAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallMasterPersonAddressEntityLogic callMasterPersonAddressEntityLogic;

    /** 関連者個人基本マスタ取得Logic */
    @Autowired
    private CallMasterPersonBaseEntityLogic callMasterPersonBaseEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallMasterPersonPropertyEntityLogic callMasterPersonPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterPersonEntity 関連者個人マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaPersonDto practice(final MasterPersonEntity masterPersonEntity) {
        KanrenshaPersonDto dto = new KanrenshaPersonDto();
        String kanrenshaCode = masterPersonEntity.getPersonKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        MasterPersonAccessEntity accessEntity = callMasterPersonAccessEntityLogic.practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());

        MasterPersonAddressEntity addressEntity = callMasterPersonAddressEntityLogic.practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());

        MasterPersonBaseEntity baseEntity = callMasterPersonBaseEntityLogic.practice(kanrenshaCode);
        dto.setInputPersonNameDto(new InputPersonNameDto());
        BeanUtils.copyProperties(baseEntity, dto.getInputPersonNameDto());
        dto.setInputShokugyouDto(new InputShokugyouDto());
        BeanUtils.copyProperties(baseEntity, dto.getInputShokugyouDto());
        dto.getInputAddressDto().setPostalcode1(addressEntity.getPostal1());
        dto.getInputAddressDto().setPostalcode2(addressEntity.getPostal2());
        
        MasterPersonPropertyEntity propertyEntity = callMasterPersonPropertyEntityLogic.practice(kanrenshaCode);
        BeanUtils.copyProperties(propertyEntity, dto);

        // 各テーブルのIDも設定
        dto.setAccessId(accessEntity.getMasterPersonAccessId());
        dto.setAddressId(addressEntity.getMasterPersonAddressId());
        dto.setBaseId(baseEntity.getMasterPersonBaseId());
        dto.setPropertyId(propertyEntity.getMasterPersonPropertyId());

        // 最後にMasterPersonEntityの値を複写して上書き
        dto.setMasterId(masterPersonEntity.getMasterPersonId());
        dto.getInputPersonNameDto().setAllName(masterPersonEntity.getPartnerName());
        dto.getInputAddressDto().setAddressAll(masterPersonEntity.getAllAddress());
        dto.getInputShokugyouDto().setAllShokugyou(masterPersonEntity.getPersonShokugyou());

        return dto;
    }
}
