package mitei.mitei.political.balancesheet.manage.kanrensha.logic.paging;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.paging.PeriodDatetimeCapsuleDtoInterface;

/**
 * 検索条件日時期間Dtoを年展開するLogic
 *
 * <p>
 * 大量のデータがあって発生年ごとでテーブルを分割していて、年をまたいだ検索条件指定ができる場合、<br>
 * 各年のテーブルで検索できるよう期間条件を調節します。<br>
 * 例) 検索開始日時 2022/12/05 12:34:56から2024/04/09 23:45:12<br>
 * 2022/12/05 12:34:56から2022/12/31 23:59:59<br>
 * 2023/01/01 00:00:00から2023/12/31 23:59:59<br>
 * 2024/01/01 00:00:00から2024/04/09 23:45:12<br>
 * その他の検索条件は複写します。<br>
 * インターフェースPeriodDatetimeCapsuleDtoInterfaceが戻りますがインスタンスが生成したインスタンスで戻ります。利用場所でアップキャストして使用します。<br>
 * </p>
 */
@Component
public class CreateSearchConditionMapByYearLogic {

    /**
     * 処理を行う
     *
     * @param capsuleDto 日時期間Dto
     * @return 年展開したMap
     * @throws InvocationTargetException reflectio例外
     * @throws IllegalAccessException reflectio例外
     * @throws InstantiationException reflectio例外
     * @throws NoSuchMethodException reflectio例外
     */
    public Map<Integer, PeriodDatetimeCapsuleDtoInterface> practice(final PeriodDatetimeCapsuleDtoInterface capsuleDto)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        Map<Integer, PeriodDatetimeCapsuleDtoInterface> map = new TreeMap<>();

        int yearStart = capsuleDto.getStartDate().getYear(); // NOPMD
        int yearEnd = capsuleDto.getEndDate().getYear(); // NOPMD

        for (int year = yearStart; year <= yearEnd; year++) {
            
            // 開始年と同一であれば検索条件、同一でなければ年始
            LocalDateTime start;
            if (year == yearStart) {
                start = capsuleDto.getStartDate(); // NOPMD
            } else {
                start = LocalDateTime.of(LocalDate.of(year, 1, 1), LocalTime.MIN);
            }

            // 終了年と同一であれば検索条件、同一でなければ年末
            LocalDateTime end;
            if (year == yearEnd) {
                end = capsuleDto.getEndDate(); // NOPMD
            } else {
                end = LocalDateTime.of(LocalDate.of(year, 12, 31), LocalTime.MAX); // SUPPRESS CHECKSTYLE MagicNumber
            }
            map.put(year, this.createPagingDto(capsuleDto, start, end));
        }

        return map;
    }

    private PeriodDatetimeCapsuleDtoInterface createPagingDto(final PeriodDatetimeCapsuleDtoInterface capsuleDto,
            final LocalDateTime start, final LocalDateTime end)
            throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {

        PeriodDatetimeCapsuleDtoInterface dto = capsuleDto.getClass().getDeclaredConstructor().newInstance();

        BeanUtils.copyProperties(capsuleDto, dto);
        dto.setStartDate(start);
        dto.setEndDate(end);

        return dto;
    }
}
