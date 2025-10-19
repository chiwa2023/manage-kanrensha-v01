<script setup lang="ts">
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';
import type RiyoushaManagerInterface from '../../../entity/riyoushaManagerEntity';
import RiyoushaManagerEntity from '../../../entity/riyoushaManagerEntity';
import RiyoushaManagerOrgEdit from '../../common/riyousha_manager_edit/RiyoushaManagerOrgEdit.vue';

const inputManagerEntity: Ref<RiyoushaManagerInterface> = ref(new RiyoushaManagerEntity());

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// 新規追加作業
const isNew: boolean = true;
// ユーザと関連者の紐づけは可能
const isCombineUser: boolean = true;

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>

    <hr>

    <!-- 運営者編集 -->
    <RiyoushaManagerOrgEdit :base-entity="inputManagerEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
        :user-dto="userLeastDto"></RiyoushaManagerOrgEdit>

</template>
<style scoped></style>
