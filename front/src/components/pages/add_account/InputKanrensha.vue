<script setup lang="ts">
import { ref, type Ref } from 'vue';
import PartnerCorpEdit from '../../common/partner_corp_edit/PartnerCorpEdit.vue';
import PartnerPersonEdit from '../../common/partner_person_edit/PartnerPersonEdit.vue';
import PartnerPoliOrgEdit from '../../common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import PersonNoDto from '../../../dto/partner_person/personNoDto';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';
import CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
import UserPersonLeastDto from './../../../dto/user/userPersonLeastDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import PartnerInfo from '../../common/user_info/PartnerInfo.vue';
import UserRoleConstants from '../../../dto/user/userRoleConstants';

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// 編集Dto
const inputPersonDto: Ref<PersonNoInterface> = ref(new PersonNoDto());
const inputPoliOrgDto: Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
const inputCorpNoDto: Ref<CorpNoInterface> = ref(new CorpNoDto());

// 権限取得
const userDto: Ref<UserPersonLeastDto> = ref(new UserPersonLeastDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
let role: string = "";
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
    role = userDto.value.listRoles[0];
}
let viewStatus: number = 1;
switch (role) {
    case UserRoleConstants.ROLE_PARTNER_PERSON:
        inputPersonDto.value.inputPersonNameDto.allName = userDto.value.userPersonName;
        viewStatus = 1;
        break;
    case UserRoleConstants.ROLE_PARTNER_CORP:
        inputCorpNoDto.value.inputOrgNameDto.orgName = userDto.value.userPersonName;
        viewStatus = 2;
        break;
    case UserRoleConstants.ROLE_PARTNER_POLI_ORG:
        inputPoliOrgDto.value.inputOrgNameDto.orgName = userDto.value.userPersonName;
        viewStatus = 3;
        break;
    default:
        break;
}

// 新規追加は作業をする
const isNew: boolean = true;
// ユーザと関連者の紐づけはする
const isCombineUser: boolean = true;
</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <PartnerInfo @send-user="recieveUser"></PartnerInfo>
    <hr>

    <h1>新規関連者登録</h1>
    <!-- 編集対象が個人 -->
    <div v-if="viewStatus == 1">
        <PartnerPersonEdit :edit-dto="inputPersonDto"   :is-edit-new="isNew" :is-combine-user="isCombineUser" :user-dto="userLeastDto"></PartnerPersonEdit>
    </div>
    <!-- 編集対象が法人／団体 -->
    <div v-if="viewStatus == 2">
        <PartnerCorpEdit :edit-dto="inputCorpNoDto"   :is-edit-new="isNew" :is-combine-user="isCombineUser" :user-dto="userLeastDto"></PartnerCorpEdit>
    </div>
    <!-- 編集対象が政治団体 -->
    <div v-if="viewStatus == 3">
        <PartnerPoliOrgEdit :edit-dto="inputPoliOrgDto"   :is-edit-new="isNew" :is-combine-user="isCombineUser" :user-dto="userLeastDto"></PartnerPoliOrgEdit>
    </div>
</template>
<style scoped></style>
