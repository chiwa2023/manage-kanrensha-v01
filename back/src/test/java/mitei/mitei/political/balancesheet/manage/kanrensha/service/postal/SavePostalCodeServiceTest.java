package mitei.mitei.political.balancesheet.manage.kanrensha.service.postal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;

/**
 * SavePostalCodeService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SavePostalCodeServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SavePostalCodeService savePostalCodeService;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    @Test
    @Sql("delete_address_postal.sql")
    @Transactional
    void testInsert() {

        AddressPostalEntity entity = new AddressPostalEntity();
        entity.setAddressPostalId(0);
        entity.setPostal1("1234567");
        entity.setPostal2("4567");
        entity.setLgCode("98765");
        entity.setAddressOrg("山麓町１番地～50番地");
        entity.setAddressName("架空市山麓町１番地～50番地");
        entity.setIsGyoseikuData(false);

        SavePostalCodeCapsuleDto capsuleDto = new SavePostalCodeCapsuleDto();
        capsuleDto.setAddressPostalEntity(entity);

        FrameworkMessageAndResultDto resultDto = savePostalCodeService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure()); // トラブルがなければ保存失敗にならない

        List<AddressPostalEntity> list = addressPostalRepository.findAll();
        assertEquals(1, list.size()); // 空のところに1行追加したので1件

        AddressPostalEntity entityAns = list.get(0);

        assertEquals(entity.getPostal1(), entityAns.getPostal1());
        assertEquals(entity.getPostal2(), entityAns.getPostal2());
        assertEquals(entity.getLgCode(), entityAns.getLgCode());
        assertEquals(entity.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(entity.getAddressName(), entityAns.getAddressName());
        assertEquals(entity.getIsGyoseikuData(), entityAns.getIsGyoseikuData());

    }

    @Test
    @Sql("sample_address_postal.sql")
    @Transactional
    void testUpdate() {
        Integer addressId = 6;

        AddressPostalEntity entityBase = addressPostalRepository.findById(addressId).get();

        AddressPostalEntity entityEdit = new AddressPostalEntity();
        BeanUtils.copyProperties(entityBase, entityEdit);

        entityEdit.setPostal1("1234567");
        entityEdit.setPostal2("4567");
        entityEdit.setLgCode("98765");
        entityEdit.setAddressOrg("山麓町１番地～50番地");
        entityEdit.setAddressName("架空市山麓町１番地～50番地");
        entityEdit.setIsGyoseikuData(false);

        SavePostalCodeCapsuleDto capsuleDto = new SavePostalCodeCapsuleDto();
        capsuleDto.setAddressPostalEntity(entityEdit);

        FrameworkMessageAndResultDto resultDto = savePostalCodeService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure()); // トラブルがなければ保存失敗にならない

        // 処理後に取得しなおし
        AddressPostalEntity entityAns = addressPostalRepository.findById(addressId).get();

        assertEquals(entityEdit.getAddressPostalId(), entityAns.getAddressPostalId());
        assertEquals(entityEdit.getPostal1(), entityAns.getPostal1());
        assertEquals(entityEdit.getPostal2(), entityAns.getPostal2());
        assertEquals(entityEdit.getLgCode(), entityAns.getLgCode());
        assertEquals(entityEdit.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(entityEdit.getAddressName(), entityAns.getAddressName());
        assertEquals(entityEdit.getIsGyoseikuData(), entityAns.getIsGyoseikuData());
    }

}
