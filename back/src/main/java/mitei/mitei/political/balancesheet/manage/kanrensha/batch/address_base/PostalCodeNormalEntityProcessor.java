package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 優位bン番号CsvDtoから郵便番号(通常)テーブルEntityに変換する
 */
@Component
public class PostalCodeNormalEntityProcessor implements ItemProcessor<PostalCodeCsvDto, AddressPostalEntity> {

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressPostalEntity process(final PostalCodeCsvDto item) throws Exception {

        AddressPostalEntity entity = new AddressPostalEntity();

        BeanUtils.copyProperties(item, entity);

        // チェックディジットを追加
        String lgCode = entity.getLgCode();
        String plusDigit = lgCode + ((11 - (this.calk(lgCode) % 11)) % 10); // SUPPRESS CHECKSTYLE MagicNumber

        entity.setLgCode(plusDigit);

        return entity;
    }

    private int calk(final String lgCode) {
        // CHECKSTYLE:OFF MagicNumber

        int ans6 = Integer.parseInt(lgCode.substring(0, 1)) * 6;
        int ans5 = Integer.parseInt(lgCode.substring(1, 2)) * 5;
        int ans4 = Integer.parseInt(lgCode.substring(2, 3)) * 4;
        int ans3 = Integer.parseInt(lgCode.substring(3, 4)) * 3;
        int ans2 = Integer.parseInt(lgCode.substring(4, 5)) * 2;

        return ans6 + ans5 + ans4 + ans3 + ans2;
    }

}
