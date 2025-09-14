package mitei.mitei.political.balancesheet.manage.kanrensha.logic.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRiyoushaCombineEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRiyoushaCombineRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * ユーザと利用者紐づけ挿入Logic
 */
@Component
public class InsertCombineUserRiyoushaLogic {

    /** ユーザ利用者紐づけRepository */
    @Autowired
    private UserRiyoushaCombineRepository userRiyoushaCombineRepository;

    /** テーブル履歴設定Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /**
     * 処理を行う
     *
     * @param userCode ユーザコード
     * @param role ロール
     * @param riyoushaCode 利用者コード
     * @param userDto 操作者
     * @return 処理結果(挿入Id)
     */
    public Integer practcie(final Integer userCode, final String role, final Integer riyoushaCode,
            final UserPersonLeastDto userDto) {

        UserRiyoushaCombineEntity combineEntity = new UserRiyoushaCombineEntity();
        combineEntity.setRole(role);
        combineEntity.setUseUserCode(userCode);
        combineEntity.setRiyoushaCode(riyoushaCode);

        setTableDataHistoryUtil.practiceInsert(userDto, combineEntity);
        combineEntity.setUserRiyoushaCombineId(0); // auto_increment明示
        
        return userRiyoushaCombineRepository.save(combineEntity).getUserRiyoushaCombineId();
    }

}
