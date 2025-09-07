package mitei.mitei.political.balancesheet.manage.kanrensha.service.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputAccessDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;

/**
 * GetKanrenshaPersonDtoService単体テスト
 */
@SpringBootTest
@Transactional
class GetKanrenshaPersonDtoServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetKanrenshaPersonDtoService getKanrenshaPersonDtoService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterPersonRepository masterPersonRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("EditKanrenshaPersonServiceTest.sql")
    void test() throws Exception {

        MasterPersonEntity entityMaster = masterPersonRepository.findById(901).get();

        KanrenshaPersonDto kanrenshaPersonDto = getKanrenshaPersonDtoService.practice(entityMaster);

        // 呼び出したEntityのidが保存されていること
        assertEquals(901, kanrenshaPersonDto.getMasterId());
        assertEquals(902, kanrenshaPersonDto.getAccessId());
        assertEquals(903, kanrenshaPersonDto.getAddressId());
        assertEquals(904, kanrenshaPersonDto.getBaseId());
        assertEquals(905, kanrenshaPersonDto.getPropertyId());

        InputAccessDto inputAccessDto = kanrenshaPersonDto.getInputAccessDto();
        assertEquals("access@example.com", inputAccessDto.getEmail());

        InputAddressDto inputAddressDto = kanrenshaPersonDto.getInputAddressDto();
        assertEquals("サービス住所", inputAddressDto.getAddressAll());
        assertEquals("サービスビル", inputAddressDto.getAddressBuilding());

        InputPersonNameDto inputPersonNameDto = kanrenshaPersonDto.getInputPersonNameDto();
        assertEquals("サービス太郎", inputPersonNameDto.getAllName());
        assertEquals("太郎", inputPersonNameDto.getFirstName());

        InputShokugyouDto inputShokugyouDto = kanrenshaPersonDto.getInputShokugyouDto();
        assertEquals("サービス職業", inputShokugyouDto.getAllShokugyou());
        assertEquals("部長", inputShokugyouDto.getYakushoku());

        assertEquals(false, kanrenshaPersonDto.getIsForeign());
    }

}
