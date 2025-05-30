<script setup lang="ts">
import AdminInfo from './common/user_info/AdminInfo.vue';
import ComradeInfo from './common/user_info/ComradeInfo.vue';
import ManagerInfo from './common/user_info/ManagerInfo.vue';
import PartnerInfo from './common/user_info/PartnerInfo.vue';

</script>
<template>
    <AdminInfo></AdminInfo>
    <hr>
    <ManagerInfo></ManagerInfo>
    <hr>
    <ComradeInfo></ComradeInfo>
    <hr>
    <PartnerInfo></PartnerInfo>
    <hr>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>

</template>
<style scoped></style>
