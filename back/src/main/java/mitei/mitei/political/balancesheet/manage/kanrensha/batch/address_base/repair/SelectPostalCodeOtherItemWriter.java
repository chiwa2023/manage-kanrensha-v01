package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode.CheckExistPostalCodeByOtherLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * 郵便番号不規則データ(その他)有効ItemWriter
 */
@Component
public class SelectPostalCodeOtherItemWriter extends JpaItemWriter<AddressPostalWorksEntity> {
    
    /** その他住所存在確認Logic */
    @Autowired
    private CheckExistPostalCodeByOtherLogic checkExistPostalCodeByOtherLogic;

    /** 郵便番号Repository */
    @Autowired
    private AddressPostalRepository addressPostalRepository;

    /** 郵便番号作業Repository */
    @Autowired
    private AddressPostalWorksRepository addressPostalWorksRepository;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory EntityManagerFactory
     */
    public SelectPostalCodeOtherItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalWorksEntity> items) {

        for (AddressPostalWorksEntity entity : items) {
            // 住居テーブルに共通部分データが存在すれば、データとして有効化
            List<AddressPostalEntity> list = checkExistPostalCodeByOtherLogic.practice(entity);
            if(!list.isEmpty()) {
                // 住居データ参照可能フラグをON
                for(AddressPostalEntity postalEntity : list ) {
                    postalEntity.setIsGyoseikuData(true);
                }
                addressPostalRepository.saveAll(list);
                addressPostalWorksRepository.save(entity);
            }
        }
    }

}
