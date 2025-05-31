package mitei.mitei.political.balancesheet.manage.kanrensha.service.security;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.LoginStatusRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRoleRepository;

/**
 * 独自ユーザ詳細Manager
 */
@Component
public class CustomUserDetailsManager implements UserDetailsManager {

    /** ログイン状態Repository */
    @Autowired
    private LoginStatusRepository loginStatusRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** ユーザ権限Repository */
    @Autowired
    private CopyLoginStatusHistoryService copyLoginStatusHistoryService;

    /** パスワード変更期限 */
    private static final long LIMIT_PASS_CHANGE = 12L;

    /** 無活動期限 */
    private static final long LIMIT_ACTIVE = 2L;

    /**
     * ユーザ名で該当データを呼び出す
     */
    @Override
    public UserDetails loadUserByUsername(final String username) {

        // 全回路訃音記録を呼び出して履歴に保存する
        LoginStatusEntity statusEntity = loginStatusRepository.findById(username).get();
        LocalDateTime now = LocalDateTime.now();
        copyLoginStatusHistoryService.practice(statusEntity, now.getYear());

        // 権限を呼び出してUserDetailを作成する
        List<String> listRole = userRoleRepository.findLatestRoleByMail(username);

        return User.builder().username(statusEntity.getEmail()).password(statusEntity.getPassword())
                .accountExpired(statusEntity.getLoginTime().plusYears(LIMIT_ACTIVE).isBefore(now)) // x年無活動なのでアカウントロックした
                .accountLocked(false)
                .credentialsExpired(statusEntity.getPassChangeTime().plusMonths(LIMIT_PASS_CHANGE).isBefore(now)) // xか月パスワード更新なしなのでアカウントロックした
                .disabled(statusEntity.getDisabled()).roles(listRole.toArray(new String[listRole.size()])).build(); // NOPMD

    }

    /**
     * 新規ユーザを追加する
     */
    @Override
    public void createUser(final UserDetails user) {
        // 初回ログイン処理を行う
        LoginStatusEntity loginStatusEntity = new LoginStatusEntity();
        LocalDateTime now = LocalDateTime.now();
        String mail = user.getUsername();
        loginStatusEntity.setEmail(mail);
        loginStatusEntity.setPassword(user.getPassword());
        loginStatusEntity.setIsSuccess(true);
        loginStatusEntity.setDisabled(false);
        loginStatusEntity.setLoginTime(now);
        loginStatusEntity.setPassChangeTime(now);

        loginStatusRepository.save(loginStatusEntity);
    }

    /**
     * ユーザ情報を更新する
     */
    @Override
    public void updateUser(final UserDetails user) {
        // TODO Auto-generated method stub
        // テーブルupdate

    }

    /**
     * ユーザを削除する
     */
    @Override
    public void deleteUser(final String username) {
        // TODO Auto-generated method stub
        // テーブルdelete
    }

    /**
     * パスワード変更を行う
     */
    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        // TODO Auto-generated method stub
        // テーブルupdate
    }

    /**
     * ユーザの存在確認を行う
     */
    @Override
    public boolean userExists(final String username) {
        return !loginStatusRepository.findById(username).isEmpty();
    }

}
