package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.GetDetailPostalIllegularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.GetDetailPostalIllegularResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * SearchPostalIrregularBuildingAllFloorService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchPostalIrregularBuildingAllFloorServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchPostalIrregularBuildingAllFloorService searchPostalIrregularBuildingAllFloorService;

    @Test
    @Transactional
    @Sql("sample_postal_builiding.sql")
    void test() {

        GetDetailPostalIllegularCapsuleDto capsuleDto = new GetDetailPostalIllegularCapsuleDto();
        capsuleDto.setAddressWords("さいたま市中央区新都心明治安田生命さいたま新都心ビル");

        GetDetailPostalIllegularResultDto resultDto = searchPostalIrregularBuildingAllFloorService.practice(capsuleDto);

        List<AddressPostalIrregularEntity> list = resultDto.getListIrregular();
        assertEquals(36, list.size()); // 35階建て＋地階Or不明

        AddressPostalIrregularEntity entity = list.get(3); // 4番目= 3階
        assertEquals("3306003", entity.getPostal1());
        assertEquals("新都心明治安田生命さいたま新都心ビル（３階）", entity.getAddressOrg());

    }

}
