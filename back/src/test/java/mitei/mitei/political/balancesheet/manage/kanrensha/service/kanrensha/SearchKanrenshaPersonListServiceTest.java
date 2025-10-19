package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

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
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.NaturalTextSearchPagingCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SearchKanrenshaPersonResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;

/**
 * SearchKanrenshaPersonListService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
class SearchKanrenshaPersonListServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private SearchKanrenshaPersonListService searchKanrenshaPersonListService;

    @Test
    @Tag("TableTruncate") // NOPMD
    @Transactional
    @Sql("master_person.sql")
    void test() {

        NaturalTextSearchPagingCapsuleDto capsuleDto = new NaturalTextSearchPagingCapsuleDto();
        capsuleDto.setAllCount(0);
        capsuleDto.setLimit(30);
        capsuleDto.setPageNumber(0);

        SearchKanrenshaPersonResultDto resultDto = searchKanrenshaPersonListService.practice(capsuleDto);

        List<MasterPersonEntity> list = resultDto.getListMasterPerson();
        assertEquals(resultDto.getAllCount(), list.size());
        assertEquals(capsuleDto.getLimit(), resultDto.getLimit());
        assertEquals(capsuleDto.getPageNumber(), resultDto.getPageNumber()); // 用意したテストデータではページ番号初期化はされない

        // 2件取得できて想定通りのIdが取得できている
        assertEquals(2, list.size());
        MasterPersonEntity entity00 = list.get(0);
        assertEquals(825, entity00.getMasterPersonId());
        MasterPersonEntity entity01 = list.get(1);
        assertEquals(827, entity01.getMasterPersonId());
    }

}
