<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ComradeInfo from './components/common/user_info/ComradeInfo.vue';
import UserPersonLeastDto from './dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from './dto/user/userPersonLeastDto';
import RoutePathConstants from './routePathConstants';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <ComradeInfo @send-user="recieveUser"></ComradeInfo>
    <hr>

    <h2>APIユーザ用メニュー</h2>

    <a href="#">Bearer更新</a><br>

    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style><br>