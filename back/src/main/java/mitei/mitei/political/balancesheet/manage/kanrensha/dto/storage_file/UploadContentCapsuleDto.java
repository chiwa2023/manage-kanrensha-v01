package mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;

/**
 * アップロードファイル内容Dto
 */
public class UploadContentCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userDto = new UserPersonLeastDto();

    /** アップロードファイルDto */
    private UploadFileDto uploadFileDto = new UploadFileDto();

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

    /**
     * アップロードファイルDtoを取得する
     *
     * @return アップロードファイルDto
     */
    public UploadFileDto getUploadFileDto() {
        return uploadFileDto;
    }

    /**
     * アップロードファイルDtoを取得する
     *
     * @param uploadFileDto アップロードファイルDto
     */
    public void setUploadFileDto(final UploadFileDto uploadFileDto) {
        this.uploadFileDto = uploadFileDto;
    }

}
