<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type LoginUserCapsuleInterface from './dto/login/loginUserCapsuleDto';
import LoginUserCapsuleDto from './dto/login/loginUserCapsuleDto';
import type LoginUserResultInterface from './dto/login/loginUserResultDto';
import UserPersonLeastDto from './dto/user/userPersonLeastDto';
import router from './router';

const sessionStorage = window["sessionStorage"];

//const THIS_PAGE_ROLE: string = "ROLE_admin";
//const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
//userDto.value.listRoles.push(THIS_PAGE_ROLE);
//userDto.value.listRoles.push("ROLE_manager");
//userDto.value.listRoles.push("ROLE_comrade");
//userDto.value.listRoles.push("ROLE_partner");
//sessionStorage.setItem("userDto", JSON.stringify(userDto.value));

const user: Ref<LoginUserCapsuleInterface> = ref(new LoginUserCapsuleDto());
function onLogin() {
    const url = "http://localhost:6080/login";
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
                    case "ROLE_manager":
                        // 管理者
                        router.push("/menu-manager");
                        break;
                    case "ROLE_comrade":
                        // APIユーザ
                        router.push("/menu-comrade");
                        break;
                    case "ROLE_partner_person":
                    case "ROLE_partner_corp":
                    case "ROLE_partner_poli_org":
                        // 関連者
                        router.push("/menu-partner");
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
            <RouterLink to="/add-account">新規登録ですか?</RouterLink><br>
            <br>
            <RouterLink to="/reset_password/propose">※パスワードを忘れたので再発行</RouterLink><br>
        </div>
    </div>

    <div style="clear: both;"></div>

    <!--
    <hr>
    <RouterLink to="/menu-manager">管理者メニュー</RouterLink><br>
    <RouterLink to="/menu-comrade">APIユーザメニュー</RouterLink><br>
    <RouterLink to="/menu-kanrensha">関連者メニュー</RouterLink><br>
     -->

    <hr>
    <RouterLink to="/component">コンポーネント作成台紙</RouterLink><br>

</template>
<style scoped></style>
