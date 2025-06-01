package mitei.mitei.political.balancesheet.manage.kanrensha.logic.year.y2025;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.year.y2025.LoginHistory2025Entity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.year.y2025.LoginHistory2025Repository;

/**
 * 前回のログイン状態を履歴に複写する(2025)
 */
@Component
public class CopyLoginStatusHistoryY2025Logic {

    /** ログイン履歴Respository(2025) */
    @Autowired
    private LoginHistory2025Repository loginHistory2025Repository;

    /**
     * 処理を行う
     *
     * @param statusEntity ログイン状態Entity
     */
    public void practice(final LoginStatusEntity statusEntity) {

        LoginHistory2025Entity historyEntity = new LoginHistory2025Entity();
        BeanUtils.copyProperties(statusEntity, historyEntity);
        historyEntity.setLoginHistoryId(0); // auto_increment明示

        loginHistory2025Repository.save(historyEntity);
    }
}
