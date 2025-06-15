<script setup lang="ts">
import { ref, type Ref } from 'vue';
import AdminInfo from './components/common/user_info/AdminInfo.vue';
import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
import UserPersonLeastDto from './dto/user/userPersonLeastDto';
import RoutePathConstants from './routePathConstants';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user:UserPersonLeastInterface){
    userLeastDto.value = user;
}

</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <AdminInfo @send-user="recieveUser"></AdminInfo>
    <hr>

    <h2>SE権限用メニュー</h2>

    <RouterLink to="/user/delete">ユーザ削除</RouterLink><br>
    <RouterLink to="/user/change">ユーザ編集</RouterLink><br>
    <RouterLink to="/user/promote-role">管理者からSE権限へ引き上げ</RouterLink><br>
    
    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style>
