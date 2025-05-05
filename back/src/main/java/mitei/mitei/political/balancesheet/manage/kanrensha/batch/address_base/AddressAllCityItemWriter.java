package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressAllCityRepository;

/**
 * 市区町村テーブルItemWriter
 */
@Component
public class AddressAllCityItemWriter extends JpaItemWriter<AddressAllCityEntity> {

    /** 市区町村テーブルRepository */
    @Autowired
    private AddressAllCityRepository addressAllCityRepository;

    /**
     * コンストラクタ
     *
     * @param entityManagerFactory entityManagerFactory
     */
    public AddressAllCityItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressAllCityEntity> items) {

        addressAllCityRepository.saveAll(items);
    }

}
