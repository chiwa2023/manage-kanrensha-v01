package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * ChoicePostalCodeIrregularItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class ChoicePostalCodeIrregularItemReaderTest {

    /** テスト対象 */
    @Autowired
    private ChoicePostalCodeIrregularItemReader choicePostalCodeIrregularItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        choicePostalCodeIrregularItemReader.beforeStep(execution);
        choicePostalCodeIrregularItemReader.open(execution.getExecutionContext());

        choicePostalCodeIrregularItemReader.setPageSize(2);

        AddressPostalIrregularEntity entity00 = choicePostalCodeIrregularItemReader.read();

        assertEquals("011011", entity00.getLgCode());
        assertEquals("0600042", entity00.getPostal1());
        assertEquals("0042", entity00.getPostal2());
        assertEquals("大通西（１〜１９丁目）", entity00.getAddressOrg());
        assertEquals("札幌市中央区大通西", entity00.getAddressName());
        assertEquals("", entity00.getAddressPostal());
        assertEquals("", entity00.getAddressBlock());
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
