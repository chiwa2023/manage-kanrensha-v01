<script setup lang="ts">
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import { ref, type Ref } from 'vue';
import type RiyoushaManagerInterface from '../../../entity/riyoushaManagerEntity';
import RiyoushaManagerEntity from '../../../entity/riyoushaManagerEntity';
import RiyoushaManagerEdit from '../../common/riyousha_manager_edit/RiyoushaManagerEdit.vue';

const inputManagerEntity: Ref<RiyoushaManagerInterface> = ref(new RiyoushaManagerEntity());

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// function onCancel() {
//     router.push(RoutePathConstants.PAGE_MENU_MANAGER);
// }

// function onSave() {
//     getAuthorizedPromiseArea().then(token => {
//         if (token !== "") {
//             // TODO 選択されたUserEntityを最小限ユーザに変換して削除対象、操作者はメニューから取得する
//             const capsuleDto: FrameworkCapsuleInterface = new FrameworkCapsuleDto();

//             const url = "http://localhost:6080/add-user/manager";
//             const method = "POST";
//             const body = JSON.stringify(capsuleDto);
//             const headers = {
//                 'Accept': 'application/json',
//                 'Content-Type': 'application/json',
//                 'X-AUTH-TOKEN': 'Bearer ' + token
//             };
//             fetch(url, { method, headers, body })
//                 .then(async (response) => {
//                     const resultDto: FrameworkResultInterface = await response.json();

//                     alert(resultDto.message);
//                 })
//                 .catch((e) => { alert(e); });
//         } else {
//             alert("エラーのつもり");
//         }
//     });
// }


// 新規追加作業
const isNew: boolean = true;
// ユーザと関連者の紐づけは可能
const isCombineUser: boolean = true;

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>

    <hr>

    <!-- 運営者編集 -->
    <RiyoushaManagerEdit :base-entity="inputManagerEntity" :is-edit-new="isNew" :is-combine-user="isCombineUser"
        :user-dto="userLeastDto"></RiyoushaManagerEdit>

</template>
<style scoped></style>
