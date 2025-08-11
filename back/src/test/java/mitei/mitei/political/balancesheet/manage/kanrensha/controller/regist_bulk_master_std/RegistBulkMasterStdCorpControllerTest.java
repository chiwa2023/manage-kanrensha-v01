package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_bulk_master_std;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_std.UpdateWkTblStdCorpCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblMasterCorpEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblMasterCorpRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * RegistBulkMasterStdCorpController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistBulkMasterStdCorpControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblMasterCorpRepository wkTblMasterCorpRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("../../service/regist_bulk_master_std/sample_wk_tbl_master_corp.sql")
    @Transactional
    void test() throws Exception {

        UpdateWkTblStdCorpCapsuleDto capsuleDto01 = new UpdateWkTblStdCorpCapsuleDto();
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblMasterCorpEntity entityInput01 = wkTblMasterCorpRepository.findById(412).get();
        WkTblMasterCorpEntity entityBase = new WkTblMasterCorpEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setAddressBlock("山ビル2F");
        entityBase.setPartnerName("");
        capsuleDto01.setWkTblMasterCorpEntity(entityBase);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/regist-bulk-master-std/update-corp";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto01)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
