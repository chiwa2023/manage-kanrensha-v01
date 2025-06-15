<script setup lang="ts">
import { ref, type Ref } from 'vue';
import PartnerInfo from './components/common/user_info/PartnerInfo.vue';
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
    <PartnerInfo @send-user="recieveUser"></PartnerInfo>
    <hr>

    <h2>関連者用メニュー</h2>

    <RouterLink to="/partner_edit">関連者編集</RouterLink><br>

    <RouterLink :to="RoutePathConstants.PAGE_LOGIN">ログインページ</RouterLink><br>

</template>
<style scoped></style>
