<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type RiyoushaComradeDtoInterface from '../../../dto/riyousha/riyoushaComradeDto';
import RiyoushaComradeDto from '../../../dto/riyousha/riyoushaComradeDto';
import type RiyoushaComradeEntityInterface from '../../../entity/riyoushaComradeEntity';
import type InputPersonNameInterface from '../../../dto/input_person_name/inputPersonNameDto';
import type InputOrgNameInterface from '../../../dto/input_org_name/inputOrgNameDto';
import type InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import type SaveRiyoushaComradeCapsuleInterface from '../../../dto/riyousha/saveRiyoushaComradeCapsuleDto';
import SaveRiyoushaComradeCapsuleDto from '../../../dto/riyousha/saveRiyoushaComradeCapsuleDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import type GetRiyoushaComradeCapsuleInterface from '../../../dto/riyousha/getRiyoushaComradeCapsuleDto';
import GetRiyoushaComradeCapsuleDto from '../../../dto/riyousha/getRiyoushaComradeCapsuleDto';
import type GetRiyoushaComradeResultInterface from '../../../dto/riyousha/getRiyoushaComradeResultDto';
import ViewInputPersonName from '../input_person_name/ViewInputPersonName.vue';
import InputOrgName from '../input_org_name/InputOrgName.vue';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputAccess from '../input_access/InputAccess.vue';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';

// props,emmits
const props = defineProps<{ baseEntity: RiyoushaComradeEntityInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();

// よく使う定数
// const BLANK: string = "";
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

const inputComradeDto: Ref<RiyoushaComradeDtoInterface> = ref(new RiyoushaComradeDto());
inputComradeDto.value.isNotOrg = props.baseEntity.isNotOrg;

watch(props, () => {
    // リストからの変更
    onChangeEntity();
});


// 起動時
onChangeEntity();

function onChangeEntity() {
    if (props.baseEntity.riyoushaComradeId != 0) {
        const capsuleDto: GetRiyoushaComradeCapsuleInterface = new GetRiyoushaComradeCapsuleDto();
        capsuleDto.riyoushaComradeEntity = props.baseEntity;
        capsuleDto.userPersonLeastDto = props.userDto;

        getAuthorizedPromiseArea().then(token => {
            const url = urlBack + "/user-riyousha/get-comrade";
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

}


const isRecordOrg: Ref<boolean> = ref(false);

function recieveInputPersonNameInterface(sendDto: InputPersonNameInterface) {
    inputComradeDto.value.inputPersonNameDto = sendDto;
}

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
                if (isRecordOrg.value) {
                    // 組織登録 
                    router.push(RoutePathConstants.PAGE_COMBINE_COMRADE);

                } else {
                    // 引き続き組織を登録しない場合はメニューに戻る
                    router.push(RoutePathConstants.PAGE_MENU_COMRADE);
                }
            })
            .catch((error) => { alert(error); });
    });
}

function onDeletePerson(posIndex: number) {
    // 指定位置のEntity削除
    inputComradeDto.value.listPerson.splice(posIndex, 1);
}

function getOrgLabel(isNotOrg: boolean) {
    return isNotOrg ? "個人" : "組織";
}
</script>
<template>
    <h1>利用者APIユーザ編集</h1>


    <div class="left-area">
        個人／団体
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="inputComradeDto.isNotOrg" :value="true" disabled="true">個人</span>
        <span class="left-space"><input type="radio" v-model="inputComradeDto.isNotOrg" :value="false" disabled="true">
            団体</span>
        <div v-if="props.isEditNew">
            <br>
            <input type="checkbox" v-model="isRecordOrg">次のページで自分の所属団体を登録する
        </div>
    </div>
    <div class="clear-both"><br></div>

    <!-- 名称 -->
    <div v-if="inputComradeDto.isNotOrg">
        <!-- 姓名入力 -->
        <ViewInputPersonName :edit-dto="inputComradeDto.inputPersonNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputPersonNameInterface"></ViewInputPersonName>
    </div>
    <div v-else>
        <!-- 団体名称入力 -->
        <InputOrgName :edit-dto="inputComradeDto.inputOrgNameDto" :is-raise-edit-view="true"
            @send-input-person-name-interface="recieveInputOrgNameInterface"></InputOrgName>
    </div>
    <div class="clear-both"><br></div>

    <!-- 団体紐づけ個人一覧 -->
    <!-- TODO コンポーネント化する -->
    <div v-if="!inputComradeDto.isNotOrg">
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
                    <tr v-for="(entity, index) of inputComradeDto.listPerson" :key="entity.riyoushaComradeId">
                        <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                        <td>({{ entity.riyoushaComradeCode }}) <br> {{ entity.riyoushaComradeName }}</td>
                        <td><button @click="onDeletePerson(index)">削除</button></td>
                    </tr>
                </tbody>
            </table>
            <br>
            <button>個人を組織に招待する</button>
        </div>
        <div class="clear-both"><br></div>
    </div>

    <!-- 住所 -->
    <ViewInputAddress :edit-dto="inputComradeDto.inputAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>
    <div class="clear-both"><br></div>

    <!-- 連絡先 -->
    <InputAccess :edit-dto="inputComradeDto.inputAccessDto"></InputAccess>
    <div class="clear-both"><br></div>


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
