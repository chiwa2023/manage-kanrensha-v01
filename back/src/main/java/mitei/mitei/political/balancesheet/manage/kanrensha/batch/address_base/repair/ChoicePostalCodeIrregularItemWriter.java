package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode.CopyPostalCodeByRangeNameLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * 郵便番号不規則抽出ItemWriter
 */
@Component
public class ChoicePostalCodeIrregularItemWriter extends JpaItemWriter<AddressPostalWorksEntity> {

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;
    
    
    /** 郵便番号範囲複写Logic */
    @Autowired
    private CopyPostalCodeByRangeNameLogic copyPostalCodeByRangeNameLogic;
    
    
    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public ChoicePostalCodeIrregularItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalWorksEntity> items) {

        for (AddressPostalWorksEntity entity : items) {
            // 範囲複写用郵便番号作成を試みて空リストが返ってこなければ作業対象
            List<AddressPostalEntity> list = copyPostalCodeByRangeNameLogic.practice(entity);
            if(!list.isEmpty()) {
                addressPostalRepository.saveAll(list);
                addressPostalWorksRepository.save(entity);
            }
        }
    }

}
