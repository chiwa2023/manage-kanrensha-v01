<script setup lang="ts">
import RoutePathConstants from './routePathConstants';


</script>
<template>
    <h1>SE権限メニュー</h1>

    <RouterLink to="/partner-manage">関連者管理</RouterLink><br>
    <RouterLink to="/works-approval">作業内容承認</RouterLink><br>

    <hr>

    <RouterLink :to=RoutePathConstants.PAGE_DUMP_MASTER>関連者マスタcsvダンプ(指定期間まで)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_HISTORY>関連者履歴csvダンプ(指定期間まで)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_SABUN_MASTER>関連者マスタcsv差分ダンプ</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_SABUN_HISTORY>関連者履歴csv差分ダンプ</RouterLink><br>
    
    <hr>
    <RouterLink to="/">ログインページ</RouterLink><br>

</template>
<style scoped></style>
