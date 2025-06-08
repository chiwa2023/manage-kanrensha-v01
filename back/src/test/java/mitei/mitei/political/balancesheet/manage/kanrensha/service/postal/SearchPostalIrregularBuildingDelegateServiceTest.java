package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalIllegularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SearchPostalIllegularResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SearchPostalIrregularBuildingDelegateService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPostalIrregularBuildingDelegateServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPostalIrregularBuildingDelegateService searchPostalIrregularBuildingDelegateService;

    @Test
    @Transactional
    @Sql("sample_postal_builiding.sql")
    void test() {

        SearchPostalIllegularCapsuleDto capsuleDto = new SearchPostalIllegularCapsuleDto();

        SearchPostalIllegularResultDto resultDto = searchPostalIrregularBuildingDelegateService.practice(capsuleDto);

        List<AddressPostalIrregularEntity> list = resultDto.getListItem();
        AddressPostalIrregularEntity entityFirst = list.getFirst();
        AddressPostalIrregularEntity entityLast = list.getLast();
        assertEquals(70, list.size());
        assertEquals("中央ＳＳ３０住友生命仙台中央ビル（地階・階層不明）", entityFirst.getAddressOrg());
        assertEquals("城見松下ＩＭＰビル（地階・階層不明）", entityLast.getAddressOrg());
    }

}
