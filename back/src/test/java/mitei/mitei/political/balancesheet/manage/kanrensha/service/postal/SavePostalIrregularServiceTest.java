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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal.SavePostalIrregularCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * SavePostalIrregularService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SavePostalIrregularServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SavePostalIrregularService savePostalIrregularService;

    /** 郵便番号不規則Repository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Sql("delete_address_postal_irregular.sql")
    @Transactional
    void testInsert() {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        entity.setAddressPostalIrregularId(0);
        entity.setLgCode("135790");
        entity.setPostal1("1234567");
        entity.setPostal2("4567");
        entity.setAddressOrg("湖畔町１～３丁目");
        entity.setAddressName("実在市湖畔町１丁目");
        entity.setAddressPostal("山形県実在市湖畔町１丁目");
        entity.setAddressBlock("５番地７");
        entity.setIsAddPostal(false);
        entity.setIsRepairRsdt(true);

        SavePostalIrregularCapsuleDto capsuleDto = new SavePostalIrregularCapsuleDto();
        capsuleDto.setAddressPostalIrregularEntity(entity);

        // 特に保存に問題は起きない場合
        FrameworkMessageAndResultDto resultDto = savePostalIrregularService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        List<AddressPostalIrregularEntity> list = addressPostalIrregularRepository.findAll();
        assertEquals(1, list.size());

        AddressPostalIrregularEntity entityAns = list.get(0);

        assertEquals(entity.getLgCode(), entityAns.getLgCode());
        assertEquals(entity.getPostal1(), entityAns.getPostal1());
        assertEquals(entity.getPostal2(), entityAns.getPostal2());
        assertEquals(entity.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(entity.getAddressName(), entityAns.getAddressName());
        assertEquals(entity.getAddressPostal(), entityAns.getAddressPostal());
        assertEquals(entity.getAddressBlock(), entityAns.getAddressBlock());
        assertEquals(entity.getIsAddPostal(), entityAns.getIsAddPostal());
        assertEquals(entity.getIsRepairRsdt(), entityAns.getIsRepairRsdt());

    }

    @Test
    @Sql("sample_address_postal_irregular.sql")
    @Transactional
    void testUpdate() {

        final int addressId = 463;
        AddressPostalIrregularEntity entityBase = addressPostalIrregularRepository.findById(addressId).get();

        AddressPostalIrregularEntity entityEdit = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(entityBase, entityEdit);

        entityEdit.setLgCode("135790");
        entityEdit.setPostal1("1234567");
        entityEdit.setPostal2("4567");
        entityEdit.setAddressOrg("湖畔町１～３丁目");
        entityEdit.setAddressName("実在市湖畔町１丁目");
        entityEdit.setAddressPostal("山形県実在市湖畔町１丁目");
        entityEdit.setAddressBlock("５番地７");
        entityEdit.setIsAddPostal(false);
        entityEdit.setIsRepairRsdt(true);

        SavePostalIrregularCapsuleDto capsuleDto = new SavePostalIrregularCapsuleDto();
        capsuleDto.setAddressPostalIrregularEntity(entityEdit);

        // 特に保存に問題は起きない場合
        FrameworkMessageAndResultDto resultDto = savePostalIrregularService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        // 処理後に取得しなおし
        AddressPostalIrregularEntity entityAns = addressPostalIrregularRepository.findById(addressId).get();

        assertEquals(entityEdit.getAddressPostalIrregularId(), entityAns.getAddressPostalIrregularId());
        assertEquals(entityEdit.getLgCode(), entityAns.getLgCode());
        assertEquals(entityEdit.getPostal1(), entityAns.getPostal1());
        assertEquals(entityEdit.getPostal2(), entityAns.getPostal2());
        assertEquals(entityEdit.getAddressOrg(), entityAns.getAddressOrg());
        assertEquals(entityEdit.getAddressName(), entityAns.getAddressName());
        assertEquals(entityEdit.getAddressPostal(), entityAns.getAddressPostal());
        assertEquals(entityEdit.getAddressBlock(), entityAns.getAddressBlock());
        assertEquals(entityEdit.getIsAddPostal(), entityAns.getIsAddPostal());
        assertEquals(entityEdit.getIsRepairRsdt(), entityAns.getIsRepairRsdt());

    }

}
