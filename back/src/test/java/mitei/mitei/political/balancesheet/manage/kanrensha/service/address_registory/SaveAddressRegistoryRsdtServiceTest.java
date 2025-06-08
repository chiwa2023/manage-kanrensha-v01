package mitei.mitei.political.balancesheet.manage.kanrensha.service.address_registory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkMessageAndResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory.SaveAddressRegistoryCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * SaveAddressRegistoryRsdtService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SaveAddressRegistoryRsdtServiceTest {

    /** テスト対象 */
    @Autowired
    private SaveAddressRegistoryRsdtService saveAddressRegistoryRsdtService;

    /** EntityManager */
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Sql("delete_011002.sql")
    @SuppressWarnings("unchecked")
    void testInsert() {

        // 011002は札幌市・・・アドレス・ベース・レジストリ住居では細分化した区に
        // データが存在し不使用なのでテストでテーブルを借りる
        String lgCode = "011002";

        AddressRsdtTemplateEntity entityEdit = new AddressRsdtTemplateEntity();
        entityEdit.setAddressRsdtId(0); // auto_increment明記
        entityEdit.setAddressBlock("山形県実在市架空町145番地");
        entityEdit.setAddressBuilding("四角アパート302号室");
        entityEdit.setEffectDate(LocalDate.of(2022, 12, 5));
        entityEdit.setLgCode(lgCode);
        entityEdit.setMachiazaId("123");
        entityEdit.setParcelRsdtId("456");
        entityEdit.setPostalCode("789");

        SaveAddressRegistoryCapsuleDto capsuleDto = new SaveAddressRegistoryCapsuleDto();
        capsuleDto.setAddressRsdtTemplateEntity(entityEdit);

        FrameworkMessageAndResultDto resultDto = saveAddressRegistoryRsdtService.practice(capsuleDto);
        assertFalse(resultDto.getIsFailure());

        Query query = entityManager.createNativeQuery("SELECT * FROM address_rsdt_" + lgCode,
                AddressRsdtTemplateEntity.class);
        
        List<AddressRsdtTemplateEntity> list = (List<AddressRsdtTemplateEntity>) query.getResultList();
        assertEquals(1, list.size());

        AddressRsdtTemplateEntity entityAns = list.get(0);
        assertEquals(entityEdit.getAddressBlock(), entityAns.getAddressBlock());
        assertEquals(entityEdit.getAddressBuilding(), entityAns.getAddressBuilding());
        assertEquals(entityEdit.getEffectDate(), entityAns.getEffectDate());
        assertEquals(entityEdit.getLgCode(), entityAns.getLgCode());
        assertEquals(entityEdit.getMachiazaId(), entityAns.getMachiazaId());
        assertEquals(entityEdit.getParcelRsdtId(), entityAns.getParcelRsdtId());
        assertEquals(entityEdit.getPostalCode(), entityAns.getPostalCode());
    }

    @Test
    void testUpdate() {

        // TODO 更新処理はテーブル構造が決まり次第作成する

        fail("Not yet implemented");
    }

}
