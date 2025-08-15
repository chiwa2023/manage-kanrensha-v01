<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import router from '../../../router';
import type SelectOptionStringInterface from '../../../dto/selectOptionStringDto';
import mockGetRoleMenuOpions from '../../../dto/user/mock/mockGetRoleMenuOpions';

// 常にどのメニューにも移行できるMock
const listRouter: Ref<SelectOptionStringInterface[]> = ref(mockGetRoleMenuOpions());

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
    <div style="background-color: darkred;padding-left: 0.7%;">
        <div style=" background-color: white;z-index: 2;padding-left: 1.3%;opacity: 1;text-align: right;">
                 <div style="float: left;">
                    管理者ページ
                    </div>
                <!-- 必要アイコンはここに追加 -->
            <div style="padding-right: 2.5%;">
                <div style="float: right;" class="left-space">
                    <img src="../../../assets/manager.png" style="width: 80px;" @click="onInfo">
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
