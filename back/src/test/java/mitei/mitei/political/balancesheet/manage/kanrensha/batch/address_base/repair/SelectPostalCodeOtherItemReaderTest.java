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
 * SelectPostalCodeOtherItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SelectPostalCodeOtherItemReaderTest {

    /** テスト対象 */
    @Autowired
    private SelectPostalCodeOtherItemReader selectPostalCodeOtherItemReader;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql("irregular_other.sql")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        selectPostalCodeOtherItemReader.beforeStep(execution);
        selectPostalCodeOtherItemReader.open(execution.getExecutionContext());
        
        // 常盤（その他）
        AddressPostalIrregularEntity entity0 = selectPostalCodeOtherItemReader.read();
        assertEquals("常盤（その他）", entity0.getAddressOrg());

        // 藤野（その他）
        AddressPostalIrregularEntity entity1 = selectPostalCodeOtherItemReader.read();
        assertEquals("藤野（その他）", entity1.getAddressOrg());

        // 真駒内（その他）
        AddressPostalIrregularEntity entity2 = selectPostalCodeOtherItemReader.read();
        assertEquals("真駒内（その他）", entity2.getAddressOrg());

        // 簾舞（その他）
        AddressPostalIrregularEntity entity3 = selectPostalCodeOtherItemReader.read();
        assertEquals("簾舞（その他）", entity3.getAddressOrg());

        // 5件目はなし
        AddressPostalIrregularEntity entity4 = selectPostalCodeOtherItemReader.read();
        assertEquals(null, entity4);

    }

    private StepExecution getStepExecution() {

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("lgCodePref", "13").toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
