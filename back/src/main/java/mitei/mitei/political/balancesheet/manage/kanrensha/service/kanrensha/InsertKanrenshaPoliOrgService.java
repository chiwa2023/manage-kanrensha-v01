package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPoliOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPoliOrgUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者政治団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaPoliOrgService {

    /** 政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 政治団体マスタ連絡先Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    /** 政治団体マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** 政治団体マスタ基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /** 政治団体マスタ属性Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /** 関連者政治団体Dtoマスタ住所Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic;

    /** 関連者政治団体Dtoマスタ連絡先Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic;

    /** 関連者政治団体Dtoマスタ基本Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic;

    /** 関連者政治団体Dtoマスタ属性Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic;

    /** 関連者政治団体履歴追加Service */
    @Autowired
    private InsertPartnerPoliOrgHistoryService insertPartnerPoliOrgHistoryService;

    /** ユーザ関連者紐づけLogic */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPoliOrgUtil createDokujiCodeForPoliOrgUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 登録したマスタのID
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPoliOrgCapsuleDto capsuleDto) {

        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = capsuleDto.getKanrenshaPoliOrgDto();

        // マスタ本体設定
        MasterPoliticalOrganizationEntity poliOrgEntity = new MasterPoliticalOrganizationEntity();
        poliOrgEntity.setPartnerName(kanrenshaPoliOrgDto.getInputOrgNameDto().getOrgName());
        poliOrgEntity.setAllAddress(kanrenshaPoliOrgDto.getInputAddressDto().getAddressAll());
        poliOrgEntity.setPoliOrgDelegate(kanrenshaPoliOrgDto.getOrgDelegateLeastDto().getPersonName());
        poliOrgEntity.setPoliOrgKanrenshaCode(createDokujiCodeForPoliOrgUtil.practice(""));
        poliOrgEntity.setDantaiKbn(kanrenshaPoliOrgDto.getDantaiKbn());
        poliOrgEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(poliOrgEntity.getPartnerName()));

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceInsert(userDto, poliOrgEntity);

        // 登録
        MasterPoliticalOrganizationEntity savedEntity = masterPoliticalOrganizationRepository.save(poliOrgEntity);
        String newCode = savedEntity.getPoliOrgKanrenshaCode();

        // 住所
        MasterPoliticalOrganizationAddressEntity addressEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAddressEntityLogic
                .practice(kanrenshaPoliOrgDto);
        addressEntity.setPoliOrgKanrenshaCode(newCode);
        addressEntity.setPartnerName(poliOrgEntity.getPartnerName());
        addressEntity.setMasterPoliticalOrganizationAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        masterPoliticalOrganizationAddressRepository.save(addressEntity);

        // 連絡先
        MasterPoliticalOrganizationAccessEntity accessEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationAccessEntityLogic
                .practice(kanrenshaPoliOrgDto);
        accessEntity.setPoliOrgKanrenshaCode(newCode);
        accessEntity.setPartnerName(poliOrgEntity.getPartnerName());
        accessEntity.setMasterPoliticalOrganizationAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterPoliticalOrganizationAccessRepository.save(accessEntity);

        // 基本
        MasterPoliticalOrganizationBaseEntity baseEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationBaseEntityLogic
                .practice(kanrenshaPoliOrgDto);
        baseEntity.setPoliOrgKanrenshaCode(newCode);
        baseEntity.setPartnerName(poliOrgEntity.getPartnerName());
        baseEntity.setMasterPoliticalOrganizationBaseId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        masterPoliticalOrganizationBaseRepository.save(baseEntity);

        // 属性
        MasterPoliticalOrganizationPropertyEntity propertyEntity = convertKanrenshaPoliOrgDtoToMasterPoliticalOrganizationPropertyEntityLogic
                .practice(kanrenshaPoliOrgDto);
        propertyEntity.setPoliOrgKanrenshaCode(newCode);
        propertyEntity.setPartnerName(poliOrgEntity.getPartnerName());
        propertyEntity.setMasterPoliticalOrganizationId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterPoliticalOrganizationPropertyRepository.save(propertyEntity);

        // 履歴を追加
        PartnerPoliOrgHistoryBaseEntity historyEntity = new PartnerPoliOrgHistoryBaseEntity();
        historyEntity.setPartnerName(poliOrgEntity.getPartnerName());
        historyEntity.setAllAddress(poliOrgEntity.getAllAddress());
        historyEntity.setPoliOrgKanrenshaCode(newCode);

        insertPartnerPoliOrgHistoryService.practice(userDto, historyEntity);

        // 運営者以上が他人のデータを追加している以外の場合は操作者ユーザと登録した関連者を紐づける
        if (kanrenshaPoliOrgDto.getIsCombineUser()) {
            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.POLI_ORG, newCode,
                    userDto);
        }
        return savedEntity.getMasterPoliticalOrganizationId();
    }

}