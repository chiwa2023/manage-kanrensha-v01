import RoutePathConstants from "../../../routePathConstants";
import type SelectOptionStringInterface from "../../selectOptionStringDto";
import SelectOptionStringDto from "../../selectOptionStringDto";

export default function mockGetRoleMenuOpions() {

    const list: SelectOptionStringInterface[] = [];

    // 管理者メニューを追加
    list.push(createDto(RoutePathConstants.PAGE_MENU_MANAGER, "管理者　メニュー"))

    // APIユーザメニューを追加
    list.push(createDto(RoutePathConstants.PAGE_MENU_COMRADE, "APIユーザ　メニュー"))

    // 関連者メニューを追加
    list.push(createDto(RoutePathConstants.PAGE_MENU_PARTNER, "関連者　メニュー"))

    list.push(createDto("", ""))

    return list;
}

function createDto(value: string, text: string) {
    const dto: SelectOptionStringInterface = new SelectOptionStringDto();
    dto.value = value;
    dto.text = text;

    return dto;

}