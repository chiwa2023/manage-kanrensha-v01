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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SelectPostalCodeSingleAddressItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SelectPostalCodeSingleAddressItemReaderTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeSingleAddressItemReader selectPostalCodeSingleAddressItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("irregular_single_address.sql")
    void test()throws Exception {

        StepExecution execution = getStepExecution();
        selectPostalCodeSingleAddressItemReader.beforeStep(execution);
        selectPostalCodeSingleAddressItemReader.open(execution.getExecutionContext());

        AddressPostalIrregularEntity entity0 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals("八幡町（五の沢）", entity0.getAddressOrg());

        AddressPostalIrregularEntity entity1 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals("八幡町（高岡）", entity1.getAddressOrg());

        AddressPostalIrregularEntity entity2 = selectPostalCodeSingleAddressItemReader.read();
        assertEquals(null, entity2 );
    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "01").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }
}
