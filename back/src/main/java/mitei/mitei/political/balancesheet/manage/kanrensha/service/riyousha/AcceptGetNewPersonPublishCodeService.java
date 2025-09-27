package mitei.mitei.political.balancesheet.manage.kanrensha.service.riyousha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.riyousha.SendAcceptCodeResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.RiyoushaInviteNewEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.RiyoushaInviteNewRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 利用者組織個人承認コード取得Service
 */
@Service
public class AcceptGetNewPersonPublishCodeService {

    /** 組織招待承認コードRepository */
    @Autowired
    private RiyoushaInviteNewRepository riyoushaInviteNewRepository;

    /**
     * 処理を行う
     *
     * @param userDto ユーザ最小限Dto
     * @return 承認コード格納Dto
     */
    public SendAcceptCodeResultDto practice(final UserPersonLeastDto userDto) {
        
        List<RiyoushaInviteNewEntity> listNew = riyoushaInviteNewRepository
                .findByPersonUserCodeAndIsLatest(userDto.getUserPersonCode(), SetTableDataHistoryUtil.INSERT_STATE);

        SendAcceptCodeResultDto resultDto = new SendAcceptCodeResultDto();
        // 取得できないときはメッセージ(システム的例外ではない)
        if (listNew.isEmpty()) {
            resultDto.setIsFailure(true);
            resultDto.setMessage("招待コードが取得できませんでした");
        }

        // 複数の組織から同時に招待を受けたら対応する
        resultDto.setListAcceptCode(listNew);

        return resultDto;
    }
}
