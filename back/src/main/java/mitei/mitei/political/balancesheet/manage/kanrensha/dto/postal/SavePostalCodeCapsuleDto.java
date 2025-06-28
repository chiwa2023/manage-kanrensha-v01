package mitei.mitei.political.balancesheet.manage.kanrensha.dto.postal;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressPostalEntity;

/**
 * 郵便番号編集内容Dto
 */
public class SavePostalCodeCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 郵便番号Entity */
    private AddressPostalEntity addressPostalEntity = new AddressPostalEntity();

    /**
     * 郵便番号Entityを取得する
     *
     * @return 郵便番号Entity
     */
    public AddressPostalEntity getAddressPostalEntity() {
        return addressPostalEntity;
    }

    /**
     * 郵便番号Entityを設定する
     *
     * @param addressPostalEntity 郵便番号Entity
     */
    public void setAddressPostalEntity(final AddressPostalEntity addressPostalEntity) {
        this.addressPostalEntity = addressPostalEntity;
    }

}
