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
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonHistoryEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPersonHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory99Repository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * PartnerPersonWkTblFixItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonWkTblFixItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPersonWkTblFixItemWriter partnerPersonWkTblFixItemWriter;

    /** 関連者企業・団体ワークテーブルRepository */
    @Autowired
    private WkTblPartnerPersonHistoryRepository wkTblPartnerPersonHistoryRepository;

    /** 個人履歴その他Repository */
    @Autowired
    private PartnerPersonHistory99Repository partnerPersonHistory99Repository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_partner_person_history.sql", "sample_wk_tbl_partner_person_history.sql" })
    void test() throws Exception {

        List<WkTblPartnerPersonHistoryEntity> listLoad = wkTblPartnerPersonHistoryRepository
                .findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(userCode, SetTableDataHistoryUtil.INSERT_STATE,
                        true, false, Pageable.unpaged())
                .toList();

        WkTblPartnerPersonHistoryEntity entity00 = listLoad.get(0);
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("正常");

        WkTblPartnerPersonHistoryEntity entity01 = listLoad.get(1);
        entity01.setIsAffected(true);
        entity01.setIsFinish(true);
        entity01.setJudgeReason("正常");

        WkTblPartnerPersonHistoryEntity entity02 = listLoad.get(2);
        entity02.setIsAffected(false);
        entity02.setIsFinish(true);
        entity02.setJudgeReason("指定された関連者コードと団体名が存在しません");

        List<WkTblPartnerPersonHistoryEntity> list = new ArrayList<>();
        list.addAll(listLoad);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPersonHistoryEntity> items = new Chunk<>(listLoad);

        partnerPersonWkTblFixItemWriter.beforeStep(this.getStepExecution());
        partnerPersonWkTblFixItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        WkTblPartnerPersonHistoryEntity entity10 = wkTblPartnerPersonHistoryRepository.findById(98).get();
        assertEquals(entity00.getWkPartnerPersonHistoryId(), entity10.getWkPartnerPersonHistoryId());
        assertEquals(entity00.getIsAffected(), entity10.getIsAffected());
        assertEquals(true, entity10.getIsFinish());
        assertEquals("正常保存", entity10.getJudgeReason());

        // 履歴テーブル本体に正常登録できなかった(同じファイル内に同じ登録データが存在した場合)
        WkTblPartnerPersonHistoryEntity entity11 = wkTblPartnerPersonHistoryRepository.findById(99).get();
        assertEquals(entity01.getWkPartnerPersonHistoryId(), entity11.getWkPartnerPersonHistoryId());
        assertEquals(true, entity11.getIsAffected());
        assertEquals(true, entity11.getIsFinish());
        assertEquals("正常保存", entity11.getJudgeReason());

        // 保存しない判定の場合は何もしない
        WkTblPartnerPersonHistoryEntity entity12 = wkTblPartnerPersonHistoryRepository.findById(100).get();
        assertEquals(entity02.getWkPartnerPersonHistoryId(), entity12.getWkPartnerPersonHistoryId());
        assertEquals(entity02.getIsAffected(), entity12.getIsAffected());
        assertEquals(entity02.getIsFinish(), entity12.getIsFinish());
        assertEquals(entity02.getJudgeReason(), entity12.getJudgeReason());

        // 履歴に登録された確認(住所は正規の県名で始まっていないので99:その他)
        List<PartnerPersonHistory99Entity> listAns = partnerPersonHistory99Repository.findAll();
        assertEquals(2, listAns.size());

        PartnerPersonHistory99Entity partnerEntity00 = listAns.get(0);
        assertEquals(entity10.getPartnerName(), partnerEntity00.getPartnerName());
        assertEquals(entity10.getAllAddress(), partnerEntity00.getAllAddress());
        assertEquals(entity10.getPersonShokugyou(), partnerEntity00.getPersonShokugyou());
        assertEquals(entity10.getPersonKanrenshaCode(), partnerEntity00.getPersonKanrenshaCode());

        PartnerPersonHistory99Entity partnerEntity01 = listAns.get(1);
        assertEquals(entity11.getPartnerName(), partnerEntity01.getPartnerName());
        assertEquals(entity11.getAllAddress(), partnerEntity01.getAllAddress());
        assertEquals(entity11.getPersonShokugyou(), partnerEntity01.getPersonShokugyou());
        assertEquals(entity11.getPersonKanrenshaCode(), partnerEntity01.getPersonKanrenshaCode());
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
