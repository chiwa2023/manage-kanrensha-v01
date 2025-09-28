<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaAdminDtoInterface from '../../../dto/riyousha/riyoushaAdminDto';
import RiyoushaAdminDto from '../../../dto/riyousha/riyoushaAdminDto';
import type RiyoushaAdminEntityInterface from '../../../entity/riyoushaAdminEntity';
import InputAccess from '../input_access/InputAccess.vue';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputOrgName from '../input_org_name/InputOrgName.vue';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import type InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
import type InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
import type SaveRiyoushaAdminCapsuleInterface from '../../../dto/riyousha/saveRiyoushaAdminCapsuleDto';
import SaveRiyoushaAdminCapsuleDto from '../../../dto/riyousha/saveRiyoushaAdminCapsuleDto';
import type GetRiyoushaAdminCapsuleInterface from '../../../dto/riyousha/getRiyoushaAdminCapsuleDto';
import GetRiyoushaAdminCapsuleDto from '../../../dto/riyousha/getRiyoushaAdminCapsuleDto';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import type GetRiyoushaAdminResultInterface from '../../../dto/riyousha/getRiyoushaAdminResultDto';
import RoutePathConstants from '../../../routePathConstants';


// props,emmits
const props = defineProps<{ baseEntity: RiyoushaAdminEntityInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_ERROR: number = 400;
const SERVER_STATUS_OK: number = 200;

const inputAdminDto: Ref<RiyoushaAdminDtoInterface> = ref(new RiyoushaAdminDto());
const isNotOrgRadio: ComputedRef<string> = computed(() => inputAdminDto.value.isNotOrg ? "1" : "0");


if (props.baseEntity.riyoushaAdminId != 0) {
    const capsuleDto: GetRiyoushaAdminCapsuleInterface = new GetRiyoushaAdminCapsuleDto();
    capsuleDto.riyoushaAdminEntity = props.baseEntity;
    capsuleDto.userPersonLeastDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-riyousha/get-admin";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetRiyoushaAdminResultInterface = await response.json();
                if (SERVER_STATUS_OK === response.status) {
                    inputAdminDto.value = resultDto.riyoushaAdminDto;
                } else {
                    alert(resultDto.message);
                }
            })
            .catch((error) => { alert(error); });
    });
}

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {
    inputAdminDto.value.inputPersonNameDto = sendDto;
}

function recieveInputOrgNameInterface(sendDto: InputOrgNameInterface) {
    inputAdminDto.value.inputOrgNameDto = sendDto;
}

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    inputAdminDto.value.inputAddressDto = sendDto;
}


function onCancel() {
    // TODO 管理者など上位権限で下位権限コンポーネントを使用している場合の処理
    alert("キャンセル");
    //router.push(RoutePathConstants.PAGE_MENU_ADMIN);
}

function onSave() {

    const capsuleDto: SaveRiyoushaAdminCapsuleInterface = new SaveRiyoushaAdminCapsuleDto();
    capsuleDto.riyoushaAdminDto = inputAdminDto.value;
    capsuleDto.userPersonLeastDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-riyousha/save-admin";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: FrameworkMessageAndResultInterface = await response.json();
                alert(resultDto.message);
            })
            .catch((error) => { alert(error); });
    });
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
            <input type="checkbox">次のページで自分の所属団体を別に登録したい場合はチェックを入れる
        </div>
    </div>
    <div class="clear-both"><br></div>

    <!-- 名称 -->
    <div v-if="inputAdminDto.isNotOrg">
        <!-- 姓名入力 -->
        <ViewInputPersonName :edit-dto="inputAdminDto.inputPersonNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>
    </div>
    <div v-else>
        <!-- 団体名称入力 -->
        <InputOrgName :edit-dto="inputAdminDto.inputOrgNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputOrgNameInterface"></InputOrgName>
    </div>

    <!-- 住所 -->
    <ViewInputAddress :edit-dto="inputAdminDto.inputAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <!-- 連絡先 -->
    <InputAccess :edit-dto="inputAdminDto.inputAccessDto"></InputAccess>

    <div v-if="!props.isEditNew">

    </div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
