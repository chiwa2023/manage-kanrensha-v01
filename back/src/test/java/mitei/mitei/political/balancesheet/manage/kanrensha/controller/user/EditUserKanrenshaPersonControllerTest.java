package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * EditUserKanrenshaPersonController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class EditUserKanrenshaPersonControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @WithMockUser
    @Sql("../../service/kanrensha/EditKanrenshaPersonServiceTest.sql")
    void test() throws Exception {

        KanrenshaPersonDto dto = new KanrenshaPersonDto();

        // Set IDs for all entities
        dto.setMasterId(901);
        dto.setAccessId(902);
        dto.setAddressId(903);
        dto.setBaseId(904);
        dto.setPropertyId(905);

        // Set values that differ from the SQL data to trigger updates
        dto.getInputPersonNameDto().setAllName("New Name");
        dto.getInputAccessDto().setEmail("new.email@example.com");
        dto.getInputAddressDto().setAddressBuilding("New Building");
        dto.getInputPersonNameDto().setLastName("New Last Name");
        // dto.setInputShokugyouDto(new InputShokugyouDto());
        dto.setIsForeign(true);

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(dto);
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/user-kanrensha/edit-person";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
