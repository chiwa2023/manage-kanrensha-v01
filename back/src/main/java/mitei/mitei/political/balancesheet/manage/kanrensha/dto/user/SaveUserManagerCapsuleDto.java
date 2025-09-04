package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;

/**
 * 運営者ユーザー格納Dto
 */
public class SaveUserManagerCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 運営者ユーザーDto */
    private UserManagerDto userManagerDto;

    /**
     * 運営者ユーザーDtoを取得する
     *
     * @return 運営者ユーザーDto
     */
    public UserManagerDto getUserManagerDto() {
        return userManagerDto;
    }

    /**
     * 運営者ユーザーDtoを設定する
     *
     * @param userManagerDto 運営者ユーザーDto
     */
    public void setUserManagerDto(final UserManagerDto userManagerDto) {
        this.userManagerDto = userManagerDto;
    }

}
