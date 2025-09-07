package mitei.mitei.political.balancesheet.manage.kanrensha.logic.kanrensha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.address.InputAddressDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputPersonNameDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.input.InputShokugyouDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.KanrenshaPersonDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.user.SaveKanrenshaPersonCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.MasterPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.MasterPersonRepository;

/**
 * EditMasterPersonLogicのテスト
 *
 * @author chiwaki2023
 * @author supported by Gemini CLI
 */
@SpringBootTest
@Transactional
@Sql("EditMasterPersonLogicTest.sql")
class EditMasterPersonLogicTest {
    // CHECKSTYLE:OFF

    /** テスト対象 */
    @Autowired
    private EditMasterPersonLogic logic;

    /** 関連者個人マスタrepository */
    @Autowired
    private MasterPersonRepository repository;

    private SaveKanrenshaPersonCapsuleDto createCapsuleDto(final Integer masterId, final String partnerName,
            final String address, final String shokugyou) {
        KanrenshaPersonDto personDto = new KanrenshaPersonDto();
        personDto.setMasterId(masterId);

        InputPersonNameDto nameDto = new InputPersonNameDto();
        nameDto.setAllName(partnerName);
        personDto.setInputPersonNameDto(nameDto);

        InputAddressDto addressDto = new InputAddressDto();
        addressDto.setAddressAll(address);
        personDto.setInputAddressDto(addressDto);

        InputShokugyouDto shokugyouDto = new InputShokugyouDto();
        shokugyouDto.setAllShokugyou(shokugyou);
        personDto.setInputShokugyouDto(shokugyouDto);

        UserPersonLeastDto userDto = new UserPersonLeastDto();
        userDto.setUserPersonId(99);
        userDto.setUserPersonCode(999);
        userDto.setUserPersonName("test-updater");

        SaveKanrenshaPersonCapsuleDto capsuleDto = new SaveKanrenshaPersonCapsuleDto();
        capsuleDto.setKanrenshaPersonDto(personDto);
        capsuleDto.setUserPersonLeastDto(userDto);
        return capsuleDto;
    }

    @Test
    void testUpdateOccurs() {
        // Arrange: DBと異なる値を持つDTOを作成
        final int originalId = 501;
        final String kanrenshaCode = "P0501";
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(originalId, "更新後 太郎", "更新後住所", "更新後職業");

        // Act
        Integer newId = logic.practice(capsuleDto);

        // Assert
        assertNotEquals(0, newId, "新しいIDが返却されること");
        assertNotEquals(originalId, newId, "元のIDとは異なるIDが返却されること");

        // 元のレコードが更新されていることを確認
        MasterPersonEntity oldEntity = repository.findById(originalId).orElseThrow();
        assertFalse(oldEntity.getIsLatest(), "元のレコードのis_latestがfalseになっていること");
        assertEquals(99, oldEntity.getDeleteUserId(), "無効化したユーザーIDが設定されていること");

        // 新しいレコードが作成されていることを確認
        MasterPersonEntity newEntity = repository.findById(newId).orElseThrow();
        assertTrue(newEntity.getIsLatest(), "新しいレコードのis_latestがtrueになっていること");
        assertEquals("更新後 太郎", newEntity.getPartnerName(), "新しいデータがDTOの値で登録されていること");
        assertEquals(kanrenshaCode, newEntity.getPersonKanrenshaCode(), "関連者コードが引き継がれていること");
        assertEquals(99, newEntity.getInsertUserId(), "挿入したユーザーIDが設定されていること");
    }

    @Test
    void testNoUpdateOccurs() {
        // Arrange: DBと完全に同じ値を持つDTOを作成
        SaveKanrenshaPersonCapsuleDto capsuleDto = createCapsuleDto(501, "更新前 太郎", "更新前住所", "更新前職業");

        // Act
        Integer resultId = logic.practice(capsuleDto);

        // Assert
        assertEquals(0, resultId, "変更がない場合は0が返却されること");

        // レコードが追加されていないことを確認
        List<MasterPersonEntity> entities = repository.findAll();
        assertEquals(1, entities.size(), "レコード数は1のままであること");
    }
}
