package mitei.mitei.political.balancesheet.manage.kanrensha.batch.houjin_no;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.batch.address_base.repair.AddressPostalWorksIrregularProcessor;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.HoujinNoLatestEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.FormatNaturalSearchTextUtil;

/**
 * 法人番号Csvから法人番号最新Entityに変換する
 */
@Component
public class HoujinNoCsvEntityProcessor implements ItemProcessor<HoujinNoCsvDto, HoujinNoLatestEntity> {

    /** 日付変換Formatter */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** Logger */
    private final Logger log = LoggerFactory.getLogger(AddressPostalWorksIrregularProcessor.class);

    /** falseを表す文字 */
    private static final String FALSE = "0";
    /** Trueを表す文字 */
    private static final String TRUE = "1";

    /** 自然検索から作成Util */
    @Autowired
    private FormatNaturalSearchTextUtil formatNaturalSearchTextUtil;

    /**
     * 変換処理を実行する
     */
    @Override
    public HoujinNoLatestEntity process(final HoujinNoCsvDto item) throws Exception {

        HoujinNoLatestEntity entity = new HoujinNoLatestEntity();
        BeanUtils.copyProperties(item, entity);

        // 型変換が必要なプロパティ
        String lineNumber = item.getSequenceNumber();
        entity.setCorrect(this.convertBoolean(item.getCorrect(), lineNumber));
        entity.setUpdateDate(this.convertLocalDate(item.getUpdateDate(), lineNumber));
        entity.setChangeDate(this.convertLocalDate(item.getChangeDate(), lineNumber));
        entity.setCloseDate(this.convertLocalDate(item.getCloseDate(), lineNumber));
        entity.setAssignmentDate(this.convertLocalDate(item.getAssignmentDate(), lineNumber));
        entity.setLatest(this.convertBoolean(item.getLatest(), lineNumber));
        entity.setHihyoji(this.convertBoolean(item.getHihyoji(), lineNumber));

        StringBuilder builder = new StringBuilder();
        builder.append(entity.getName()).append(entity.getPrefectureName()).append(entity.getCityName())
                .append(entity.getStreetNumber()).append(entity.getFurigana());

        entity.setSearchText(formatNaturalSearchTextUtil.practice(builder.toString()));

        return entity;
    }

    private Boolean convertBoolean(final String data, final String lineNumber) { // SUPPRESS CHECKSTYLE ReturnCount

        // 0/1であれば正常値
        if (TRUE.equals(data)) {
            return true;
        }
        if (FALSE.equals(data)) {
            return false;
        }

        log.error(lineNumber + "行目にtrue/falseで0/1以外の値が入っています" + data);

        return false;
    }

    /*
     * 日付を変換する
     * 
     * @param dateText 日付テキスト
     * 
     * @return 日付
     */
    private LocalDate convertLocalDate(final String data, final String lineNumber) {
        
        if(!"".equals(data)) {
            try {
                return LocalDate.parse(data, formatter);
            } catch (DateTimeParseException e) {
                log.error(lineNumber + "行目日付変換不可", e);
            }
        }
        return LocalDate.of(1948, 7, 29); // SUPPRESS CHECKSTYLE MagicNumber
    }

}
