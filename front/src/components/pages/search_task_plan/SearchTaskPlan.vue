<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ShowTask from '../../common/show_task/ShowTask.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

//仮

// よく使う定数
// const BLANK: string = "";
// const INIT_NUMBER: number = 0;
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;


</script>
<template>

    <!-- 全メニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>

    <h1>タスク計画検索</h1>

    <!-- 全メニュー兼チェック -->
    <ShowTask :is-search-condition="true" :user-dto="userLeastDto"></ShowTask>

</template>
<style scoped></style>
