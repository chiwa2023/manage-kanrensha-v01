package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * 作業テーブルから不規則テーブルに変換Processor
 */
@Component
public class AddressPostalWorksIrregularProcessor
        implements ItemProcessor<AddressPostalWorksEntity, AddressPostalIrregularEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalIrregularEntity process(final AddressPostalWorksEntity item) throws Exception {

        AddressPostalIrregularEntity entity = new AddressPostalIrregularEntity();
        BeanUtils.copyProperties(item, entity);

        return entity;
    }

}
