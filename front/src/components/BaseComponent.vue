<script setup lang="ts">
import { ref, type Ref } from 'vue';
import UserPersonLeastDto from './../dto/user/userPersonLeastDto';
import UserRoleConstants from '../dto/user/userRoleConstants';

const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
userDto.value.listRoles.push(UserRoleConstants.ROLE_ADMIN);

</script>
<template>
    <h1>コンポーネントをページと関係なく作成するための台紙</h1>

</template>
<style scoped></style>
