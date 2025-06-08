package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * SavePostalIrregularBuildingAllFloorService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SavePostalIrregularBuildingAllFloorServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SavePostalIrregularBuildingAllFloorService savePostalIrregularBuildingAllFloorService;

    /** 郵便番号不規則データRepository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Transactional
    @Sql("sample_postal_builiding.sql")
    void test() {

        AddressPostalIrregularEntity entityEdit = addressPostalIrregularRepository.findById(776).get();
        final String addressPostal = "埼玉県さいたま市中央区新都心";
        final String addressBlock = "11番2";

        entityEdit.setAddressPostal(addressPostal);
        entityEdit.setAddressBlock(addressBlock);
        final String addressName = entityEdit.getAddressName();

        SavePostalIrregularCapsuleDto capsuleDto = new SavePostalIrregularCapsuleDto();
        capsuleDto.setAddressPostalIrregularEntity(entityEdit);

        FrameworkMessageAndResultDto resultDto = savePostalIrregularBuildingAllFloorService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        List<AddressPostalIrregularEntity> listAns = addressPostalIrregularRepository
                .findByAddressName(entityEdit.getAddressName());
        assertEquals(36, listAns.size());
        for (AddressPostalIrregularEntity entity : listAns) {
            assertEquals(addressName, entity.getAddressName());
            assertEquals(addressPostal, entity.getAddressPostal());
            assertEquals(addressBlock, entity.getAddressBlock());
        }
    }

}
