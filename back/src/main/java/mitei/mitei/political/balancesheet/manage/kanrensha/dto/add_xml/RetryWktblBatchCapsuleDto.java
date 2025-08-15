package mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * ワークテーブル(編集後)再実行条件Dto
 */
public class RetryWktblBatchCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto = new UserPersonLeastDto();

    /**
     * ユーザ最低限Dtoを取得する
     *
     * @return ユーザ最低限Dto
     */
    public UserPersonLeastDto getUserDto() {
        return userDto;
    }

    /**
     * ユーザ最低限Dtoを設定する
     *
     * @param userDto ユーザ最低限Dto
     */
    public void setUserDto(final UserPersonLeastDto userDto) {
        this.userDto = userDto;
    }

}
