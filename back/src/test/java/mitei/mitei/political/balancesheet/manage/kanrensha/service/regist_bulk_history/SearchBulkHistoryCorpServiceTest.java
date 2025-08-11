package mitei.mitei.political.balancesheet.manage.kanrensha.service.regist_bulk_history;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_history.SearchWkTblHistoryCorpPagingResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SearchBulkHistoryCorpService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchBulkHistoryCorpServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchBulkHistoryCorpService searchBulkHistoryCorpService;

    @Test
    @Tag("TableTruncate")
    @Sql("sample_wk_tbl_partner_corp_history.sql")
    void test() {
        SearchWkTbPagingCapsuleDto capsuleDto00 = this.createCapsuleDto();
        SearchWkTblHistoryCorpPagingResultDto resultDto00 = searchBulkHistoryCorpService.practice(capsuleDto00);
        assertEquals(1, resultDto00.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto01 = this.createCapsuleDto();
        capsuleDto01.setHasHistorry(true);
        SearchWkTblHistoryCorpPagingResultDto resultDto01 = searchBulkHistoryCorpService.practice(capsuleDto01);
        assertEquals(2, resultDto01.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto02 = this.createCapsuleDto();
        capsuleDto02.setHasHistorry(true);
        capsuleDto02.setHasAffectNot(true);
        SearchWkTblHistoryCorpPagingResultDto resultDto02 = searchBulkHistoryCorpService.practice(capsuleDto02);
        assertEquals(3, resultDto02.getAllCount());

        SearchWkTbPagingCapsuleDto capsuleDto03 = this.createCapsuleDto();
        capsuleDto03.setHasHistorry(true);
        capsuleDto03.setHasAffectNot(true);
        capsuleDto03.setHasFinished(true);
        SearchWkTblHistoryCorpPagingResultDto resultDto03 = searchBulkHistoryCorpService.practice(capsuleDto03);
        assertEquals(4, resultDto03.getAllCount());

        // 検索条件変更によるページ番号初期化
        SearchWkTbPagingCapsuleDto capsuleDto04 = this.createCapsuleDto();
        capsuleDto04.setPageNumber(100);
        SearchWkTblHistoryCorpPagingResultDto resultDto04 = searchBulkHistoryCorpService.practice(capsuleDto04);
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
