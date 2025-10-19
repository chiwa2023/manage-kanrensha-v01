package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPoliOrgAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPoliOrgAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPoliOrgBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterPoliOrgPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaPoliOrgDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallMasterPoliOrgAccessEntityLogic callMasterPoliOrgAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallMasterPoliOrgAddressEntityLogic callMasterPoliOrgAddressEntityLogic;

    /** 関連者個人基本マスタ取得Logic */
    @Autowired
    private CallMasterPoliOrgBaseEntityLogic callMasterPoliOrgBaseEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallMasterPoliOrgPropertyEntityLogic callMasterPoliOrgPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterPoliOrgEntity 関連者個人マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaPoliOrgDto practice(final MasterPoliticalOrganizationEntity masterPoliOrgEntity) {
        KanrenshaPoliOrgDto dto = new KanrenshaPoliOrgDto();
        String kanrenshaCode = masterPoliOrgEntity.getPoliOrgKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        MasterPoliticalOrganizationAccessEntity accessEntity = callMasterPoliOrgAccessEntityLogic
                .practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());

        MasterPoliticalOrganizationAddressEntity addressEntity = callMasterPoliOrgAddressEntityLogic
                .practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());
        dto.getInputAddressDto().setPostalcode1(addressEntity.getPostal1());
        dto.getInputAddressDto().setPostalcode2(addressEntity.getPostal2());

        MasterPoliticalOrganizationBaseEntity baseEntity = callMasterPoliOrgBaseEntityLogic.practice(kanrenshaCode);
        BeanUtils.copyProperties(baseEntity, dto.getInputOrgNameDto());
        dto.getOrgDelegateLeastDto().setPersonKanrenshaCode(baseEntity.getOrgDelegateCode());

        MasterPoliticalOrganizationPropertyEntity propertyEntity = callMasterPoliOrgPropertyEntityLogic
                .practice(kanrenshaCode);
        BeanUtils.copyProperties(propertyEntity, dto);
        dto.getAccounrMgrLeastDto().setPersonKanrenshaCode(propertyEntity.getAccountMgrCode());
        dto.getAccounrMgrLeastDto().setPersonName(propertyEntity.getAccountMgrName());

        // 各テーブルのIDも設定
        dto.setAccessId(accessEntity.getMasterPoliticalOrganizationAccessId());
        dto.setAddressId(addressEntity.getMasterPoliticalOrganizationAddressId());
        dto.setBaseId(baseEntity.getMasterPoliticalOrganizationBaseId());
        dto.setPropertyId(propertyEntity.getMasterPoliticalOrganizationPropertyId());

        // 最後にMasterPoliOrgEntityの値を複写して上書き
        dto.setMasterId(masterPoliOrgEntity.getMasterPoliticalOrganizationId());
        dto.getInputOrgNameDto().setOrgName(masterPoliOrgEntity.getPartnerName());
        dto.getInputAddressDto().setAddressAll(masterPoliOrgEntity.getAllAddress());
        dto.getOrgDelegateLeastDto().setPersonName(masterPoliOrgEntity.getPoliOrgDelegate());
        dto.setDantaiKbn(masterPoliOrgEntity.getDantaiKbn());

        return dto;
    }
}
