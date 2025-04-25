<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import SearchPoliOrg from '../search_poli_org/SearchPoliOrg.vue';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import PoliOrgNoDto from '../../../dto/partner_poli_org/poliOrgNoDto';

const editAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());

/**
*住所編集受信
*/
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    editAddressDto.value.addressPostal = sendDto.addressPostal;
    editAddressDto.value.addressBlock = sendDto.addressBlock;
    editAddressDto.value.addressBuilding = sendDto.addressBuilding;
}

//const delegateCode: Ref<string> = ref("");
//const delegateName: Ref<string> = ref("");
//const accountMgrCode: Ref<string> = ref("");
//const accountMgr: Ref<string> = ref("");

// 関連者個人検索
const isPersonSearch: Ref<boolean> = ref(false);
let searchPersonType: string = "";
const delegateId: string = "delegate";
const accountMgrId: string = "accountMgr";
/**
 * 関連者個人検索を表示する
 */
function onPersonSearch(searchId: string) {

    searchPersonType = searchId;

    isPersonSearch.value = true;
}

/**
 * 検索キャンセルを受信する
 */
function recieveCancelPersonNo() {

    isPersonSearch.value = false;
}

/**
 * 選択された関連者個人を受信を表示する
 */
function recievePersonNoInterface(sendDto: PersonNoInterface) {

    // 会計責任者から呼び出した場合は選択結果は会計責任者に設定
    if (accountMgrId === searchPersonType) {
        editPoliOrgDto.value.accountMgrName = sendDto.nameAll;
        editPoliOrgDto.value.accountMgrNo = sendDto.personNo;
    }

    // 代表者から呼び出した場合は選択結果は代表者に設定
    if (delegateId === searchPersonType) {
        editPoliOrgDto.value.delegateName = sendDto.nameAll;
        editPoliOrgDto.value.delegateNo = sendDto.personNo;
    }

    isPersonSearch.value = false;
}

// 住所・法人名とも支店フラグがOnなら編集許可Offなら検索時データを強制設定
const allAddress: ComputedRef<string> = computed(() => {
    //return editAddressDto.value.addressPostal + editAddressDto.value.addressBlock + editAddressDto.value.addressBuilding;
    return editAddressDto.value.addressPostal;
});


const editPoliOrgDto:Ref<PoliOrgNoInterface> = ref(new PoliOrgNoDto());
function recievePoliOrgNoInterface(sendDto:PoliOrgNoInterface){
    editPoliOrgDto.value = sendDto;
    editAddressDto.value = editPoliOrgDto.value.inputAddress;
}

function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}

</script>
<template>
    <h2>政治団体編集</h2>

    <SearchPoliOrg :is-footer="false" @send-poli-org-no-interface="recievePoliOrgNoInterface"></SearchPoliOrg>

    <hr>

    <h3>収支報告書公開情報</h3>

    <div class="left-area">
        政治団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.poliOrgName"  disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="allAddress"  disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.delegateName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>連絡先</h3>

    <div class="left-area">
        政治団体仮コード
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.poliOrgNo" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体名
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.poliOrgName" class="text-input">
    </div>
    <div class="clear-both"></div>

    <ViewInputAddress :edit-dto="editAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.delegateNo" class="code-input" disabled="true">
        <input type="text" v-model="editPoliOrgDto.delegateName" class="text-input left-space" disabled="true"><button
            class="left-space" @click="onPersonSearch(delegateId)">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        会計責任者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.accountMgrNo" class="code-input" disabled="true">
        <input type="text" v-model="editPoliOrgDto.accountMgrName" class="text-input left-space" disabled="true"><button class="left-space"
            @click="onPersonSearch(accountMgrId)">検索</button>
    </div>
    <div class="clear-both"></div>

    <h3>追加連絡先</h3>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 関連者個人検索 -->
    <div v-if="isPersonSearch" class="overBackground"></div>
    <div v-if="isPersonSearch">
        <div class="overComponent">
            <SearchPersonNo :is-footer="true" @send-canceel-person-no="recieveCancelPersonNo"
                @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        </div>
    </div>

</template>
<style scoped></style>
