package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendInviteCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaAdminEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaAdminRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaInviteNewRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者組織紐づけ発行コードService
 */
@Service
public class InviteNewPersonPublishCodeService {

    /** 利用者組織個人紐づけ用コードRepository */
    @Autowired
    private RiyoushaInviteNewRepository riyoushaInviteNewRepository;

    /** 利用者管理者Repository */
    @Autowired
    private RiyoushaAdminRepository riyoushaAdminRepository;

    /** 利用者管理者Repository */
    @Autowired
    private RiyoushaComradeRepository riyoushaComradeRepository;

    /** 利用者運営者Repository */
    @Autowired
    private RiyoushaManagerRepository riyoushaManagerRepository;

    /** ユーザ個人Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param capsuleDto コード発行Dto
     * @return 発行されたテーブルId
     */
    @Transactional
    public Integer practice(final SendInviteCodeCapsuleDto capsuleDto) {

        RiyoushaInviteNewEntity inviteNewEntity = new RiyoushaInviteNewEntity();

        inviteNewEntity.setDantaiRole(capsuleDto.getOrgRole());
        inviteNewEntity.setMailAddress(capsuleDto.getPersonMail());
        inviteNewEntity.setRegistCode(UUID.randomUUID().toString());

        Optional<UserPersonEntity> optional = userPersonRepository.findLatestByMail(capsuleDto.getPersonMail());
        if (optional.isEmpty()) {
            throw new EmptyResultDataAccessException("メールアドレスから個人が呼び出せない", 1);
        }

        UserPersonEntity personEntity = optional.get();

        inviteNewEntity.setPersonUserId(personEntity.getUserPersonId());
        inviteNewEntity.setPersonUserCode(personEntity.getUserPersonCode());
        inviteNewEntity.setPersonUserName(personEntity.getUserPersonName());

        // 団体を呼び出して保存
        UserPersonLeastDto orgDto = this.getOrgLeast(capsuleDto.getOrgId(), capsuleDto.getOrgRole());
        inviteNewEntity.setRiyoushaDantaiId(orgDto.getUserPersonId());
        inviteNewEntity.setRiyoushaDantaiCode(orgDto.getUserPersonCode());
        inviteNewEntity.setRiyoushaDantaiName(orgDto.getUserPersonName());

        setTableDataHistoryUtil.practiceInsert(capsuleDto.getUserPersonLeastDto(), inviteNewEntity);
        inviteNewEntity.setRiyoushaInviteNewId(0); // auto increment明示

        return riyoushaInviteNewRepository.save(inviteNewEntity).getRiyoushaInviteNewId();
    }

    private UserPersonLeastDto getOrgLeast(final Integer orgId, final String orgRole) {

        UserPersonLeastDto orgDto = new UserPersonLeastDto();
        switch (orgRole) {
            case UserRoleConstants.ROLE_ADMIN:
                Optional<RiyoushaAdminEntity> optionalAdmin = riyoushaAdminRepository.findById(orgId);
                if (optionalAdmin.isEmpty()) {
                    throw new EmptyResultDataAccessException("紐づけ団体を呼び出せません", 1);
                } else {
                    RiyoushaAdminEntity entity = optionalAdmin.get();
                    orgDto.setUserPersonId(entity.getRiyoushaAdminId());
                    orgDto.setUserPersonCode(entity.getRiyoushaAdminCode());
                    orgDto.setUserPersonName(entity.getRiyoushaAdminName());
                    return orgDto;
                }
            case UserRoleConstants.ROLE_COMRADE:
                Optional<RiyoushaComradeEntity> optionalComrade = riyoushaComradeRepository.findById(orgId);
                if (optionalComrade.isEmpty()) {
                    throw new EmptyResultDataAccessException("紐づけ団体を呼び出せません", 1);
                } else {
                    RiyoushaComradeEntity entity = optionalComrade.get();
                    orgDto.setUserPersonId(entity.getRiyoushaComradeId());
                    orgDto.setUserPersonCode(entity.getRiyoushaComradeCode());
                    orgDto.setUserPersonName(entity.getRiyoushaComradeName());
                    return orgDto;
                }
            case UserRoleConstants.ROLE_MANAGER:
                Optional<RiyoushaManagerEntity> optionalManager = riyoushaManagerRepository.findById(orgId);
                if (optionalManager.isEmpty()) {
                    throw new EmptyResultDataAccessException("紐づけ団体を呼び出せません", 1);
                } else {
                    RiyoushaManagerEntity entity = optionalManager.get();
                    orgDto.setUserPersonId(entity.getRiyoushaManagerId());
                    orgDto.setUserPersonCode(entity.getRiyoushaManagerCode());
                    orgDto.setUserPersonName(entity.getRiyoushaManagerName());
                    return orgDto;
                }
            default:
                throw new IllegalArgumentException("Unexpected value: " + orgRole);
        }
    }
}
