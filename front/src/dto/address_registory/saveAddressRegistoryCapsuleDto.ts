import type AddressRsdtTemplateInterface from "../../entity/addressRsdtTemplateEntity";
import AddressRsdtTemplateEntity from "../../entity/addressRsdtTemplateEntity";

export default interface SaveAddressRegistoryCapsuleInterface {

}

export default class SaveAddressRegistoryCapsuleDto implements SaveAddressRegistoryCapsuleInterface {

    /** アドレス・ベース・レジストリ住居 */
    addressRsdtTemplateEntity: AddressRsdtTemplateInterface;

    constructor() {
        this.addressRsdtTemplateEntity = new AddressRsdtTemplateEntity();
    }
}