package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std; // NOPMD

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPersonJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPersonHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPersonJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPersonHistory01Repository;

/**
 * MasterPersonAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPersonAddStdRecordItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdRecordItemWriter masterPersonAddStdRecordItemWriter;

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblMasterPersonJudgeRepository wkTblMasterPersonJudgeRepository;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPersonAddressRepository masterPersonAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPersonAccessRepository masterPersonAccessRepository;

    /** 関連者個人マスタ基本Repository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private MasterPersonPropertyRepository masterPersonPropertyRepository;

    /** 関連者個人マスタ履歴Repository(01) */
    @Autowired
    private PartnerPersonHistory01Repository partnerPersonHistory01Repository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history_01.sql", "delete_master_person.sql", "delete_master_person_base.sql",
            "delete_master_person_address.sql", "delete_master_person_access.sql", "delete_master_person_property.sql",
            "delete_wk_tbl_master_person_judge.sql" })
    void test() throws Exception { // NOPMD

        WkTblMasterPersonEntity entity00 = new WkTblMasterPersonEntity();
        entity00.setWkTblMasterPersonId(323);
        entity00.setPartnerName("迂回献金　太郎");
        entity00.setAllAddress("北海道架空市湖畔町");
        entity00.setPersonShokugyou("団体役員");

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
        entity00.setJudgeReason("");

        List<WkTblMasterPersonEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterPersonEntity> items = new Chunk<>(listLoad);

        masterPersonAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterPersonAddStdRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<PartnerPersonHistory01Entity> listHistory = partnerPersonHistory01Repository.findAll();
        assertEquals(1, listHistory.size());
        PartnerPersonHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getPartnerName(), entity10.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity10.getPersonShokugyou());

        // マスタ本体に正常登録
        List<MasterPersonEntity> listMaster = masterPersonRepository.findAll();
        assertEquals(1, listMaster.size());
        MasterPersonEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getPartnerName(), entity11.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getPersonShokugyou(), entity11.getPersonShokugyou());
        assertEquals("迂回献金太郎", entity11.getCompareNameText());
        assertEquals(entity10.getPersonKanrenshaCode(), entity11.getPersonKanrenshaCode());

        // マスタ住所に正常登録
        List<MasterPersonAddressEntity> listAddress = masterPersonAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        MasterPersonAddressEntity entity12 = listAddress.get(0);
        assertEquals(entity00.getAddressPostal(), entity12.getAddressPostal());
        assertEquals(entity00.getAddressBlock(), entity12.getAddressBlock());
        assertEquals(entity00.getAddressBuilding(), entity12.getAddressBuilding());
        assertEquals(entity00.getPostal1(), entity12.getPostal1());
        assertEquals(entity00.getPostal2(), entity12.getPostal2());
        assertEquals(entity00.getLgCode(), entity12.getLgCode());
        assertEquals(entity00.getMachiazaId(), entity12.getMachiazaId());
        assertEquals(entity00.getBlkId(), entity12.getBlkId());
        assertEquals(entity00.getRsdtId(), entity12.getRsdtId());
        assertEquals(entity00.getRsdt2Id(), entity12.getRsdt2Id());
        assertEquals(true, entity12.getIsPostalEdit());
        assertEquals(true, entity12.getIsBlockEdit());
        assertEquals(true, entity12.getIsBuildingEdit());
        assertEquals(false, entity12.getIsPostalAccept());
        assertEquals(false, entity12.getIsBlockAccept());
        assertEquals(false, entity12.getIsBuildingAccept());

        // マスタ連絡先に正常登録
        List<MasterPersonAccessEntity> listAccess = masterPersonAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        MasterPersonAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ基本に正常登録
        List<MasterPersonBaseEntity> listBase = masterPersonBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterPersonBaseEntity entity14 = listBase.get(0);
        assertEquals(entity00.getLastName(), entity14.getLastName());
        assertEquals(entity00.getFirstName(), entity14.getFirstName());
        assertEquals(entity00.getMiddleName(), entity14.getMiddleName());
        assertEquals(entity00.getLastNameKana(), entity14.getLastNameKana());
        assertEquals(entity00.getFirstNameKana(), entity14.getFirstNameKana());
        assertEquals(entity00.getMiddleNameKana(), entity14.getMiddleNameKana());
        assertEquals(entity00.getGyoushu(), entity14.getGyoushu());
        assertEquals(entity00.getYakushoku(), entity14.getYakushoku());
        assertEquals(entity00.getShokugyouUserWrite(), entity14.getShokugyouUserWrite());
        assertEquals(entity00.getCorpNo(), entity14.getCorpNo());
        assertEquals(entity00.getCorpAddress(), entity14.getCorpAddress());
        assertEquals(entity00.getCorpName(), entity14.getCorpName());
        assertEquals(true, entity14.getIsShokyouEdit());
        assertEquals(false, entity14.getIsShokyouAccept());

        // マスタ属性に正常登録
        List<MasterPersonPropertyEntity> listProperty = masterPersonPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        MasterPersonPropertyEntity entity15 = listProperty.get(0);
        assertEquals(entity00.getIsForeign(), entity15.getIsForeign());

        // 処理結果に正常登録
        List<WkTblMasterPersonJudgeEntity> listResult = wkTblMasterPersonJudgeRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblMasterPersonId(), listResult.get(0).getWkTblMasterPersonId());
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
