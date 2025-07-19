package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonRepository;

/**
 * MasterPersonAddStdCsvItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPersonAddStdCsvItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdCsvItemWriter masterPersonAddStdCsvItemWriter;

    /** 関連者個人マスタワークテーブルRepository */
    @Autowired
    private WkTblMasterPersonRepository wkTblMasterPersonRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("delete_wk_tbl_master_person.sql")
    void test() throws Exception { // NOPMD

        WkTblMasterPersonEntity entity00 = new WkTblMasterPersonEntity();
        entity00.setPartnerName("団体名");
        entity00.setAllAddress("全住所");
        entity00.setPersonShokugyou("職業");

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

        entity00.setLastName("迂回献金");
        entity00.setFirstName("太郎");
        entity00.setMiddleName("ミカエル");
        entity00.setLastNameKana("太郎");
        entity00.setFirstNameKana("たろう");
        entity00.setMiddleNameKana("みかえる");
        entity00.setGyoushu("水産業");
        entity00.setYakushoku("団体役員");
        entity00.setShokugyouUserWrite("正義の味方");
        entity00.setCorpNo("1-2345");
        entity00.setCorpAddress("和歌山県実在市山麓町");
        entity00.setCorpName("超元素製造組合");

        entity00.setEmail("aaa@bbbb.net");
        entity00.setIsForeign(true);
        entity00.setMyPortalUrl("https://bbb.net/");
        entity00.setPhon1("567");
        entity00.setPhon2("8901");
        entity00.setPhon3("2345");
        entity00.setSnsAccount("@abcds");
        entity00.setSnsServiceName("弱小ブログ");

        entity00.setIsForeign(false);

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblMasterPersonEntity> list = new ArrayList<>();
        list.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterPersonEntity> items = new Chunk<>(list);

        masterPersonAddStdCsvItemWriter.beforeStep(this.getStepExecution());
        masterPersonAddStdCsvItemWriter.write(items);

        List<WkTblMasterPersonEntity> listAns = wkTblMasterPersonRepository.findAll();
        assertEquals(1, listAns.size());

        WkTblMasterPersonEntity entityAns = listAns.get(0);

        assertEquals(entity00.getPartnerName(), entityAns.getPartnerName());
        assertEquals(entity00.getAllAddress(), entityAns.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entityAns.getPersonShokugyou());

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

        assertEquals(entity00.getLastName(), entityAns.getLastName());
        assertEquals(entity00.getFirstName(), entityAns.getFirstName());
        assertEquals(entity00.getMiddleName(), entityAns.getMiddleName());
        assertEquals(entity00.getLastNameKana(), entityAns.getLastNameKana());
        assertEquals(entity00.getFirstNameKana(), entityAns.getFirstNameKana());
        assertEquals(entity00.getMiddleNameKana(), entityAns.getMiddleNameKana());
        assertEquals(entity00.getGyoushu(), entityAns.getGyoushu());
        assertEquals(entity00.getYakushoku(), entityAns.getYakushoku());
        assertEquals(entity00.getShokugyouUserWrite(), entityAns.getShokugyouUserWrite());
        assertEquals(entity00.getCorpNo(), entityAns.getCorpNo());
        assertEquals(entity00.getCorpAddress(), entityAns.getCorpAddress());
        assertEquals(entity00.getCorpName(), entityAns.getCorpName());

        assertEquals(entity00.getEmail(), entityAns.getEmail());
        assertEquals(entity00.getIsForeign(), entityAns.getIsForeign());
        assertEquals(entity00.getMyPortalUrl(), entityAns.getMyPortalUrl());
        assertEquals(entity00.getPhon1(), entityAns.getPhon1());
        assertEquals(entity00.getPhon2(), entityAns.getPhon2());
        assertEquals(entity00.getPhon3(), entityAns.getPhon3());
        assertEquals(entity00.getSnsAccount(), entityAns.getSnsAccount());
        assertEquals(entity00.getSnsServiceName(), entityAns.getSnsServiceName());

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
