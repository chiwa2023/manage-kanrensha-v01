package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaInviteNewRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.CreateLeastUserForTestUtil;

/**
 * AcceptSaveNewPersonService単体テスト
 */
@SpringJUnitConfig
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
@Sql("AcceptSaveNewPersonServiceTest.sql")
class AcceptSaveNewPersonServiceTest {

    /** テスト対象 */
    @Autowired
    private AcceptSaveNewPersonService acceptSaveNewPersonService;

    /** APIユーザ利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaOrgComradeRepository riyoushaOrgComradeRepository;

    /** APIユーザ利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaOrgManagerRepository riyoushaOrgManagerRepository;

    /** 組織招待承認コードRepository */
    @Autowired
    private RiyoushaInviteNewRepository riyoushaInviteNewRepository;

    @Test
    @Tag("TableTruncate")
    void testComrade() throws Exception {

        SendAcceptCodeCapsuleDto capsuleDto = new SendAcceptCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        final int codeTableId = 101;
        RiyoushaInviteNewEntity inviteNewEntity = riyoushaInviteNewRepository.findById(codeTableId).get();
        capsuleDto.setRiyoushaInviteNewEntity(inviteNewEntity);

        // コードをちゃんとコピペした
        capsuleDto.setInputAcceptCode(inviteNewEntity.getRegistCode());

        Integer newId = acceptSaveNewPersonService.practice(capsuleDto);

        assertNotEquals(0, newId);
        RiyoushaOrgComradeEntity orgEntity = riyoushaOrgComradeRepository.findById(newId).get();
        assertEquals(inviteNewEntity.getRiyoushaDantaiCode(), orgEntity.getRiyoushaOrgCode());
        assertEquals(inviteNewEntity.getPersonUserCode(), orgEntity.getRiyoushaPersonCode());

        // 承認コードテーブルは履歴に変更
        RiyoushaInviteNewEntity inviteNewEntityHistory = riyoushaInviteNewRepository.findById(codeTableId).get();
        assertEquals(false, inviteNewEntityHistory.getIsLatest(), "コード登録は履歴であること");
    }

    @Test
    @Tag("TableTruncate")
    void testManager() throws Exception {

        SendAcceptCodeCapsuleDto capsuleDto = new SendAcceptCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        final int codeTableId = 201;
        RiyoushaInviteNewEntity inviteNewEntity = riyoushaInviteNewRepository.findById(codeTableId).get();
        capsuleDto.setRiyoushaInviteNewEntity(inviteNewEntity);

        // コードをちゃんとコピペした
        capsuleDto.setInputAcceptCode(inviteNewEntity.getRegistCode());

        Integer newId = acceptSaveNewPersonService.practice(capsuleDto);

        assertNotEquals(0, newId);
        RiyoushaOrgManagerEntity orgEntity = riyoushaOrgManagerRepository.findById(newId).get();
        assertEquals(inviteNewEntity.getRiyoushaDantaiCode(), orgEntity.getRiyoushaOrgCode());
        assertEquals(inviteNewEntity.getPersonUserCode(), orgEntity.getRiyoushaPersonCode());

        // 承認コードテーブルは履歴に変更
        RiyoushaInviteNewEntity inviteNewEntityHistory = riyoushaInviteNewRepository.findById(codeTableId).get();
        assertEquals(false, inviteNewEntityHistory.getIsLatest(), "コード登録は履歴であること");
    }

    @Test
    @Tag("TableTruncate")
    void testWrongInputCode() throws Exception {

        SendAcceptCodeCapsuleDto capsuleDto = new SendAcceptCodeCapsuleDto();
        capsuleDto.setUserPersonLeastDto(CreateLeastUserForTestUtil.practice());

        final int codeTableId = 101;
        RiyoushaInviteNewEntity inviteNewEntity = riyoushaInviteNewRepository.findById(codeTableId).get();
        capsuleDto.setRiyoushaInviteNewEntity(inviteNewEntity);

        // コードがコピペできていない
        capsuleDto.setInputAcceptCode(inviteNewEntity.getRegistCode() + "1-3-4-");

        Integer newId = acceptSaveNewPersonService.practice(capsuleDto);

        assertEquals(-1, newId);
    }

}
