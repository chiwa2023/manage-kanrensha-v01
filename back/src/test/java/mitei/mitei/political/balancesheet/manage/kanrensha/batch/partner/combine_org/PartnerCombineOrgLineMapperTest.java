package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.combine_org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * PartnerCombineOrgLineMapper単体テスト
 */
class PartnerCombineOrgLineMapperTest {

    @Test
    void test() throws Exception {

        PartnerCombineOrgLineMapper lineMapper = new PartnerCombineOrgLineMapper();

        PartnerCombineOrgDto dto = lineMapper.mapLine(
                "\"12-34567-8901-2345-67890\",\"迂回献金　太郎\",\"1-2345-67-890123-4567890\",\"ふんだくり企業\",\"2021\",\"abcd\"",
                0);

        assertEquals("12-34567-8901-2345-67890", dto.getPersonKanrenshaCode());
        assertEquals("迂回献金　太郎", dto.getPersonName());
        assertEquals("1-2345-67-890123-4567890", dto.getOrgKanrenshaCode());
        assertEquals("ふんだくり企業", dto.getOrgName());
        assertEquals(Short.valueOf("2021"), dto.getStartYear());
        assertEquals(Short.valueOf("-1"), dto.getEndYear()); // 数字変換できないときは-1
    }

}
