package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressRsdtTemplateRepository;

/**
 * RsdtAddressItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class RsdtAddressItemWriterTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private RsdtAddressItemWriter rsdtAddressItemWriter;

    /** 住居データRepository */
    @Autowired
    private AddressRsdtTemplateRepository addressRsdtTemplateRepository;
    
    
    @Test
    @Tag("TableTruncate")
    @Sql("delete_address_rsdt_template.sql")
    void test() throws Exception {

        AddressRsdtBaseEntity baseEntity = new AddressRsdtBaseEntity();

        baseEntity.setLgCode("template");
        baseEntity.setPostalCode(""); // 以降の処理で追加
        baseEntity.setMachiazaId("0013018");
        baseEntity.setParcelRsdtId("017");
        baseEntity.setEffectDate(LocalDate.of(2022, 11, 19));
        baseEntity.setAddressBlock("札幌市豊平区月寒東五条十八丁目aaa17番地11号");
        baseEntity.setAddressBuilding("99号室");

        // 地番CSV内で住居フラグオンのため、登録しないために空Entityを返してきた想定
        AddressRsdtBaseEntity escapeEntity = new AddressRsdtBaseEntity();
        
        List<AddressRsdtBaseEntity> list = new ArrayList<>();
        list.add(baseEntity);
        list.add(escapeEntity);

        // Chunkを作成してセット
        Chunk<? extends AddressRsdtBaseEntity> items = new Chunk<>(list);
        rsdtAddressItemWriter.write(items);

        List<AddressRsdtTemplateEntity> listAnswer = addressRsdtTemplateRepository.findAll();
        assertEquals(1, listAnswer.size());
        
        AddressRsdtTemplateEntity entityAnswer = listAnswer.get(0);
      
        assertEquals(baseEntity.getLgCode(), entityAnswer.getLgCode());
        assertEquals(baseEntity.getPostalCode(), entityAnswer.getPostalCode()); // 以降の処理で追加
        assertEquals(baseEntity.getMachiazaId(), entityAnswer.getMachiazaId());
        assertEquals(baseEntity.getParcelRsdtId(), entityAnswer.getParcelRsdtId());
        assertEquals(baseEntity.getEffectDate(), entityAnswer.getEffectDate());
        assertEquals(baseEntity.getAddressBlock(), entityAnswer.getAddressBlock());
        assertEquals(baseEntity.getAddressBuilding(), entityAnswer.getAddressBuilding());
     }

}
