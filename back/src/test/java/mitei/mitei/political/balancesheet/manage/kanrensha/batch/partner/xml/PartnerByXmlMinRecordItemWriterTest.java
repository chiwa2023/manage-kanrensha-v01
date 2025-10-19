package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;

/**
 * PartnerByXmlMinRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
// すべてのマスタと履歴を削除してバッティングしないようにする
@Sql({ "sample_wk_tbl_master_all_by_xml2.sql", "delete_history_corp01.sql", "delete_history_person01.sql",
        "delete_master_corp.sql", "delete_master_person.sql", "delete_history_poli_org01.sql",
        "delete_master_political_organization.sql" })
class PartnerByXmlMinRecordItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerByXmlMinRecordItemWriter partnerByXmlMinRecordItemWriter;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblMasterAllByXmlJudgeRepository wkTblMasterAllByXmlJudgeRepository;

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

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPerson() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(342).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        partnerByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        partnerByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlJudgeEntity> listAns = wkTblMasterAllByXmlJudgeRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlJudgeEntity entity = listAns.get(0);

        assertEquals(false, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を確認

        PartnerPersonHistory01Entity entiytHistory = partnerPersonHistory01Repository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entiytHistory.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getPersonShokugyou(), entiytHistory.getPersonShokugyou());

        MasterPersonEntity entityMaster = masterPersonRepository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entityMaster.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getPersonShokugyou(), entityMaster.getPersonShokugyou());

        // 同じコードで紐づけ
        assertEquals(entityMaster.getPersonKanrenshaCode(), entiytHistory.getPersonKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testCorp() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(347).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        partnerByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        partnerByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlJudgeEntity> listAns = wkTblMasterAllByXmlJudgeRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlJudgeEntity entity = listAns.get(0);

        assertEquals(false, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を確認

        PartnerCorpHistory01Entity entiytHistory = partnerCorpHistory01Repository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entiytHistory.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entiytHistory.getCorpDelegate());

        MasterCorporationEntity entityMaster = masterCorporationRepository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entityMaster.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entityMaster.getCorpDelegate());
        assertEquals(entityBase.getHoujinNo(), entityMaster.getHoujinNo());

        // 同じコードで紐づけ
        assertEquals(entityMaster.getCorpKanrenshaCode(), entiytHistory.getCorpKanrenshaCode());
    }

    @Test
    @Tag("TableTruncate")
    @Transactional
    void testPoliOrg() {

        final Integer saveId = 1059;

        WkTblMasterAllByXmlEntity entity00 = wkTblMasterAllByXmlRepository.findById(348).get();
        WkTblMasterAllByXmlEntity entityBase = new WkTblMasterAllByXmlEntity();
        BeanUtils.copyProperties(entity00, entityBase);
        entityBase.setWkTblMasterAllByXmlId(saveId);
        entityBase.setIsAffected(false);
        entityBase.setJudgeReason("理由");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.add(entityBase);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(list);

        partnerByXmlMinRecordItemWriter.beforeStep(this.getStepExecution());
        partnerByXmlMinRecordItemWriter.write(items);

        List<WkTblMasterAllByXmlJudgeEntity> listAns = wkTblMasterAllByXmlJudgeRepository.findAll();
        assertEquals(1, list.size());
        WkTblMasterAllByXmlJudgeEntity entity = listAns.get(0);

        assertEquals(false, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals(saveId, entity.getWkTblMasterAllByXmlId());

        // すべてのマスタと履歴テーブルとそのauto_incrementをクリアしているのでId1をで取得して登録内容を

        PartnerPoliOrgHistory01Entity entiytHistory = partnerPoliOrgHistory01Repository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entiytHistory.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entiytHistory.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entiytHistory.getPoliOrgDelegate());

        MasterPoliticalOrganizationEntity entityMaster = masterPoliticalOrganizationRepository.findById(1).get();
        assertEquals(entityBase.getPartnerName(), entityMaster.getPartnerName());
        assertEquals(entityBase.getAllAddress(), entityMaster.getAllAddress());
        assertEquals(entityBase.getOrgDelegate(), entityMaster.getPoliOrgDelegate());
        assertEquals(entityBase.getDantaiKbn(), entityMaster.getDantaiKbn());

        // 同じコードで紐づけ
        assertEquals(entityMaster.getPoliOrgKanrenshaCode(), entiytHistory.getPoliOrgKanrenshaCode());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addLong("userId", Long.parseLong(userId.toString()))
                .addLong("userCode", Long.parseLong(userCode.toString())).addString("userName", userName)
                .toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
