<script setup lang="ts">
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import router from '../../../router';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user:UserPersonLeastInterface){
    userLeastDto.value = user;
}

function onCancel() {
    router.push("/menu-comrade");
}

function onSave() {
    getAuthorizedPromiseArea().then(token => {
        if (token !== "") {
            // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
            const capsuleDto: FrameworkCapsuleInterface = new FrameworkCapsuleDto();

            const url = "http://localhost:6080/add-user/manager";
            const method = "POST";
            const body = JSON.stringify(capsuleDto);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
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
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>

    <h1>新規管理者登録</h1>

    入力内容
    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

</template>
<style scoped></style>
