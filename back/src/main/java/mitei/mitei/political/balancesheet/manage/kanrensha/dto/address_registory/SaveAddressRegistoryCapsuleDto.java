package mitei.mitei.political.balancesheet.manage.kanrensha.dto.address_registory;

import java.io.Serializable;

import mitei.mitei.political.balancesheet.manage.kanrensha.entity.AddressRsdtTemplateEntity;

/**
 * アドレス・ベース・レジストリ住居更新Dto
 */
public class SaveAddressRegistoryCapsuleDto implements Serializable {

    /** Serialize id */
    private static final long serialVersionUID = 1L;

    /** 編集アドレス・ベース・レジストリ住居Entity */
    private AddressRsdtTemplateEntity addressRsdtTemplateEntity = new AddressRsdtTemplateEntity();

    /**
     * 編集アドレス・ベース・レジストリ住居を取得する
     *
     * @return 編集アドレス・ベース・レジストリ住居
     */
    public AddressRsdtTemplateEntity getAddressRsdtTemplateEntity() {
        return addressRsdtTemplateEntity;
    }

    /**
     * 編集アドレス・ベース・レジストリ住居を設定する
     *
     * @param addressRsdtTemplateEntity 編集アドレス・ベース・レジストリ住居
     */
    public void setAddressRsdtTemplateEntity(final AddressRsdtTemplateEntity addressRsdtTemplateEntity) {
        this.addressRsdtTemplateEntity = addressRsdtTemplateEntity;
    }

}
