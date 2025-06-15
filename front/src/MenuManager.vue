<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ManagerInfo from './components/common/user_info/ManagerInfo.vue';
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
    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>

    <h2>管理者用メニュー</h2>

    <RouterLink to="/partner_manage">関連者管理</RouterLink><br>
    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style>
