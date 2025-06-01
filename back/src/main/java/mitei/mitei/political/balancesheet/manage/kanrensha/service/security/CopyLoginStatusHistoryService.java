package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025.CopyLoginStatusHistoryY2025Logic;

/**
 * ログイン履歴複写を実施年ごとに行うService
 */
@Service
public class CopyLoginStatusHistoryService {

    /** 登録対応年(2025) */
    private static final int YEAR_2025 = 2025;
    /** ログイン履歴複写Logic(2025) */
    @Autowired
    private CopyLoginStatusHistoryY2025Logic copyLoginStatusHistoryY2025Logic;

    /**
     * 処理を行う
     *
     * @param statusEntity ログイン状態Entity
     * @param year         現在時刻の年
     */
    public void practice(final LoginStatusEntity statusEntity, final Integer year) {

        switch (year) {
            case YEAR_2025:
                copyLoginStatusHistoryY2025Logic.practice(statusEntity);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + year);
        }

    }
}
