package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * IrregularPostalItemWriter単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class IrregularPostalItemWriterTest {

    /** テスト対象 */
    @Autowired
    private IrregularPostalItemWriter irregularPostalItemWriter;

    /** 郵便番号不規則Respository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;

    @Test
    @Tag("TableTruncate")
    @Transactional
    void test()throws Exception {
        
        final int dataId = 197;
        
        AddressPostalIrregularEntity irregularPreEntity = addressPostalIrregularRepository.findById(dataId).get();
        assertEquals(false,irregularPreEntity.getIsAddPostal());
        assertEquals(false,irregularPreEntity.getIsRepairRsdt());
        
        AddressPostalIrregularWorksProcessor worksProcessor = new AddressPostalIrregularWorksProcessor();
        
        AddressPostalWorksEntity worksEntity = worksProcessor.process(irregularPreEntity);
        worksEntity.setIsAddPostal(true);
        worksEntity.setIsRepairRsdt(true);
        

        AddressPostalWorksIrregularProcessor irregularProcessor = new AddressPostalWorksIrregularProcessor();
        AddressPostalIrregularEntity irregularEditEntity = irregularProcessor.process(worksEntity);
        
        List<AddressPostalIrregularEntity> list = new ArrayList<>();
        list.add(irregularEditEntity);
        
        // Chunkを作成してセット
        Chunk<? extends AddressPostalIrregularEntity> items = new Chunk<>(list);
        irregularPostalItemWriter.write(items);

        AddressPostalIrregularEntity irregularProEntity = addressPostalIrregularRepository.findById(dataId).get();
        assertEquals(true,irregularProEntity.getIsAddPostal());
        assertEquals(true,irregularProEntity.getIsRepairRsdt());

    }

}
