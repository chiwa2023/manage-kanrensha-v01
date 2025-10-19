package mitei.mitei.political.balancesheet.manage.kanrensha.controller.task_plan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Path;
import java.nio.file.Paths;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.GetCurrentResourcePath;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.task.GetTaskStackTraceCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.CopyFolderWalkTreeAllLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.file.GetAbsolutePathLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * DownloadStackTraceByTaskCodeController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class DownloadStackTraceByTaskCodeControllerTest {
    // CHECKSTYLE:OFF MagicNumber

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ファイル置き場絶対パス取得Logic */
    @Autowired
    private GetAbsolutePathLogic getAbsolutePathLogic;

    /** ファイル複写Logic */
    @Autowired
    private CopyFolderWalkTreeAllLogic copyFolderWalkTreeAllLogic;

    @Test
    @Tag("TableTruncate")
    @WithMockUser
    void test() throws Exception {

        // テストデータを複写
        Path pathSrc = Paths.get(GetCurrentResourcePath.getBackTestResourcePath(), "file", "stack_trace");
        Path pathCopy = Paths.get(getAbsolutePathLogic.getStorageFolder(), "stack_trace");
        copyFolderWalkTreeAllLogic.practice(pathSrc, pathCopy);

        GetTaskStackTraceCapsuleDto capsuleDto = new GetTaskStackTraceCapsuleDto();
        capsuleDto.setTaskYear(2025);
        capsuleDto.setTaskPlanCode(329);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/stack-trace/get-by-code";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }
}
