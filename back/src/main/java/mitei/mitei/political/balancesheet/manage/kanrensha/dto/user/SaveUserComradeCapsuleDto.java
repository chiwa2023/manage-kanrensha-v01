package mitei.mitei.political.balancesheet.manage.kanrensha.dto.user;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.FrameworkCapsuleDto;

/**
 * APIユーザ格納Dto
 */
public class SaveUserComradeCapsuleDto extends FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** APIユーザDto */
    private UserComradeDto userComradeDto = new UserComradeDto();

    /**
     * APIユーザDtoを取得する
     *
     * @return APIユーザDto
     */
    public UserComradeDto getUserComradeDto() {
        return userComradeDto;
    }

    /**
     * APIユーザDtoを設定する
     *
     * @param userComradeDto APIユーザDto
     */
    public void setUserComradeDto(final UserComradeDto userComradeDto) {
        this.userComradeDto = userComradeDto;
    }

}
