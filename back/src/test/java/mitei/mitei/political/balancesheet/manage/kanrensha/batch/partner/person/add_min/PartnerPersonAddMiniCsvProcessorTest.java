package mitei.mitei.political.balancesheet.manage.kanrensha.batch.partner.person.add_min;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.WkTblPartnerPersonAddMinEntity;

/**
 * PartnerPersonAddMiniCsvProcessor単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class PartnerPersonAddMiniCsvProcessorTest {

    /** テスト対象 */
    @Autowired
    private PartnerPersonAddMiniCsvProcessor partnerPersonAddMiniCsvProcessor;

    @Test
    @Tag("TableTruncate")
    @Transactional
    @Sql({ "sample_partner_person_history_01.sql", "sample_master_person.sql" })
    void test() throws Exception {

        // 未入力カラムがあると追加作業をしません
        WkTblPartnerPersonAddMinEntity minEntity00 = partnerPersonAddMiniCsvProcessor
                .process(new PartnerPersonAddMiniDto());
        assertEquals(false, minEntity00.getIsAffected());
        assertEquals("名称が入力されていません;住所が入力されていません;職業が入力されていません;", minEntity00.getJudgeReason());

        // 完全一致する履歴がある場合は追加できません
        PartnerPersonAddMiniDto dto02 = new PartnerPersonAddMiniDto();
        dto02.setPartnerName("迂回献金　太郎");
        dto02.setAllAddress("和歌山県架空市山麓町");
        dto02.setPersonShokugyou("医師");

        WkTblPartnerPersonAddMinEntity minEntity02 = partnerPersonAddMiniCsvProcessor.process(dto02);
        assertEquals(false, minEntity02.getIsAffected());
        assertEquals("すでに登録が存在します(12-3456);", minEntity02.getJudgeReason());

        // 同名の個人が存在する場合はケースバイケースですが、バッチによる自動登録はできません
        PartnerPersonAddMiniDto dto03 = new PartnerPersonAddMiniDto();
        dto03.setPartnerName("迂回献金　次郎");
        dto03.setAllAddress("宮崎県架空市山麓町");
        dto03.setPersonShokugyou("教師");

        WkTblPartnerPersonAddMinEntity minEntity03 = partnerPersonAddMiniCsvProcessor.process(dto03);
        assertEquals(false, minEntity03.getIsAffected());
        assertEquals("同名の個人があります。確認調査の上、必要に応じて追加してください;", minEntity03.getJudgeReason());

        // 全くの新規であれば追加作業します
        PartnerPersonAddMiniDto dto04 = new PartnerPersonAddMiniDto();
        dto04.setPartnerName("組合長　花子");
        dto04.setAllAddress("宮崎県実在市湖畔町");
        dto04.setPersonShokugyou("団体役員");

        WkTblPartnerPersonAddMinEntity minEntity04 = partnerPersonAddMiniCsvProcessor.process(dto04);
        assertEquals(true, minEntity04.getIsAffected());
        assertEquals("", minEntity04.getJudgeReason());

    }

}
