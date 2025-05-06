package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

/**
 * 法人番号CSV読み取りLineMapper
 */
@Component
public class HoujinNoCsvLineMapper implements LineMapper<HoujinNoCsvDto> {

    /** 引用符 */
    private static final String QUOTE= "\""; 
    /** 空白 */
    private static final String BLANK= ""; 
    
    /**
     * 処理を行う
     */
    @Override
    public HoujinNoCsvDto mapLine(final String line, final int lineNumber) throws Exception {
        // CHECKSTYLE:OFF MagicNumber

        String[] cell = line.split(",");

        HoujinNoCsvDto csvDto = new HoujinNoCsvDto();

        // 7 一連番号 sequenceNumber
        csvDto.setSequenceNumber(cell[0].replaceAll(QUOTE, BLANK));

        // 8 法人番号 corporateNumber
        csvDto.setCorporateNumber(cell[1].replaceAll(QUOTE, BLANK));

        // 9 処理区分 process
        csvDto.setProcess(cell[2].replaceAll(QUOTE, BLANK));

        // 10 訂正区分 correct
        csvDto.setCorrect(cell[3].replaceAll(QUOTE, BLANK));

        // 11 更新年月日 updateDate
        csvDto.setUpdateDate(cell[4].replaceAll(QUOTE, BLANK));

        // 12 変更年月日 changeDate
        csvDto.setChangeDate(cell[5].replaceAll(QUOTE, BLANK));

        // 13 商号又は名称 name
        csvDto.setName(cell[6].replaceAll(QUOTE, BLANK));

        // 14 商号又は名称イメージID nameImageId
        csvDto.setNameImageId(cell[7].replaceAll(QUOTE, BLANK));

        // 15 法人種別 kind
        csvDto.setKind(cell[8].replaceAll(QUOTE, BLANK));

        // 16 国内所在地（都道府県） prefectureName
        csvDto.setPrefectureName(cell[9].replaceAll(QUOTE, BLANK));

        // 17 国内所在地（市区町村） cityName
        csvDto.setCityName(cell[10].replaceAll(QUOTE, BLANK));

        // 18 国内所在地（丁目番地等） streetNumber
        csvDto.setStreetNumber(cell[11].replaceAll(QUOTE, BLANK));

        // 19 国内所在地イメージID addressImageId
        csvDto.setAddressImageId(cell[12].replaceAll(QUOTE, BLANK));

        // 20 都道府県コード prefectureCode
        csvDto.setPrefectureCode(cell[13].replaceAll(QUOTE, BLANK));

        // 21 市区町村コード cityCode
        csvDto.setCityCode(cell[14].replaceAll(QUOTE, BLANK));

        // 22 郵便番号 postCode
        csvDto.setPostCode(cell[15].replaceAll(QUOTE, BLANK));

        // 23 国外所在地 addressOutside
        csvDto.setAddressOutside(cell[16].replaceAll(QUOTE, BLANK));

        // 24 国外所在地イメージID addressOutsideImageId
        csvDto.setAddressOutsideImageId(cell[17].replaceAll(QUOTE, BLANK));

        // 25 登記記録の閉鎖等年月日 closeDate
        csvDto.setCloseDate(cell[18].replaceAll(QUOTE, BLANK));

        // 26 登記記録の閉鎖等の事由 closeCause
        csvDto.setCloseCause(cell[19].replaceAll(QUOTE, BLANK));

        // 27 承継先法人番号 successorCorporateNumber
        csvDto.setSuccessorCorporateNumber(cell[20].replaceAll(QUOTE, BLANK));

        // 28 変更事由の詳細 changeCause
        csvDto.setChangeCause(cell[21].replaceAll(QUOTE, BLANK));

        // 29 法人番号指定年月日 assignmentDate
        csvDto.setAssignmentDate(cell[22].replaceAll(QUOTE, BLANK));

        // 30 最新履歴 latest
        csvDto.setLatest(cell[23].replaceAll(QUOTE, BLANK));

        // 31 商号又は名称（英語表記） enName
        csvDto.setEnName(cell[24].replaceAll(QUOTE, BLANK));

        // 32 国内所在地（都道府県）（英語表記） enPrefectureName
        csvDto.setEnPrefectureName(cell[25].replaceAll(QUOTE, BLANK));

        // 33 国内所在地（市町村丁目番地等）（英語表記） enCityName
        csvDto.setEnCityName(cell[26].replaceAll(QUOTE, BLANK));

        // 34 国外所在地（英語表記） enAddressOutside
        csvDto.setEnAddressOutside(cell[27].replaceAll(QUOTE, BLANK));

        // 35 フリガナ furigana
        csvDto.setFurigana(cell[28].replaceAll(QUOTE, BLANK));

        // 36 検索対象除外 hihyoji
        csvDto.setHihyoji(cell[29].replaceAll(QUOTE, BLANK));

        return csvDto;
    }

}
