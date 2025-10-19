package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * ChoiceAddressRegistoryByCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("sample_011002.sql")
class ChoiceAddressRegistoryByCodeServiceTest {

    /** テスト対象 */
    @Autowired
    private ChoiceAddressRegistoryByCodeService choiceAddressRegistoryByCodeService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        AddressRsdtTemplateEntity entitySearch = new AddressRsdtTemplateEntity();
        entitySearch.setLgCode("011002");
        entitySearch.setMachiazaId("987");
        entitySearch.setParcelRsdtId("5431");

        List<AddressRsdtTemplateEntity> list = choiceAddressRegistoryByCodeService.practice(entitySearch);

        // テストデータに適用日が2999年に設定してあるので、それ以降の日付に実施するとはテストの結果が変わる
        assertEquals(1, list.size());

        AddressRsdtTemplateEntity entity = list.get(0);

        assertEquals(entitySearch.getLgCode(), entity.getLgCode());
        assertEquals(entitySearch.getMachiazaId(), entity.getMachiazaId());
        assertEquals(entitySearch.getParcelRsdtId(), entity.getParcelRsdtId());
    }

}
