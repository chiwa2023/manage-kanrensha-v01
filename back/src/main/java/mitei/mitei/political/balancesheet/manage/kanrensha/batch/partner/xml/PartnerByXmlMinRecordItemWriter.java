package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml; // NOPMD

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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForCorpUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPersonUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateDokujiCodeForPoliOrgUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateUserLeastDtoByBatchParamUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 関連者企業団体マスタ履歴最小登録IterWriter
 */
@Component
public class PartnerByXmlMinRecordItemWriter extends JpaItemWriter<WkTblMasterAllByXmlEntity> {

    /** 関連者個人履歴(01)Repository */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者政治団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** XMl登録最小マスタ判定Repository */
    @Autowired
    private WkTblMasterAllByXmlJudgeRepository wkTblMasterAllByXmlJudgeRepository;

    /** 全文検索用フォーマットUtility */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /** バッチ起動条件からユーザ最低限作成Utility */
    @Autowired
    private CreateUserLeastDtoByBatchParamUtil createUserLeastDtoByBatchParamUtil;

    /** テーブル履歴設定Utility */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPersonUtil createDokujiCodeForPersonUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForCorpUtil createDokujiCodeForCorpUtil;

    /** 関連者コード政治団体用発行Utility */
    @Autowired
    private CreateDokujiCodeForPoliOrgUtil createDokujiCodeForPoliOrgUtil;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public PartnerByXmlMinRecordItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
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
    public void write(final Chunk<? extends WkTblMasterAllByXmlEntity> items) {

        final List<WkTblMasterAllByXmlJudgeEntity> list = new ArrayList<>();

        // 編集処理
        for (WkTblMasterAllByXmlEntity entity : items) {

            int masterId = 0;
            int historyId = 0;
            String kanrenshaCode;
            switch (entity.getKanrenshaKbn()) {
                case 1:
                    // 個人登録
                    kanrenshaCode = createDokujiCodeForPersonUtil.practice("");

                    masterId = this.insertMasterPerson(entity, kanrenshaCode);
                    historyId = this.insertHistoryPerson(entity, kanrenshaCode);
                    break;

                case 2:
                    // 企業団体登録
                    kanrenshaCode = createDokujiCodeForCorpUtil.practice(entity.getHoujinNo());

                    masterId = this.insertMasterCorp(entity, kanrenshaCode);
                    historyId = this.insertHistoryCorp(entity, kanrenshaCode);
                    break;

                case 3: // SUPPRESS CHECKSTYLE MagicNumber
                    // 政治団体登録
                    kanrenshaCode = createDokujiCodeForPoliOrgUtil.practice("");

                    masterId = this.insertMasterPoliOrg(entity, kanrenshaCode);
                    historyId = this.insertHistoryPoliOrg(entity, kanrenshaCode);
                    break;

                default:
                    throw new IllegalArgumentException("Unexpected value: " + entity.getKanrenshaKbn());
            }

            // 両方間違いなく更新できたら結果に残す
            if (masterId != 0 && historyId != 0) {
                list.add(this.createResult(entity));
                wkTblMasterAllByXmlJudgeRepository.flush();
            }
        }

        wkTblMasterAllByXmlJudgeRepository.saveAllAndFlush(list);
    }

    private int insertMasterCorp(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        MasterCorporationEntity entity = new MasterCorporationEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setCorpKanrenshaCode(kanrenshaCode);
        entity.setCorpDelegate(entityWkTbl.getOrgDelegate());

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterCorporationId(0); // auto_increment明示

        return masterCorporationRepository.save(entity).getMasterCorporationId();

    }

    private int insertHistoryCorp(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerCorpHistory01Entity entity = new PartnerCorpHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setCorpKanrenshaCode(kanrenshaCode);
        entity.setCorpDelegate(entityWkTbl.getOrgDelegate());

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerCorpHistoryId(0); // auto_increment明示

        return partnerCorpHistory01Repository.save(entity).getPartnerCorpHistoryId();

    }

    private int insertMasterPerson(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        MasterPersonEntity entity = new MasterPersonEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getPartnerName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterPersonId(0); // auto_increment明示

        return masterPersonRepository.save(entity).getMasterPersonId();

    }

    private int insertHistoryPerson(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerPersonHistory01Entity entity = new PartnerPersonHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPersonKanrenshaCode(kanrenshaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPersonHistoryId(0); // auto_increment明示

        return partnerPersonHistory01Repository.save(entity).getPartnerPersonHistoryId();

    }

    private int insertMasterPoliOrg(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        MasterPoliticalOrganizationEntity entity = new MasterPoliticalOrganizationEntity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPoliOrgKanrenshaCode(kanrenshaCode);
        entity.setPoliOrgDelegate(entityWkTbl.getOrgDelegate());
        
        entity.setCompareNameText(formatNaturalSearchTextUtil.practice(entity.getPartnerName()));

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setMasterPoliticalOrganizationId(0); // auto_increment明示

        return masterPoliticalOrganizationRepository.save(entity).getMasterPoliticalOrganizationId();

    }

    private int insertHistoryPoliOrg(final WkTblMasterAllByXmlEntity entityWkTbl, final String kanrenshaCode) {

        // TODO 47都道府県とそれ以外に分割して登録する
        PartnerPoliOrgHistory01Entity entity = new PartnerPoliOrgHistory01Entity();
        BeanUtils.copyProperties(entityWkTbl, entity);
        entity.setPoliOrgKanrenshaCode(kanrenshaCode);
        entity.setPoliOrgDelegate(entityWkTbl.getOrgDelegate());

        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setPartnerPoliOrgHistoryId(0); // auto_increment明示

        return partnerPoliOrgHistory01Repository.save(entity).getPartnerPoliOrgHistoryId();

    }

    private WkTblMasterAllByXmlJudgeEntity createResult(final WkTblMasterAllByXmlEntity entityWkTbl) {
        WkTblMasterAllByXmlJudgeEntity entity = new WkTblMasterAllByXmlJudgeEntity();
        setTableDataHistoryUtil.practiceInsert(userDto, entity);
        entity.setIsAffected(true);
        entity.setWkTblMasterAllByXmlId(entityWkTbl.getWkTblMasterAllByXmlId());

        return entity;
    }

}
