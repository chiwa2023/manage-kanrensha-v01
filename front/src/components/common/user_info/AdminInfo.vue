<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import router from '../../../router';
import type SelectOptionStringInterface from '../../../dto/selectOptionStringDto';
import getRoleMenuOpions from '../../../dto/user/getRoleMenuOpions';

const THIS_PAGE_ROLE: string = "ROLE_manager";
// ユーザ情報を持ってくる
const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
    if (!userDto.value.listRoles.includes(THIS_PAGE_ROLE)) {
        // roleが存在しない
        alert("操作権限が存在しません。再ログインしてください。");
        router.push("/");
    }
} else {
    // ユーザ情報が存在しない
    alert("ユーザ情報が存在しません。再ログインしてください。");
    router.push("/");
}

const roles: string[] = userDto.value.listRoles
const otherRole: string[] = roles.filter((e) => { return e !== THIS_PAGE_ROLE });
const listRouter: Ref<SelectOptionStringInterface[]> = ref(getRoleMenuOpions(otherRole));

function onInfo() {
    alert("押した");
    // TODO 簡易ユーザ情報を表示する
}

// 役割異動メニュー
const moveRole: Ref<string> = ref("");
function onMoveRole() {
    const route: string = listRouter.value.filter((e) => { return e.text === moveRole.value })[0].value;
    router.push(route);
}
const isMoveMenu:ComputedRef<boolean> = computed(() => listRouter.value.length>0);


</script>
<template>
    <div style="background-color: red;padding-left: 0.7%;">
        <div style=" background-color: white;z-index: 2;padding-left: 1.3%;opacity: 1;text-align: right;">
            <div style="padding-right: 2.5%;">
                <!-- 必要アイコンはここに追加 -->
                <div style="float: right;" class="left-space">
                    <img src="../../../../manager.png" style="width: 80px;" @click="onInfo">
                </div>
                <div class="left-space">
                    <br>
                    <select v-model="moveRole" @change="onMoveRole" :disabled="!isMoveMenu">
                        <option v-for="option of listRouter"> {{ option.text }}</option>
                    </select>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </div>
</template>
<style scoped></style>
