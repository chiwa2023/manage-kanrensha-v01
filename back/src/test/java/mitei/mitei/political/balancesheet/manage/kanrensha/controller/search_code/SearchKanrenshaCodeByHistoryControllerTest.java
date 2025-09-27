package mitei.mitei.political.balancesheet.manage.kanrensha.controller.search_code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.KanrenshaKbnConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.partner.SearchPartnerHistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * SearchKanrenshaCodeByHistoryController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Transactional
@Sql({ "../../service/kanrensha/sample_partner_person_history.sql",
        "../../service/kanrensha/sample_partner_corp_history.sql",
        "../../service/kanrensha/sample_partner_poli_org_history.sql" })
class SearchKanrenshaCodeByHistoryControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testAll() throws Exception {

        // 全条件設定
        SearchPartnerHistoryCapsuleDto capsuleDto = new SearchPartnerHistoryCapsuleDto();
        capsuleDto.setPartnerName("迂回献金　太郎");
        capsuleDto.setAllAddress("北海道架空市山麓町");
        capsuleDto.setRecognizedKey("経営者");
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.PERSON);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/kanrenssha-list/search";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void testMin() throws Exception {

        // 名前住所条件設定
        SearchPartnerHistoryCapsuleDto capsuleDto = new SearchPartnerHistoryCapsuleDto();
        capsuleDto.setPartnerName("ぼったくり企業");
        capsuleDto.setAllAddress("岩手県架空市山麓町");
        capsuleDto.setKanrenshaKbn(KanrenshaKbnConstants.CORP);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/kanrenssha-list/search";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }
}
