package mitei.mitei.political.balancesheet.manage.kanrensha.dto.add_xml;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.dto.sequrity.UserPersonLeastDto;
import mitei.mitei.political.balancesheet.manage.kanrensha.dto.storage_file.StorageFileDto;

/**
 * Csvファイルから登録条件Dto
 */
public class RegistDataByXmlCapsuleDto implements Serializable { // NOPMD DataClass

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** ユーザ最低限Dto */
    private UserPersonLeastDto userPersonLeastDto = new UserPersonLeastDto();

    /** ストレージ保存ファイルDto */
    private StorageFileDto storageFileDto = new StorageFileDto();

    /**
     * ユーザ最低限Dtoを取得する
     *
     * @return ユーザ最低限Dto
     */
    public UserPersonLeastDto getUserPersonLeastDto() {
        return userPersonLeastDto;
    }

    /**
     * ユーザ最低限Dtoを設定する
     *
     * @param userPersonLeastDto ユーザ最低限Dto
     */
    public void setUserPersonLeastDto(final UserPersonLeastDto userPersonLeastDto) {
        this.userPersonLeastDto = userPersonLeastDto;
    }

    /**
     * ストレージ保存ファイルDtoを取得する
     *
     * @return ストレージ保存ファイルDto
     */
    public StorageFileDto getStorageFileDto() {
        return storageFileDto;
    }

    /**
     * ストレージ保存ファイルDtoを設定する
     *
     * @param storageFileDto ストレージ保存ファイルDto
     */
    public void setStorageFileDto(final StorageFileDto storageFileDto) {
        this.storageFileDto = storageFileDto;
    }

}
