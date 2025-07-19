package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std; // NOPMD

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.PartnerPersonHistoryBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha.InsertPartnerPersonHistoryService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者個人標準登録マスタ複写ItemWriter
 */
@Component
public class MasterPersonAddStdRecordItemWriter extends JpaItemWriter<WkTblMasterPersonEntity> {

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblMasterPersonJudgeRepository wkTblMasterPersonJudgeRepository;

    /** 関連者個人履歴登録Service */
    @Autowired
    private InsertPartnerPersonHistoryService insertPartnerPersonHistoryService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonaddAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    /** 関連者個人マスタ基本Repository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード企業・団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /** 空文字 */
    private static final String EMPTY = "";

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public MasterPersonAddStdRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblMasterPersonEntity> items) {

        final List<WkTblMasterPersonJudgeEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblMasterPersonEntity entity : items) {

            // 関連者コードを設定
            String kanrenshaCode = createDokujiCodeForPersonUtil.practice("");

            // マスタ登録
            int masterId = this.insertMaster(entity, kanrenshaCode);
            // マスタの内容を複写した履歴を登録
            int historyId = this.insertHistory(entity, kanrenshaCode);

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createJudge(entity));
            }
        }

        wkTblMasterPersonJudgeRepository.saveAllAndFlush(list);
    }

    /* マスタ登録処理を行う */
    private int insertMaster(final WkTblMasterPersonEntity entityWkTbl, final String kanrenshaCode) {

        // マスタ本体登録
        MasterPersonEntity masterPersonEntity = new MasterPersonEntity();
        BeanUtils.copyProperties(entityWkTbl, masterPersonEntity);
        setTableDataHistoryUtil.practiceInsert(userDto, masterPersonEntity);
        int masterId = masterPersonRepository.save(masterPersonEntity).getMasterPersonId();

        // マスタ住所登録
        MasterPersonAddressEntity addressEntity = new MasterPersonAddressEntity();
        addressEntity.setPersonKanrenshaCode(kanrenshaCode);
        addressEntity.setMasterPersonId(masterId);
        setTableDataHistoryUtil.practiceInsert(userDto, addressEntity);
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
        masterPersonaddAddressRepository.save(addressEntity);

        // マスタ連絡先登録
        MasterPersonAccessEntity accessEntity = new MasterPersonAccessEntity();
        accessEntity.setPersonKanrenshaCode(kanrenshaCode);
        accessEntity.setMasterPersonId(masterId);
        setTableDataHistoryUtil.practiceInsert(userDto, accessEntity);
        BeanUtils.copyProperties(entityWkTbl, accessEntity);
        masterPersonAccessRepository.save(accessEntity);

        // マスタ基本登録
        MasterPersonBaseEntity baseEntity = new MasterPersonBaseEntity();
        baseEntity.setPersonKanrenshaCode(kanrenshaCode);
        baseEntity.setMasterPersonId(masterId);
        setTableDataHistoryUtil.practiceInsert(userDto, baseEntity);
        BeanUtils.copyProperties(entityWkTbl, baseEntity);
        if (!EMPTY.equals(entityWkTbl.getShokugyouUserWrite())) {
            baseEntity.setIsShokyouEdit(true);
            baseEntity.setIsShokyouAccept(false);
        }
        masterPersonBaseRepository.save(baseEntity);

        // マスタ(その他)属性登録
        MasterPersonPropertyEntity propertyEntity = new MasterPersonPropertyEntity();
        propertyEntity.setPersonKanrenshaCode(kanrenshaCode);
        propertyEntity.setMasterPersonId(masterId);
        setTableDataHistoryUtil.practiceInsert(userDto, propertyEntity);
        BeanUtils.copyProperties(entityWkTbl, propertyEntity);
        masterPersonPropertyRepository.save(propertyEntity);

        return masterId;
    }

    /* マスタ履歴登録処理を行う */
    private int insertHistory(final WkTblMasterPersonEntity entityWkTbl, final String kanrenshaCode) {

        PartnerPersonHistoryBaseEntity entity = new PartnerPersonHistoryBaseEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);

        return insertPartnerPersonHistoryService.practice(userDto, entity);
    }

    /* ワークテーブル処理結果Entityを作成する */
    private WkTblMasterPersonJudgeEntity createJudge(final WkTblMasterPersonEntity entityWkTbl) {

        WkTblMasterPersonJudgeEntity entity = new WkTblMasterPersonJudgeEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setWkTblMasterPersonId(entityWkTbl.getWkTblMasterPersonId());

        return entity;
    }
}
