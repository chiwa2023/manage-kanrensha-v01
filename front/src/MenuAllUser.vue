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

    <RouterLink to="/user/reflesh-password">パスワード更新</RouterLink><br>
    <RouterLink to="/user/edit">ユーザ編集</RouterLink><br>
    <RouterLink to="/user/change-role">権限変更</RouterLink><br>
    <RouterLink to="/user/withdraw">退会</RouterLink><br>


    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style><br>
