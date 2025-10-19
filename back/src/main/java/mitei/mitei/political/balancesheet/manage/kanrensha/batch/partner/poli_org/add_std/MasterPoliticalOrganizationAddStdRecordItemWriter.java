package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std; // NOPMD

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPoliOrgHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertPartnerPoliOrgHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPoliOrgUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録マスタ複写ItemWriter
 */
@Component
public class MasterPoliticalOrganizationAddStdRecordItemWriter extends JpaItemWriter<WkTblMasterPoliOrgEntity> {

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblMasterPoliOrgJudgeRepository wkTblMasterPoliOrgJudgeRepository;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertPartnerPoliOrgHistoryService insertPartnerPoliOrgHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    /** 関連者個人マスタ基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPoliOrgUtil createDokujiCodeForPoliOrgUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterPoliticalOrganizationAddStdRecordItemWriter(
            final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * BeforeStep(読み取りファイル指定)
     *
     * @param stepExecution stepExecution
     */
    @BeforeStep
    public void beforeStep(final StepExecution stepExecution) {

        userDto = createUserLeastDtoByBatchParamUtil.practice(stepExecution);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends WkTblMasterPoliOrgEntity> items) {

        final List<WkTblMasterPoliOrgJudgeEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblMasterPoliOrgEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForPoliOrgUtil.practice("");

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblMasterPoliOrgJudgeRepository.saveAllAndFlush(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblMasterPoliOrgEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        MasterPoliticalOrganizationEntity masterPoliticalOrganizationEntity = new MasterPoliticalOrganizationEntity();
        BeanUtils.copyProperties(entityWkTbl, masterPoliticalOrganizationEntity);
        masterPoliticalOrganizationEntity.setPoliOrgKanrenshaCode(kanrenshaCode);
        masterPoliticalOrganizationEntity.setCompareNameText(
                formatNaturalSearchTextUtil.practice(masterPoliticalOrganizationEntity.getPartnerName()));
        setTableDataHistoryUtil.practiceInsert(userDto, masterPoliticalOrganizationEntity);
        int masterId = masterPoliticalOrganizationRepository.save(masterPoliticalOrganizationEntity)
                .getMasterPoliticalOrganizationId();

        // マスタ住所登録
        MasterPoliticalOrganizationAddressEntity addressEntity = new MasterPoliticalOrganizationAddressEntity();
        addressEntity.setPoliOrgKanrenshaCode(kanrenshaCode);
        addressEntity.setMasterPoliticalOrganizationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, addressEntity);
        // 各住所項目に記載がある場合はチェック対象とする
        if (!EMPTY.equals(entityWkTbl.getAddressPostal())) {
            addressEntity.setIsPostalEdit(true);
            addressEntity.setIsPostalAccept(false);
        }
        if (!EMPTY.equals(entityWkTbl.getAddressBlock())) {
            addressEntity.setIsBlockEdit(true);
            addressEntity.setIsBlockAccept(false);
        }
        if (!EMPTY.equals(entityWkTbl.getAddressBuilding())) {
            addressEntity.setIsBuildingEdit(true);
            addressEntity.setIsBuildingAccept(false);
        }
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
        masterPoliticalOrganizationAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        MasterPoliticalOrganizationAccessEntity accessEntity = new MasterPoliticalOrganizationAccessEntity();
        accessEntity.setPoliOrgKanrenshaCode(kanrenshaCode);
        accessEntity.setMasterPoliticalOrganizationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterPoliticalOrganizationAccessRepository.save(accessEntity);

        // マスタ基本登録
        MasterPoliticalOrganizationBaseEntity baseEntity = new MasterPoliticalOrganizationBaseEntity();
        baseEntity.setPoliOrgKanrenshaCode(kanrenshaCode);
        baseEntity.setMasterPoliticalOrganizationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, baseEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        masterPoliticalOrganizationBaseRepository.save(baseEntity);

        // マスタ(その他)属性登録
        MasterPoliticalOrganizationPropertyEntity propertyEntity = new MasterPoliticalOrganizationPropertyEntity();
        propertyEntity.setPoliOrgKanrenshaCode(kanrenshaCode);
        propertyEntity.setMasterPoliticalOrganizationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterPoliticalOrganizationPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblMasterPoliOrgEntity entityWkTbl, final String kanrenshaCode) {

        PartnerPoliOrgHistoryBaseEntity entity = new PartnerPoliOrgHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPoliOrgKanrenshaCode(kanrenshaCode);

        return insertPartnerPoliOrgHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblMasterPoliOrgJudgeEntity createJudge(final WkTblMasterPoliOrgEntity entityWkTbl) {

        WkTblMasterPoliOrgJudgeEntity entity = new WkTblMasterPoliOrgJudgeEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblMasterPoliOrgId(entityWkTbl.getWkTblMasterPoliOrgId());

        return entity;
    }
}
