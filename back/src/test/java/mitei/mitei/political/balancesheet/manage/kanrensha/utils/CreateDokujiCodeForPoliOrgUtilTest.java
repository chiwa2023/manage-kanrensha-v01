package mitei.mitei.political.balancesheet.manage.kanrensha.utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * CreateDokujiCodeForPoliOrgUtil単体テスト
 */
class CreateDokujiCodeForPoliOrgUtilTest {
    // CHECKSTYLE:OFF

    @Test
    void test() {

        CreateDokujiCodeForPoliOrgUtil createDokujiCodeForPoliOrgUtil = new CreateDokujiCodeForPoliOrgUtil();

        assertDoesNotThrow(() -> createDokujiCodeForPoliOrgUtil.practice(null), "積極的ではないがnull許容");
        assertDoesNotThrow(() -> createDokujiCodeForPoliOrgUtil.practice(""), "正規コードが存在しない場合");

      //判定するパターンを生成
        // 123-4567-8901-2345-67890が最終形
        Pattern pattern = Pattern.compile(".{3}-.{4}-.{4}-.{4}-.{5}");
        
        final String length24 = "24文字";
        final String match = "形式にマッチ";
        String answer1 = createDokujiCodeForPoliOrgUtil.practice("1-234-567-890");
        // System.out.println(answer1);
        assertEquals(24, answer1.length(), length24);
        assertTrue(answer1.startsWith("123-4567-890"), "ハイフンを取り除いて成形");
        assertTrue(pattern.matcher(answer1).find(), match);

        String answer2 = createDokujiCodeForPoliOrgUtil.practice("1-234-567-89*0");
        // System.out.println(answer2);
        assertEquals(24, answer2.length(), length24);
        assertTrue(answer2.startsWith("123-4567-89*0"), "ハイフン以外の記号はそのまま");
        assertTrue(pattern.matcher(answer2).find(), match);

        String answer3 = createDokujiCodeForPoliOrgUtil.practice("１－２３４-567-890ＡＢ");
        // System.out.println(answer3);
        assertEquals(24, answer3.length(), length24);
        assertTrue(answer3.startsWith("123-4567-890A-B"), "全角英数は半角に");
        assertTrue(pattern.matcher(answer3).find(), match);

        String answer4 = createDokujiCodeForPoliOrgUtil.practice("あ-いウエお-67-890");
        // System.out.println(answer4);
        assertEquals(24, answer4.length(), length24);
        assertTrue(answer4.startsWith("あいウ-エお67-890"), "正規コードにひらがなカタカナが存在(ないと思うけど)");
        assertTrue(pattern.matcher(answer4).find(), match);

    }

}
