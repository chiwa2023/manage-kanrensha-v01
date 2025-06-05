<script setup lang="ts">
import RoutePathConstants from './routePathConstants';


</script>
<template>
    <h1>政治資金関連者標準化サイト</h1>

    <h2>ログイン処理を省略・・・</h2>

    <RouterLink :to=RoutePathConstants.PAGE_MENU_MANAGER>管理者メニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_COMRADE>APIユーザメニュー</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_MENU_PARTNER>関連者メニュー</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_COMPONENT>コンポーネント作成台紙</RouterLink><br>

</template>
<style scoped></style>
