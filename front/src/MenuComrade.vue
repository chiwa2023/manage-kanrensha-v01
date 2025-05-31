<script setup lang="ts">
import MockComradeInfo from './components/common/user_info/MockComradeInfo.vue';

</script>
<template>
    <!-- APIユーザメニュー兼チェック -->
    <MockComradeInfo></MockComradeInfo>
    <hr>

    <h1>APIユーザ用メニュー</h1>

    <a href="#">接続トークン更新</a><br>

    <hr>
    <RouterLink to="/">ログインページ</RouterLink><br>

</template>
<style scoped></style>
