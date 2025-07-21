package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;

/**
 * MasterCorporationAddStdCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterCorporationAddStdCsvItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterCorporationAddStdCsvItemWriter masterCorporationAddStdCsvItemWriter;

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_corp.sql")
    void test() throws Exception { // NOPMD

        WkTblMasterCorpEntity entity00 = new WkTblMasterCorpEntity();
        entity00.setPartnerName("団体名");
        entity00.setAllAddress("全住所");
        entity00.setCorpDelegate("職業");
        entity00.setHoujinNo("11-222-3333");

        entity00.setAddressPostal("郵便番号住所");
        entity00.setAddressBlock("番地住所");
        entity00.setAddressBuilding("建物住所");
        entity00.setPostal1("987");
        entity00.setPostal2("6543");
        entity00.setLgCode("012");
        entity00.setMachiazaId("123");
        entity00.setBlkId("234");
        entity00.setRsdtId("345");
        entity00.setRsdt2Id("678");

        entity00.setEmail("aaa@bbbb.net");
        entity00.setMyPortalUrl("https://bbb.net/");
        entity00.setPhon1("567");
        entity00.setPhon2("8901");
        entity00.setPhon3("2345");
        entity00.setSnsAccount("@abcds");
        entity00.setSnsServiceName("弱小ブログ");

        entity00.setOrgNameKana("だんたいめい");
        entity00.setIsShiten(false);
        entity00.setOrgDelegateCode("111-222-333");
        entity00.setHoujinSbts("201");
        entity00.setIsForeign(false);

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblMasterCorpEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterCorpEntity> items = new Chunk<>(list);

        masterCorporationAddStdCsvItemWriter.beforeStep(this.getStepExecution());
        masterCorporationAddStdCsvItemWriter.write(items);

        List<WkTblMasterCorpEntity> listAns = wkTblMasterCorpRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblMasterCorpEntity entityAns = listAns.get(0);

        assertEquals(entity00.getPartnerName(), entityAns.getPartnerName());
        assertEquals(entity00.getAllAddress(), entityAns.getAllAddress());
        assertEquals(entity00.getCorpDelegate(), entityAns.getCorpDelegate());
        assertEquals(entity00.getHoujinNo(), entityAns.getHoujinNo());

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

        assertEquals(entity00.getEmail(), entityAns.getEmail());
        assertEquals(entity00.getIsForeign(), entityAns.getIsForeign());
        assertEquals(entity00.getMyPortalUrl(), entityAns.getMyPortalUrl());
        assertEquals(entity00.getPhon1(), entityAns.getPhon1());
        assertEquals(entity00.getPhon2(), entityAns.getPhon2());
        assertEquals(entity00.getPhon3(), entityAns.getPhon3());
        assertEquals(entity00.getSnsAccount(), entityAns.getSnsAccount());
        assertEquals(entity00.getSnsServiceName(), entityAns.getSnsServiceName());

        assertEquals(entity00.getOrgNameKana(), entityAns.getOrgNameKana());
        assertEquals(entity00.getIsShiten(), entityAns.getIsShiten());
        assertEquals(entity00.getOrgDelegateCode(), entityAns.getOrgDelegateCode());

        assertEquals(entity00.getHoujinSbts(), entityAns.getHoujinSbts());
        assertEquals(entity00.getIsForeign(), entityAns.getIsForeign());

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
