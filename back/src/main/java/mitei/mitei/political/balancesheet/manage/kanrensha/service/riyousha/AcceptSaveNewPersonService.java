package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mitei.mitei.political.balancesheet.manage.kanrensha.constants.UserRoleConstants;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgComradeEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaOrgManagerEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaInviteNewRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgComradeRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaOrgManagerRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者組織と個人紐づけ登録Service
 */
@Service
public class AcceptSaveNewPersonService {

    /** APIユーザ利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaOrgComradeRepository riyoushaOrgComradeRepository;

    /** APIユーザ利用者組織個人紐づけRepository */
    @Autowired
    private RiyoushaOrgManagerRepository riyoushaOrgManagerRepository;

    /** 組織招待承認コードRepository */
    @Autowired
    private RiyoushaInviteNewRepository riyoushaInviteNewRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 未処理結果0 */
    private static final Integer ZERO = 0;

    /**
     * 処理を行う
     *
     * @param capsuleDto 承認コード照合Dto
     * @return 処理結果
     */
    @Transactional
    public Integer practice(final SendAcceptCodeCapsuleDto capsuleDto) {

        // 入力コードのチェック
        RiyoushaInviteNewEntity riyoushaInviteNewEntity = capsuleDto.getRiyoushaInviteNewEntity();
        if (!capsuleDto.getInputAcceptCode().equals(riyoushaInviteNewEntity.getRegistCode())) {
            return -1;
        }

        // TODO 組織紐づけテーブルを組織権限で分けてしまったが、統一する予定
        UserPersonLeastDto userDto = capsuleDto.getUserPersonLeastDto();

        Integer newId;
        if (UserRoleConstants.ROLE_COMRADE.equals(riyoushaInviteNewEntity.getDantaiRole())) {
            newId = this.saveOrgComrade(riyoushaInviteNewEntity, userDto);
        } else {
            newId = this.saveOrgManager(riyoushaInviteNewEntity, userDto);
        }

        // 照合できて紐づけを記録したら承認コードを履歴に変更
        if (ZERO.equals(newId)) {
            return 0;
        } else {
            setTableDataHistoryUtil.practiceDelete(userDto, riyoushaInviteNewEntity);
            riyoushaInviteNewRepository.save(riyoushaInviteNewEntity);
            return newId;
        }

    }

    private Integer saveOrgComrade(final RiyoushaInviteNewEntity riyoushaInviteNewEntity,
            final UserPersonLeastDto userDto) {

        RiyoushaOrgComradeEntity riyoushaOrgEntity = new RiyoushaOrgComradeEntity();

        riyoushaOrgEntity.setRiyoushaOrgCode(riyoushaInviteNewEntity.getRiyoushaDantaiCode());
        riyoushaOrgEntity.setRiyoushaPersonCode(riyoushaInviteNewEntity.getPersonUserCode());

        setTableDataHistoryUtil.practiceInsert(userDto, riyoushaOrgEntity);
        riyoushaOrgEntity.setRiyoushaOrgComradeId(0); // auto increment明示
        return riyoushaOrgComradeRepository.save(riyoushaOrgEntity).getRiyoushaOrgComradeId();
    }

    private Integer saveOrgManager(final RiyoushaInviteNewEntity riyoushaInviteNewEntity,
            final UserPersonLeastDto userDto) {

        RiyoushaOrgManagerEntity riyoushaOrgEntity = new RiyoushaOrgManagerEntity();

        riyoushaOrgEntity.setRiyoushaOrgCode(riyoushaInviteNewEntity.getRiyoushaDantaiCode());
        riyoushaOrgEntity.setRiyoushaPersonCode(riyoushaInviteNewEntity.getPersonUserCode());

        setTableDataHistoryUtil.practiceInsert(userDto, riyoushaOrgEntity);
        riyoushaOrgEntity.setRiyoushaOrgManagerId(0); // auto increment明示
        return riyoushaOrgManagerRepository.save(riyoushaOrgEntity).getRiyoushaOrgManagerId();
    }

}
