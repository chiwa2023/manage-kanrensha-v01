package mitei.mitei.political.balancesheet.manage.kanrensha.controller.regist_combine_org;

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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.wktbl_combine.UpdateWkTblCombineOrgCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerCombineOrgEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.WkTblPartnerCombineOrgRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.GetObjectMapperWithTimeModuleUtil;

/**
 * RegistCombineOrganizationController単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RegistCombineOrganizationControllerTest {
    // CHECKSTYLE:OFF

    /** MockMvc */
    @Autowired
    private MockMvc mockMvc;

    /** ワークテーブルマスタ企業／団体標準Repository */
    @Autowired
    private WkTblPartnerCombineOrgRepository wkTblPartnerCombineOrgRepository;

    @Test
    @Transactional
    @Tag("TableTruncate")
    @Sql({ "../../service/regist_combine_org/sample_wk_tbl_partner_combine_org.sql",
            "../../service/regist_combine_org/master_person.sql",
            "../../service/regist_combine_org/master_corporation.sql",
            "../../service/regist_combine_org/master_political_organization.sql" })
    void test() throws Exception {

        UpdateWkTblCombineOrgCapsuleDto capsuleDto01 = new UpdateWkTblCombineOrgCapsuleDto();
        UserPersonLeastDto userDto = CreateLeastUserForTestUtil.practice();
        capsuleDto01.setUserPersonLeastDto(userDto);
        WkTblPartnerCombineOrgEntity entityInput01 = wkTblPartnerCombineOrgRepository.findById(211).get();
        WkTblPartnerCombineOrgEntity entityBase = new WkTblPartnerCombineOrgEntity();
        BeanUtils.copyProperties(entityInput01, entityBase);
        entityBase.setOrgName("超元素製造組合");
        entityBase.setStartYear(Short.valueOf("2025"));
        entityBase.setEndYear(Short.valueOf("2023"));
        entityBase.setYearArrayText("2025");
        capsuleDto01.setWkTblPartnerCombineOrgEntity(entityBase);

        ObjectMapper objectMapper = GetObjectMapperWithTimeModuleUtil.practice();

        String path = "/regist-combine/update";

        // サーバステータスがOK(200)
        assertEquals(HttpStatus.OK.value(), mockMvc // NOPMD LawOfDemeter
                .perform(post(path).content(objectMapper.writeValueAsString(capsuleDto01)) // リクエストボディを指定
                        .contentType(MediaType.APPLICATION_JSON_VALUE)) // Content Typeを指定
                .andExpect(status().isOk()).andReturn().getResponse().getStatus());
    }

}
