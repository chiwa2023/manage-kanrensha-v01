package mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;

/**
 * SearchApprovalAddressService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveApprovalAddressServiceTest.sql")
class SearchApprovalAddressServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

    @Test
    @Tag("TableTruncate")
    void testPlusAccept() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(false);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(1960, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2090, 6, 1));

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(6, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<MasterKanrenshaAddressBaseEntity> list = resultDto.getListAddress();
        assertEquals(101, list.get(0).getKanrenshaAddressId());
        assertEquals(102, list.get(1).getKanrenshaAddressId());
        assertEquals(2201, list.get(2).getKanrenshaAddressId());
        assertEquals(2202, list.get(3).getKanrenshaAddressId());
        assertEquals(701, list.get(4).getKanrenshaAddressId());
        assertEquals(702, list.get(5).getKanrenshaAddressId());
    }

    @Test
    @Tag("TableTruncate")
    void testWorks() throws Exception {

        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(true);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(1960, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2090, 6, 1));

        SearchApprovalAddressResultDto resultDto = searchApprovalAddressService.practice(capsuleDto);

        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(3, resultDto.getAllCount());
        assertEquals(0, resultDto.getPageNumber());

        List<MasterKanrenshaAddressBaseEntity> list = resultDto.getListAddress();
        assertEquals(101, list.get(0).getKanrenshaAddressId());
        assertEquals(2202, list.get(1).getKanrenshaAddressId());
        assertEquals(701, list.get(2).getKanrenshaAddressId());
    }
}
