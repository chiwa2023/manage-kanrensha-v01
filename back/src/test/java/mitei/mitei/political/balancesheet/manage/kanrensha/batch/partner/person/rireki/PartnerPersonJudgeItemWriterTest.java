package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.rireki;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonJudgeRepository;

/**
 * PartnerPersonJudgeItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonJudgeItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPersonJudgeItemWriter partnerPersonJudgeItemWriter;
    

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblPartnerPersonJudgeRepository wkTblPartnerPersonJudgeRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_partner_person_judge.sql")
    void test()throws Exception {
        

        WkTblPartnerPersonJudgeEntity entity00 = new WkTblPartnerPersonJudgeEntity();
        entity00.setWkPartnerPersonHistoryId(124);
        entity00.setIsAffected(false);
        entity00.setJudgeReason("理由");

        List<WkTblPartnerPersonJudgeEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPersonJudgeEntity> items = new Chunk<>(list);

        partnerPersonJudgeItemWriter.beforeStep(this.getStepExecution());
        partnerPersonJudgeItemWriter.write(items);

        List<WkTblPartnerPersonJudgeEntity> listAns = wkTblPartnerPersonJudgeRepository.findAll();
        assertEquals(1, list.size());
        WkTblPartnerPersonJudgeEntity entity = listAns.get(0);

        assertEquals(false, entity.getIsAffected());
        assertEquals(true, entity.getIsLatest());
        assertEquals("理由", entity.getJudgeReason());
        assertEquals(124, entity.getWkPartnerPersonHistoryId());

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
