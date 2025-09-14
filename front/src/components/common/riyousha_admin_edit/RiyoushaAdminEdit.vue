<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaAdminInterface from '../../../dto/riyousha/riyoushaAdminDto';
import InputAccess from '../input_access/InputAccess.vue';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputPersonName from '../input_person_name/InputPersonName.vue';
import InputOrgName from '../input_org_name/InputOrgName.vue';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';


// props,emmits
const props = defineProps<{ editDto: RiyoushaAdminInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();
const inputAdminoDto: ComputedRef<RiyoushaAdminInterface> = computed(() => props.editDto);

const isNotOrgRadio: ComputedRef<string> = computed(() => inputAdminoDto.value.isNotOrg ? "1" : "0");



function recieveInputPersonNameInterface() {

}

function recieveInputOrgNameInterface() {

}


function onCancel() {
    alert("キャンセル");
}

function onSave() {
    alert("キャンセル");
}

</script>
<template>
    <h1>利用者管理者編集</h1>



    <div class="left-area">
        個人／団体
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="isNotOrgRadio" :value="1" disabled="true">個人</span>
        <span class="left-space"><input type="radio" v-model="isNotOrgRadio" :value="0" disabled="true"> 団体</span>
        <div v-if="props.isEditNew">
            <br>
            <input type="checkbox">次の団体を別に登録したい場合はチェックを入れる
        </div>
    </div>
    <div class="clear-both"><br></div>

    <!-- 名称 -->
    <div v-if="inputAdminoDto.isNotOrg">
        <!-- 姓名入力 -->
        <ViewInputPersonName :edit-dto="inputAdminoDto.inputPersonNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>
    </div>
    <div v-else>
        <!-- 姓名入力 -->
        <InputOrgName :edit-dto="inputAdminoDto.inputOrgNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputOrgNameInterface"></InputOrgName>
    </div>

    <!-- 住所 -->
    <ViewInputAddress :edit-dto="inputAdminoDto.inputAddressDto" :is-raise-edit-view="false"></ViewInputAddress>

    <!-- 連絡先 -->
    <InputAccess :edit-dto="inputAdminoDto.inputAccessDto"></InputAccess>

    <div v-if="!props.isEditNew">
        
    </div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
