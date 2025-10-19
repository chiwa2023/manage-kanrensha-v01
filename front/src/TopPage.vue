<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type LoginUserCapsuleInterface from './dto/login/loginUserCapsuleDto';
import LoginUserCapsuleDto from './dto/login/loginUserCapsuleDto';
import type LoginUserResultInterface from './dto/login/loginUserResultDto';
import router from './router';
import RoutePathConstants from './routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

const sessionStorage = window["sessionStorage"];

// ログイン後にログイン画面に戻ってきたときはログアウト処理
const userText: string | null = sessionStorage.getItem("userDto");
if (userText !== null) {
    const url = urlBack + "/logout";
    const method = "POST";
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers })
        .then(async (response) => {
            const status = await response.status;
            if (status !== 200) {
                // TODO ログアウト失敗時の対処方法を修正する
                alert("ログアウトに失敗しました" + status);
            } else {
                sessionStorage.removeItem("userDto")
            }
        })
        .catch((error) => { alert(error); });
}

const loginErrorText: string | null = sessionStorage.getItem("loginError");
if (loginErrorText !== null) {
    alert(loginErrorText);
    sessionStorage.removeItem("loginError");
}

// const THIS_PAGE_ROLE: string = "ROLE_admin";
// const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
// userDto.value.listRoles.push(THIS_PAGE_ROLE);
// userDto.value.listRoles.push("ROLE_manager");
// userDto.value.listRoles.push("ROLE_comrade");
// userDto.value.listRoles.push("ROLE_partner");
// sessionStorage.setItem("userDto", JSON.stringify(userDto.value));

const user: Ref<LoginUserCapsuleInterface> = ref(new LoginUserCapsuleDto());
function onLogin() {
    const url = urlBack + "/login";
    const method = "POST";
    const body = JSON.stringify(user.value);
    const headers = {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    };
    fetch(url, { method, headers, body })
        .then(async (response) => {

            const status = response.status;
            if (status === 200) {
                const resultDto: LoginUserResultInterface = await response.json();
                sessionStorage.setItem("userDto", JSON.stringify(resultDto.userPersonLeastDto));
                sessionStorage.setItem("jwtToken", JSON.stringify(resultDto.jwtTokenDto));
                switch (resultDto.userPersonLeastDto.listRoles[0]) {
                    case "ROLE_admin":
                        // 管理者
                        router.push(RoutePathConstants.PAGE_MENU_ADMIN);
                        break;
                    case "ROLE_manager":
                        // 管理者
                        router.push(RoutePathConstants.PAGE_MENU_MANAGER);
                        break;
                    case "ROLE_comrade":
                        // APIユーザ
                        router.push(RoutePathConstants.PAGE_MENU_COMRADE);
                        break;
                    case "ROLE_partner_person":
                    case "ROLE_partner_corp":
                    case "ROLE_partner_poli_org":
                        // 関連者
                        router.push(RoutePathConstants.PAGE_MENU_PARTNER);
                        break;
                    default:
                        alert("権限設定が登録できませんでした");
                        break;
                }
            }
            if (status === 401) {
                alert("status 401");
            }
        })
        .catch((error) => { alert(error); });
}

// パスワード可視／不可視切り替えロジック
const isPasswordVisible: Ref<boolean> = ref(false);
const passwordInputType: Ref<string> = ref("password");
function changeVisiblePassword() {
    isPasswordVisible.value = !isPasswordVisible.value;

    if (isPasswordVisible.value) {
        passwordInputType.value = "text";
    } else {
        passwordInputType.value = "password";
    }
}

</script>
<template>
    <h1>政治資金関連者コード標準化サイト</h1>
    <div style="text-align: center">
        <div
            style="width: 50%;margin-left: auto;margin-right: auto;border-width: 2px;border-style: solid;margin-top: 5%;">
            <br>
            ユーザログイン<br>
            <br>
            <div style="width: 99%;margin-left: auto;margin-right: auto;">
                <div class="left-area-center">
                    メールアドレス
                </div>
                <div class="right-area-center">
                    <input v-model="user.userId">
                </div>
                <div class="clear-both"><br></div>
                <div class="left-area-center">
                    <span @click="changeVisiblePassword"><img v-show="!isPasswordVisible"
                            src="./assets/password_hidden.png" style="height:1.35em"><img v-show="isPasswordVisible"
                            src="./assets/password_visible.png" style="height:1.35em">&nbsp;パスワード</span>
                </div>
                <div class="right-area-center">
                    <input :type="passwordInputType" v-model="user.password">
                </div>
                <div class="clear-both"><br></div>
            </div>
            <button @click="onLogin">ログイン</button><br>
            <br>
            <RouterLink :to=RoutePathConstants.PAGE_ADD_ACCOUNT>新規登録ですか?</RouterLink><br>
            <br>
            <RouterLink to="/reset_password/propose">※パスワードを忘れたので再発行</RouterLink><br>
        </div>
    </div>

    <div style="clear: both;"></div>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_HISTORY>関連者履歴データダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_MASTER_MIN>関連者マスタ最小ダウンロード(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_HISTORY>関連者履歴データダウンロード差分(公開文書記載水準)</RouterLink><br>
    <RouterLink :to=RoutePathConstants.PAGE_DOWNLOAD_SABUN_MASTER_MIN>関連者マスタ最小ダウンロード差分(公開文書記載水準)</RouterLink><br>

    <hr>
    <RouterLink :to=RoutePathConstants.PAGE_COMPONENT>コンポーネント作成台紙</RouterLink><br>

</template>
<style scoped></style>
