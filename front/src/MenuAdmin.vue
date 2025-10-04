<script setup lang="ts">
import { ref, type Ref } from 'vue';
import AdminInfo from './components/common/user_info/AdminInfo.vue';
import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
import UserPersonLeastDto from './dto/user/userPersonLeastDto';
import RoutePathConstants from './routePathConstants';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}


</script>
<template>
    <h1>SE権限メニュー</h1>
    <!-- ユーザメニュー兼チェック -->
    <AdminInfo @send-user="recieveUser"></AdminInfo>
    <hr>

    <h2>SE権限用メニュー</h2>

    <RouterLink :to=RoutePathConstants.PAGE_USER_DELETE>ユーザ削除</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_CHANGE>ユーザ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_PROMOTE_ROLE>管理者からSE権限へ引き上げ</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_REGI_PARTNER_MANAGE>関連者管理</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_WORKS_APPROVAL>作業内容承認</RouterLink><br>

    <hr>

    <RouterLink :to=RoutePathConstants.PAGE_DUMP_MASTER>関連者マスタcsvダンプ(指定期間まで)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_HISTORY>関連者履歴csvダンプ(指定期間まで)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_MASTER_STD>関連者マスタ標準csvダンプ(指定期間まで)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_SABUN_MASTER>関連者マスタcsv差分ダンプ</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_SABUN_HISTORY>関連者履歴csv差分ダンプ</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DUMP_SABUN_MASTER_STD>関連者マスタ標準csv差分ダンプ</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_SEARCH_RIYOUSHA>利用者検索／編集</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_SEARCH_TASK_PLAN>タスク計画検索</RouterLink><br>

    <hr>
    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style>
