<script setup lang="ts">
import { ref, type Ref } from 'vue';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
function onEdit() {
    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
        capsuleDto.value.userPersonLeastDto = userLeastDto.value;
        if (token !== "") {
            // パスワード更新
            const url = urlBack + "/";
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

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo @send-user="recieveUser"></AllUserInfo>
    <hr>

    <h1>ユーザ編集</h1>

    <div class="left-area">
        編集
    </div>
    <div class="right-area">
        <button @click="onEdit">編集</button>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onEdit" class="footer-button left-space">削除</button>
    </div>

</template>
<style scoped></style>
