<script setup lang="ts">
import { ref, type Ref } from 'vue';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import router from '../../../router';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import type RefleshPasswordCapsuleInterface from '../../../dto/login/refleshPasswordCapsuleDto';
import RefleshPasswordCapsuleDto from '../../../dto/login/refleshPasswordCapsuleDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}


// パスワード再入力
const reInputPassword: Ref<string> = ref("");

const capsuleDto: Ref<RefleshPasswordCapsuleInterface> = ref(new RefleshPasswordCapsuleDto());
function onSave() {

    if (reInputPassword.value !== capsuleDto.value.newPassword) {
        // パスワードの再入力が異なる場合はメッセージを出して離脱
        alert("新しいパスワードと確認再入力に差異があります");
        reInputPassword.value = "";
        return;
    }

    getAuthorizedPromiseArea().then(token => {
        if (token !== "") {
            // パスワード更新
            const url = urlBack + "/edit-user/reflesh-password";
            const method = "POST";
            const body = JSON.stringify(capsuleDto.value);
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

    <h1>パスワード変更</h1>

    <div class="left-area">
        現在のパスワード
    </div>
    <div class="right-area">
        <input type="text" v-model="capsuleDto.oldPassword">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        新しいパスワード
    </div>
    <div class="right-area">
        <input type="text" v-model="capsuleDto.newPassword">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        パスワードの再入力
    </div>
    <div class="right-area">
        <input type="text" v-model="reInputPassword">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        パスワード変更
    </div>
    <div class="right-area">
        <button @click="onSave">パスワード変更</button>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">削除</button>
    </div>

</template>
<style scoped></style>
