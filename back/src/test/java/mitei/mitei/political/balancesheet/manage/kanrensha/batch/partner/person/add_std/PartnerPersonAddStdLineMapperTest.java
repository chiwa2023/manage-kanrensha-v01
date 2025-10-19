package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * PartnerPersonAddStdLineMapper単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonAddStdLineMapperTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonAddStdLineMapper partnerPersonAddStdLineMapper;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception { // NOPMD

        final String quote = "\"";
        final String comma = ",";

        StringBuilder builder = new StringBuilder();

        // 名称
        final String name = "名称";
        builder.append(quote).append(name).append(quote).append(comma);

        // 全住所
        final String address = "全住所";
        builder.append(quote).append(address).append(quote).append(comma);

        // 個人職業
        final String shokugyou = "職業";
        builder.append(quote).append(shokugyou).append(quote).append(comma);

        // 住所郵便番号まで
        final String addressPostal = "住所郵便番号まで";
        builder.append(quote).append(addressPostal).append(quote).append(comma);

        // 住所番地まで
        final String addressBlock = "住所番地まで";
        builder.append(quote).append(addressBlock).append(quote).append(comma);

        // 住所建物まで
        final String addressBuilding = "住所建物";
        builder.append(quote).append(addressBuilding).append(quote).append(comma);

        // 郵便番号1
        final String postal1 = "012";
        builder.append(quote).append(postal1).append(quote).append(comma);

        // 郵便番号2
        final String postal2 = "3456";
        builder.append(quote).append(postal2).append(quote).append(comma);

        // 電話番号市外局番
        final String phon1 = "012";
        builder.append(quote).append(phon1).append(quote).append(comma);

        // 電話番号局番
        final String phon2 = "3456";
        builder.append(quote).append(phon2).append(quote).append(comma);

        // 電話番号番号
        final String phon3 = "7890";
        builder.append(quote).append(phon3).append(quote).append(comma);

        // メールアドレス
        final String email = "aaa@bbb.net";
        builder.append(quote).append(email).append(quote).append(comma);

        // 自分の公式サイト
        final String myPortal = "https://myportal.net/";
        builder.append(quote).append(myPortal).append(quote).append(comma);

        // 外国籍該否
        final String foreign = "はい";
        builder.append(quote).append(foreign).append(quote).append(comma);

        // 姓名の姓
        final String lastName = "迂回献金";
        builder.append(quote).append(lastName).append(quote).append(comma);

        // 姓名の名
        final String firstName = "太郎";
        builder.append(quote).append(firstName).append(quote).append(comma);

        // 姓名のミドルネーム
        final String middleName = "ミカエル";
        builder.append(quote).append(middleName).append(quote).append(comma);

        // 姓名の姓のかな
        final String lastNameKana = "うかいけんきん";
        builder.append(quote).append(lastNameKana).append(quote).append(comma);

        // 姓名の名のかな
        final String firstNameKana = "たろう";
        builder.append(quote).append(firstNameKana).append(quote).append(comma);

        // 姓名のミドルネームのかな
        final String middleNameKana = "みかえる";
        builder.append(quote).append(middleNameKana).append(quote).append(comma);

        // 職業の業種
        final String gyoushu = "水産業";
        builder.append(quote).append(gyoushu).append(quote).append(comma);

        // 職業の役職
        final String yakushoku = "団体役職者";
        builder.append(quote).append(yakushoku).append(quote).append(comma);

        // 職業のユーザ記載
        final String userWrite = "ユーザ記載職業";
        builder.append(quote).append(userWrite).append(quote).append(comma);

        // 職業法人番号
        final String corpNo = "1-2345-67";
        builder.append(quote).append(corpNo).append(quote).append(comma);

        // 職業法人住所
        final String corpAddress = "和歌山県実在市山麓町";
        builder.append(quote).append(corpAddress).append(quote).append(comma);

        // 職業法人名
        final String corpName = "超元素製造組合";
        builder.append(quote).append(corpName).append(quote).append(comma);

        // 地方公共団体コード
        final String lgcode = "01234";
        builder.append(quote).append(lgcode).append(quote).append(comma);
        // 町字Id
        final String machiaza = "12345";
        builder.append(quote).append(machiaza).append(quote).append(comma);
        // 街区Id
        final String block = "23456";
        builder.append(quote).append(block).append(quote).append(comma);
        // 住居Id
        final String rsdt = "123";
        builder.append(quote).append(rsdt).append(quote).append(comma);
        // 住居2Id
        final String rsdt2 = "234";
        builder.append(quote).append(rsdt2).append(quote).append(comma);

        PartnerPersonAddStdDto dto = partnerPersonAddStdLineMapper.mapLine(builder.toString(), 0);

        assertEquals(name, dto.getPartnerName());
        assertEquals(address, dto.getAllAddress());
        assertEquals(shokugyou, dto.getPersonShokugyou());
        assertEquals(addressPostal, dto.getAddressPostal());
        assertEquals(addressBlock, dto.getAddressBlock());
        assertEquals(addressBuilding, dto.getAddressBuilding());

        assertEquals(postal1, dto.getPostal1());
        assertEquals(postal2, dto.getPostal2());

        assertEquals(phon1, dto.getPhon1());
        assertEquals(phon2, dto.getPhon2());
        assertEquals(phon3, dto.getPhon3());
        assertEquals(email, dto.getEmail());
        assertEquals(myPortal, dto.getMyPortalUrl());
        assertEquals(true, dto.getIsForeign());

        assertEquals(lastName, dto.getLastName());
        assertEquals(firstName, dto.getFirstName());
        assertEquals(middleName, dto.getMiddleName());
        assertEquals(lastNameKana, dto.getLastNameKana());
        assertEquals(firstNameKana, dto.getFirstNameKana());
        assertEquals(middleNameKana, dto.getMiddleNameKana());
        assertEquals(gyoushu, dto.getGyoushu());
        assertEquals(yakushoku, dto.getYakushoku());
        assertEquals(userWrite, dto.getShokugyouUserWrite());
        assertEquals(corpNo, dto.getCorpNo());
        assertEquals(corpAddress, dto.getCorpAddress());
        assertEquals(corpName, dto.getCorpName());

        assertEquals(lgcode, dto.getLgCode());
        assertEquals(machiaza, dto.getMachiazaId());
        assertEquals(block, dto.getBlkId());
        assertEquals(rsdt, dto.getRsdtId());
        assertEquals(rsdt2, dto.getRsdt2Id());
    }

}
