package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * SearchPostalCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPostalCodeServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPostalCodeService searchPostalCodeService;

    @Test
    @Tag("TableTruncate")
    @Sql("sample_address_postal.sql")
    void test() {

        SearchPostalCodeCapsuleDto capsuleDto = new SearchPostalCodeCapsuleDto();
        capsuleDto.setAddressWords("大通西");
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);

        SearchPostalCodeResultDto resultDto = searchPostalCodeService.practice(capsuleDto);

        List<AddressPostalEntity> list = resultDto.getListItem();
        assertEquals(2, list.size());

        AddressPostalEntity entity0 = list.get(0);
        assertEquals("0600042", entity0.getPostal1());
        AddressPostalEntity entity1 = list.get(1);
        assertEquals("0640820", entity1.getPostal1());
    }

}
