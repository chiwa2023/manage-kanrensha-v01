package mitei.mitei.political.balancesheet.manage.kanrensha.batch.dump;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;

/**
 * DumpMasterCorporationProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DumpMasterCorporationProcessorTest {

    /** テスト対象 */
    @Autowired
    private DumpMasterCorporationProcessor dumpMasterCorporationProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterCorporationEntity entity = new MasterCorporationEntity();
        entity.setCorpKanrenshaCode("123-4567");
        entity.setHoujinNo("987-6543");
        entity.setPartnerName("超元素製造組合");
        entity.setAllAddress("和歌山県架空市山麓町");
        entity.setCorpDelegate("組合長　花子");

        MasterCorporationDto dto = dumpMasterCorporationProcessor.process(entity);

        assertEquals(entity.getCorpKanrenshaCode(), dto.getCorpKanrenshaCode());
        assertEquals(entity.getHoujinNo(), dto.getHoujinNo());
        assertEquals(entity.getPartnerName(), dto.getPartnerName());
        assertEquals(entity.getAllAddress(), dto.getAllAddress());
        assertEquals(entity.getCorpDelegate(), dto.getCorpDelegate());

    }

}
