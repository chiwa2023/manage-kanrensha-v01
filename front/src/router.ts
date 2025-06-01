import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";

const routes = [
    { path: "/", name: "TopPage", component: TopPage },

    // ユーザ区分ごとメニュー
    { path: "/menu-manager", name: "MenuManager", component: () => import("./MenuManager.vue") },
    { path: "/menu-partner", name: "MenuPartner", component: () => import("./MenuPartner.vue") },
    { path: "/menu-comrade", name: "MenuComrade", component: () => import("./MenuComrade.vue") },


    // 新規アカウント作成
    { path: "/add-account", name: "AddAccountMenu", component: () => import("./components/pages/add_account/AddAccountMenu.vue") },
    { path: "/send-access-code", name: "SendAccessCode", component: () => import("./components/pages/add_account/SendAccessCode.vue") },
    { path: "/switch-user-kbn", name: "SwitchNewUserKbn", component: () => import("./components/pages/add_account/SwitchNewUserKbn.vue") },

    { path: "/input-kanrensha", name: "InputKanrensha", component: () => import("./components/pages/add_account/InputKanrensha.vue") },
    { path: "/input-comrade", name: "InputComrade", component: () => import("./components/pages/add_account/InputComrade.vue") },
    { path: "/input-manager", name: "InputManager", component: () => import("./components/pages/add_account/InputManager.vue") },


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