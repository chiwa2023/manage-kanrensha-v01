package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha; // NOPMD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha.ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人を編集する
 */
@Service
public class InsertKanrenshaPersonService {

    /** 企業団体マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /** 企業団体マスタ連絡先Repository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 企業団体マスタ属性Repository */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /** 関連者個人Dtoマスタ住所Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonAddressEntityLogic convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic;

    /** 関連者個人Dtoマスタ住所Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonAccessEntityLogic convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic;

    /** 関連者個人Dtoマスタ基本Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonBaseEntityLogic convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic;

    /** 関連者個人Dtoマスタ属性Entity変換Logic */
    @Autowired
    private ConvertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic;

    /** 関連者個人履歴追加Service */
    @Autowired
    private InsertPartnerPersonHistoryService insertPartnerPersonHistoryService;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード個人用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto 処理条件
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SaveKanrenshaPersonCapsuleDto capsuleDto) {

        KanrenshaPersonDto kanrenshaPersonDto = capsuleDto.getKanrenshaPersonDto();

        // マスタ本体設定
        MasterPersonEntity personEntity = new MasterPersonEntity();
        personEntity.setAllAddress(kanrenshaPersonDto.getInputAddressDto().getAddressAll());
        personEntity.setPartnerName(kanrenshaPersonDto.getInputPersonNameDto().getAllName());
        personEntity.setPersonShokugyou(kanrenshaPersonDto.getInputShokugyouDto().getAllShokugyou());
        personEntity.setPersonKanrenshaCode(createDokujiCodeForPersonUtil.practice(""));
        personEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(personEntity.getPartnerName()));

        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();
        setTableDataHistoryUtil.practiceInsert(userDto, personEntity);

        // 登録コードが連番でないので悲観ロック不要
        MasterPersonEntity savedEntity = masterPersonRepository.save(personEntity);
        String newCode = savedEntity.getPersonKanrenshaCode();

        // 住所
        MasterPersonAddressEntity addressEntity = convertKanrenshaPersonDtoToMasterPersonAddressEntityLogic
                .practice(kanrenshaPersonDto);
        addressEntity.setPersonKanrenshaCode(newCode);
        addressEntity.setPartnerName(personEntity.getPartnerName());
        addressEntity.setMasterPersonAddressId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        masterPersonAddressRepository.save(addressEntity);

        // 連絡先
        MasterPersonAccessEntity accessEntity = convertKanrenshaPersonDtoToMasterPersonAccessEntityLogic
                .practice(kanrenshaPersonDto);
        accessEntity.setPersonKanrenshaCode(newCode);
        accessEntity.setPartnerName(personEntity.getPartnerName());
        accessEntity.setMasterPersonAccessId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterPersonAccessRepository.save(accessEntity);

        // 基本
        MasterPersonBaseEntity baseEntity = convertKanrenshaPersonDtoToMasterPersonBaseEntityLogic
                .practice(kanrenshaPersonDto);
        baseEntity.setPersonKanrenshaCode(newCode);
        baseEntity.setPartnerName(personEntity.getPartnerName());
        baseEntity.setMasterPersonBaseId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        masterPersonBaseRepository.save(baseEntity);

        // 属性
        MasterPersonPropertyEntity propertyEntity = convertKanrenshaPersonDtoToMasterPersonPropertyEntityLogic
                .practice(kanrenshaPersonDto);
        propertyEntity.setPersonKanrenshaCode(newCode);
        propertyEntity.setPartnerName(personEntity.getPartnerName());
        propertyEntity.setMasterPersonPropertyId(0); // auto_increment明示
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterPersonPropertyRepository.save(propertyEntity);

        // 履歴を追加
        PartnerPersonHistoryBaseEntity historyEntity = new PartnerPersonHistoryBaseEntity();
        historyEntity.setPartnerName(personEntity.getPartnerName());
        historyEntity.setAllAddress(personEntity.getAllAddress());
        historyEntity.setPersonShokugyou(personEntity.getPersonShokugyou());
        historyEntity.setPersonKanrenshaCode(newCode);

        insertPartnerPersonHistoryService.practice(userDto, historyEntity);

        return savedEntity.getMasterPersonId();
    }

}
