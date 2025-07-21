package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.corp.add_std;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * PartnerCorpAddStdLineMapper単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerCorpAddStdLineMapperTest {

    /** テスト対象 */
    @Autowired
    private PartnerCorpAddStdLineMapper partnerCorpAddStdLineMapper;

    @Test
    void test() throws Exception { // NOPMD

        final String quote = "\"";
        final String comma = ",";

        StringBuilder builder = new StringBuilder();

        // 名称カラム位置
        final String name = "名称";
        builder.append(quote).append(name).append(quote).append(comma);

        // 全住所カラム位置
        final String address = "全住所";
        builder.append(quote).append(address).append(quote).append(comma);

        // 団体代表
        final String delegate = "代表者名";
        builder.append(quote).append(delegate).append(quote).append(comma);
        
        // 法人番号
        final String houjinNo = "法人番号";
        builder.append(quote).append(houjinNo).append(quote).append(comma);
        
        // 住所郵便番号までカラム位置
        final String addressPostal = "住所郵便番号まで";
        builder.append(quote).append(addressPostal).append(quote).append(comma);

        // 住所番地までカラム位置
        final String addressBlock = "住所番地まで";
        builder.append(quote).append(addressBlock).append(quote).append(comma);

        // 住所建物までカラム位置
        final String addressBuilding = "住所建物";
        builder.append(quote).append(addressBuilding).append(quote).append(comma);

        // 郵便番号1カラム位置
        final String postal1 = "012";
        builder.append(quote).append(postal1).append(quote).append(comma);

        // 郵便番号2カラム位置
        final String postal2 = "3456";
        builder.append(quote).append(postal2).append(quote).append(comma);

        // 電話番号市外局番カラム位置
        final String phon1 = "012";
        builder.append(quote).append(phon1).append(quote).append(comma);

        // 電話番号局番カラム位置
        final String phon2 = "3456";
        builder.append(quote).append(phon2).append(quote).append(comma);

        // 電話番号番号カラム位置
        final String phon3 = "7890";
        builder.append(quote).append(phon3).append(quote).append(comma);

        // メールアドレスカラム位置
        final String email = "aaa@bbb.net";
        builder.append(quote).append(email).append(quote).append(comma);

        // 自分の公式サイトカラム位置
        final String myPortal = "https://myportal.net/";
        builder.append(quote).append(myPortal).append(quote).append(comma);

        // 外国籍該否カラム位置
        final String foreign = "はい";
        builder.append(quote).append(foreign).append(quote).append(comma);

        // 法人種別カラム位置
        final String shubetsu = "1";
        builder.append(quote).append(shubetsu).append(quote).append(comma);

        // 関連者団体名称かなカラム位置
        final String dantaiKana = "だんたいめい";
        builder.append(quote).append(dantaiKana).append(quote).append(comma);

        // 支店該当カラム位置
        final String shiten = "true";
        builder.append(quote).append(shiten).append(quote).append(comma);

        // 団体代表者関連者コードカラム位置
        final String delegateCode = "1111-22";
        builder.append(quote).append(delegateCode).append(quote).append(comma);

        // SNS名称カラム位置
        final String snsName = "弱小SNS";
        builder.append(quote).append(snsName).append(quote).append(comma);
        
        // SNSアカウントカラム位置
        final String snsAccount = "@bbb_ccc";
        builder.append(quote).append(snsAccount).append(quote).append(comma);
        
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

        PartnerCorpAddStdDto dto = partnerCorpAddStdLineMapper.mapLine(builder.toString(), 0);

        assertEquals(name, dto.getPartnerName());
        assertEquals(address, dto.getAllAddress());
        assertEquals(delegate, dto.getCorpDelegate());
        assertEquals(houjinNo, dto.getHoujinNo());
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
        assertEquals(shubetsu, dto.getHoujinSbts());
        assertEquals(dantaiKana, dto.getOrgNameKana());
        assertEquals(true, dto.getIsShiten());
        assertEquals(delegateCode, dto.getOrgDelegateCode());
        assertEquals(snsName, dto.getSnsServiceName());
        assertEquals(snsAccount, dto.getSnsAccount());
        assertEquals(lgcode, dto.getLgCode());
        assertEquals(machiaza, dto.getMachiazaId());
        assertEquals(block, dto.getBlkId());
        assertEquals(rsdt, dto.getRsdtId());
        assertEquals(rsdt2, dto.getRsdt2Id());
    }

}
