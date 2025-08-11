package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_master_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.SearchWkTbPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_min.SearchWkTblMinCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchBulkMasterMinCorpService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchBulkMasterMinCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchBulkMasterMinCorpService searchBulkMasterMinCorpService;

    @Test
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_corp_add_min.sql")
    void test() {
        SearchWkTbPagingCapsuleDto capsuleDto00 = this.createCapsuleDto();
        SearchWkTblMinCorpPagingResultDto resultDto00 = searchBulkMasterMinCorpService.practice(capsuleDto00);
        assertEquals(1, resultDto00.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto01 = this.createCapsuleDto();
        capsuleDto01.setHasHistorry(true);
        SearchWkTblMinCorpPagingResultDto resultDto01 = searchBulkMasterMinCorpService.practice(capsuleDto01);
        assertEquals(2, resultDto01.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto02 = this.createCapsuleDto();
        capsuleDto02.setHasHistorry(true);
        capsuleDto02.setHasAffectNot(true);
        SearchWkTblMinCorpPagingResultDto resultDto02 =searchBulkMasterMinCorpService.practice(capsuleDto02);
        assertEquals(3, resultDto02.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto03 = this.createCapsuleDto();
        capsuleDto03.setHasHistorry(true);
        capsuleDto03.setHasAffectNot(true);
        capsuleDto03.setHasFinished(true);
        SearchWkTblMinCorpPagingResultDto resultDto03 = searchBulkMasterMinCorpService.practice(capsuleDto03);
        assertEquals(4, resultDto03.getAllCount());

        // 検索条件変更によるページ番号初期化
        SearchWkTbPagingCapsuleDto capsuleDto04 = this.createCapsuleDto();
        capsuleDto04.setPageNumber(100);
        SearchWkTblMinCorpPagingResultDto resultDto04 = searchBulkMasterMinCorpService.practice(capsuleDto04);
        assertEquals(0, resultDto04.getPageNumber());
    }

    private SearchWkTbPagingCapsuleDto createCapsuleDto() {
        SearchWkTbPagingCapsuleDto capsuleDto = new SearchWkTbPagingCapsuleDto();
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setUserLeast(CreateLeastUserForTestUtil.practice());

        return capsuleDto;
    }
}
