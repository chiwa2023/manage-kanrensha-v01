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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * DumpMasterPersonProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DumpMasterPersonProcessorTest {

    /** テスト対象 */
    @Autowired
    private DumpMasterPersonProcessor dumpMasterPersonProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterPersonEntity entity = new MasterPersonEntity();
        entity.setPersonKanrenshaCode("123-4567");
        entity.setPartnerName("迂回献金　太郎");
        entity.setAllAddress("宮崎県実在市山麓町");
        entity.setPersonShokugyou("医師");

        MasterPersonDto dto = dumpMasterPersonProcessor.process(entity);

        assertEquals(entity.getPersonKanrenshaCode(), dto.getPersonKanrenshaCode());
        assertEquals(entity.getPartnerName(), dto.getPartnerName());
        assertEquals(entity.getAllAddress(), dto.getAllAddress());
        assertEquals(entity.getPersonShokugyou(), dto.getPersonShokugyou());

    }

}
