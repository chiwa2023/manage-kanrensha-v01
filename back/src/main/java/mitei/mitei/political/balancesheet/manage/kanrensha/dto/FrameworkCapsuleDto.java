package mitei.mitei.political.balancesheet.manage.kanrensha.dto;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * (Frameworkという名称だが実態は仮)CapsuleDto
 */
public class FrameworkCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 最小限ユーザDto */
    private UserPersonLeastDto userPersonLeastDto = new UserPersonLeastDto();

    /**
     * 最小限ユーザDtoを取得する
     *
     * @return 最小限ユーザDto
     */
    public UserPersonLeastDto getUserPersonLeastDto() {
        return userPersonLeastDto;
    }

    /**
     * 最小限ユーザDtoを設定する
     *
     * @param userPersonLeastDto 最小限ユーザDto
     */
    public void setUserPersonLeastDto(final UserPersonLeastDto userPersonLeastDto) {
        this.userPersonLeastDto = userPersonLeastDto;
    }

}
