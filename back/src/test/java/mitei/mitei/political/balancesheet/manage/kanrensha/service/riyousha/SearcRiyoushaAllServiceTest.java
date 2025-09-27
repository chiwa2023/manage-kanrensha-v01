package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SearchRiyoushaResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;

/**
 * SearcRiyoushaAllService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql({ "GetRiyoushaComradeDtoServiceTest.sql", "SaveRiyoushaAdminDtoServiceTest.sql",
        "SaveRiyoushaManagerDtoServiceTest.sql" })
class SearcRiyoushaAllServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SearcRiyoushaAllService searcRiyoushaAllService;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        SearchRiyoushaCapsuleDto capsuleDto = new SearchRiyoushaCapsuleDto();
        capsuleDto.setIsComradeSearch(true);
        capsuleDto.setIsManagerSearch(true);
        capsuleDto.setIsAdminSearch(true);

        SearchRiyoushaResultDto resultDto = searcRiyoushaAllService.practice(capsuleDto);

        List<RiyoushaComradeEntity> listComrade = resultDto.getListComrade();
        assertEquals(1, listComrade.size());
        assertEquals(467, listComrade.get(0).getRiyoushaComradeId());

        List<RiyoushaManagerEntity> listManager = resultDto.getListManager();
        assertEquals(1, listManager.size());
        assertEquals(467, listManager.get(0).getRiyoushaManagerId());

        List<RiyoushaAdminEntity> listAdmin = resultDto.getListAdmin();
        assertEquals(1, listAdmin.size());
        assertEquals(467, listAdmin.get(0).getRiyoushaAdminId());

    }

}
