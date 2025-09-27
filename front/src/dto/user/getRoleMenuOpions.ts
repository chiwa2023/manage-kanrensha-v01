import RoutePathConstants from "../../routePathConstants";
import type SelectOptionStringInterface from "../selectOptionStringDto";
import SelectOptionStringDto from "../selectOptionStringDto";
import UserRoleConstants from "./userRoleConstants";

export default function getRoleMenuOpions(listRole: string[]) {

    const list: SelectOptionStringInterface[] = [];

    const ROLE_ADMIN: string = UserRoleConstants.ROLE_ADMIN;
    const ROLE_MANAGER: string = UserRoleConstants.ROLE_MANAGER;
    const ROLE_COMRADE: string = UserRoleConstants.ROLE_COMRADE;
    const ROLE_PARTNER_PERSON: string = UserRoleConstants.ROLE_PARTNER_PERSON;
    const ROLE_PARTNER_CORP: string = UserRoleConstants.ROLE_PARTNER_CORP;
    const ROLE_PARTNER_POLI_ORG: string = UserRoleConstants.ROLE_PARTNER_POLI_ORG;

    // SE権限メニューを追加
    if (listRole.includes(ROLE_ADMIN)) {
        list.push(createDto(RoutePathConstants.PAGE_MENU_ADMIN, "SE権限　メニュー"))
    }

    // 管理者メニューを追加
    if (listRole.includes(ROLE_MANAGER)) {
        list.push(createDto(RoutePathConstants.PAGE_MENU_MANAGER, "管理者　メニュー"))
    }

    // APIユーザメニューを追加
    if (listRole.includes(ROLE_COMRADE)) {
        list.push(createDto(RoutePathConstants.PAGE_MENU_COMRADE, "APIユーザ　メニュー"))
    }

    // 関連者メニューを追加
    if (listRole.includes(ROLE_PARTNER_PERSON)
        || listRole.includes(ROLE_PARTNER_CORP)
        || listRole.includes(ROLE_PARTNER_POLI_ORG)) {
        list.push(createDto(RoutePathConstants.PAGE_MENU_PARTNER, "関連者　メニュー"))
    }

    // 前ユーザ共通メニューは常に追加
    list.push(createDto(RoutePathConstants.PAGE_MENU_ALL_USER, "全ユーザ共通　メニュー"))

    // 非選択できるようにする
    list.push(createDto("", ""))

    return list;
}

function createDto(value: string, text: string) {
    const dto: SelectOptionStringInterface = new SelectOptionStringDto();
    dto.value = value;
    dto.text = text;

    return dto;

}