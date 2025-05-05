package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode.SelectSingleAddressLogic;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.AddressPostalWorksRepository;

/**
 * （）内単一住所データの呼び出し住所を正規郵便番号に反映するItemWriter
 */
@Component
public class SelectPostalCodeSingleAddressItemWriter extends JpaItemWriter<AddressPostalWorksEntity> {

    /** 単独住所検索Logic */
    @Autowired
    private SelectSingleAddressLogic selectSingleAddressLogic;

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
    public SelectPostalCodeSingleAddressItemWriter(final @Autowired EntityManagerFactory entityManagerFactory) {
        super();
        super.setEntityManagerFactory(entityManagerFactory);
    }

    /**
     * 書き込み処理
     */
    @Override
    public void write(final Chunk<? extends AddressPostalWorksEntity> items) {

        for (AddressPostalWorksEntity entity : items) {
            // 住居テーブルに存在する地名で有効化
            List<AddressPostalEntity> list = selectSingleAddressLogic.practice(entity);
            if (!list.isEmpty()) {
                // 住所データは必要部分は編集済みなのでそのまま保存
                addressPostalRepository.saveAll(list);
                addressPostalWorksRepository.save(entity);
            }
        }
    }

}
