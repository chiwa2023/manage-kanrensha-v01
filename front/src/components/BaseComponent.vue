<script setup lang="ts">
import { ref, type Ref } from 'vue';
import RiyoushaAdminEdit from './common/riyousha_admin_edit/RiyoushaAdminEdit.vue';
import UserPersonLeastDto from './../dto/user/userPersonLeastDto';
import type RiyoushaAdminInterface from '../dto/riyousha/riyoushaAdminDto';
import RiyoushaAdminDto from '../dto/riyousha/riyoushaAdminDto';

const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
userDto.value.listRoles.push("ROLE_manager");
const editDto: RiyoushaAdminInterface = new RiyoushaAdminDto();
//editDto.isNotOrg = true;

</script>
<template>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>

    <RiyoushaAdminEdit :edit-dto="editDto" :is-edit-new="true" :user-dto="userDto"></RiyoushaAdminEdit>

</template>
<style scoped></style>
