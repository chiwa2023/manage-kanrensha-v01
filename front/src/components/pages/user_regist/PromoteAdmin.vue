<script setup lang="ts">
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import mockGetUserList from './mock/mockGetUserList';
import type UserPersonInterface from '../../../entity/userPersonEntity';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}


function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
function onSave() {
    getAuthorizedPromiseArea().then(token => {
        // TODO 選択された管理者と操作者をback側へ
        const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
        capsuleDto.value.userPersonLeastDto = userLeastDto.value;
        if (token !== "") {
            // パスワード更新
            const url = "http://localhost:6080/user-role/promote";
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

// ユーザ検索と選択
const selectedUserId:Ref<string> = ref("");
const listEntity: Ref<UserPersonInterface[]> = ref([]);
function onSearch() {
    listEntity.value = mockGetUserList();
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AdminInfo @send-user="recieveUser"></AdminInfo>
    <hr>

    <h1>SE権限昇格</h1>

    <h3>管理者検索条件</h3>
    <div class="left-area">
        名前
    </div>
    <div class="right-area">
        <input type="text">
    </div>
    <div class="clear-both"></div>


    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <h3>管理者検索結果</h3>
    <!-- 選択された管理者ユーザを昇格 -->
    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>&nbsp;</th>
                    <th>コード</th>
                    <th>名前</th>
                </tr>
                <tr v-for="entity of listEntity" :key="entity.userPersonId">
                    <td><input type="radio" id="promoteEntity" v-model="selectedUserId" :value="entity.userPersonId"> </td>
                    <td>{{ entity.userPersonCode }}</td>
                    <td>{{ entity.userPersonName }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">保存</button>
    </div>

</template>
<style scoped>
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
