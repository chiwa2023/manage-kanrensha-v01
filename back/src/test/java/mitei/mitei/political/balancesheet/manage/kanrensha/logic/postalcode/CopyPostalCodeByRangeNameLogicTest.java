package mitei.mitei.political.balancesheet.manage.kanrensha.logic.postalcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalWorksEntity;

/**
 * CopyPostalCodeByRangeNameLogic単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class CopyPostalCodeByRangeNameLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private CopyPostalCodeByRangeNameLogic copyPostalCodeByRangeNameLogic;

    @Test
    void test() {

        // データに句読点、が入っている並列データはテーブルから抽出する時点で扱わない

        // ～が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない

        AddressPostalWorksEntity worksEntity00 = new AddressPostalWorksEntity();
        worksEntity00.setAddressOrg("富士町（西４〜８線４９〜７８番地）");
        List<AddressPostalEntity> list0 = copyPostalCodeByRangeNameLogic.practice(worksEntity00);
        assertTrue(list0.isEmpty());

        // 番地、丁目が２種入っているデータは住所リストを見ながらしないと修正できないので自動修正しない
        AddressPostalWorksEntity worksEntity01 = new AddressPostalWorksEntity();
        worksEntity01.setAddressOrg("門静（４丁目５５〜１１４番地）");
        List<AddressPostalEntity> list1 = copyPostalCodeByRangeNameLogic.practice(worksEntity01);
        assertTrue(list1.isEmpty());

        // 「線」は除外
        AddressPostalWorksEntity worksEntity02 = new AddressPostalWorksEntity();
        worksEntity02.setAddressOrg("士幌西（１線〜３線）");
        List<AddressPostalEntity> list2 = copyPostalCodeByRangeNameLogic.practice(worksEntity02);
        assertTrue(list2.isEmpty());

        // 「条」は除外
        AddressPostalWorksEntity worksEntity03 = new AddressPostalWorksEntity();
        worksEntity03.setAddressOrg("士幌西（１条〜３条）");
        List<AddressPostalEntity> list3 = copyPostalCodeByRangeNameLogic.practice(worksEntity03);
        assertTrue(list3.isEmpty());

        // 「-」が入っているデータは複雑すぎ
        AddressPostalWorksEntity worksEntity04 = new AddressPostalWorksEntity();
        worksEntity04.setAddressOrg("追名牛（６７−４〜１１３−７番地）");
        List<AddressPostalEntity> list4 = copyPostalCodeByRangeNameLogic.practice(worksEntity04);
        assertTrue(list4.isEmpty());

        // もっとも単純な範囲データ(番地)
        AddressPostalWorksEntity worksEntity05 = new AddressPostalWorksEntity();
        worksEntity05.setAddressOrg("東旭川町豊田（１〜９番地）");
        worksEntity05.setLgCode("012041");
        worksEntity05.setAddressName("旭川市東旭川町豊田");
        List<AddressPostalEntity> list5 = copyPostalCodeByRangeNameLogic.practice(worksEntity05);
        assertEquals(9, list5.size());

        // もっとも単純な範囲データ(番地)
        AddressPostalWorksEntity worksEntity06 = new AddressPostalWorksEntity();
        worksEntity06.setAddressOrg("広里町（１〜５丁目）");
        worksEntity06.setLgCode("012289");
        worksEntity06.setAddressName("深川市広里町");

        List<AddressPostalEntity> list6 = copyPostalCodeByRangeNameLogic.practice(worksEntity06);
        assertEquals(5, list6.size());

    }

}
