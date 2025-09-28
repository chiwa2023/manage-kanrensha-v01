<script setup lang="ts">
import { ref, type Ref } from 'vue';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import router from '../../../router';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import AdminInfo from '../../common/user_info/AdminInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import RoutePathConstants from '../../../routePathConstants';
import type UserPersonInterface from '../../../entity/userPersonEntity';
import mockGetUserList from './mock/mockGetUserList';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

function onSave() {

    // TODO アラート

    getAuthorizedPromiseArea().then(token => {
        if (token !== "") {
            // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
            const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
            capsuleDto.value.userPersonLeastDto = userLeastDto.value;

            const url = urlBack + "/";
            const method = "POST";
            const body = JSON.stringify(null);
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
const selectedUserId: Ref<string> = ref("");
const listEntity: Ref<UserPersonInterface[]> = ref([]);
function onSearch() {
    listEntity.value = mockGetUserList();
}


function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <AdminInfo @send-user="recieveUser"></AdminInfo>
    <hr>

    <h1>ユーザ変更</h1>

    <h3>ユーザ検索条件</h3>
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


    <h3>ユーザ検索結果</h3>
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
                    <td><input type="radio" id="promoteEntity" v-model="selectedUserId" :value="entity.userPersonId">
                    </td>
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
