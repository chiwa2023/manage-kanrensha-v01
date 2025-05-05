package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;

/**
 * 郵便番号不規則データEntity変換Processor
 */
@Component
public class PostalCodeToIrregularProceesor implements ItemProcessor<AddressPostalEntity, AddressPostalIrregularEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalIrregularEntity process(final AddressPostalEntity item) throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        // コピーできる項目だけをコピーその他項目は後で追記する
        BeanUtils.copyProperties(item, entity);

        entity.setAddressPostalIrregularId(0); // auto_increment0明示
        
        return entity;
    }

}
