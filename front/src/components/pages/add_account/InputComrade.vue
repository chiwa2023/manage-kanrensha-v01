<script setup lang="ts">
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';
import RiyoushaComradeEdit from '../../common/riyousha_comrade_edit/RiyoushaComradeEdit.vue';
import type RiyoushaComradeInterface from '../../../entity/riyoushaComradeEntity';
import RiyoushaComradeEntity from '../../../entity/riyoushaComradeEntity';

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

const inputComradeEntity: Ref<RiyoushaComradeInterface> = ref(new RiyoushaComradeEntity());
inputComradeEntity.value.isNotOrg = true;

// 新規追加
const isNew: boolean = true;
// ユーザと関連者の紐づけは許可
const isCombineUser: boolean = true;
</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <ComradeInfo @send-user="recieveUser"></ComradeInfo>
    <hr>
    <!-- APIユーザ編集 -->
    <RiyoushaComradeEdit :base-entity="inputComradeEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
        :user-dto="userDto"></RiyoushaComradeEdit>
        
</template>
<style scoped></style>
