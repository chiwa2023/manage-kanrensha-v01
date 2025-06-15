import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";
import RoutePathConstants from "./routePathConstants";

const routes = [
    { path:  RoutePathConstants.PAGE_LOGIN, name: "TopPage", component: TopPage },

    // ユーザ区分ごとメニュー
    { path: RoutePathConstants.PAGE_MENU_MANAGER, name: "MenuManager", component: () => import("./MenuManager.vue") },
    { path: RoutePathConstants.PAGE_MENU_PARTNER, name: "MenuPartner", component: () => import("./MenuPartner.vue") },
    { path: RoutePathConstants.PAGE_MENU_COMRADE, name: "MenuComrade", component: () => import("./MenuComrade.vue") },
    { path: RoutePathConstants.PAGE_MENU_ADMIN, name: "MenuAdmin", component: () => import("./MenuAdmin.vue") },
    { path: RoutePathConstants.PAGE_MENU_ALL_USER, name: "MenuAllUser", component: () => import("./MenuAllUser.vue") },

    // 新規アカウント作成
    { path: "/add-account", name: "AddAccountMenu", component: () => import("./components/pages/add_account/AddAccountMenu.vue") },
    { path: "/send-access-code", name: "SendAccessCode", component: () => import("./components/pages/add_account/SendAccessCode.vue") },
    { path: "/switch-user-kbn", name: "SwitchNewUserKbn", component: () => import("./components/pages/add_account/SwitchNewUserKbn.vue") },
    { path: "/input-kanrensha", name: "InputKanrensha", component: () => import("./components/pages/add_account/InputKanrensha.vue") },
    { path: "/input-comrade", name: "InputComrade", component: () => import("./components/pages/add_account/InputComrade.vue") },
    { path: "/input-manager", name: "InputManager", component: () => import("./components/pages/add_account/InputManager.vue") },

    // ユーザ編集
    { path: "/user/change", name: "ChangeUser", component: () => import("./components/pages/user_regist/ChangeUser.vue") },
    { path: "/user/delete", name: "DeleteUser", component: () => import("./components/pages/user_regist/DeleteUser.vue") },
    { path: "/user/edit", name: "EditUser", component: () => import("./components/pages/user_regist/EditUser.vue") },
    { path: "/user/change-role", name: "ChangeRole", component: () => import("./components/pages/user_regist/ChangeRole.vue") },
    { path: "/user/promote-role", name: "PromoteAdmin", component: () => import("./components/pages/user_regist/PromoteAdmin.vue") },
    { path: "/user/reflesh-password", name: "RefleshPassword", component: () => import("./components/pages/user_regist/RefleshPassword.vue") },
    { path: "/user/withdraw", name: "WithdrawUser", component: () => import("./components/pages/user_regist/WithdrawUser.vue") },

    // 管理者による関連者変更
    { path: "/partner_manage", name: "PartnerManage", component: () => import("./components/pages/partner_manage/PartnerManage.vue") },

    // 関連者本人による情報更新
    { path: "/partner_edit", name: "PartnerEdit", component: () => import("./components/pages/partner_edit/PartnerEdit.vue") },

    // TODO 開発終了時に削除する コンポーネント台紙
    { path: "/component", name: "BaseComponent", component: () => import("./components/BaseComponent.vue") },
];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;