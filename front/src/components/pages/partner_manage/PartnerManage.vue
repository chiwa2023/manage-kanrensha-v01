<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import PartnerPersonEdit from '../../common/partner_person_edit/PartnerPersonEdit.vue';
import SearchPersonNo from '../../common/search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import PersonNoDto from '../../../dto/partner_person/personNoDto';
import PartnerCorpEdit from '../../common/partner_corp_edit/PartnerCorpEdit.vue';
import PartnerPoliOrgEdit from '../../common/partner_poli_org_edit/PartnerPoliOrgEdit.vue';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';
import SearchCorpNo from '../../common/search_corp_no/SearchCorpNo.vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
import SearchPoliOrg from '../../common/search_poli_org/SearchPoliOrg.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type MasterPersonInterface from '../../../entity/masterPersonEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type GetKanrenshaPersonCapsuleInterface from '../../../dto/kanrensha/getKanrenshaPersonCapsuleDto';
import GetKanrenshaPersonCapsuleDto from '../../../dto/kanrensha/getKanrenshaPersonCapsuleDto';
import type GetKanrenshaPersonResultInterface from '../../../dto/kanrensha/getKanrenshaPersonResultDto';
import type MasterPoliticalOrganizationInterface from '../../../entity/masterPoliticalOrganizationEntity';
import type GetKanrenshaPoliOrgCapsuleInterface from '../../../dto/kanrensha/getKanrenshaPoliOrgCapsuleDto';
import GetKanrenshaPoliOrgCapsuleDto from '../../../dto/kanrensha/getKanrenshaPoliOrgCapsuleDto';
import type GetKanrenshaPoliOrgResultInterface from '../../../dto/kanrensha/getKanrenshaPoliOrgResultDto';
import type MasterCorporationInterface from '../../../entity/masterCorporationEntity';
import type GetKanrenshaCorpCapsuleInterface from '../../../dto/kanrensha/getKanrenshaCorpCapsuleDto';
import GetKanrenshaCorpCapsuleDto from '../../../dto/kanrensha/getKanrenshaCorpCapsuleDto';
import type GetKanrenshaCorpResultInterface from '../../../dto/kanrensha/getKanrenshaCorpResultDto';

// よく使う定数
const BLANK: string = "";
const SERVWER_STATUS_OK: number = 200;
// const SERVWER_STATUS_ERROR: number = 400;

// ユーザメニューで取得したユーザを保持
const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

// 表示
const viewStatus: Ref<number> = ref(1);

const isEditNew: Ref<boolean> = ref(true);

/** 選択された関連者個人を受信する */
const inputPersonDto: Ref<PersonNoInterface> = ref(new PersonNoDto());
function recievePersonNoInterface(sendDto: MasterPersonInterface) {

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<GetKanrenshaPersonCapsuleInterface> = ref(new GetKanrenshaPersonCapsuleDto());
        capsuleDto.value.masterPersonEntity = sendDto;
        if (token !== BLANK) {
            // 保存処理
            const method = "POST";
            const url: string = "http://localhost:6080/user-kanrensha/get-person";
            const body = JSON.stringify(capsuleDto.value);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    // 結果を受け取ってメッセージ表示
                    const resultDto: GetKanrenshaPersonResultInterface = await response.json();
                    if (SERVWER_STATUS_OK === response.status) {
                        inputPersonDto.value = structuredClone(toRaw(resultDto.kanrenshaPersonDto));
                        isEditNew.value = false;
                    } else {
                        alert(resultDto.message);
                    }
                })
                .catch((e) => { alert(e); });
        } else {
            alert("エラーのつもり");
        }
    });
}

/** 選択された関連者政治団体を受信する */
const inputPoliOrgDto: Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
function recievePoliOrgNoInterface(sendDto: MasterPoliticalOrganizationInterface) {
    //inputPoliOrgDto.value = structuredClone(toRaw(sendDto));
    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<GetKanrenshaPoliOrgCapsuleInterface> = ref(new GetKanrenshaPoliOrgCapsuleDto());
        capsuleDto.value.masterPoliticalOrganizationEntity = sendDto;
        if (token !== BLANK) {
            // 保存処理
            const method = "POST";
            const url: string = "http://localhost:6080/user-kanrensha/get-poli-org";
            const body = JSON.stringify(capsuleDto.value);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    // 結果を受け取ってメッセージ表示
                    const resultDto: GetKanrenshaPoliOrgResultInterface = await response.json();
                    if (SERVWER_STATUS_OK === response.status) {
                        inputPoliOrgDto.value = structuredClone(toRaw(resultDto.kanrenshaPoliOrgDto));
                        isEditNew.value = false;
                    } else {
                        alert(resultDto.message);
                    }
                })
                .catch((e) => { alert(e); });
        } else {
            alert("エラーのつもり");
        }
    });
}

const inputCorpNoDto: Ref<CorpNoInterface> = ref(new CorpNoDto());
/** 選択された関連者企業／団体を受信する */
function recieveCorpCoInterface(sendDto: MasterCorporationInterface) {
    // TODO Backに接続しEntityからDtoを取得する
    //inputCorpNoDto.value = structuredClone(toRaw(sendDto));

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<GetKanrenshaCorpCapsuleInterface> = ref(new GetKanrenshaCorpCapsuleDto());
        capsuleDto.value.masterCorporationEntity = sendDto;
        if (token !== BLANK) {
            // 保存処理
            const method = "POST";
            const url: string = "http://localhost:6080/user-kanrensha/get-corp";
            const body = JSON.stringify(capsuleDto.value);
            const headers = {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                'X-AUTH-TOKEN': 'Bearer ' + token
            };
            fetch(url, { method, headers, body })
                .then(async (response) => {
                    // 結果を受け取ってメッセージ表示
                    const resultDto: GetKanrenshaCorpResultInterface = await response.json();
                    if (SERVWER_STATUS_OK === response.status) {
                        inputCorpNoDto.value = structuredClone(toRaw(resultDto.kanrenshaCorpDto));
                        isEditNew.value = false;
                    } else {
                        alert(resultDto.message);
                    }
                })
                .catch((e) => { alert(e); });
        } else {
            alert("エラーのつもり");
        }
    });

}

// 新規追加は許可する
const isNew: boolean = true;
// ユーザと関連者の紐づけは変更しない
const isCombineUser: boolean = false;

</script>
<template>

    <!-- ユーザメニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>

    <h1>関連者管理</h1>

    <div class="clear-both"></div>
    <div class="left-area">
        関連者区分
    </div>
    <div class="right-area">
        <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
    </div>
    <div class="clear-both"></div>

    <hr>

    <div v-if="viewStatus == 1" class="one-line">
        <!-- 検索 -->
        <SearchPersonNo :is-footer="false" @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        <hr>
        <PartnerPersonEdit :edit-dto="inputPersonDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto">
        </PartnerPersonEdit>
    </div>

    <div v-if="viewStatus == 2" class="one-line">
        <!-- 検索 -->
        <SearchCorpNo :is-footer="false" @send-corp-no-interface="recieveCorpCoInterface">
        </SearchCorpNo>
        <hr>
        <PartnerCorpEdit :edit-dto="inputCorpNoDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto"></PartnerCorpEdit>
    </div>

    <div v-if="viewStatus == 3" class="one-line">
        <!-- 検索 -->
        <SearchPoliOrg :is-footer="false" @send-poli-org-no-interface="recievePoliOrgNoInterface"></SearchPoliOrg>
        <hr>
        <PartnerPoliOrgEdit :edit-dto="inputPoliOrgDto" :is-edit-new="isNew" :is-combine-user="isCombineUser"
            :user-dto="userLeastDto">
        </PartnerPoliOrgEdit>
    </div>


</template>
<style scoped></style>
