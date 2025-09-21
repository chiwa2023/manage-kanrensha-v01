<script setup lang="ts">
import { ref, type Ref } from 'vue';
import UserPersonLeastDto from './dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
import RoutePathConstants from './routePathConstants';
import AllUserInfo from './components/common/user_info/AllUserInfo.vue';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo @send-user="recieveUser"></AllUserInfo>
    <hr>

    <h2>全ユーザ共通メニュー</h2>

    <RouterLink :to=RoutePathConstants.PAGE_REFLESH_PASSWORD>パスワード更新</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_EDIT>ユーザ編集</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_CHANGE_ROLE>権限変更</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_USER_WITHDRAW>退会</RouterLink><br>

    <hr>
    <h3>マスタ・履歴ダウンロード(公開文書記載水準)</h3>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_HISTORY>関連者履歴データダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN>関連者マスタ最小ダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_HISTORY>関連者履歴データダウンロード差分(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_MIN>関連者マスタ最小ダウンロード差分(公開文書記載水準)</RouterLink><br>

    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style><br>
