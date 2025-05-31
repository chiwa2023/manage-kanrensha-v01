<script setup lang="ts">
import MockPartnerInfo from './components/common/user_info/MockPartnerInfo.vue';

</script>
<template>
    <!-- 関連者メニュー兼チェック -->
    <MockPartnerInfo></MockPartnerInfo>
    <hr>

    <h1>関連者用メニュー</h1>

    <RouterLink to="/partner-edit">関連者編集</RouterLink><br>

    <hr>
    <RouterLink to="/">ログインページ</RouterLink><br>

</template>
<style scoped></style>
