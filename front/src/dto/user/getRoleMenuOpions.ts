import type SelectOptionStringInterface from "../selectOptionStringDto";
import SelectOptionStringDto from "../selectOptionStringDto";

export default function getRoleMenuOpions(listRole: string[]) {

    const list: SelectOptionStringInterface[] = [];

    const ROLE_MANAGER: string = "ROLE_manager";
    const ROLE_COMRADE: string = "ROLE_comrade";
    const ROLE_PARTNER_PERSON: string = "ROLE_partner_person";
    const ROLE_PARTNER_CORP: string = "ROLE_partner_corp";
    const ROLE_PARTNER_POLI_ORG: string = "ROLE_partner_poli_org";


    // 管理者メニューを追加
    if (listRole.includes(ROLE_MANAGER)) {
        list.push(createDto("/menu-manager", "管理者　メニュー"))
    }

    // APIユーザメニューを追加
    if (listRole.includes(ROLE_COMRADE)) {
        list.push(createDto("/menu-comrade", "APIユーザ　メニュー"))
    }

    // 関連者メニューを追加
    if (listRole.includes(ROLE_PARTNER_PERSON)
        || listRole.includes(ROLE_PARTNER_CORP)
        || listRole.includes(ROLE_PARTNER_POLI_ORG)) {
        list.push(createDto("/menu-partner", "関連者　メニュー"))
    }

    list.push(createDto("", ""))

    return list;
}

function createDto(value: string, text: string) {
    const dto: SelectOptionStringInterface = new SelectOptionStringDto();
    dto.value = value;
    dto.text = text;

    return dto;

}