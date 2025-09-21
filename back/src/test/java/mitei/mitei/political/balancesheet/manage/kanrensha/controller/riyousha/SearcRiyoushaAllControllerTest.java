package mitei.mitei.political.balancesheet.manage.kanrensha.controller.riyousha;

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

import jakarta.transaction.Transactional;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * SearcRiyoushaAllController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "../../service/riyousha/GetRiyoushaComradeDtoServiceTest.sql",
        "../../service/riyousha/SaveRiyoushaAdminDtoServiceTest.sql",
        "../../service/riyousha/SaveRiyoushaManagerDtoServiceTest.sql" })
class SearcRiyoushaAllControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @WithMockUser
    void test() throws Exception {

        SearchRiyoushaCapsuleDto capsuleDto = new SearchRiyoushaCapsuleDto();
        capsuleDto.setIsComradeSearch(true);
        capsuleDto.setIsManagerSearch(true);
        capsuleDto.setIsAdminSearch(true);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/user-riyousha/save-manager";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
