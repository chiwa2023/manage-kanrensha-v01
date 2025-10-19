<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import router from '../../../router';
import type SelectOptionStringInterface from '../../../dto/selectOptionStringDto';
import getRoleMenuOpions from '../../../dto/user/getRoleMenuOpions';
import RoutePathConstants from '../../../routePathConstants';
import UserRoleConstants from '../../../dto/user/userRoleConstants';

// props,emits
const emits = defineEmits(["sendUser"]);

const ROLE_PERSON: string = UserRoleConstants.ROLE_PARTNER_PERSON;
const ROLE_CORP: string = UserRoleConstants.ROLE_PARTNER_CORP;
const ROLE_POLI_ORG: string = UserRoleConstants.ROLE_PARTNER_POLI_ORG;

// ユーザ情報を持ってくる
const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
    if (userDto.value.listRoles.includes(ROLE_PERSON)
        || userDto.value.listRoles.includes(ROLE_CORP)
        || userDto.value.listRoles.includes(ROLE_POLI_ORG)) {
        emits("sendUser", userDto.value);
    } else {
        // roleが存在しない
        alert("操作権限が存在しません。再ログインしてください。");
        router.push(RoutePathConstants.PAGE_LOGIN);

    }
} else {
    // ユーザ情報が存在しない
    alert("ユーザ情報が存在しません。再ログインしてください。");
    router.push(RoutePathConstants.PAGE_LOGIN);
}

const listRouter: Ref<SelectOptionStringInterface[]> = ref(getRoleMenuOpions(userDto.value.listRoles));

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
const isMoveMenu: ComputedRef<boolean> = computed(() => listRouter.value.length > 0);

</script>
<template>
    <div style="background-color: darkkhaki;padding-left: 0.7%;">
        <div style=" background-color: white;z-index: 2;padding-left: 1.3%;opacity: 1;text-align: right;">
            <div style="float: left;">
                関連者
            </div>
            <!-- 必要アイコンはここに追加 -->
            <div style="padding-right: 2.5%;">
                <div style="float: right;" class="left-space">
                    <img src="../../../assets/partner.png" style="width: 80px;" @click="onInfo">
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
