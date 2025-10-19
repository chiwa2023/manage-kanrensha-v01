package mitei.mitei.political.balancesheet.manage.kanrensha.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.PostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * SecurityConfigPath単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SecurityConfigPathTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostal() throws Exception {

        PostalCodeCapsuleDto capsuleDto = new PostalCodeCapsuleDto();
        capsuleDto.setPostal1("123");
        capsuleDto.setPostal2("4567");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/postal-search/postal";

        // ユーザが存在しないため401
        assertEquals(HttpStatus.UNAUTHORIZED.value(),
                mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // NOPMD
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
                        ).andExpect(status().isUnauthorized()).andReturn().getResponse().getStatus());
    }

    @Test
    @WithMockUser
    void testPostalUser() throws Exception {

        PostalCodeCapsuleDto capsuleDto = new PostalCodeCapsuleDto();
        capsuleDto.setPostal1("123");
        capsuleDto.setPostal2("4567");

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/postal-search/postal";

        // MockUserと一緒に実行しているため正常終了
        assertEquals(HttpStatus.OK.value(),
                mockMvc.perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // NOPMD
                        .contentType(MediaType.APPLICATION_JSON_VALUE) // Content Typeを指定
                        ).andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

    @Test
    void testLogin() throws Exception {

        String path = "/login";

        // 認証外のため、ボディにが引数が入っていないことによるサーバステータスBAD_REQUEST(400)
        // ログインそのものの有効性はLoginUserOperatorControllerTestで確認
        assertEquals(HttpStatus.BAD_REQUEST.value(),
                mockMvc.perform(post(path)).andExpect(status().isBadRequest()).andReturn().getResponse().getStatus()); // NOPMD
    }
    
    @Test
    @WithMockUser
    void testLogout() throws Exception {
        // MockUserを設定しているのでログイン情報がMockで存在する
        assertEquals(UsernamePasswordAuthenticationToken.class, SecurityContextHolder.getContext().getAuthentication().getClass());
        String path = "/logout";

        // logoutは自動で/に遷移しようとする
        assertEquals(HttpStatus.FOUND.value(),
                mockMvc.perform(post(path)).andExpect(status().isFound()).andReturn().getResponse().getStatus()); // NOPMD

        // ログアウト後はログイン情報を取得しようとしても存在しない
        assertEquals(null, SecurityContextHolder.getContext().getAuthentication());

    }

}
