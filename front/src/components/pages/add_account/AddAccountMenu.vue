<script setup lang="ts">
import { ref, type Ref } from 'vue';
import router from '../../../router';
import type NewComerInterface from '../../../dto/user/newComerDto';
import NewComerDto from '../../../dto/user/newComerDto';
import RoutePathConstants from '../../../routePathConstants';

const sessionStorage = window["sessionStorage"];

// 入力用Dto
const newComer: Ref<NewComerInterface> = ref(new NewComerDto());

function onRegistMail() {
    const date:Date = new Date();
    date.setDate(date.getDate() + 1);
    newComer.value.limitDateTime = date;

    // メールアドレスを用いて新規登録用コードを発行
    const url = "http://localhost:6080/add-user/publish-code";
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
                // TODO　運用時にはセッションストレージへの保存を削除する
                sessionStorage.setItem("new-comer", JSON.stringify(resultDto));
                sessionStorage.setItem("regist-code", resultDto.registCode);
                alert("登録番号を発行できました");
                router.push(RoutePathConstants.PAGE_SEND_ACCESS_CODE);
            } else {
                alert("発行できませんでした");
            }
        })
        .catch((error) => { alert(error); });
}
</script>
<template>
    <h1>新規登録</h1>

    <div class="one-line">
        連絡用メールアドレスの疎通確認をします。
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        メールアドレス(アカウント)
    </div>
    <div class="right-area">
        <input type="email" v-model="newComer.mailAddress" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        登録
    </div>
    <div class="right-area">
        <button @click="onRegistMail">メールアドレスの登録</button>
    </div>
    <div class="clear-both"><br></div>

    <hr>
    <div class="one-line">
        【参考】ユーザ区分のデータ編集範囲一覧
        <table>
            <tbody>

                <tr>
                    <th>ユーザ区分</th>
                    <th>このサイトでのデータ管理</th>
                    <th>大量・一括編集</th>
                    <th>一人編集</th>
                </tr>
                <tr>
                    <td>管理者</td>
                    <td>○</td>
                    <td>○</td>
                    <td>○</td>
                </tr>
                <tr>
                    <td>APIユーザ</td>
                    <td>×</td>
                    <td>△：申請制</td>
                    <td>○</td>
                </tr>
                <tr>
                    <td>関連者</td>
                    <td>×</td>
                    <td>×</td>
                    <td>○：本人のみ</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"></div>


</template>
<style scoped>
span.explain {
    font-size: 110%;
}

span.kbn {
    font-size: 120%;
    font-weight: bold;
}

table {
    border-style: solid;
    border-width: 1px;
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}
</style>
