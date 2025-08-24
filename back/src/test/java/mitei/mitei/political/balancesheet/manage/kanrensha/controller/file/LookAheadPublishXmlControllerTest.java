package mitei.mitei.political.balancesheet.manage.kanrensha.controller.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.common.constants.GetCurrentResourcePath;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadContentCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.UploadFileDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * LookAheadPublishXmlController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class LookAheadPublishXmlControllerTest {

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        final String fileName = "2022_ホリエモン新党_SYUUSI.xml";
        UploadFileDto uploadFileDto = new UploadFileDto();
        uploadFileDto.setFileName(fileName);
        // uploadFileDto.setCharset("UTF-8");

        // ファイルをバイナリで呼び出し
        Path pathFile = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "/file", fileName);
        byte[] bytes = Files.readAllBytes(pathFile);
        uploadFileDto.setFileContent(Base64.getEncoder().encodeToString(bytes));

        UploadContentCapsuleDto capsuleDto = new UploadContentCapsuleDto();
        capsuleDto.setUploadFileDto(uploadFileDto);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/xml/look-ahead";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
