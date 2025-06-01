package mitei.mitei.political.balancesheet.manage.kanrensha.controller.user;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserCapsuleDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.LoginUserResultDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.LoginStatusEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserPersonEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.entity.UserRoleEntity;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserPersonRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.repository.UserRoleRepository;
import mitei.mitei.political.balancesheet.manage.kanrensha.utils.SetTableDataHistoryUtil;

/**
 * 新規ユーザ作成Controller
 */
@RestController
@RequestMapping("/add-user")
public class InsertUserManagerController {

    /** ユーザ人物Repository */
    @Autowired
    private UserPersonRepository userPersonRepository;

    /** ユーザ権限Repository */
    @Autowired
    private UserRoleRepository userRoleRepository;

    /** テーブル履歴Util */
    @Autowired
    private SetTableDataHistoryUtil setTableDataHistoryUtil;

    /** 権限 */
    private static final String ROLE_MANAGER = "manager";

    /**
     * 処理を行う
     *
     * @param capsuleDto ログインユーザ情報
     * @return ログイン結果
     */
    @PostMapping("/manager")
    public ResponseEntity<LoginUserResultDto> practice(final @RequestBody LoginUserCapsuleDto capsuleDto) {

        LoginStatusEntity loginStatusEntity = new LoginStatusEntity();

        LocalDateTime now = LocalDateTime.now();

        loginStatusEntity.setEmail(null);
        loginStatusEntity.setPassword(null);
        loginStatusEntity.setPassChangeTime(now);
        loginStatusEntity.setLoginTime(now);

        // ユーザを挿入
        UserPersonEntity userPersonEntity = new UserPersonEntity();
        userPersonEntity.setUserPersonId(0); // auto increment明示
        userPersonEntity.setEmail(null);
        userPersonEntity.setUserPersonCode(null);
        userPersonEntity.setUserPersonName(null);
        userPersonEntity.setEmail(null);

        UserPersonLeastDto userDto = new UserPersonLeastDto();

        setTableDataHistoryUtil.practiceInsert(userDto, userPersonEntity);
        userPersonRepository.save(userPersonEntity);

        // 権限を挿入
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserRoleId(0); // auto increment明示
        userRoleEntity.setRole(ROLE_MANAGER);
        setTableDataHistoryUtil.practiceInsert(userDto, userRoleEntity);
        userRoleRepository.save(userRoleEntity);

        return null;
    }

}
