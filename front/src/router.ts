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
    { path: RoutePathConstants.PAGE_MENU_ADMIN, name: "MenuAdmin", component: () => import("./MenuAdmin.vue") },


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

    // マスタ一括登録
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_CORP, name: "RegistBulkMasterCorp", component: () => import("./components/pages/regist_bulk_master/RegistBulkMasterCorp.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_PERSON, name: "RegistBulkMasterPerson", component: () => import("./components/pages/regist_bulk_master/RegistBulkMasterPerson.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_MASTER_POLI_ORG, name: "RegistBulkMasterPoliOrg", component: () => import("./components/pages/regist_bulk_master/RegistBulkMasterPoliOrg.vue") },

    // 履歴一括登録
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_CORP, name: "RegistBulkHistoryCorp", component: () => import("./components/pages/regist_bulk_history/RegistBulkHistoryCorp.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_PERSON, name: "RegistBulkHistoryPerson", component: () => import("./components/pages/regist_bulk_history/RegistBulkHistoryPerson.vue") },
    { path: RoutePathConstants.PAGE_REGI_BULK_HISTORY_POLI_ORG, name: "RegistBulkHistoryPoliOrg", component: () => import("./components/pages/regist_bulk_history/RegistBulkHistoryPoliOrg.vue") },

    // 強制処理CSVダンプ
    { path: RoutePathConstants.PAGE_DUMP_MASTER, name: "ForceDumpHistory", component: () => import("./components/pages/z_force_dump/ForceDumpHistory.vue") },
    { path: RoutePathConstants.PAGE_DUMP_HISTORY, name: "ForceDumpMaster", component: () => import("./components/pages/z_force_dump/ForceDumpMaster.vue") },

    // 強制処理差分CSVダンプ(差分)
    { path: RoutePathConstants.PAGE_DUMP_SABUN_MASTER, name: "ForceSabunDumpHistory", component: () => import("./components/pages/z_force_dump_sabun/ForceSabunDumpHistory.vue") },
    { path: RoutePathConstants.PAGE_DUMP_SABUN_HISTORY, name: "ForceSabunDumpMaster", component: () => import("./components/pages/z_force_dump_sabun/ForceSabunDumpMaster.vue") },

    // XMLから編集
    { path: RoutePathConstants.PAGE_ADD_XML, name: "AddByXml", component: () => import("./components/pages/add_xml/AddByXml.vue") },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;