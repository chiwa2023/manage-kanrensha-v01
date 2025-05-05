package mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * アドレス・ベース・レジストリ地番CsvデータLineMapper
 */
@Component
public class ParcelAddressCsvLineMapper implements LineMapper<ParcelAddressCsvDto> {
    // CHECKSTYLE:OFF MagicNumber
    // 列の最初から該当カラムまでカラム順に取得する処理なので、変に定数で位置指定しないのが正しい書法と想定

    /** 日付変換Formatter */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(ParcelAddressCsvLineMapper.class);

    /** 空文字 */
    private static final String BLANK = "";

    /**
     * 処理を行う
     */
    @Override
    public ParcelAddressCsvDto mapLine(final String line, final int lineNumber) throws Exception {

        ParcelAddressCsvDto csvDto = new ParcelAddressCsvDto();

        String[] cell = line.split(",");

        // 地方自治体コード
        csvDto.setLgCode(cell[0]);
        // 町字コード
        csvDto.setMachiazaId(cell[1]);
        // 地番コード
        csvDto.setPrcId(cell[2]);
        // 市区町村名
        csvDto.setCity(cell[3]);
        // 政令市区名
        csvDto.setWard(cell[4]);
        // 大字・町名
        csvDto.setOazaCho(cell[5]);
        // 丁目名
        csvDto.setChome(cell[6]);
        // 小字名
        csvDto.setKoaza(cell[7]);
        // 同一町字識別情報
        csvDto.setMachiazaDist(cell[8]);
        // 地番1
        csvDto.setPrcNum1(cell[9]);
        // 地番2
        csvDto.setPrcNum2(cell[10]);
        // 地番3
        csvDto.setPrcNum3(cell[11]);

        // 住居表示フラグ
        // 必須項目は値が入らないことも例外
        csvDto.setRsdtAddrFlg(this.convertInteger(cell[12], lineNumber));

        // 地番レコード区分フラグ
        String prcRecFlg = cell[13];
        if (!BLANK.equals(prcRecFlg)) {
            csvDto.setPrcRecFlg(this.convertInteger(prcRecFlg, lineNumber));
        }
        // 地番区域コード
        String prcAreaCode = cell[14];
        if (!BLANK.equals(prcAreaCode)) {
            csvDto.setPrcAreaCode(this.convertInteger(prcAreaCode, lineNumber));
        }

        // 適用開始日
        // 必須項目は値が入らないことも例外
        csvDto.setEffectDate(this.convertLocalDate(cell[15], lineNumber));

        // 廃止日
        String abolitionDate = cell[16];
        if (!BLANK.equals(abolitionDate)) {
            csvDto.setAbolitionDate(this.convertLocalDate(abolitionDate, lineNumber));
        }

        return csvDto;
    }

    /*
     * 日付を変換する
     * 
     * @param dateText 日付テキスト
     * @param lineNumber 行数
     * @return 日付
     */
    private LocalDate convertLocalDate(final String dateText, final int lineNumber) {
        try {
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            log.error(lineNumber + "行目日付変換不可", e);
        }
        return LocalDate.of(1948, 7, 29);
    }

    /*
     * 数値に変換する
     * 
     * @param codeText コードテキスト
     * @param lineNumber 行数
     * @return
     */
    private Integer convertInteger(final String codeText, final int lineNumber) {

        try {
            return Integer.parseInt(codeText);
        } catch (NumberFormatException e) {
            log.error(lineNumber + "行目数値変換不可", e);
        }
        return 0;
    }

}
