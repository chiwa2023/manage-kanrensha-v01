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
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory99Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgHistoryRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory99Repository;
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

    /** 個人履歴その他Repository */
    @Autowired
    private PartnerPoliOrgHistory99Repository partnerPoliOrgHistory99Repository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_wk_tbl_partner_poli_org_history.sql", "delete_partner_poli_org_history.sql" })
    void test() throws Exception {

        List<WkTblPartnerPoliOrgHistoryEntity> listLoad = wkTblPartnerPoliOrgHistoryRepository
                .findByInsertUserCodeAndIsLatestAndIsAffectedAndIsFinish(userCode, SetTableDataHistoryUtil.INSERT_STATE,
                        true, false, Pageable.unpaged())
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

        // 履歴テーブル本体に正常登録
        WkTblPartnerPoliOrgHistoryEntity entity10 = wkTblPartnerPoliOrgHistoryRepository.findById(313).get();
        assertEquals(entity00.getWkPartnerPoliOrgHistoryId(), entity10.getWkPartnerPoliOrgHistoryId());
        assertEquals(entity00.getIsAffected(), entity10.getIsAffected());
        assertEquals(true, entity10.getIsFinish());
        assertEquals("正常保存", entity10.getJudgeReason());

        // 履歴テーブル本体に正常登録できなかった(同じファイル内に同じ登録データが存在した場合)
        WkTblPartnerPoliOrgHistoryEntity entity11 = wkTblPartnerPoliOrgHistoryRepository.findById(314).get();
        assertEquals(entity01.getWkPartnerPoliOrgHistoryId(), entity11.getWkPartnerPoliOrgHistoryId());
        assertEquals(true, entity11.getIsAffected());
        assertEquals(true, entity11.getIsFinish());
        assertEquals("正常保存", entity11.getJudgeReason());

        // 保存しない判定の場合は何もしない
        WkTblPartnerPoliOrgHistoryEntity entity12 = wkTblPartnerPoliOrgHistoryRepository.findById(315).get();
        assertEquals(entity02.getWkPartnerPoliOrgHistoryId(), entity12.getWkPartnerPoliOrgHistoryId());
        assertEquals(entity02.getIsAffected(), entity12.getIsAffected());
        assertEquals(entity02.getIsFinish(), entity12.getIsFinish());
        assertEquals(entity02.getJudgeReason(), entity12.getJudgeReason());

        // 履歴に登録された確認(住所は正規の県名で始まっていないので99:その他)
        List<PartnerPoliOrgHistory99Entity> listAns = partnerPoliOrgHistory99Repository.findAll();
        assertEquals(2, listAns.size());

        PartnerPoliOrgHistory99Entity partnerEntity00 = listAns.get(0);
        assertEquals(entity10.getPartnerName(), partnerEntity00.getPartnerName());
        assertEquals(entity10.getAllAddress(), partnerEntity00.getAllAddress());
        assertEquals(entity10.getPoliOrgDelegate(), partnerEntity00.getPoliOrgDelegate());
        assertEquals(entity10.getPoliOrgKanrenshaCode(), partnerEntity00.getPoliOrgKanrenshaCode());

        PartnerPoliOrgHistory99Entity partnerEntity01 = listAns.get(1);
        assertEquals(entity11.getPartnerName(), partnerEntity01.getPartnerName());
        assertEquals(entity11.getAllAddress(), partnerEntity01.getAllAddress());
        assertEquals(entity11.getPoliOrgDelegate(), partnerEntity01.getPoliOrgDelegate());
        assertEquals(entity11.getPoliOrgKanrenshaCode(), partnerEntity01.getPoliOrgKanrenshaCode());

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
