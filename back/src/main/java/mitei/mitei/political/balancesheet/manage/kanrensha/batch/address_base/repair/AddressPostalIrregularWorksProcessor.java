package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalIrregularEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * 郵便番号不規則テーブル郵便番号作業テーブル変換Processor
 */
@Component
public class AddressPostalIrregularWorksProcessor
        implements ItemProcessor<AddressPostalIrregularEntity, AddressPostalWorksEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalWorksEntity process(final AddressPostalIrregularEntity item) throws Exception {

        AddressPostalWorksEntity entity = new AddressPostalWorksEntity();

        // PG上で@Tableアノテーションが重複するエラーが発生するため、実装には反映していないが
        // AddressPostalWorksEntityはAddressPostalIrregularEntityをextendsしたテーブル
        BeanUtils.copyProperties(item, entity);

        entity.setAddressPostalWorksId(0); // auto_increment0明示
        entity.setIsAddPostal(true); // 最終的には郵便番号(正規)データを追加する

        return entity;
    }

}
