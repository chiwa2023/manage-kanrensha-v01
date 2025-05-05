package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * CreateDokujiCodeForCorpUtil単体テスト
 */
class CreateDokujiCodeForCorpUtilTest {
    // CHECKSTYLE:OFF

    @Test
    void test() {

        CreateDokujiCodeForCorpUtil createDokujiCodeForCorpUtil = new CreateDokujiCodeForCorpUtil();

        assertDoesNotThrow(() -> createDokujiCodeForCorpUtil.practice(null), "積極的ではないがnull許容");
        assertDoesNotThrow(() -> createDokujiCodeForCorpUtil.practice(""), "正規コードが存在しない場合");

        // 判定するパターンを生成
        // 1-2345-67-890123-4567890が最終形
        Pattern pattern = Pattern.compile(".{1}-.{4}-.{2}-.{6}-.{7}");

        final String length24 = "24文字";
        final String match = "形式にマッチ";
        String answer1 = createDokujiCodeForCorpUtil.practice("1-234-567-890123");
        assertEquals(24, answer1.length(), length24);
        assertTrue(answer1.startsWith("1-2345-67-890123"), "ハイフンを取り除いて成形");
        assertTrue(pattern.matcher(answer1).find(), match);

        String answer2 = createDokujiCodeForCorpUtil.practice("1-234-567-89*012");
        // System.out.println(answer2);
        assertEquals(24, answer2.length(), length24);
        assertTrue(answer2.startsWith("1-2345-67-89*012"), "ハイフン以外の記号はそのまま");
        assertTrue(pattern.matcher(answer2).find(), match);

        String answer3 = createDokujiCodeForCorpUtil.practice("１－２３４-567-890Ａｂc");
        // System.out.println(answer3);
        assertEquals(24, answer3.length(), length24);
        assertTrue(answer3.startsWith("1-2345-67-890Abc"), "全角英数は半角に");
        assertTrue(pattern.matcher(answer3).find(), match);

        // 正規コード=法人番号なのでかなは想定しなくていい
        // String answer4 = createDokujiCodeForCorpUtil.practice("あ-いウエお-67-890123");
        // // System.out.println(answer4);
        // assertEquals(24, answer4.length(), length24);
        // assertTrue(answer4.startsWith("あ-いウエお-67-890123"),
        // "正規コードにひらがなカタカナが存在(ないと思うけど)");
        // assertTrue(p.matcher(answer4).find(), match);
    }

}
