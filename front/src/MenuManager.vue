<script setup lang="ts">
import MockManagerInfo from './components/common/user_info/MockManagerInfo.vue';
import RoutePathConstants from './routePathConstants';

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo></MockManagerInfo>
    <hr>

    <h1>管理者用メニュー</h1>

    <h3>関連者メニュー</h3>
    <RouterLink to="/partner-manage">関連者管理</RouterLink><br>
    <RouterLink to="/works-approval">作業内容承認</RouterLink><br>

    <hr>

    <h3>郵便番号メニュー</h3>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_BUILDING>フロア郵便番号住所追加</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_EDIT>郵便番号編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_POSTAL_SABUN>郵便番号差分更新</RouterLink><br>
    <hr>

    <h3>アドレス・ベース・レジストリ</h3>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_EDIT>アドレス・ベース・レジストリ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_SABUN>アドレス・ベース・レジストリ差分更新</RouterLink><br>
    <hr>

    <RouterLink to="/">ログインページ</RouterLink><br>

</template>
<style scoped></style>
