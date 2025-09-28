<script setup lang="ts">
import { ref, type Ref } from 'vue';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import router from '../../../router';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import type UserPersonLeastInterface from './../../../dto/user/userPersonLeastDto';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

function onWithdraw() {

    // TODO アラート

    getAuthorizedPromiseArea().then(token => {
        if (token !== "") {
            const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
            capsuleDto.value.userPersonLeastDto = userLeastDto.value;
            // TODO capsuleDtoを新設しUserDtoを削除対象、操作者両方に指定する処理
            const url = urlBack + "/user/delete";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    // 結果を受け取ってメッセージ表示
                    const resultDto: FrameworkResultInterface = await response.json();
                    alert(resultDto.message);
                })
                .catch((e) => { alert(e); });
        } else {
            alert("エラーのつもり");
        }
    });

}

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
</script>
<template>
    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo @send-user="recieveUser"></AllUserInfo>
    <hr>

    <h1>退会処理</h1>

    <div class="left-area">
        退会
    </div>
    <div class="right-area">
        <button @click="onWithdraw">退会する</button>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onWithdraw" class="footer-button left-space">削除</button>
    </div>

</template>
<style scoped></style>
