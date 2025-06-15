<script setup lang="ts">
import { ref, type Ref } from 'vue';
import AdminInfo from './common/user_info/AdminInfo.vue';
import ComradeInfo from './common/user_info/ComradeInfo.vue';
import ManagerInfo from './common/user_info/ManagerInfo.vue';
import PartnerInfo from './common/user_info/PartnerInfo.vue';
import type UserPersonLeastInterface from '../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../dto/user/userPersonLeastDto';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user:UserPersonLeastInterface){
    userLeastDto.value = user;
}

</script>
<template>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>
</template>
<style scoped></style>
