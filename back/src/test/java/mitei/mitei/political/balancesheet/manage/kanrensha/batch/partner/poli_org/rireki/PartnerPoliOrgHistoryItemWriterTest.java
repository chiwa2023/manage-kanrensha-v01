package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;

/**
 * PartnerPoliOrgHistoryItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgHistoryItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgHistoryItemWriter partnerPoliOrgHistoryItemWriter;

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPoliOrgHistoryRepository wkTblPartnerPoliOrgHistoryRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_partner_poli_org_history.sql")
    void test() throws Exception {

        WkTblPartnerPoliOrgHistoryEntity entity00 = new WkTblPartnerPoliOrgHistoryEntity();
        entity00.setAllAddress("全住所");
        entity00.setPoliOrgDelegate("代表者");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");
        entity00.setPartnerName("団体名");

        List<WkTblPartnerPoliOrgHistoryEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPoliOrgHistoryEntity> items = new Chunk<>(list);

        partnerPoliOrgHistoryItemWriter.beforeStep(this.getStepExecution());
        partnerPoliOrgHistoryItemWriter.write(items);

        List<WkTblPartnerPoliOrgHistoryEntity> listAns = wkTblPartnerPoliOrgHistoryRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblPartnerPoliOrgHistoryEntity entityAns = listAns.get(0);

        assertEquals(entity00.getAllAddress(), entityAns.getAllAddress());
        assertEquals(entity00.getPoliOrgDelegate(), entityAns.getPoliOrgDelegate());
        assertEquals(entity00.getPoliOrgKanrenshaCode(), entityAns.getPoliOrgKanrenshaCode());
        assertEquals(entity00.getIsAffected(), entityAns.getIsAffected());
        assertEquals(entity00.getIsFinish(), entityAns.getIsFinish());
        assertEquals(entity00.getJudgeReason(), entityAns.getJudgeReason());
        assertEquals(entity00.getPartnerName(), entityAns.getPartnerName());

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
