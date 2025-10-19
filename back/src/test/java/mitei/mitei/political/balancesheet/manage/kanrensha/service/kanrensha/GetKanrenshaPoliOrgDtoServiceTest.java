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
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPoliOrgDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPoliticalOrganizationEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPoliticalOrganizationRepository;

/**
 * GetKanrenshaPoliOrgDtoService単体テスト
 */
@SpringBootTest
@Transactional
class GetKanrenshaPoliOrgDtoServiceTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private GetKanrenshaPoliOrgDtoService getKanrenshaPoliOrgDtoService;

    /** 関連者政治団体マスタRepository */
    @Autowired
    private MasterPoliticalOrganizationRepository masterPoliticalOrganizationRepository;

    @Test
    @Tag("TableTruncate")
    @Sql("EditKanrenshaPoliOrgServiceTest.sql")
    void test() throws Exception {

        MasterPoliticalOrganizationEntity entityMaster = masterPoliticalOrganizationRepository.findById(724).get();

        KanrenshaPoliOrgDto kanrenshaPoliOrgDto = getKanrenshaPoliOrgDtoService.practice(entityMaster);

        // 読み込まれたEntityのIdを確認
        assertEquals(724, kanrenshaPoliOrgDto.getMasterId());
        assertEquals(601, kanrenshaPoliOrgDto.getAccessId());
        assertEquals(701, kanrenshaPoliOrgDto.getAddressId());
        assertEquals(801, kanrenshaPoliOrgDto.getBaseId());
        assertEquals(901, kanrenshaPoliOrgDto.getPropertyId());

        // 関連者コード
        assertEquals("12-345-ABCCDEF", kanrenshaPoliOrgDto.getPoliOrgKanrenshaCode());
        
        // 必須政治団体区分
        assertEquals("05", kanrenshaPoliOrgDto.getDantaiKbn());

        // 団体名
        InputOrgNameDto inputOrgNameDto = kanrenshaPoliOrgDto.getInputOrgNameDto();
        assertEquals("陰謀論政治団体", inputOrgNameDto.getOrgName());
        assertEquals("テストセイジダンタイ1", inputOrgNameDto.getOrgNameKana());

        // 連絡先
        InputAccessDto inputAccessDto = kanrenshaPoliOrgDto.getInputAccessDto();
        assertEquals("012", inputAccessDto.getPhon1());
        assertEquals("345", inputAccessDto.getPhon2());
        assertEquals("6789", inputAccessDto.getPhon3());
        assertEquals("org1@example.com", inputAccessDto.getEmail());
        assertEquals("https://example.com/org1", inputAccessDto.getMyPortalUrl());
        assertEquals("テストSNS", inputAccessDto.getSnsServiceName());
        assertEquals("https://sns.example.com", inputAccessDto.getSnsPortalUrl());
        assertEquals("@org1", inputAccessDto.getSnsAccount());

        // 住所
        InputAddressDto inputAddressDto = kanrenshaPoliOrgDto.getInputAddressDto();
        assertEquals("和歌山県架空市実在町", inputAddressDto.getAddressAll());
        // assertEquals("", inputAddressDto.getOrginAddressAll());
        assertEquals("100", inputAddressDto.getPostalcode1());
        assertEquals("0001", inputAddressDto.getPostalcode2());
        assertEquals("100-0001", inputAddressDto.getAddressPostal());
        assertEquals("千代田区千代田１−１", inputAddressDto.getAddressBlock());
        assertEquals("宮殿", inputAddressDto.getAddressBuilding());
        assertEquals("131016", inputAddressDto.getLgCode());
        assertEquals("0001000", inputAddressDto.getMachiazaId());
        assertEquals("001", inputAddressDto.getBlkId());
        assertEquals("001", inputAddressDto.getRsdtId());
        assertEquals(false, inputAddressDto.getIsPostalEdit());
        assertEquals(false, inputAddressDto.getIsBlockEdit());
        assertEquals(false, inputAddressDto.getIsBuildingEdit());

        // 会計責任者
        InputKanrenshaPersonLeastDto accountmgrDto = kanrenshaPoliOrgDto.getAccounrMgrLeastDto();
        assertEquals("P000000001", accountmgrDto.getPersonKanrenshaCode());
        assertEquals("会計管理者1", accountmgrDto.getPersonName());

        // 団体代表者
        InputKanrenshaPersonLeastDto delegateDto = kanrenshaPoliOrgDto.getOrgDelegateLeastDto();
        assertEquals("P000000007", delegateDto.getPersonKanrenshaCode());
        assertEquals("代表者　太郎", delegateDto.getPersonName());

    }

}
