<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from './../../../dto/user/userPersonLeastDto';
import ComradeInfo from '../../common/user_info/ComradeInfo.vue';
import type SendAcceptCodeCapsuleInterface from '../../../dto/riyousha/sendAcceptCodeCapsuleDto';
import SendAcceptCodeCapsuleDto from '../../../dto/riyousha/sendAcceptCodeCapsuleDto';
import type SendAcceptCodeResultInterface from '../../../dto/riyousha/sendAcceptCodeResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type RiyoushaInviteNewInterface from '../../../entity/riyoushaInviteNewEntity';
import RiyoushaInviteNewEntity from '../../../entity/riyoushaInviteNewEntity';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// コード突合せ確認格納Dto
const capsuleDto: Ref<SendAcceptCodeCapsuleInterface> = ref(new SendAcceptCodeCapsuleDto());
capsuleDto.value.userPersonLeastDto = userLeastDto.value;

const listAcceptCode: Ref<RiyoushaInviteNewInterface[]> = ref([]);

// 紐づけ申請中リスト取得格納Dto
const capsuleDtoGetList: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());

getAuthorizedPromiseArea().then(token => {
    capsuleDtoGetList.value.userPersonLeastDto = userLeastDto.value;
    const url = "http://localhost:6080/user-riyousha/get-accept-code-list";
    const method = "POST";
    const body = JSON.stringify(capsuleDtoGetList.value);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'X-AUTH-TOKEN': 'Bearer ' + token
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {
            const resultDto: SendAcceptCodeResultInterface = await response.json();
            if (resultDto.isFailure) {
                alert(resultDto.message);
            } else {
                // リストに複写して候補が1件なら選択
                listAcceptCode.value = resultDto.listAcceptCode;
                if(listAcceptCode.value.length === 1){
                    selectedDantai.value = listAcceptCode.value[0].riyoushaInviteNewId;
                    onChangeDantai();
                }
            }
        })
        .catch((error) => { alert(error); });
});

function onCheckSendCode() {
    capsuleDto.value.riyoushaInviteNewEntity = entityEdit.value;
    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/user-riyousha/save-accept-code";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
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

    router.push(RoutePathConstants.PAGE_MENU_COMRADE);
}


// 編集入力準備
const entityEdit: Ref<RiyoushaInviteNewInterface> = ref(new RiyoushaInviteNewEntity());
const selectedDantai: Ref<number> = ref(0);
function onChangeDantai() {
    // 選択された団体を編集対象にする
    entityEdit.value = structuredClone(toRaw(
        listAcceptCode.value.filter((e) => selectedDantai.value === e.riyoushaInviteNewId)[0]));
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ComradeInfo @send-user="recieveUser"></ComradeInfo>
    <hr>


    <h1>APIユーザ組織に個人所属承諾</h1>
    <hr>
    <div class="one-line">

        メールアドレスに登録コードを送信しました。<br>
        メールアドレスで送付されたコードを入力して登録してください<br>

        紐づけ申請が出ている団体
        <select v-model="selectedDantai" @change="onChangeDantai()" class="left-space">
            <option v-for="entity of listAcceptCode" :value="entity.riyoushaInviteNewId">{{ entity.riyoushaDantaiName }}
            </option>
        </select><br>

        ユーザ名：({{ userLeastDto.userPersonCode }})<span class="left-space"> {{ userLeastDto.userPersonName }}</span><br>
        メールアドレス：{{ entityEdit.mailAddress }}<br>
        登録コード：{{ entityEdit.registCode }}<br>
    </div>

    <div class="left-area">
        送信されたコード
    </div>
    <div class="right-area">
        <input type="email" v-model="capsuleDto.inputAcceptCode" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        ユーザ
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.personUserCode" class="name-input" disabled="true"><span
            class="left-space"><input type="text" v-model="entityEdit.personUserName" class="name-input"
                disabled="true"></input></span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        メールアドレス
    </div>
    <div class="right-area">
        <input type="email" v-model="entityEdit.mailAddress" class="name-input" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        確認
    </div>
    <div class="right-area">
        <button @click="onCheckSendCode">コード確認</button>
    </div>
    <div class="clear-both"><br></div>

</template>
<style scoped></style>
