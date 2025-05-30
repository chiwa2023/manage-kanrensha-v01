<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type NewComerInterface from '../../../dto/user/newComerDto';
import NewComerDto from '../../../dto/user/newComerDto';
import router from '../../../router';
import NewComerInfo from '../../common/user_info/NewComerInfo.vue';

const sessionStorage = window["sessionStorage"];
const newComer: Ref<NewComerInterface> = ref(new NewComerDto());

const dtoJson:string|null = sessionStorage.getItem("new-comer");
const regiCode:Ref<string> = ref(""); 
if(null !== dtoJson){
    newComer.value = JSON.parse(dtoJson);
    regiCode.value = newComer.value.registCode;
    newComer.value.registCode = "";
}

function onCheckSendCode() {
    // メールアドレスを用いて新規登録用コードを発行
    const url = "http://localhost:6080/add-user/check-code";
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
                const resultDto: NewComerInterface = await response.json();
                if (resultDto.isSuccess) {
                sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
                    alert("コードチェックができました");
                    router.push("/switch-user-kbn");
                } else {
                    alert(resultDto.message);
                }
            } else {
                alert("チェックできませんでした");
            }
        })
        .catch((error) => { alert(error); });
}

function changeCode(){
        newComer.value.registCode = newComer.value.registCode + "x";
}
</script>
<template>
    <NewComerInfo :regist-code="newComer.registCode"></NewComerInfo>
    <hr>
    <div class="one-line">
        メールアドレスに登録コードを送信しました。<br>
        メールアドレスで送付されたコードを入力して登録してください<br>
        メールアドレス：{{ newComer.mailAddress }}<br>
        登録コード：{{ regiCode }}<br>
    </div>

    <div class="left-area">
        送信されたコード
    </div>
    <div class="right-area">
        <input type="email" v-model="newComer.registCode" class="name-input">
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
        確認
    </div>
    <div class="right-area">
        <button @click="onCheckSendCode">コード確認</button>
    </div>
    <div class="clear-both"><br></div>
</template>
<style scoped></style>
