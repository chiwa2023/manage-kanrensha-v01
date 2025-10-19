package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerCorpHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerCorpHistory01Repository;

/**
 * MasterCorporationAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterCorporationAddStdRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterCorporationAddStdRecordItemWriter masterCorporationAddStdRecordItemWriter;

    /** 関連者企業・団体履歴Repository(01) */
    @Autowired
    private PartnerCorpHistory01Repository partnerCorpHistory01Repository;

    /** 関連者企業・団体マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAddressRepository masterCorporationaddAddressRepository;

    /** 関連者企業・団体マスタ住所Repository */
    @Autowired
    private MasterCorporationAccessRepository masterCorporationAccessRepository;

    /** 関連者企業・団体マスタ基本Repository */
    @Autowired
    private MasterCorporationBaseRepository masterCorporationBaseRepository;

    /** 関連者企業・団体マスタ(その他属性)Repository */
    @Autowired
    private MasterCorporationPropertyRepository masterCorporationPropertyRepository;

    /** 関連者企業・団体ワークテーブル判定Repository */
    @Autowired
    private WkTblMasterCorpJudgeRepository wkTblMasterCorpJudgeRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history_01.sql", "delete_master_corporation.sql", "delete_master_corporation_base.sql",
            "delete_master_corporation_address.sql", "delete_master_corporation_access.sql",
            "delete_master_corporation_property.sql", "delete_wk_tbl_master_corp_judge.sql" })
    void test() throws Exception { // NOPMD

        WkTblMasterCorpEntity entity00 = new WkTblMasterCorpEntity();

        entity00.setPartnerName("超元素製造組合");
        entity00.setAllAddress("北海道架空市湖畔町");
        entity00.setCorpDelegate("組合長　花子");
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
        entity00.setIsShiten(true);
        entity00.setOrgDelegateCode("111-222-333");
        entity00.setHoujinSbts("201");
        entity00.setIsForeign(true);

        entity00.setIsAffected(true);
        entity00.setIsFinish(false);
        entity00.setJudgeReason("理由");

        List<WkTblMasterCorpEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterCorpEntity> items = new Chunk<>(listLoad);

        masterCorporationAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterCorporationAddStdRecordItemWriter.write(items);

        // 履歴テーブル本体に正常登録
        List<PartnerCorpHistory01Entity> listHistory = partnerCorpHistory01Repository.findAll();
        assertEquals(1, listHistory.size());
        PartnerCorpHistory01Entity entity10 = listHistory.get(0);
        assertEquals(entity00.getPartnerName(), entity10.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity10.getAllAddress());
        assertEquals(entity00.getCorpDelegate(), entity10.getCorpDelegate());
        assertTrue(entity10.getCorpKanrenshaCode().startsWith("1-1222-33-33"));

        // マスタ本体に正常登録
        List<MasterCorporationEntity> listMaster = masterCorporationRepository.findAll();
        assertEquals(1, listMaster.size());
        MasterCorporationEntity entity11 = listMaster.get(0);
        assertEquals(entity00.getPartnerName(), entity11.getPartnerName());
        assertEquals(entity00.getAllAddress(), entity11.getAllAddress());
        assertEquals(entity00.getCorpDelegate(), entity11.getCorpDelegate());
        assertEquals(entity00.getHoujinNo(), entity11.getHoujinNo());
        assertEquals(entity10.getCorpKanrenshaCode(), entity11.getCorpKanrenshaCode());

        // マスタ住所に正常登録
        List<MasterCorporationAddressEntity> listAddress = masterCorporationaddAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        MasterCorporationAddressEntity entity12 = listAddress.get(0);
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
        List<MasterCorporationAccessEntity> listAccess = masterCorporationAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        MasterCorporationAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ基本に正常登録
        List<MasterCorporationBaseEntity> listBase = masterCorporationBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterCorporationBaseEntity entity14 = listBase.get(0);
        assertEquals(true, entity14.getIsShiten());
        assertEquals(entity00.getOrgDelegateCode(), entity14.getOrgDelegateCode());
        assertEquals(entity00.getOrgNameKana(), entity14.getOrgNameKana());

        // マスタ属性に正常登録
        List<MasterCorporationPropertyEntity> listProperty = masterCorporationPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        MasterCorporationPropertyEntity entity15 = listProperty.get(0);
        assertEquals(entity00.getHoujinSbts(), entity15.getHoujinSbts());
        assertEquals(entity00.getIsForeign(), entity15.getIsForeign());

        // 処理結果に正常登録
        List<WkTblMasterCorpJudgeEntity> listResult = wkTblMasterCorpJudgeRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblMasterCorpId(), listResult.get(0).getWkTblMasterCorpId());
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
