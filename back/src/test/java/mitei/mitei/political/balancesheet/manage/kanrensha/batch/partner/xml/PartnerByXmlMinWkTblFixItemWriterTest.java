package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.xml;

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
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterAllByXmlEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterAllByXmlRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * PartnerByXmlMinWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerByXmlMinWkTblFixItemWriterTest {

    /** テスト対象 */
    @Autowired
    private PartnerByXmlMinWkTblFixItemWriter partnerByXmlMinWkTblFixItemWriter;

    /** 関連者政治団体登録最小限Repository */
    @Autowired
    private WkTblMasterAllByXmlRepository wkTblMasterAllByXmlRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("sample_wk_tbl_master_all_by_xml.sql")
    void test() {
        List<WkTblMasterAllByXmlEntity> listLoad = wkTblMasterAllByXmlRepository
                .findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(userCode, SetTableDataHistoryUtil.INSERT_STATE,
                        true, false, Pageable.unpaged()).toList();

        WkTblMasterAllByXmlEntity entity00 = listLoad.get(0);
        entity00.setIsAffected(true);
        entity00.setIsFinish(true);
        entity00.setJudgeReason("正常");

        List<WkTblMasterAllByXmlEntity> list = new ArrayList<>();
        list.addAll(listLoad);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterAllByXmlEntity> items = new Chunk<>(listLoad);

        partnerByXmlMinWkTblFixItemWriter.beforeStep(this.getStepExecution());
        partnerByXmlMinWkTblFixItemWriter.write(items);

        WkTblMasterAllByXmlEntity entityAns = wkTblMasterAllByXmlRepository
                .findById(entity00.getWkTblMasterAllByXmlId()).get();

        assertEquals(entity00.getIsAffected(), entityAns.getIsAffected());
        assertEquals(entity00.getIsFinish(), entityAns.getIsFinish());
        assertEquals(entity00.getJudgeReason(), entityAns.getJudgeReason());
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
