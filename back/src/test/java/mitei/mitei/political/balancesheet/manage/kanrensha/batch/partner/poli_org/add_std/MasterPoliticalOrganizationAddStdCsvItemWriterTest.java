package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgRepository;

/**
 * MasterPoliticalOrganizationAddStdCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPoliticalOrganizationAddStdCsvItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterPoliticalOrganizationAddStdCsvItemWriter masterPoliticalOrganizationAddStdCsvItemWriter;

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterPoliOrgRepository wkTblMasterPoliOrgRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_poli_org.sql")
    void test() throws Exception { // NOPMD

        WkTblMasterPoliOrgEntity entity00 = new WkTblMasterPoliOrgEntity();

        // 政治団体名
        entity00.setPartnerName("ちゃらんぽらん政治団体");
        // 政治団体全住所
        entity00.setAllAddress("北海道架空市山麓町");
        // 政治団体代表者
        entity00.setPoliOrgDelegate("代表者　　太郎");
        // 政治団体区分
        entity00.setDantaiKbn("05");
        // 住所郵便番号
        entity00.setAddressPostal("北海道架空市山麓町");
        // 住所番地
        entity00.setAddressBlock("6丁目32番地");
        // 住所建物
        entity00.setAddressBuilding("三角ビル4F");
        // 郵便番号1
        entity00.setPostal1("012");
        // 郵便番号2
        entity00.setPostal2("3456");
        // 地方自治体コード
        entity00.setLgCode("987654");
        // 町字コード
        entity00.setMachiazaId("876");
        // 街区コード
        entity00.setBlkId("765");
        // 住居コード
        entity00.setRsdtId("654");
        // 住居2コード
        entity00.setRsdt2Id("543");
        // 電話番号1
        entity00.setPhon1("012");
        // 電話番号2
        entity00.setPhon2("34566");
        // 電話番号3
        entity00.setPhon3("7890");
        // 電子メール
        entity00.setEmail("aaa@bbb.com");
        // 所有(公式)url
        entity00.setMyPortalUrl("https://bbb.com/aaa");
        // SNSサービス名称
        entity00.setSnsServiceName("弱小SNS");
        // SNSサービスアカウント
        entity00.setSnsAccount("@aaa_bbb");
        // 関連者団体名称かな
        entity00.setOrgNameKana("ちゃらんぽらんせいじだんたい");
        // 団体代表者関連者コード
        entity00.setOrgDelegateCode("3333-444444");
        // 会計責任者関連者個人コード
        entity00.setAccountMgrCode("4444-5555555");
        // 会計責任者関連者個人氏名
        entity00.setAccountMgrName("会計責任者　花子");

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");
        
        List<WkTblMasterPoliOrgEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterPoliOrgEntity> items = new Chunk<>(list);

        masterPoliticalOrganizationAddStdCsvItemWriter.beforeStep(this.getStepExecution());
        masterPoliticalOrganizationAddStdCsvItemWriter.write(items);

        List<WkTblMasterPoliOrgEntity> listAns = wkTblMasterPoliOrgRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblMasterPoliOrgEntity entityAns = listAns.get(0);

        assertEquals(entity00.getPartnerName(), entityAns.getPartnerName());
        assertEquals(entity00.getAllAddress(), entityAns.getAllAddress());
        assertEquals(entity00.getPoliOrgDelegate(), entityAns.getPoliOrgDelegate());
        assertEquals(entity00.getDantaiKbn(), entityAns.getDantaiKbn());
        assertEquals(entity00.getAddressPostal(), entityAns.getAddressPostal());
        assertEquals(entity00.getAddressBlock(), entityAns.getAddressBlock());
        assertEquals(entity00.getAddressBuilding(), entityAns.getAddressBuilding());
        assertEquals(entity00.getPostal1(), entityAns.getPostal1());
        assertEquals(entity00.getPostal2(), entityAns.getPostal2());
        assertEquals(entity00.getLgCode(), entityAns.getLgCode());
        assertEquals(entity00.getMachiazaId(), entityAns.getMachiazaId());
        assertEquals(entity00.getBlkId(), entityAns.getBlkId());
        assertEquals(entity00.getRsdtId(), entityAns.getRsdtId());
        assertEquals(entity00.getRsdt2Id(), entityAns.getRsdt2Id());
        assertEquals(entity00.getPhon1(), entityAns.getPhon1());
        assertEquals(entity00.getPhon2(), entityAns.getPhon2());
        assertEquals(entity00.getPhon3(), entityAns.getPhon3());
        assertEquals(entity00.getEmail(), entityAns.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entityAns.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entityAns.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entityAns.getSnsAccount());
        assertEquals(entity00.getOrgNameKana(), entityAns.getOrgNameKana());
        assertEquals(entity00.getOrgDelegateCode(), entityAns.getOrgDelegateCode());
        assertEquals(entity00.getAccountMgrCode(), entityAns.getAccountMgrCode());
        assertEquals(entity00.getAccountMgrName(), entityAns.getAccountMgrName());

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
