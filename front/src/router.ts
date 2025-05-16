import { createRouter, createWebHistory } from "vue-router";
import TopPage from "./TopPage.vue";

const routes = [
    { path: "/", name: "TopPage", component: TopPage },
    { path: "/component", name: "BaseComponent", component: () => import("./components/BaseComponent.vue") },


    { path: "/partner-manage", name: "PartnerManage", component: () => import("./components/pages/partner_manage/PartnerManage.vue") },
    { path: "/partner-edit", name: "PartnerEdit", component: () => import("./components/pages/partner_edit/PartnerEdit.vue") },
    { path: "/works-approval", name: "WorksApproval", component: () => import("./components/pages/works_approval/WorksApproval.vue") },

];

const router = createRouter({
    history: createWebHistory(), // HTML5 History モード
    routes,
});

export default router;