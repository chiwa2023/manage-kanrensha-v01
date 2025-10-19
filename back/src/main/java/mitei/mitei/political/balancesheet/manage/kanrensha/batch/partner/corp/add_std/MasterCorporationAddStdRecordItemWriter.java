package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std; // NOPMD

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerCorpHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertPartnerCorpHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForCorpUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業・団体標準登録マスタ複写ItemWriter
 */
@Component
public class MasterCorporationAddStdRecordItemWriter extends JpaItemWriter<WkTblMasterCorpEntity> {

    /** 関連者企業・団体マスタ標準判定結果Repository */
    @Autowired
    private WkTblMasterCorpJudgeRepository wkTblMasterCorpJudgeRepository;

    /** 関連者企業・団体履歴登録Service */
    @Autowired
    private InsertPartnerCorpHistoryService insertPartnerCorpHistoryService;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationaddAddressRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    /** 関連者企業・団体マスタ基本Repository */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /** 関連者企業・団体マスタ(その他属性)Repository */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 全文自然検索整形Utility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    
    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForCorpUtil createDokujiCodeForCorpUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterCorporationAddStdRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblMasterCorpEntity> items) {

        final List<WkTblMasterCorpJudgeEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblMasterCorpEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForCorpUtil.practice(entity.getHoujinNo());

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblMasterCorpJudgeRepository.saveAllAndFlush(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblMasterCorpEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        MasterCorporationEntity masterCorporationEntity = new MasterCorporationEntity();
        BeanUtils.copyProperties(entityWkTbl, masterCorporationEntity);
        masterCorporationEntity.setCorpKanrenshaCode(kanrenshaCode);
        setTableDataHistoryUtil.practiceInsert(userDto, masterCorporationEntity);
        masterCorporationEntity.setCompareNameText(formatNaturalSearchTextUtil.practice(masterCorporationEntity.getPartnerName()));
        int masterId = masterCorporationRepository.save(masterCorporationEntity).getMasterCorporationId();

        // マスタ住所登録
        MasterCorporationAddressEntity addressEntity = new MasterCorporationAddressEntity();
        addressEntity.setCorpKanrenshaCode(kanrenshaCode);
        addressEntity.setMasterCorporationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, addressEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
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
        masterCorporationaddAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        MasterCorporationAccessEntity accessEntity = new MasterCorporationAccessEntity();
        accessEntity.setCorpKanrenshaCode(kanrenshaCode);
        accessEntity.setMasterCorporationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        masterCorporationAccessRepository.save(accessEntity);

        // マスタ基本登録
        MasterCorporationBaseEntity baseEntity = new MasterCorporationBaseEntity();
        baseEntity.setCorpKanrenshaCode(kanrenshaCode);
        baseEntity.setMasterCorporationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, baseEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        masterCorporationBaseRepository.save(baseEntity);

        // マスタ(その他)属性登録
        MasterCorporationPropertyEntity propertyEntity = new MasterCorporationPropertyEntity();
        propertyEntity.setCorpKanrenshaCode(kanrenshaCode);
        propertyEntity.setMasterCorporationId(masterId);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        masterCorporationPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblMasterCorpEntity entityWkTbl, final String kanrenshaCode) {

        PartnerCorpHistoryBaseEntity entity = new PartnerCorpHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setCorpKanrenshaCode(kanrenshaCode);

        return insertPartnerCorpHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblMasterCorpJudgeEntity createJudge(final WkTblMasterCorpEntity entityWkTbl) {

        WkTblMasterCorpJudgeEntity entity = new WkTblMasterCorpJudgeEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblMasterCorpId(entityWkTbl.getWkTblMasterCorpId());

        return entity;
    }
}
