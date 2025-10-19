<script setup lang="ts">
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import AllUserInfo from '../../common/user_info/AllUserInfo.vue';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import UserRoleConstants from '../../../dto/user/userRoleConstants';

const BLANK: string = "";

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// 初期値設定
const isManager: Ref<boolean> = ref(userLeastDto.value.listRoles.includes(UserRoleConstants.ROLE_MANAGER));
const isComrade: Ref<boolean> = ref(userLeastDto.value.listRoles.includes(UserRoleConstants.ROLE_COMRADE));
const selectedPartner: Ref<string> = ref(BLANK);
if (userLeastDto.value.listRoles.includes(UserRoleConstants.ROLE_PARTNER_PERSON)) {
    selectedPartner.value = UserRoleConstants.ROLE_PARTNER_PERSON;
}
if (userLeastDto.value.listRoles.includes(UserRoleConstants.ROLE_PARTNER_CORP)) {
    selectedPartner.value = UserRoleConstants.ROLE_PARTNER_CORP;
}
if (userLeastDto.value.listRoles.includes(UserRoleConstants.ROLE_PARTNER_POLI_ORG)) {
    selectedPartner.value = UserRoleConstants.ROLE_PARTNER_POLI_ORG;
}
// 関連者がすでに設定される場合は関連者間で変更させない
const isSetPartner: boolean = selectedPartner.value !== BLANK;

function onPartnerClear() {
    selectedPartner.value = BLANK;
}

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
function onSave() {

    // 入力内容から権限リストを作成
    const listRole: string[] = [];
    if (isManager.value) {
        listRole.push(UserRoleConstants.ROLE_MANAGER);
    }
    if (isComrade.value) {
        listRole.push(UserRoleConstants.ROLE_COMRADE);
    }
    if (selectedPartner.value !== BLANK) {
        listRole.push(selectedPartner.value);
    }

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
        capsuleDto.value.userPersonLeastDto = userLeastDto.value;
        if (token !== "") {
            // パスワード更新
            const url = urlBack + "/user-role/change";
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
            // トークン更新ができなかった、jwtがそもそも入っていなかったなどの場合は
            // 問答無用でログインページに遷移
            sessionStorage.setItem("loginError", "ログインできていない状態のためログインページに戻ります");
            router.push(RoutePathConstants.PAGE_LOGIN);
        }
    });

}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AllUserInfo @send-user="recieveUser"></AllUserInfo>
    <hr>

    <h1>権限変更</h1>

    <div class="left-area">
        ユーザ区分
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isManager">このサイトで<span class="explain">大量・一括関連者データ編集</span>を行いたい<span
            class="kbn">管理者</span><br>
        <input type="checkbox" v-model="isComrade">自作ソフトウェアに<span class="explain">このサイトの関連者情報を取り込みたい</span><span
            class="kbn">APIユーザ</span><br>
        <input type="radio" id="role" v-model="selectedPartner" value="ROLE_partner_person"
            :disabled="isSetPartner"><span class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者個人</span><br>
        <input type="radio" id="role" v-model="selectedPartner" value="ROLE_partner_corp" :disabled="isSetPartner"><span
            class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者企業・団体</span><br>
        <input type="radio" id="role" v-model="selectedPartner" value="ROLE_partner_poli_org"
            :disabled="isSetPartner"><span class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者政治団体</span><br>
        <button @click="onPartnerClear">関連者の選択を中断する</button><br>
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">保存</button>
    </div>

</template>
<style scoped></style>
