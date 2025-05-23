﻿import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";

const routes = [
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/component", name: "BaseComponent", component: () => import("./components/BaseComponent.vue") },


    { path: "/partner_manage", name: "PartnerManage", component: () => import("./components/pages/partner_manage/PartnerManage.vue") },
    { path: "/partner_edit", name: "PartnerEdit", component: () => import("./components/pages/partner_edit/PartnerEdit.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;