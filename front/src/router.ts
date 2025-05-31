import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";

const routes = [
    // トップと開発用台紙
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/component", name: "BaseComponent", component: () => import("./components/BaseComponent.vue") },

    // ユーザ区分ごとメニュー
    { path: "/menu-manager", name: "MenuManager", component: () => import("./MenuManager.vue") },
    { path: "/menu-partner", name: "MenuPartner", component: () => import("./MenuPartner.vue") },
    { path: "/menu-comrade", name: "MenuComrade", component: () => import("./MenuComrade.vue") },


    // 関連者編集
    { path: "/partner-manage", name: "PartnerManage", component: () => import("./components/pages/partner_manage/PartnerManage.vue") },
    { path: "/partner-edit", name: "PartnerEdit", component: () => import("./components/pages/partner_edit/PartnerEdit.vue") },

    // 入力内容承認
    { path: "/works-approval", name: "WorksApproval", component: () => import("./components/pages/works_approval/WorksApproval.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;