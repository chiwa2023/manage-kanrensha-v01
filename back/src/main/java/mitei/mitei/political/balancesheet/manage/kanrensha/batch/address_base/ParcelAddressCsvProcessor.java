package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtBaseEntity;

/**
 * アドレス・ベース・レジストリ地番データEntity変換Processor
 */
@Component
public class ParcelAddressCsvProcessor implements ItemProcessor<ParcelAddressCsvDto, AddressRsdtBaseEntity> {

    /** 空白文字 */
    private static final String BLANK = "";

    /** 空白文字 */
    private static final int HAS_NOT_JUKYO = 0;

    /**
     * 変換処理を実行する
     */
    @Override
    public AddressRsdtBaseEntity process(final ParcelAddressCsvDto item) throws Exception {

        AddressRsdtBaseEntity entity = new AddressRsdtBaseEntity();

        // 現在地番データと住居データが、地方自治体内の町ごとに住居csvに収録されたり収録されなかったりしている
        // サイトにはこれで仕様確定とされているが、最終的にはすべての住所が住居データに集約されるものと思われる
        // (そうしないとアパート済みの市民の住所が表現できないから)
        // それまでは住居データをすべて収録、住居データ化されていない場合は地番データを参照するようにする
        if(HAS_NOT_JUKYO == item.getRsdtAddrFlg()) {
            BeanUtils.copyProperties(item, entity);
            
            entity.setParcelRsdtId(item.getPrcId());
            // 地番までの住所変換
            entity.setAddressBlock(this.convertBlockAddress(item));
        }
        return entity;
    }

    private String convertBlockAddress(final ParcelAddressCsvDto item) {

        StringBuilder builder = new StringBuilder();
        builder.append(item.getCity()).append(item.getWard()).append(item.getOazaCho()).append(item.getChome())
                .append(item.getKoaza());

        // TODO 規約で全角文字であることを要求される場合は変換する
        if (!BLANK.equals(item.getPrcNum1())) {
            builder.append(item.getPrcNum1()).append("番地");
        }
        if (!BLANK.equals(item.getPrcNum2())) {
            builder.append(item.getPrcNum2()).append('号');
        }
        if (!BLANK.equals(item.getPrcNum3())) {
            builder.append('の').append(item.getPrcNum3());
        }

        return builder.toString();
    }


}
