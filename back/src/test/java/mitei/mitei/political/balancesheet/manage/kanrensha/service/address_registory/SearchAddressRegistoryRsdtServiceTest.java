package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SearchAddressRegistoryResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * SearchAddressRegistoryRsdtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchAddressRegistoryRsdtServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchAddressRegistoryRsdtService searchAddressRegistoryRsdtService;

    @Test
    @Tag("TableTruncate")
    void test() {

        SearchAddressRegistoryCapsuleDto capsuleDto = new SearchAddressRegistoryCapsuleDto();
        capsuleDto.setLgCode("011029");
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);

        SearchAddressRegistoryResultDto resultDto = searchAddressRegistoryRsdtService.practice(capsuleDto);

        assertEquals(174272, resultDto.getAllCount());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(capsuleDto.getPageNumber(), resultDto.getPageNumber());

        List<AddressRsdtTemplateEntity> list = resultDto.getListRsdt();
        assertEquals(capsuleDto.getLimit(), list.size());

        // 札幌市北区あいの里一条三丁目1番地1号
        AddressRsdtTemplateEntity entity00 = list.get(0);
        assertEquals("札幌市北区あいの里一条三丁目1番地1号", entity00.getAddressBlock());

        AddressRsdtTemplateEntity entity29 = list.get(29);
        assertEquals("札幌市北区あいの里一条三丁目3番地12号", entity29.getAddressBlock());

        // 基本的にはないが存在しない自治体コードを指定された場合は失敗フラグを立てる
        SearchAddressRegistoryCapsuleDto capsuleDto1 = new SearchAddressRegistoryCapsuleDto();
        capsuleDto1.setLgCode("99999");
        capsuleDto1.setLimit(30);
        capsuleDto1.setPageNumber(0);

        SearchAddressRegistoryResultDto resultDto1 = searchAddressRegistoryRsdtService.practice(capsuleDto1);
        assertTrue(resultDto1.getIsFailure());

        // 0件の場合もメッセージを出す都合上失敗とする(011002)
        SearchAddressRegistoryCapsuleDto capsuleDto2 = new SearchAddressRegistoryCapsuleDto();
        capsuleDto2.setLgCode("011002");
        capsuleDto2.setLimit(30);
        capsuleDto2.setPageNumber(0);

        SearchAddressRegistoryResultDto resultDto2 = searchAddressRegistoryRsdtService.practice(capsuleDto2);
        assertTrue(resultDto2.getIsFailure());

    }

}
