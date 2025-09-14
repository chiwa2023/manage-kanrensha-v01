package mitei.mitei.political.balancesheet.manage.kanrensha.controller.kanrensha;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * GetKanrenshaAllByUserController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class GetKanrenshaAllByUserControllerTest {


    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @WithMockUser
    @Sql("../../service/kanrensha/GetKanrenshaAllByUserServiceTest.sql")
    void test()throws Exception {
        
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        userDto.getListRoles().add(UserRoleConstants.ROLE_PARTNER_PERSON);

        FrameworkCapsuleDto capsuleDto = new FrameworkCapsuleDto();
        capsuleDto.setUserPersonLeastDto(userDto);
        
        
        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/user-kanrensha/get-by-user";

        // サーバステータスがOK(200)※コードは一致していないが、特にサーバステータスｊは変えていないので・・・
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
