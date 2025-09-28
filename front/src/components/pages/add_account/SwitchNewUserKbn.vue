<script setup lang="ts">
import type NewComerInterface from '../../../dto/user/newComerDto';
import NewComerInfo from '../../common/user_info/NewComerInfo.vue';
import NewComerDto from '../../../dto/user/newComerDto';
import { ref, type Ref } from 'vue';
import type LoginUserResultInterface from '../../../dto/login/loginUserResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 入力用Dto
const sessionStorage = window["sessionStorage"];
const newComer: Ref<NewComerInterface> = ref(new NewComerDto());
const dtoJson: string | null = sessionStorage.getItem("new-comer");
if (null !== dtoJson) {
    newComer.value = JSON.parse(dtoJson);
}

// 入力されたコードをチェックして正常ならパスワード入力と
// 権限を選択してもらう
function onRegistUser() {
    // パスワード、権限、ニックネームを登録
    const url = urlBack + "/add-user/user";
    const method = "POST";
    const body = JSON.stringify(newComer.value);
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

                switch (newComer.value.role) {
                    case "manager":
                        // 管理者
                        router.push(RoutePathConstants.PAGE_INPUT_MANAGER);
                        break;
                    case "comrade":
                        // APIユーザ
                        router.push(RoutePathConstants.PAGE_INPUT_COMRADE);
                        break;
                    case "partner_person":
                    case "partner_corp":
                    case "partner_poli_org":
                        // 関連者
                        router.push(RoutePathConstants.PAGE_INPUT_KANRENSHA);
                        break;
                    default:
                        alert("権限設定が登録できませんでした");
                        break;
                }
            } else {
                alert("登録できませんでした");
            }
        })
        .catch((error) => { alert(error); });

}

// パスワード可視／不可視切り替えロジック
const isPasswordVisible: Ref<boolean> = ref(true);
const passwordInputType: Ref<string> = ref("text");
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
    <!-- 新規登録コードチェック -->
    <NewComerInfo :regist-code="newComer.registCode"></NewComerInfo>
    <hr>
    <h1>ユーザ登録</h1>

    <div class="left-area">
        送信されたコード
    </div>
    <div class="right-area">
        <input type="email" v-model="newComer.registCode" class="name-input" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        メールアドレス(アカウント)
    </div>
    <div class="right-area">
        <input type="email" v-model="newComer.mailAddress" class="name-input" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        このサイトだけで使用する名前
    </div>
    <div class="right-area">
        <input type="text" v-model="newComer.nickName" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        パスワード &nbsp;<span @click="changeVisiblePassword"><img v-show="!isPasswordVisible"
                src="../../../assets/password_hidden.png" style="height:1.35em"><img v-show="isPasswordVisible"
                src="../../../assets/password_visible.png" style="height:1.35em"></span>
    </div>
    <div class="right-area">
        <input :type="passwordInputType" v-model="newComer.password" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        ユーザ区分
    </div>
    <div class="right-area">
        <input type="radio" id="role" v-model="newComer.role" value="manager">このサイトで<span
            class="explain">大量・一括関連者データ編集</span>を行いたい<span class="kbn">運営者</span><br>
        <input type="radio" id="role" v-model="newComer.role" value="comrade">自作ソフトウェアに<span
            class="explain">このサイトの関連者情報を取り込みたい</span><span class="kbn">APIユーザ</span><br>
        <input type="radio" id="role" v-model="newComer.role" value="partner_person"><span
            class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者個人</span><br>
        <input type="radio" id="role" v-model="newComer.role" value="partner_corp"><span
            class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者企業・団体</span><br>
        <input type="radio" id="role" v-model="newComer.role" value="partner_poli_org"><span
            class="explain">政治団体と資金・物品の取引</span>をする<span class="kbn">関連者政治団体</span><br>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        登録
    </div>
    <div class="right-area">
        <button @click="onRegistUser">ユーザ登録</button>
    </div>
    <div class="clear-both"><br></div>
</template>
<style scoped></style>
