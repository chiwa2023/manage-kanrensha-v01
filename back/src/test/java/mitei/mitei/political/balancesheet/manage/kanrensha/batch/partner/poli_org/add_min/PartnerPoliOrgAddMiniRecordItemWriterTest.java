package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_min;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPoliOrgAddMinResultEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerPoliOrgAddMinResultRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;

/**
 * PartnerPoliOrgAddMiniRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPoliOrgAddMiniRecordItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private PartnerPoliOrgAddMiniRecordItemWriter partnerPoliOrgAddMiniRecordItemWriter;

    /** 関連者企業・団体履歴(01)Repository */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 関連者企業・団体マスタ履歴処理結果Repository */
    @Autowired
    private WkTblPartnerPoliOrgAddMinResultRepository wkTblPartnerPoliOrgAddMinResultRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history01.sql", "delete_master.sql", "delete_wk_tbl_partner_poli_org_add_min_result.sql" })
    void test() throws Exception {

        WkTblPartnerPoliOrgAddMinEntity entity00 = new WkTblPartnerPoliOrgAddMinEntity();
        entity00.setWkTblPartnerPoliOrgAddMinId(487);
        entity00.setPartnerName("ちゃらんぽらん政治団体A");
        entity00.setAllAddress("宮崎県架空市湖畔町");
        entity00.setPoliOrgDelegate("組合長　花子");
        entity00.setDantaiKbn("04");
        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("");

        List<WkTblPartnerPoliOrgAddMinEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblPartnerPoliOrgAddMinEntity> items = new Chunk<>(listLoad);

        partnerPoliOrgAddMiniRecordItemWriter.beforeStep(this.getStepExecution());
        partnerPoliOrgAddMiniRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<PartnerPoliOrgHistory01Entity> listHistory = partnerPoliOrgHistory01Repository.findAll();
        assertEquals(1, listHistory.size());

        PartnerPoliOrgHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getPartnerName(), entity10.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getPoliOrgDelegate(), entity10.getPoliOrgDelegate());

        // マスタ本体に正常登録
        List<MasterPoliticalOrganizationEntity> listMaster = masterPoliticalOrganizationRepository.findAll();
        assertEquals(1, listMaster.size());

        MasterPoliticalOrganizationEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getPartnerName(), entity11.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getPoliOrgDelegate(), entity11.getPoliOrgDelegate());
        assertEquals(entity00.getDantaiKbn(), entity11.getDantaiKbn());
        assertEquals("ちゃらんぽらん政治団体a", entity11.getCompareNameText());

        // 処理結果に正常登録
        List<WkTblPartnerPoliOrgAddMinResultEntity> listResult = wkTblPartnerPoliOrgAddMinResultRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblPartnerPoliOrgAddMinId(), listResult.get(0).getWkTblPartnerPoliOrgAddMinId());

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
