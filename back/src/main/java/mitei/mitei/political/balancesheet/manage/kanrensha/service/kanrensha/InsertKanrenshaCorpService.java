package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.user.InsertCombineUserKanrenshaLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForCorpUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体を新規登録する
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@Service
public class InsertKanrenshaCorpService {

    /** 企業団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    /** 企業団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationAddressRepository;

    /** 企業団体マスタ基本Repository */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /** 関連者企業団体Dtoマスタ住所Entity変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic convertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic;

    /** 関連者企業団体Dtoマスタ連絡先Entity変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic convertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic;

    /** 関連者企業団体Dtoマスタ基本Entity変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic convertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic;

    /** 関連者企業団体Dtoマスタ属性Entity変換Logic */
    @Autowired
    private ConvertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic convertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic;

    /** 関連者企業団体履歴追加Service */
    @Autowired
    private InsertPartnerCorpHistoryService insertPartnerCorpHistoryService;

    /** ユーザ関連者紐づけLogic */
    @Autowired
    private InsertCombineUserKanrenshaLogic insertCombineUserKanrenshaLogic;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForCorpUtil createDokujiCodeForCorpUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 登録したマスタのID
     */
    @Transactional
    public Integer practice(final SaveKanrenshaCorpCapsuleDto capsuleDto) {

        KanrenshaCorpDto kanrenshaCorpDto = capsuleDto.getKanrenshaCorpDto();

        // マスタ本体設定
        MasterCorporationEntity corporationEntity = new MasterCorporationEntity();
        corporationEntity.setPartnerName(kanrenshaCorpDto.getInputOrgNameDto().getOrgName());
        corporationEntity.setAllAddress(kanrenshaCorpDto.getInputAddressDto().getAddressAll());
        corporationEntity.setHoujinNo(kanrenshaCorpDto.getHoujinNo());
        corporationEntity.setCorpKanrenshaCode(createDokujiCodeForCorpUtil.practice(kanrenshaCorpDto.getHoujinNo()));
        corporationEntity.setCorpDelegate(kanrenshaCorpDto.getOrgDelegateLeastDto().getPersonName());
        corporationEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(corporationEntity.getPartnerName()));

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceInsert(userDto, corporationEntity);

        // 登録
        MasterCorporationEntity savedEntity = masterCorporationRepository.save(corporationEntity);
        String newCode = savedEntity.getCorpKanrenshaCode();
        Integer newId = savedEntity.getMasterCorporationId();

        // 住所
        MasterCorporationAddressEntity addressEntity = convertKanrenshaCorpDtoToMasterCorporationAddressEntityLogic
                .practice(kanrenshaCorpDto);
        addressEntity.setCorpKanrenshaCode(newCode);
        addressEntity.setPartnerName(corporationEntity.getPartnerName());
        addressEntity.setMasterCorporationId(newId);
        addressEntity.setMasterCorporationAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        masterCorporationAddressRepository.save(addressEntity);

        // 連絡先
        MasterCorporationAccessEntity accessEntity = convertKanrenshaCorpDtoToMasterCorporationAccessEntityLogic
                .practice(kanrenshaCorpDto);
        accessEntity.setCorpKanrenshaCode(newCode);
        accessEntity.setPartnerName(corporationEntity.getPartnerName());
        accessEntity.setMasterCorporationAccessId(0); // auto_increment明示
        accessEntity.setMasterCorporationId(newId);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterCorporationAccessRepository.save(accessEntity);

        // 基本
        MasterCorporationBaseEntity baseEntity = convertKanrenshaCorpDtoToMasterCorporationBaseEntityLogic
                .practice(kanrenshaCorpDto);
        baseEntity.setCorpKanrenshaCode(newCode);
        baseEntity.setPartnerName(corporationEntity.getPartnerName());
        baseEntity.setMasterCorporationBaseId(0); // auto_increment明示
        baseEntity.setMasterCorporationId(newId);
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        masterCorporationBaseRepository.save(baseEntity);

        // 属性
        MasterCorporationPropertyEntity propertyEntity = convertKanrenshaCorpDtoToMasterCorporationPropertyEntityLogic
                .practice(kanrenshaCorpDto);
        propertyEntity.setCorpKanrenshaCode(newCode);
        propertyEntity.setPartnerName(corporationEntity.getPartnerName());
        propertyEntity.setMasterCorporationPropertyId(0); // auto_increment明示
        propertyEntity.setMasterCorporationId(newId);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterCorporationPropertyRepository.save(propertyEntity);

        // 履歴を追加
        PartnerCorpHistoryBaseEntity historyEntity = new PartnerCorpHistoryBaseEntity();
        historyEntity.setPartnerName(corporationEntity.getPartnerName());
        historyEntity.setAllAddress(corporationEntity.getAllAddress());
        historyEntity.setCorpKanrenshaCode(newCode);

        insertPartnerCorpHistoryService.practice(userDto, historyEntity);

        // 運営者以上が他人のデータを追加している以外の場合は操作者ユーザと登録した関連者を紐づける
        if (kanrenshaCorpDto.getIsCombineUser()) {
            insertCombineUserKanrenshaLogic.practcie(userDto.getUserPersonCode(), KanrenshaKbnConstants.CORP, newCode,
                    userDto);
        }
        return savedEntity.getMasterCorporationId();
    }

}