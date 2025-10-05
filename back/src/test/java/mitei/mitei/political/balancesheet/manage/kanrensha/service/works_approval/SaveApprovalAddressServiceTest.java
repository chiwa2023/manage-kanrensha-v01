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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveApprovalAddressService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SaveApprovalAddressServiceTest.sql")
class SaveApprovalAddressServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveApprovalAddressService saveApprovalAddressService;

    /** 編集対象取得のための検索Service */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        // 全行取得
        SearchWorksApprovalCapsuleDto capsuleDto = new SearchWorksApprovalCapsuleDto();
        capsuleDto.setIsExcludeFinishedTask(false);
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);
        capsuleDto.setStartDate(LocalDate.of(1960, 2, 1));
        capsuleDto.setEndDate(LocalDate.of(2090, 6, 1));

        SearchApprovalAddressResultDto resultDtoSearch = searchApprovalAddressService.practice(capsuleDto);

        List<MasterKanrenshaAddressBaseEntity> list = resultDtoSearch.getListAddress();

        // 住所内容変更(関連者個人、企業、政治団体を変更)
        list.get(0).setAddressBlock("2-2-2");
        assertEquals(KanrenshaKbnConstants.PERSON, (short) list.get(0).getKanrenshaKbn());
        list.get(2).setAddressBlock("3-3-3");
        assertEquals(KanrenshaKbnConstants.CORP, (short) list.get(2).getKanrenshaKbn());
        list.get(4).setAddressBlock("4-4-4");
        assertEquals(KanrenshaKbnConstants.POLI_ORG, (short) list.get(4).getKanrenshaKbn());

        int updateCount = saveApprovalAddressService.practice(list, CreateLeastUserForTestUtil.practice());
        assertEquals(3, updateCount);
    }

}
