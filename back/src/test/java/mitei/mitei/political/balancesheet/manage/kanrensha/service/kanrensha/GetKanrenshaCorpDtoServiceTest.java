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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputKanrenshaPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputOrgNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaCorpDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterCorporationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterCorporationRepository;

/**
 * GetKanrenshaCorpDtoService単体テスト
 */
@SpringBootTest
@Transactional
class GetKanrenshaCorpDtoServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetKanrenshaCorpDtoService getKanrenshaCorpDtoService;

    /** 関連者個人マスタRepository */
    @Autowired
    private MasterCorporationRepository masterCorporationRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("EditKanrenshaCorpServiceTest.sql")
    void test() throws Exception {

        MasterCorporationEntity entityMaster = masterCorporationRepository.findById(191).get();

        KanrenshaCorpDto kanrenshaCorpDto = getKanrenshaCorpDtoService.practice(entityMaster);

        // 読み込まれたEntityのIdを確認
        assertEquals(191, kanrenshaCorpDto.getMasterId());
        assertEquals(2601, kanrenshaCorpDto.getAccessId());
        assertEquals(2201, kanrenshaCorpDto.getAddressId());
        assertEquals(2301, kanrenshaCorpDto.getBaseId());
        assertEquals(2401, kanrenshaCorpDto.getPropertyId());

        // 関連者コード
        assertEquals("111-222-3333", kanrenshaCorpDto.getCorpKanrenshaCode());

        // 団体名
        InputOrgNameDto inputOrgNameDto = kanrenshaCorpDto.getInputOrgNameDto();
        assertEquals("ぼったくり企業", inputOrgNameDto.getOrgName());
        assertEquals("テストホウジンイチ", inputOrgNameDto.getOrgNameKana());

        // 連絡先
        InputAccessDto inputAccessDto = kanrenshaCorpDto.getInputAccessDto();
        assertEquals("012", inputAccessDto.getPhon1());
        assertEquals("345", inputAccessDto.getPhon2());
        assertEquals("6789", inputAccessDto.getPhon3());
        assertEquals("aaa@example.com", inputAccessDto.getEmail());
        assertEquals("https://example.com/blog", inputAccessDto.getMyPortalUrl());
        assertEquals("弱小SNS", inputAccessDto.getSnsServiceName());
        assertEquals("https://example.com/?acount=222", inputAccessDto.getSnsPortalUrl());
        assertEquals("@corp", inputAccessDto.getSnsAccount());

        // 住所
        InputAddressDto inputAddressDto = kanrenshaCorpDto.getInputAddressDto();
        assertEquals("和歌山県実在市山麓町", inputAddressDto.getAddressAll());
        // assertEquals("", inputAddressDto.getOrginAddressAll());
        assertEquals("100", inputAddressDto.getPostalcode1());
        assertEquals("0001", inputAddressDto.getPostalcode2());
        assertEquals("100-0001", inputAddressDto.getAddressPostal());
        assertEquals("千代田区千代田１−１", inputAddressDto.getAddressBlock());
        assertEquals("テストビル1", inputAddressDto.getAddressBuilding());
        assertEquals("131016", inputAddressDto.getLgCode());
        assertEquals("0001000", inputAddressDto.getMachiazaId());
        assertEquals("001", inputAddressDto.getBlkId());
        assertEquals("001", inputAddressDto.getRsdtId());
        assertEquals(false, inputAddressDto.getIsPostalEdit());
        assertEquals(false, inputAddressDto.getIsBlockEdit());
        assertEquals(false, inputAddressDto.getIsBuildingEdit());

        // 団体代表者
        InputKanrenshaPersonLeastDto delegateDto = kanrenshaCorpDto.getOrgDelegateLeastDto();
        assertEquals("P000000001", delegateDto.getPersonKanrenshaCode());
        assertEquals("代表者　太郎", delegateDto.getPersonName());

        // 法人番号関連
        assertEquals("1-22-33", kanrenshaCorpDto.getHoujinNo());
        assertEquals("101", kanrenshaCorpDto.getHoujinSbts());
        assertEquals(false, kanrenshaCorpDto.getIsShiten());
    }

}
