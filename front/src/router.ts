import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";
import RoutePathConstants from "./routePathConstants";

const routes = [
    // トップと開発用台紙
    { path: RoutePathConstants.PAGE_LOGIN, name: "TopPage", component: TopPage },
    { path: RoutePathConstants.PAGE_COMPONENT, name: "BaseComponent", component: () => import("./components/BaseComponent.vue") },

    // ユーザ区分ごとメニュー
    { path: RoutePathConstants.PAGE_MENU_MANAGER, name: "MenuManager", component: () => import("./MenuManager.vue") },
    { path: RoutePathConstants.PAGE_MENU_PARTNER, name: "MenuPartner", component: () => import("./MenuPartner.vue") },
    { path: RoutePathConstants.PAGE_MENU_COMRADE, name: "MenuComrade", component: () => import("./MenuComrade.vue") },


    // 関連者編集
    { path: "/partner-manage", name: "PartnerManage", component: () => import("./components/pages/partner_manage/PartnerManage.vue") },
    { path: "/partner-edit", name: "PartnerEdit", component: () => import("./components/pages/partner_edit/PartnerEdit.vue") },

    // 入力内容承認
    { path: "/works-approval", name: "WorksApproval", component: () => import("./components/pages/works_approval/WorksApproval.vue") },

    // 郵便番号編集
    { path: RoutePathConstants.PAGE_POSTAL_BUILDING, name: "PostalCodeBuilding", component: () => import("./components/pages/postal_code_repair/PostalCodeBuilding.vue") },
    { path: RoutePathConstants.PAGE_POSTAL_EDIT, name: "PostalCodeEdit", component: () => import("./components/pages/postal_code_repair/PostalCodeEdit.vue") },
    { path: RoutePathConstants.PAGE_POSTAL_SABUN, name: "PostalCodeSabun", component: () => import("./components/pages/postal_code_repair/PostalCodeSabun.vue") },

    // アドレス・ベース・レジストリ編集
   { path: RoutePathConstants.PAGE_ADDRESS_REGI_EDIT, name: "AddressRegistryEdit", component: () => import("./components/pages/address_registry_repair/AddressRegistryEdit.vue") },
    { path: RoutePathConstants.PAGE_ADDRESS_REGI_SABUN, name: "AddressRegistrySabun", component: () => import("./components/pages/address_registry_repair/AddressRegistrySabun.vue") },


];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;