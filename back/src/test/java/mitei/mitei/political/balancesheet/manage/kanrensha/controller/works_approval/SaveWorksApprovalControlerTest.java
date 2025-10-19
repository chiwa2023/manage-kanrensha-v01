package mitei.mitei.political.balancesheet.manage.kanrensha.controller.works_approval; // NOPMD

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SaveWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchApprovalAddressResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.works_approval.SearchWorksApprovalCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterKanrenshaAddressBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval.SearchApprovalAddressService;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * SaveWorksApprovalControler単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SaveWorksApprovalControlerTest {
    // CHECKSTYLE:OFF MagicNymber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** 関連者個人BaseRespository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    /** 編集対象取得のための検索Service */
    @Autowired
    private SearchApprovalAddressService searchApprovalAddressService;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @WithMockUser
    @Sql({ "../../service/works_approval/SaveApprovalAddressServiceTest.sql",
            "../../service/works_approval/SearchApprovalShokugyouServiceTest.sql" })
    void test() throws Exception {

        // 全行取得
        SearchWorksApprovalCapsuleDto capsuleDtoSearch = new SearchWorksApprovalCapsuleDto();
        capsuleDtoSearch.setIsExcludeFinishedTask(false);
        capsuleDtoSearch.setAllCount(0);
        capsuleDtoSearch.setLimit(30);
        capsuleDtoSearch.setPageNumber(0);
        capsuleDtoSearch.setStartDate(LocalDate.of(1960, 2, 1));
        capsuleDtoSearch.setEndDate(LocalDate.of(2090, 6, 1));

        SearchApprovalAddressResultDto resultDtoSearch = searchApprovalAddressService.practice(capsuleDtoSearch);

        List<MasterKanrenshaAddressBaseEntity> list = resultDtoSearch.getListAddress();

        // 住所内容変更(関連者個人、企業、政治団体を変更)
        list.get(0).setAddressBlock("2-2-2");
        assertEquals(KanrenshaKbnConstants.PERSON, (short) list.get(0).getKanrenshaKbn());
        list.get(2).setAddressBlock("3-3-3");
        assertEquals(KanrenshaKbnConstants.CORP, (short) list.get(2).getKanrenshaKbn());
        list.get(4).setAddressBlock("4-4-4");
        assertEquals(KanrenshaKbnConstants.POLI_ORG, (short) list.get(4).getKanrenshaKbn());

        SaveWorksApprovalCapsuleDto capsuleDto = new SaveWorksApprovalCapsuleDto();
        capsuleDto.setListAddress(list);

        List<MasterPersonBaseEntity> listShokugyou = masterPersonBaseRepository.findAll();

        final String newGyoushu = "小売";
        listShokugyou.get(0).setGyoushu(newGyoushu);
        // 画面上は変更できない姓名に関する事項を変更しても変更対象ではない
        listShokugyou.get(1).setFirstName("職業");

        capsuleDto.setListShokugyou(listShokugyou);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/works-approval/save";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
