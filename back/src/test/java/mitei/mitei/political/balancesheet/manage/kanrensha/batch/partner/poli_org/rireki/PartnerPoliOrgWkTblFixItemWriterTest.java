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
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * PartnerPoliOrgWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgWkTblFixItemWriter partnerPoliOrgWkTblFixItemWriter;

    /** 関連者政治団体ワークテーブルRepository */
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
    @Sql({ "sample_wk_tbl_partner_poli_org_history.sql", "delete_history01.sql" })
    void test() throws Exception {

        List<WkTblPartnerPoliOrgHistoryEntity> listLoad = wkTblPartnerPoliOrgHistoryRepository
                .findByInsertUserCodeAndIsLatest(userCode, SetTableDataHistoryUtil.INSERT_STATE, Pageable.unpaged())
                .toList();

        WkTblPartnerPoliOrgHistoryEntity entity00 = listLoad.get(0);
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("正常");

        WkTblPartnerPoliOrgHistoryEntity entity01 = listLoad.get(1);
        entity01.setIsAffected(true);
        entity01.setIsFinish(true);
        entity01.setJudgeReason("正常");

        WkTblPartnerPoliOrgHistoryEntity entity02 = listLoad.get(2);
        entity02.setIsAffected(false);
        entity02.setIsFinish(true);
        entity02.setJudgeReason("指定された関連者コードと団体名が存在しません");

        List<WkTblPartnerPoliOrgHistoryEntity> list = new ArrayList<>();
        list.addAll(listLoad);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPoliOrgHistoryEntity> items = new Chunk<>(listLoad);

        partnerPoliOrgWkTblFixItemWriter.beforeStep(this.getStepExecution());
        partnerPoliOrgWkTblFixItemWriter.write(items);

        List<WkTblPartnerPoliOrgHistoryEntity> listAns = wkTblPartnerPoliOrgHistoryRepository
                .findByInsertUserCodeAndIsLatest(userCode, SetTableDataHistoryUtil.INSERT_STATE, Pageable.unpaged())
                .toList();
        assertEquals(3, listAns.size());

        // 履歴テーブル本体に正常登録
        WkTblPartnerPoliOrgHistoryEntity entity10 = listAns.get(0);
        assertEquals(entity00.getWkPartnerPoliOrgHistoryId(), entity10.getWkPartnerPoliOrgHistoryId());
        assertEquals(entity00.getIsAffected(), entity10.getIsAffected());
        assertEquals(true, entity10.getIsFinish());
        assertEquals("正常保存", entity10.getJudgeReason());

        // 履歴テーブル本体に正常登録できなかった(同じファイル内に同じ登録データが存在した場合)
        WkTblPartnerPoliOrgHistoryEntity entity11 = listAns.get(1);
        assertEquals(entity01.getWkPartnerPoliOrgHistoryId(), entity11.getWkPartnerPoliOrgHistoryId());
        assertEquals(true, entity11.getIsAffected());
        assertEquals(true, entity11.getIsFinish());
        assertEquals("正常保存", entity11.getJudgeReason());

        // 保存しない判定の場合は何もしない
        WkTblPartnerPoliOrgHistoryEntity entity12 = listAns.get(2);
        assertEquals(entity02.getWkPartnerPoliOrgHistoryId(), entity12.getWkPartnerPoliOrgHistoryId());
        assertEquals(entity02.getIsAffected(), entity12.getIsAffected());
        assertEquals(entity02.getIsFinish(), entity12.getIsFinish());
        assertEquals(entity02.getJudgeReason(), entity12.getJudgeReason());

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
