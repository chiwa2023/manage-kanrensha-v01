package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchLocalGovernmentResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * SearchLocalGovService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchLocalGovServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchLocalGovService searchLocalGovService;

    @Test
    @Tag("TableTruncate")
    void test() {

        // メッセージを出す都合で0件の場合は失敗扱い
        SearchLocalGovernmentCapsuleDto capsuleDto0 = new SearchLocalGovernmentCapsuleDto();
        capsuleDto0.setLimit(30);
        capsuleDto0.setPageNumber(0);
        capsuleDto0.setAddressWords("ほにゃらら");
        SearchLocalGovernmentResultDto resultDto0 = searchLocalGovService.practice(capsuleDto0);
        assertTrue(resultDto0.getIsFailure());

        // ページングが発生するくらい該当データがある
        SearchLocalGovernmentCapsuleDto capsuleDto1 = new SearchLocalGovernmentCapsuleDto();
        capsuleDto1.setLimit(30);
        capsuleDto1.setPageNumber(0);
        capsuleDto1.setAddressWords("中");
        SearchLocalGovernmentResultDto resultDto1 = searchLocalGovService.practice(capsuleDto1);

        assertEquals(70, resultDto1.getAllCount());
        assertEquals(capsuleDto1.getLimit(), resultDto1.getLimit());
        assertEquals(capsuleDto1.getPageNumber(), resultDto1.getPageNumber());

        List<AddressAllCityEntity> list1 = resultDto1.getListAllCity();
        assertEquals(capsuleDto1.getLimit(), list1.size());

        AddressAllCityEntity entity00 = list1.get(0);
        assertEquals("北海道札幌市中央区", entity00.getAddressName());

        AddressAllCityEntity entity29 = list1.get(29);
        assertEquals("神奈川県足柄上郡中井町", entity29.getAddressName());
    }

}
