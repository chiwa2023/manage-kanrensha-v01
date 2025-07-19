package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

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
 * MasterPersonAddStdCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class MasterPersonAddStdCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private MasterPersonAddStdCsvItemReader masterPersonAddStdCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution stepExecution = this.getStepExecution();
        masterPersonAddStdCsvItemReader.beforeStep(stepExecution);
        masterPersonAddStdCsvItemReader.open(stepExecution.getExecutionContext());

        PartnerPersonAddStdDto dto01 = masterPersonAddStdCsvItemReader.read();

        assertEquals("迂回献金　三郎", dto01.getPartnerName());
        assertEquals("和歌山県実在市山麓町", dto01.getAllAddress());
        assertEquals("経営者", dto01.getPersonShokugyou());
        assertEquals("和歌山県実在市山麓町", dto01.getAddressPostal());
        assertEquals("100番地2", dto01.getAddressBlock());
        assertEquals("四角アパート405", dto01.getAddressBuilding());
        assertEquals("012", dto01.getPostal1());
        assertEquals("3456", dto01.getPostal2());
        assertEquals("0123", dto01.getPhon1());
        assertEquals("4567", dto01.getPhon2());
        assertEquals("8901", dto01.getPhon3());
        assertEquals("aaaa@bcdef.net", dto01.getEmail());
        assertEquals("https://bcdef.net/index", dto01.getMyPortalUrl());
        assertEquals(true, dto01.getIsForeign());
        
        assertEquals("迂回献金", dto01.getLastName());
        assertEquals("太郎", dto01.getFirstName());
        assertEquals("ミカエル", dto01.getMiddleName());
        assertEquals("うかいけんきん", dto01.getLastNameKana());
        assertEquals("たろう", dto01.getFirstNameKana());
        assertEquals("みかえる", dto01.getMiddleNameKana());
        assertEquals("水産業", dto01.getGyoushu());
        assertEquals("団体役員", dto01.getYakushoku());
        assertEquals("正義の味方", dto01.getShokugyouUserWrite());
        assertEquals("1-22-34", dto01.getCorpNo());
        assertEquals("山形県架空市湖畔町", dto01.getCorpAddress());
        assertEquals("超元素製造組合", dto01.getCorpName());

        assertEquals("987654", dto01.getLgCode());
        assertEquals("321", dto01.getMachiazaId());
        assertEquals("210", dto01.getBlkId());
        assertEquals("109", dto01.getRsdtId());
        assertEquals("098", dto01.getRsdt2Id());


        assertNull(masterPersonAddStdCsvItemReader.read());
        fail("Not yet implemented");

    }

    private StepExecution getStepExecution() {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "batch/partner_person", "関連者個人標準.csv");
        
        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
