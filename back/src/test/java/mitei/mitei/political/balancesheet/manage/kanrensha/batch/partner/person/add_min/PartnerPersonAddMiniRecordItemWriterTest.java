package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;

/**
 * PartnerPersonAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPersonAddMiniRecordItemWriter partnerPersonAddMiniRecordItemWriter;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者企業・団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerPersonAddMinResultRepository wkTblPartnerPersonAddMinResultRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history01.sql", "delete_master.sql", "delete_wk_tbl_partner_person_add_min_result.sql" })
    void test() throws Exception {

        WkTblPartnerPersonAddMinEntity entity00 = new WkTblPartnerPersonAddMinEntity();
        entity00.setWkTblPartnerPersonAddMinId(323);
        entity00.setPartnerName("寄付上限　花子");
        entity00.setAllAddress("宮崎県架空市湖畔町");
        entity00.setPersonShokugyou("団体役員");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblPartnerPersonAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPersonAddMinEntity> items = new Chunk<>(listLoad);

        partnerPersonAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        partnerPersonAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<PartnerPersonHistory01Entity> listHistory = partnerPersonHistory01Repository.findAll();
        assertEquals(1, listHistory.size());

        PartnerPersonHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getPartnerName(), entity10.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity10.getPersonShokugyou());

        // マスタ本体に正常登録
        List<MasterPersonEntity> listMaster = masterPersonRepository.findAll();
        assertEquals(1, listMaster.size());

        MasterPersonEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getPartnerName(), entity11.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity11.getPersonShokugyou());
        assertEquals("寄付上限花子", entity11.getCompareNameText());

        // 処理結果に正常登録
        List<WkTblPartnerPersonAddMinResultEntity> listResult = wkTblPartnerPersonAddMinResultRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblPartnerPersonAddMinId(), listResult.get(0).getWkTblPartnerPersonAddMinId());

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
