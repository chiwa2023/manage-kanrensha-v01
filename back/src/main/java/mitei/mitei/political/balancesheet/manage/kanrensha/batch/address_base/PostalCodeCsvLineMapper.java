package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 郵便番号Csv行読み取りMapper
 */
@Component
public class PostalCodeCsvLineMapper implements LineMapper<PostalCodeCsvDto> {

    /** 自治体コード読み取り位置 */
    private static final int POS_LOCAL_GOV = 0;

    /** 郵便番号読み取り位置 */
    private static final int POS_POSTALCODE = 2;

    /** 市区町村記載位置 */
    private static final int POS_ADDRESS_ORG = 8;

    /** 住所記載位置 */
    private static final int POS_ADDRESS_CITY = 7;

    /** ダブルクォーテーション */
    private static final String QUATE = "\"";
    /** 空文字 */
    private static final String BALNK = "";
    /** 掲載のない場合の郵便番号コード */
    private static final String KEISAI_NASHI_CODE = "0000";

    /**
     * 処理を行う
     */
    @Override
    public PostalCodeCsvDto mapLine(final String line, final int lineNumber) throws Exception {

        String[] cell = line.split(",");
        String postal = cell[POS_POSTALCODE].replaceAll(QUATE, BALNK);

        PostalCodeCsvDto csvDto = new PostalCodeCsvDto();

        csvDto.setLgCode(cell[POS_LOCAL_GOV].replaceAll(QUATE, BALNK));
        csvDto.setPostal1(postal);
        csvDto.setPostal2(postal.substring(3, 7)); // SUPPRESS CHECKSTYLE MagicNumber
        csvDto.setIsGyoseikuData(true);

        String addressOrg = cell[POS_ADDRESS_ORG].replaceAll(QUATE, BALNK);
        csvDto.setAddressOrg(addressOrg);

        String name = "";

        if (!KEISAI_NASHI_CODE.equals(csvDto.getPostal2())) {
            int pos = addressOrg.indexOf("（");
            if (-1 == pos) {
                name = addressOrg;
            }else {
                name = addressOrg.substring(0, pos);
                csvDto.setIsGyoseikuData(false); // 仮で検索しない。データ整備後に戻す
            }
        }

        csvDto.setAddressName(cell[POS_ADDRESS_CITY].replaceAll(QUATE, BALNK) + name);

        return csvDto;
    }

}
