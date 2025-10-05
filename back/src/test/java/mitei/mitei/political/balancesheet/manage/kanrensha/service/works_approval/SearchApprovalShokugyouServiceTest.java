package mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalShokugyouResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;

/**
 * SearchApprovalShokugyouService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchApprovalShokugyouServiceTest.sql")
class SearchApprovalShokugyouServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchApprovalShokugyouService searchApprovalShokugyouService;

    @Test
    @Tag("TableTruncate")
    void testWorks() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(true);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(2025, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 6, 1));

        SearchApprovalShokugyouResultDto resultDto = searchApprovalShokugyouService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(1, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        assertEquals(301, resultDto.getListShokugyou().get(0).getMasterPersonBaseId());
    }

    @Test
    @Tag("TableTruncate")
    void testPlusAccept() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(false);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(2025, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2025, 6, 1));

        SearchApprovalShokugyouResultDto resultDto = searchApprovalShokugyouService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(2, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());
        assertEquals(301, resultDto.getListShokugyou().get(0).getMasterPersonBaseId());
        assertEquals(302, resultDto.getListShokugyou().get(1).getMasterPersonBaseId());
    }

}
