package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * アドレス・ベース・レジストリ市区町村(mt_city_all)の一行をDtoに変換
 */
@Component
public class AllCityCsvLineMapper implements LineMapper<AllCityCsvDto> {

    /** 日付変換Formatter */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** 都道府県読み取り位置 */
    private static final int POS_NAME_PREFCTURE = 1;
    /** 都道府県かな読み取り位置 */
    private static final int POS_KANA_PREFCTURE = 2;

    //county
    /** 郡読み取り位置 */
    private static final int POS_NAME_COUNTY = 4;
    /** 郡かな読み取り位置 */
    private static final int POS_KANA_COUNTY = 5;

    //city
    /** 市読み取り位置 */
    private static final int POS_NAME_CITY = 7;
    /** 市かな読み取り位置 */
    private static final int POS_KANA_CITY = 8;
    
    //ward
    /** 政令区読み取り位置 */
    private static final int POS_NAME_WARD = 10;
    /** 政令区かな読み取り位置 */
    private static final int POS_KANA_WARD = 11;
    

    /** 適用日読み取り位置 */
    private static final int POS_EFFECT_DATE = 13;

    /**
     * 処理を行う
     */
    @Override
    public AllCityCsvDto mapLine(final String line, final int lineNumber) throws Exception {

        AllCityCsvDto dto = new AllCityCsvDto();

        String[] cell = line.split(",");

        dto.setLgCode(cell[0]);
        dto.setAddressName(cell[POS_NAME_PREFCTURE] + cell[POS_NAME_COUNTY] + cell[POS_NAME_CITY] + cell[POS_NAME_WARD]);
        dto.setAddressNameKana(cell[POS_KANA_PREFCTURE] + cell[POS_KANA_COUNTY] + cell[POS_KANA_CITY] + cell[POS_KANA_WARD]);
        
        dto.setEffectDate(LocalDate.parse(cell[POS_EFFECT_DATE], formatter));

        return dto;
    }

}
