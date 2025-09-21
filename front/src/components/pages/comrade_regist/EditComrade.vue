<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import RiyoushaComradeOrgEdit from '../../common/riyousha_comrade_edit/RiyoushaComradeOrgEdit.vue';

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
    <ComradeInfo @send-user="recieveUser"></ComradeInfo>
    <hr>
    <!-- APIユーザ組織編集 -->
    <RiyoushaComradeOrgEdit :is-edit-new="isNew" :is-combine-user="isCombineUser" :user-dto="userDto">
    </RiyoushaComradeOrgEdit>

</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
