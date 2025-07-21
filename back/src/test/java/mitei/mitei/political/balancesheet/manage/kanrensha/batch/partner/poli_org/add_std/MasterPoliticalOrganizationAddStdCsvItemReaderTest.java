package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.poli_org.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;

/**
 * MasterPoliticalOrganizationAddStdCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPoliticalOrganizationAddStdCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private MasterPoliticalOrganizationAddStdCsvItemReader masterPoliticalOrganizationAddStdCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        masterPoliticalOrganizationAddStdCsvItemReader.beforeStep(stepExecution);
        masterPoliticalOrganizationAddStdCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerPoliOrgAddStdDto dto01 = masterPoliticalOrganizationAddStdCsvItemReader.read();

        assertEquals("ちゃらんぽらん政治団体", dto01.getPartnerName());
        assertEquals("北海道架空市山麓町", dto01.getAllAddress());
        assertEquals("代表者　太郎", dto01.getPoliOrgDelegate());
        assertEquals("05", dto01.getDantaiKbn());
        assertEquals("北海道架空市山麓町", dto01.getAddressPostal());
        assertEquals("3丁目6番地", dto01.getAddressBlock());
        assertEquals("四角ビル", dto01.getAddressBuilding());
        assertEquals("012", dto01.getPostal1());
        assertEquals("3456", dto01.getPostal2());
        assertEquals("098", dto01.getPhon1());
        assertEquals("7654", dto01.getPhon2());
        assertEquals("3210", dto01.getPhon3());
        assertEquals("aaaa@bbb.com", dto01.getEmail());
        assertEquals("https://bbb.com/aaaa", dto01.getMyPortalUrl());
        assertEquals("弱小SNS", dto01.getSnsServiceName());
        assertEquals("@aaa_bbb", dto01.getSnsAccount());
        assertEquals("ちゃらんぽらんせいじだんたい", dto01.getOrgNameKana());
        assertEquals("222-3333", dto01.getOrgDelegateCode());
        assertEquals("333-4444", dto01.getAccountMgrCode());
        assertEquals("会計責任者　花子", dto01.getAccountMgrName());
        assertEquals("987654", dto01.getLgCode());
        assertEquals("876", dto01.getMachiazaId());
        assertEquals("765", dto01.getBlkId());
        assertEquals("654", dto01.getRsdtId());
        assertEquals("543", dto01.getRsdt2Id());
    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_poli_org",
                "関連者政治団体標準.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
