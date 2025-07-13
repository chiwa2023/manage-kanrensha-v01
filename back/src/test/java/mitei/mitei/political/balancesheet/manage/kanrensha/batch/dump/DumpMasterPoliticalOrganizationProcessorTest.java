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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;

/**
 * DumpMasterPoliticalOrganizationProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DumpMasterPoliticalOrganizationProcessorTest {

    /** テスト対象 */
    @Autowired
    private DumpMasterPoliticalOrganizationProcessor dumpMasterPoliticalOrganizationProcessor;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        MasterPoliticalOrganizationEntity entity = new MasterPoliticalOrganizationEntity();
        entity.setPoliOrgKanrenshaCode("123-4567");
        entity.setPartnerName("ちゃらんぽらん政治団体");
        entity.setAllAddress("山形県架空市山麓町");
        entity.setPoliOrgDelegate("代表者　次郎");

        MasterPoliticalOrganizationDto dto = dumpMasterPoliticalOrganizationProcessor.process(entity);

        assertEquals(entity.getPoliOrgKanrenshaCode(), dto.getPoliOrgKanrenshaCode());
        assertEquals(entity.getPartnerName(), dto.getPartnerName());
        assertEquals(entity.getAllAddress(), dto.getAllAddress());
        assertEquals(entity.getPoliOrgDelegate(), dto.getPoliOrgDelegate());
    }

}
