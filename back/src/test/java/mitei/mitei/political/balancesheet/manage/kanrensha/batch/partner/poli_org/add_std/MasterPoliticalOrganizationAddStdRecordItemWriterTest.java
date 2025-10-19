package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std; // NOPMD

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAccessEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationAddressEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationPropertyEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterPoliOrgJudgeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.lgcode.PartnerPoliOrgHistory01Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAccessRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationAddressRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationPropertyRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterPoliOrgJudgeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.lgccode.PartnerPoliOrgHistory01Repository;

/**
 * MasterPoliticalOrganizationAddStdRecordItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPoliticalOrganizationAddStdRecordItemWriterTest {

    /** テスト対象 */
    @Autowired
    private MasterPoliticalOrganizationAddStdRecordItemWriter masterPoliticalOrganizationAddStdRecordItemWriter;

    /** 関連者個人マスタ標準判定結果Repository */
    @Autowired
    private WkTblMasterPoliOrgJudgeRepository wkTblMasterPoliOrgJudgeRepository;

    /** 関連者政治団体履歴Repository(01) */
    @Autowired
    private PartnerPoliOrgHistory01Repository partnerPoliOrgHistory01Repository;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAddressRepository masterPoliticalOrganizationAddressRepository;

    /** 関連者個人マスタ住所Repository */
    @Autowired
    private MasterPoliticalOrganizationAccessRepository masterPoliticalOrganizationAccessRepository;

    /** 関連者個人マスタ基本Repository */
    @Autowired
    private MasterPoliticalOrganizationBaseRepository masterPoliticalOrganizationBaseRepository;

    /** 関連者個人マスタ(その他属性)Repository */
    @Autowired
    private MasterPoliticalOrganizationPropertyRepository masterPoliticalOrganizationPropertyRepository;

    /** userId */
    private static final Integer userId = 219;
    /** userCode */
    private static final Integer userCode = 190;
    /** userName */
    private static final String userName = "代表者　太郎";

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "delete_history_01.sql", "delete_master_political_organization.sql",
            "delete_master_political_organization_base.sql", "delete_master_political_organization_address.sql",
            "delete_master_political_organization_access.sql", "delete_master_political_organization_property.sql",
            "delete_wk_tbl_master_poli_org_judge.sql" })
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
        
        List<WkTblMasterPoliOrgEntity> listLoad = new ArrayList<>();
        listLoad.add(entity00);

        // Chunkを作成してセット
        Chunk<? extends WkTblMasterPoliOrgEntity> items = new Chunk<>(listLoad);

        masterPoliticalOrganizationAddStdRecordItemWriter.beforeStep(this.getStepExecution());
        masterPoliticalOrganizationAddStdRecordItemWriter.write(items);

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
        assertEquals(entity10.getPoliOrgKanrenshaCode(), entity11.getPoliOrgKanrenshaCode());
        
        // マスタ住所に正常登録
        List<MasterPoliticalOrganizationAddressEntity> listAddress = masterPoliticalOrganizationAddressRepository.findAll();
        assertEquals(1, listAddress.size());
        MasterPoliticalOrganizationAddressEntity entity12 = listAddress.get(0);
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
        List<MasterPoliticalOrganizationAccessEntity> listAccess = masterPoliticalOrganizationAccessRepository.findAll();
        assertEquals(1, listAccess.size());
        MasterPoliticalOrganizationAccessEntity entity13 = listAccess.get(0);
        assertEquals(entity00.getPhon1(), entity13.getPhon1());
        assertEquals(entity00.getPhon2(), entity13.getPhon2());
        assertEquals(entity00.getPhon3(), entity13.getPhon3());
        assertEquals(entity00.getEmail(), entity13.getEmail());
        assertEquals(entity00.getMyPortalUrl(), entity13.getMyPortalUrl());
        assertEquals(entity00.getSnsServiceName(), entity13.getSnsServiceName());
        assertEquals(entity00.getSnsAccount(), entity13.getSnsAccount());

        // マスタ基本に正常登録
        List<MasterPoliticalOrganizationBaseEntity> listBase = masterPoliticalOrganizationBaseRepository.findAll();
        assertEquals(1, listBase.size());
        MasterPoliticalOrganizationBaseEntity entity14 = listBase.get(0);
        assertEquals(entity00.getOrgNameKana(), entity14.getOrgNameKana());
        assertEquals(entity00.getOrgDelegateCode(), entity14.getOrgDelegateCode());

        // マスタ属性に正常登録
        List<MasterPoliticalOrganizationPropertyEntity> listProperty = masterPoliticalOrganizationPropertyRepository.findAll();
        assertEquals(1, listProperty.size());
        MasterPoliticalOrganizationPropertyEntity entity15 = listProperty.get(0);
        assertEquals(entity00.getAccountMgrCode(), entity15.getAccountMgrCode());
        assertEquals(entity00.getAccountMgrName(), entity15.getAccountMgrName());
        
        // 処理結果に正常登録
        List<WkTblMasterPoliOrgJudgeEntity> listResult = wkTblMasterPoliOrgJudgeRepository.findAll();
        assertEquals(1, listResult.size());
        assertEquals(entity00.getWkTblMasterPoliOrgId(), listResult.get(0).getWkTblMasterPoliOrgId());
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
