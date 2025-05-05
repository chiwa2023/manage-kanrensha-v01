package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalIrregularRepository;

/**
 * 郵便番号不規則更新ItemWriter
 */
@Component
public class IrregularPostalItemWriter extends JpaItemWriter<AddressPostalIrregularEntity> {

    /** 郵便番号不規則Respository */
    @Autowired
    private AddressPostalIrregularRepository addressPostalIrregularRepository;
    
    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public IrregularPostalItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalIrregularEntity> items) {
        
        // 編集済みデータを保存するだけ
        addressPostalIrregularRepository.saveAllAndFlush(items);
    }

}
