<script setup lang="ts">
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import { ref, type Ref } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import RiyoushaManagerOrgEdit from '../../common/riyousha_manager_edit/RiyoushaManagerOrgEdit.vue';

// ユーザメニューで取得したユーザを保持
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
function recieveUser(user: UserPersonLeastInterface) {
    userDto.value = user;
}

// 新規追加
const isNew: boolean = false;
// ユーザと関連者の紐づけは許可
const isCombineUser: boolean = true;

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>
    <!-- APIユーザ組織編集 -->
    <RiyoushaManagerOrgEdit :is-edit-new="isNew" :is-combine-user="isCombineUser" :user-dto="userDto">
    </RiyoushaManagerOrgEdit>

</template>
<style scoped></style>
