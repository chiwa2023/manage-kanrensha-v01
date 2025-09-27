package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_by_xml;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml.RetryWktblBatchCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * RetryBatchAddByXmlController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
//すべてのマスタと履歴を削除してバッティングしないようにする
@Sql({ 
    "../../service/regist_by_xml/sample_wk_tbl_partner_corp_add_min.sql",
    "../../service/regist_by_xml/sample_wk_tbl_partner_person_add_min.sql",
    "../../service/regist_by_xml/sample_wk_tbl_partner_poli_org_add_min.sql",
        "../../service/regist_by_xml/delete_history_corp01.sql",
        "../../service/regist_by_xml/delete_history_person01.sql", "../../service/regist_by_xml/delete_master_corp.sql",
        "../../service/regist_by_xml/delete_master_person.sql",
        "../../service/regist_by_xml/delete_history_poli_org01.sql",
        "../../service/regist_by_xml/delete_master_political_organization.sql" })
class RetryBatchAddByXmlControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        RetryWktblBatchCapsuleDto capsuleDto = new RetryWktblBatchCapsuleDto();
        capsuleDto.setUserDto(CreateLeastUserForTestUtil.practice());
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/regist-by-xml/retry";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
