<script setup lang="ts">
import { ref, type Ref } from 'vue';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type SendInviteCodeCapsuleInterface from '../../../dto/riyousha/sendInviteCodeCapsuleDto';
import SendInviteCodeCapsuleDto from '../../../dto/riyousha/sendInviteCodeCapsuleDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import UserRoleConstants from '../../../dto/user/userRoleConstants';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// よく使う定数
// const BLANK: string = "";
const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// 選択された組織情報を取り出し
const sessionStorage = window["sessionStorage"];
const orgId: string | null = sessionStorage.getItem("orgId");
const orgName: string | null = sessionStorage.getItem("orgName");

const capsuleDto: Ref<SendInviteCodeCapsuleInterface> = ref(new SendInviteCodeCapsuleDto());
if (orgId !== null) {
    capsuleDto.value.orgId = parseInt(orgId);
    capsuleDto.value.orgRole = UserRoleConstants.ROLE_MANAGER;
}

function onSendCode() {

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/user-riyousha/publish-accept-code";
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
                if (SERVER_STATUS_OK === response.status) {
                    // 正常登録できたらメニューに戻る
                    router.push(RoutePathConstants.PAGE_MENU_MANAGER);
                }
            })
            .catch((error) => { alert(error); });
    });
}
</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>

    <h1>APIユーザ組織に個人所属招待</h1>

    <div class="one-line">
        招待する個人に登録用コード送信をします。
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        招待している組織
    </div>
    <div class="right-area">
        {{ orgName }}
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        メールアドレス(アカウント)
    </div>
    <div class="right-area">
        <input type="email" v-model="capsuleDto.personMail" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        登録
    </div>
    <div class="right-area">
        <button @click="onSendCode">メールアドレスの登録</button>
    </div>
    <div class="clear-both"><br></div>

</template>
<style scoped></style>
