package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.test.MetaDataInstanceFactory;
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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCorpAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCorpAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;

/**
 * PartnerCorpAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerCorpAddMiniRecordItemWriter partnerCorpAddMiniRecordItemWriter;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者企業・団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerCorpAddMinResultRepository wkTblPartnerCorpAddMinResultRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history01.sql", "delete_master.sql", "delete_wk_tbl_partner_corp_add_min_result.sql" })
    void test() throws Exception {

        WkTblPartnerCorpAddMinEntity entity00 = new WkTblPartnerCorpAddMinEntity();
        entity00.setWkTblPartnerCorpAddMinId(323);
        entity00.setPartnerName("超元素製造組合");
        entity00.setAllAddress("宮崎県架空市湖畔町");
        entity00.setCorpDelegate("組合長　花子");
        entity00.setHoujinNo("1234ABCD");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblPartnerCorpAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerCorpAddMinEntity> items = new Chunk<>(listLoad);

        partnerCorpAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        partnerCorpAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<PartnerCorpHistory01Entity> listHistory = partnerCorpHistory01Repository.findAll();
        assertEquals(1, listHistory.size());

        final String key = "1-234A-BC-D";

        PartnerCorpHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getPartnerName(), entity10.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getCorpDelegate(), entity10.getCorpDelegate());
        assertTrue(entity10.getCorpKanrenshaCode().startsWith(key));

        // マスタ本体に正常登録
        List<MasterCorporationEntity> listMaster = masterCorporationRepository.findAll();
        assertEquals(1, listMaster.size());

        MasterCorporationEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getPartnerName(), entity11.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getCorpDelegate(), entity11.getCorpDelegate());
        assertTrue(entity11.getCorpKanrenshaCode().startsWith(key));
        assertEquals("超元素製造組合", entity11.getCompareNameText());

        // 処理結果に正常登録
        List<WkTblPartnerCorpAddMinResultEntity> listResult = wkTblPartnerCorpAddMinResultRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblPartnerCorpAddMinId(), listResult.get(0).getWkTblPartnerCorpAddMinId());

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
