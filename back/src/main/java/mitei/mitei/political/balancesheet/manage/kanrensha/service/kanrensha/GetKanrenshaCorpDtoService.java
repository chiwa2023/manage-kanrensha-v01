package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterCorpAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterCorpAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterCorpBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.CallMasterCorpPropertyEntityLogic;

/**
 * 関連者個人マスタを取得してDTOに変換するService
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class GetKanrenshaCorpDtoService {

    /** 関連者個人連絡先マスタ取得Logic */
    @Autowired
    private CallMasterCorpAccessEntityLogic callMasterCorpAccessEntityLogic;

    /** 関連者個人住所マスタ取得Logic */
    @Autowired
    private CallMasterCorpAddressEntityLogic callMasterCorpAddressEntityLogic;

    /** 関連者個人基本マスタ取得Logic */
    @Autowired
    private CallMasterCorpBaseEntityLogic callMasterCorpBaseEntityLogic;

    /** 関連者個人属性マスタ取得Logic */
    @Autowired
    private CallMasterCorpPropertyEntityLogic callMasterCorpPropertyEntityLogic;

    /**
     * 処理を行う
     *
     * @param masterCorpEntity 関連者企業団体マスタ
     * @return 関連者個人DTO
     */
    public KanrenshaCorpDto practice(final MasterCorporationEntity masterCorpEntity) {
        KanrenshaCorpDto dto = new KanrenshaCorpDto();
        String kanrenshaCode = masterCorpEntity.getCorpKanrenshaCode();

        // 各マスタからエンティティを取得
        // Entity -> DTOへの値複写
        MasterCorporationAccessEntity accessEntity = callMasterCorpAccessEntityLogic.practice(kanrenshaCode);
        dto.setInputAccessDto(new InputAccessDto());
        BeanUtils.copyProperties(accessEntity, dto.getInputAccessDto());

        MasterCorporationAddressEntity addressEntity = callMasterCorpAddressEntityLogic.practice(kanrenshaCode);
        dto.setInputAddressDto(new InputAddressDto());
        BeanUtils.copyProperties(addressEntity, dto.getInputAddressDto());
        dto.getInputAddressDto().setPostalcode1(addressEntity.getPostal1());
        dto.getInputAddressDto().setPostalcode2(addressEntity.getPostal2());

        MasterCorporationBaseEntity baseEntity = callMasterCorpBaseEntityLogic.practice(kanrenshaCode);
        dto.setIsShiten(baseEntity.getIsShiten());
        dto.getInputOrgNameDto().setOrgNameKana(baseEntity.getOrgNameKana());
        dto.getOrgDelegateLeastDto().setPersonKanrenshaCode(baseEntity.getOrgDelegateCode());
        
        MasterCorporationPropertyEntity propertyEntity = callMasterCorpPropertyEntityLogic.practice(kanrenshaCode);
        BeanUtils.copyProperties(propertyEntity, dto);

        // 各テーブルのIDも設定
        dto.setAccessId(accessEntity.getMasterCorporationAccessId());
        dto.setAddressId(addressEntity.getMasterCorporationAddressId());
        dto.setBaseId(baseEntity.getMasterCorporationBaseId());
        dto.setPropertyId(propertyEntity.getMasterCorporationPropertyId());

        // 最後にMasterPersonEntityの値を複写して上書き
        dto.setMasterId(masterCorpEntity.getMasterCorporationId());
        dto.getInputOrgNameDto().setOrgName(masterCorpEntity.getPartnerName());
        dto.getInputAddressDto().setAddressAll(masterCorpEntity.getAllAddress());
        dto.getOrgDelegateLeastDto().setPersonName(masterCorpEntity.getCorpDelegate());
        dto.setHoujinNo(masterCorpEntity.getHoujinNo());

        return dto;
    }
}
