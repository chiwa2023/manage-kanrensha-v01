package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressAllCityEntity;

/**
 * アドレス・ベース・レジストリCsvからEntity変換Processor
 */
@Component
public class AddressAllCityProcessor  implements ItemProcessor<AllCityCsvDto, AddressAllCityEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressAllCityEntity process(final AllCityCsvDto item) throws Exception {

        AddressAllCityEntity entity = new AddressAllCityEntity();

        BeanUtils.copyProperties(item, entity);

        return entity;
    }

}
