<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type RiyoushaComradeInterface from '../../../entity/riyoushaComradeEntity';
import RiyoushaComradeEntity from '../../../entity/riyoushaComradeEntity';
import type RiyoushaManagerInterface from '../../../entity/riyoushaManagerEntity';
import RiyoushaManagerEntity from '../../../entity/riyoushaManagerEntity';
import RiyoushaAdminInterface from '../../../entity/riyoushaAdminEntity';
import RiyoushaAdminEntity from '../../../entity/riyoushaAdminEntity';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from './../../../dto/user/userPersonLeastDto';
import RiyoushaComradeEdit from '../../common/riyousha_comrade_edit/RiyoushaComradeEdit.vue';
import RiyoushaManagerEdit from '../../common/riyousha_manager_edit/RiyoushaManagerEdit.vue';
import RiyoushaAdminEdit from '../../common/riyousha_admin_edit/RiyoushaAdminEdit.vue';
import UserRoleConstants from '../../../dto/user/userRoleConstants';
import type SearchRiyoushaResultInterface from '../../../dto/riyousha/searchRiyoushaResultDto';
import SearchRiyoushaResultDto from '../../../dto/riyousha/searchRiyoushaResultDto';
import type SearchRiyoushaCapsuleInterface from '../../../dto/riyousha/searchRiyoushaCapsuleDto';
import SearchRiyoushaCapsuleDto from '../../../dto/riyousha/searchRiyoushaCapsuleDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';

// よく使う定数
// const BLANK: string = "";
// const SERVER_STATUS_OK: number = 200;
// const SERVER_STATUS_ERROR: number = 400;

// ユーザメニューで取得したユーザを保持
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}

// 入力用変数
const viewStatus: Ref<string> = ref("");
const inputComradeEntity: Ref<RiyoushaComradeInterface> = ref(new RiyoushaComradeEntity());
const inputManagerEntity: Ref<RiyoushaManagerInterface> = ref(new RiyoushaManagerEntity());
const inputAdminEntity: Ref<RiyoushaAdminInterface> = ref(new RiyoushaAdminEntity());

// 検索処理変数
const capsuleDto: Ref<SearchRiyoushaCapsuleInterface> = ref(new SearchRiyoushaCapsuleDto());
const resultDto: Ref<SearchRiyoushaResultInterface> = ref(new SearchRiyoushaResultDto());

function onEditComrade(id: number) {
    alert("編集ボタン押下" + id);
    viewStatus.value = UserRoleConstants.ROLE_COMRADE;
    inputComradeEntity.value = resultDto.value.listComrade.filter((e) => id === e.riyoushaComradeId)[0];
    alert("props" + inputComradeEntity.value.riyoushaComradeId);

}

function onEditManager(id: number) {
    viewStatus.value = UserRoleConstants.ROLE_MANAGER;
    inputManagerEntity.value = resultDto.value.listManager.filter((e) => id === e.riyousharManagerId)[0];
}

function onEditAdmin(id: number) {
    viewStatus.value = UserRoleConstants.ROLE_ADMIN;
    inputAdminEntity.value = resultDto.value.listAdmin.filter((e) => id === e.riyoushaAdminId)[0];
}

function onSearch() {
    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/user-riyousha/search";
        const method = "POST";
        const body = JSON.stringify(capsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                resultDto.value = await response.json();
            })
            .catch((error) => { alert(error); });
    });
}

// 新規追加は許可しない
const isNew: boolean = false;
// ユーザと関連者の紐づけはしない
const isCombineUser: boolean = false;

function getOrgLabel(isNotOrg: boolean) {
    return isNotOrg ? "個人" : "組織";
}
</script>
<template>
    <h1>利用者検索</h1>

    <h3>API接続者</h3>
    <input type="checkbox" v-model="capsuleDto.isComradeSearch">検索する
    <table>
        <tbody>
            <tr>
                <th>個人／組織</th>
                <th>姓名・名称</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="entity of resultDto.listComrade" :key="entity.riyoushaComradeId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaComradeCode }}) <br> {{ entity.riyoushaComradeName }}</td>
                <td><button @click="onEditComrade(entity.riyoushaComradeId)">編集</button></td>
            </tr>
        </tbody>
    </table>

    <h3>運営者</h3>
    <input type="checkbox" v-model="capsuleDto.isManagerSearch">検索する
    <table>
        <tbody>
            <tr>
                <th>個人／組織</th>
                <th>姓名・名称</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="entity of resultDto.listManager" :key="entity.riyousharManagerId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaManagerCode }}) <br> {{ entity.riyoushaManagerName }}</td>
                <td><button @click="onEditManager(entity.riyousharManagerId)">編集</button></td>
            </tr>
        </tbody>
    </table>

    <h3>管理者</h3>
    <input type="checkbox" v-model="capsuleDto.isAdminSearch">検索する
    <table>
        <tbody>
            <tr>
                <th>個人／組織</th>
                <th>姓名・名称</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="entity of resultDto.listAdmin" :key="entity.riyoushaAdminId">
                <td>{{ getOrgLabel(entity.isNotOrg) }}</td>
                <td>({{ entity.riyoushaAdminCode }}) <br> {{ entity.riyoushaAdminName }}</td>
                <td><button @click="onEditAdmin(entity.riyoushaAdminId)">編集</button></td>
            </tr>
        </tbody>
    </table>

    <button @click="onSearch">検索実行</button>
    <hr>

    <!-- APIユーザ編集 -->
    <div v-if="viewStatus === UserRoleConstants.ROLE_COMRADE">
        <RiyoushaComradeEdit :base-entity="inputComradeEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userDto"></RiyoushaComradeEdit>
    </div>

    <!-- 運営者編集 -->
    <div v-if="viewStatus === UserRoleConstants.ROLE_MANAGER">
        <RiyoushaManagerEdit :base-entity="inputManagerEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userDto"></RiyoushaManagerEdit>
    </div>

    <!-- 管理者編集 -->
    <div v-if="viewStatus === UserRoleConstants.ROLE_ADMIN">
        <RiyoushaAdminEdit :base-entity="inputAdminEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userDto"></RiyoushaAdminEdit>
    </div>
</template>
<style scoped>
table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 26);
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
