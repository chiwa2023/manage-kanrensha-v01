package mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity;

import java.io.Serializable;

/**
 * パスワード更新Dto
 */
public class RefleshPasswordCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 初期データ(String) */
    private static final String INIT_String = "";

    /** 最小限UserDto */
    private UserPersonLeastDto userPersonLeastDto;

    /** 新パスワード */
    private String newPassword = INIT_String;

    /** 旧パスワード */
    private String oldPassword = INIT_String;

    /**
     * 最小限UserDtoを取得する
     *
     * @return 最小限UserDto
     */
    public UserPersonLeastDto getUserPersonLeastDto() {
        return userPersonLeastDto;
    }

    /**
     * 最小限UserDtoを設定する
     *
     * @param userPersonLeastDto 最小限UserDto
     */
    public void setUserPersonLeastDto(final UserPersonLeastDto userPersonLeastDto) {
        this.userPersonLeastDto = userPersonLeastDto;
    }

    /**
     * 新パスワードを取得する
     *
     * @return 新パスワード
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * 新パスワードを設定する
     *
     * @param newPassword 新パスワード
     */
    public void setNewPassword(final String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 旧パスワードを取得する
     *
     * @return 旧パスワード
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * 旧パスワードを設定する
     *
     * @param oldPassword 旧パスワード
     */
    public void setOldPassword(final String oldPassword) {
        this.oldPassword = oldPassword;
    }

}
