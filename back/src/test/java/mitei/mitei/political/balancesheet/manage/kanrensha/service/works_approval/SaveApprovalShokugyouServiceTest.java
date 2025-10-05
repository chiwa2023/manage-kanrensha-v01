package mitei.mitei.political.balancesheet.manage.kanrensha.service.works_approval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonBaseEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonBaseRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * SaveApprovalShokugyouService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("SearchApprovalShokugyouServiceTest.sql")
class SaveApprovalShokugyouServiceTest {
    // CHECKSTYLE:OFF MagicNumber

    /** テスト対象 */
    @Autowired
    private SaveApprovalShokugyouService saveApprovalShokugyouService;

    /** 関連者個人BaseRespository */
    @Autowired
    private MasterPersonBaseRepository masterPersonBaseRepository;

    @Test
    @Tag("TableTruncate")
    void test() throws Exception {

        List<MasterPersonBaseEntity> listShokugyou = masterPersonBaseRepository.findAll();

        final String newGyoushu = "小売";
        listShokugyou.get(0).setGyoushu(newGyoushu);
        // 画面上は変更できない姓名に関する事項を変更しても変更対象ではない
        listShokugyou.get(1).setFirstName("職業");

        Integer affectedRow = saveApprovalShokugyouService.practice(listShokugyou,
                CreateLeastUserForTestUtil.practice());

        assertEquals(1, affectedRow);

        List<MasterPersonBaseEntity> listResult = masterPersonBaseRepository.findAll();
        assertEquals(4, listResult.size());

        assertEquals(false, listResult.get(0).getIsLatest());
        // 最後の新規行は最新で指定された値が入っている
        assertEquals(true, listResult.get(3).getIsLatest());
        assertEquals(newGyoushu, listResult.get(3).getGyoushu());
    }

}
