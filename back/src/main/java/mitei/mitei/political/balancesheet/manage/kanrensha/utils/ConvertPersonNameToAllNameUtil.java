package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;

/**
 * 入力個人名を姓名表記に変換するUtility
 *
 * <p>
 * 姓＋全角スペース＋(ミドルネーム)名形式。かなも同様
 * </p>
 */
public final class ConvertPersonNameToAllNameUtil {

    /**
     * コンストラクタ
     */
    private ConvertPersonNameToAllNameUtil() {

    }

    /** 全角スペース */
    private static final String WIDE_SPACE = "　";

    /**
     * 姓名を仕様どおりで結合する
     *
     * @param nameDto 個人名入力Dto
     * @return 姓名
     */
    public static String practiceName(final InputPersonNameDto nameDto) {

        return nameDto.getLastName() + WIDE_SPACE + nameDto.getMiddleName() + nameDto.getFirstName();
    }

    /**
     * 姓名かなを仕様どおりで結合する
     *
     * @param nameDto 個人名入力Dto
     * @return 姓名かな
     */
    public static String practiceNameKana(final InputPersonNameDto nameDto) {

        return nameDto.getLastNameKana() + WIDE_SPACE + nameDto.getMiddleNameKana() + nameDto.getFirstNameKana();
    }
}
