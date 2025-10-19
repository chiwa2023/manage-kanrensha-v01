<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaComradeDtoInterface from '../../../dto/riyousha/riyoushaComradeDto';
import RiyoushaComradeDto from '../../../dto/riyousha/riyoushaComradeDto';
import type InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
import type InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import type SaveRiyoushaComradeCapsuleInterface from '../../../dto/riyousha/saveRiyoushaComradeCapsuleDto';
import SaveRiyoushaComradeCapsuleDto from '../../../dto/riyousha/saveRiyoushaComradeCapsuleDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import InputOrgName from '../input_org_name/InputOrgName.vue';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputAccess from '../input_access/InputAccess.vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type PickupOrgSelectOptionResultInterface from '../../../dto/riyousha/pickupOrgSelectOptionResultDto';
import PickupOrgSelectOptionResultDto from '../../../dto/riyousha/pickupOrgSelectOptionResultDto';
import type GetRiyoushaComradeResultInterface from '../../../dto/riyousha/getRiyoushaComradeResultDto';
import type GetRiyoushaByMasterIdCapsuleInterface from '../../../dto/riyousha/getRiyoushaByMasterIdCapsuleDto';
import GetRiyoushaByMasterIdCapsuleDto from '../../../dto/riyousha/getRiyoushaByMasterIdCapsuleDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';

// props,emmits
const props = defineProps<{ isEditNew: boolean, userDto: UserPersonLeastInterface }>();

// よく使う定数
// const BLANK: string = "";
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;


const inputComradeDto: Ref<RiyoushaComradeDtoInterface> = ref(new RiyoushaComradeDto());
inputComradeDto.value.isNotOrg = false;


// ユーザIdにひもづく組織選択肢を呼び出し

const listOrgOptions: Ref<SelectOptionNumberInterface[]> = ref([]);
const optionCapsuleDto: FrameworkCapsuleInterface = new FrameworkCapsuleDto();
const optionResultDto: Ref<PickupOrgSelectOptionResultInterface> = ref(new PickupOrgSelectOptionResultDto());
optionCapsuleDto.userPersonLeastDto = props.userDto;
getAuthorizedPromiseArea().then(token => {
    const url = urlBack + "/user-riyousha/org-comrade-option";
    const method = "POST";
    const body = JSON.stringify(optionCapsuleDto);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN': 'Bearer ' + token
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {
            optionResultDto.value = await response.json();
            if (SERVER_STATUS_OK === response.status) {
                listOrgOptions.value = optionResultDto.value.listOrgOptions;
                if (listOrgOptions.value.length == 1) {
                    // 1件しかないときは詳細データ呼びだし
                    selectedOrg.value = listOrgOptions.value[0].value;
                    getComradeDto();
                }
            } else {
                alert(optionResultDto.value.message);
            }
        })
        .catch((error) => { alert(error); });
});


function getComradeDto() {

    // 選択中の項目が新規の場合は値を取得しない
    const masterId: number = selectedOrg.value;
    if (masterId === 0) {
        inputComradeDto.value.listPerson.splice(0);
        inputComradeDto.value.listPerson.push(optionResultDto.value.riyoushaComradeEntity);
        return;
    }

    const capsuleDto: GetRiyoushaByMasterIdCapsuleInterface = new GetRiyoushaByMasterIdCapsuleDto();
    capsuleDto.masterId = masterId;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-riyousha/get-comrade-by-id";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: GetRiyoushaComradeResultInterface = await response.json();
                if (SERVER_STATUS_OK === response.status) {
                    inputComradeDto.value = resultDto.riyoushaComradeDto;
                } else {
                    alert(resultDto.message);
                }
            })
            .catch((error) => { alert(error); });
    });
}

// 個人に紐づく選択肢
const selectedOrg: Ref<number> = ref(0);

function recieveInputOrgNameInterface(sendDto: InputOrgNameInterface) {
    inputComradeDto.value.inputOrgNameDto = sendDto;
}

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    inputComradeDto.value.inputAddressDto = sendDto;
}


function onCancel() {
    // TODO 管理者など上位権限で下位権限コンポーネントを使用している場合の処理
    alert("キャンセル");
    // router.push(RoutePathConstants.PAGE_MENU_COMRADE);
}

function onSave() {

    const capsuleDto: SaveRiyoushaComradeCapsuleInterface = new SaveRiyoushaComradeCapsuleDto();
    capsuleDto.riyoushaComradeDto = inputComradeDto.value;
    capsuleDto.userPersonLeastDto = props.userDto;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-riyousha/save-comrade";
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
                // 正常登録できたらメニューに戻る
                router.push(RoutePathConstants.PAGE_MENU_COMRADE);
            })
            .catch((error) => { alert(error); });
    });
}

function onChangeEditComrade() {
    // Dto呼び出し
    getComradeDto();
}


function onDeletePerson(personId: number) {
    alert("個人削除" + personId);
}

function getOrgLabel(isNotOrg: boolean) {
    return isNotOrg ? "個人" : "組織";
}

function onInviteDantai() {
    // 現在選択中の組織をSessioStrageに保持してコード送信画面へ遷移
    const sessionStorage = window["sessionStorage"];
    const optionDto: SelectOptionNumberInterface = listOrgOptions.value.filter((e) => selectedOrg.value === e.value)[0];
    sessionStorage.setItem("orgId", String(optionDto.value));
    sessionStorage.setItem("orgName", String(optionDto.text));
    router.push(RoutePathConstants.PAGE_INVITE_COMRADE_PERSON);
}

</script>
<template>
    <h1>利用者APIユーザ組織編集</h1>

    <div class="left-area">
        個人／団体
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="inputComradeDto.isNotOrg" :value="true" disabled="true">個人</span>
        <span class="left-space"><input type="radio" v-model="inputComradeDto.isNotOrg" :value="false" disabled="true">
            団体</span>
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        編集対象組織
    </div>
    <div class="right-area">
        <select v-model="selectedOrg" @change="onChangeEditComrade">
            <option v-for="dto of listOrgOptions" :value=dto.value>{{ dto.text }}</option>
        </select>
    </div>
    <div class="clear-both"><br></div>

    <!-- 団体名称入力 -->
    <InputOrgName :edit-dto="inputComradeDto.inputOrgNameDto" :is-raise-edit-view="true"
        @send-input-person-name-interface="recieveInputOrgNameInterface"></InputOrgName>

    <div class="clear-both"><br></div>

    <!-- 団体紐づけ個人一覧 -->
    <!-- TODO コンポーネント化する -->
    <div class="left-area">
        組織に属する個人一覧
    </div>
    <div class="right-area">
        <table>
            <tbody>
                <tr>
                    <th>個人／組織</th>
                    <th>姓名・名称</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity of inputComradeDto.listPerson" :key="entity.riyoushaComradeId">
                    <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                    <td>({{ entity.riyoushaComradeCode }}) <br> {{ entity.riyoushaComradeName }}</td>
                    <td><button @click="onDeletePerson(entity.riyoushaComradeId)"
                            :disabled="0 === selectedOrg">削除</button></td>
                </tr>
            </tbody>
        </table>
        <br>
        <button :disabled="0 === selectedOrg" @click="onInviteDantai">個人を組織に招待する</button>
    </div>
    <div class="clear-both"><br></div>

    <!-- 住所 -->
    <ViewInputAddress :edit-dto="inputComradeDto.inputAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <!-- 連絡先 -->
    <InputAccess :edit-dto="inputComradeDto.inputAccessDto"></InputAccess>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>


</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 26);
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
