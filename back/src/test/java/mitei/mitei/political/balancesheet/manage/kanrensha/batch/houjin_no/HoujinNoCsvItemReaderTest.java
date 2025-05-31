package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
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
 * HoujinNoCsvItemReader単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class HoujinNoCsvItemReaderTest {

    /** テスト対象 */
    @Autowired
    private HoujinNoCsvItemReader houjinNoCsvItemReader;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        StepExecution execution = getStepExecution();
        houjinNoCsvItemReader.beforeStep(execution);
        houjinNoCsvItemReader.open(execution.getExecutionContext());

        // ﻿1,1010001007380,01,1,2025-04-14,2015-10-05,"株式会社藤原硝子店",,301,"東京都","文京区","本郷３丁目８番５号",,13,105,1130033,,,,,,,2015-10-05,1,,,,,"フジワラガラステン",0

        HoujinNoCsvDto csvDto0 = houjinNoCsvItemReader.read();

        assertEquals("1", csvDto0.getSequenceNumber());
        assertEquals("1010001007380", csvDto0.getCorporateNumber());
        assertEquals("01", csvDto0.getProcess());
        assertEquals("1", csvDto0.getCorrect());
        assertEquals("2025-04-14", csvDto0.getUpdateDate());
        assertEquals("2015-10-05", csvDto0.getChangeDate());
        assertEquals("株式会社藤原硝子店", csvDto0.getName());
        assertEquals("", csvDto0.getNameImageId());
        assertEquals("301", csvDto0.getKind());
        assertEquals("東京都", csvDto0.getPrefectureName());
        assertEquals("文京区", csvDto0.getCityName());
        assertEquals("本郷３丁目８番５号", csvDto0.getStreetNumber());
        assertEquals("", csvDto0.getAddressImageId());
        assertEquals("13", csvDto0.getPrefectureCode());
        assertEquals("105", csvDto0.getCityCode());
        assertEquals("1130033", csvDto0.getPostCode());
        assertEquals("", csvDto0.getAddressOutside());
        assertEquals("", csvDto0.getAddressOutsideImageId());
        assertEquals("", csvDto0.getCloseDate());
        assertEquals("", csvDto0.getCloseCause());
        assertEquals("", csvDto0.getSuccessorCorporateNumber());
        assertEquals("", csvDto0.getChangeCause());
        assertEquals("2015-10-05", csvDto0.getAssignmentDate());
        assertEquals("1", csvDto0.getLatest());
        assertEquals("", csvDto0.getEnName());
        assertEquals("", csvDto0.getEnPrefectureName());
        assertEquals("", csvDto0.getEnCityName());
        assertEquals("", csvDto0.getEnAddressOutside());
        assertEquals("フジワラガラステン", csvDto0.getFurigana());
        assertEquals("0", csvDto0.getHihyoji());

        HoujinNoCsvDto csvDto2 = houjinNoCsvItemReader.read();
        assertEquals("2", csvDto2.getSequenceNumber());

        HoujinNoCsvDto csvDto3 = houjinNoCsvItemReader.read();
        assertEquals("3", csvDto3.getSequenceNumber());

        HoujinNoCsvDto csvDto4 = houjinNoCsvItemReader.read();
        assertEquals("4", csvDto4.getSequenceNumber());

        HoujinNoCsvDto csvDto5 = houjinNoCsvItemReader.read();
        assertEquals("5", csvDto5.getSequenceNumber());

        HoujinNoCsvDto csvDto6 = houjinNoCsvItemReader.read();
        assertEquals(null, csvDto6);
    }

    private StepExecution getStepExecution() throws URISyntaxException, IOException {

        Path path = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/batch/houjin_no/",
                "diff_20250414.csv");

        JobParameters jobParameters = new JobParametersBuilder() // NOPMD
                .addString("readFilePath", path.toString()).toJobParameters();

        // 起動引数付きのStepExecutionを作成
        return MetaDataInstanceFactory.createStepExecution(jobParameters);
    }

}
