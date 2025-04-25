<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import SearchHoujinNo from '../search_houjin_no/SearchHoujinNo.vue';
import type HoujinNoInterface from '../../../dto/partner_corp/houjinNoDto';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import CorpNoDto from '../../../dto/partner_corp/corpNoDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import CheckRegistCorpResultInterface from '../../../dto/partner_corp/checkRegistCorpResultDto';
import mockCheckAlreadyRegist from './mock/mockCheckAlreadyRegist';
import HoujinSbtsConstants from '../../../dto/partner_corp/houjinSbtsConstants';
import SearchCorpNo from '../search_corp_no/SearchCorpNo.vue';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';

// 編集用Dto
const editCorpDto: Ref<CorpNoInterface> = ref(new CorpNoDto());
const addressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
const addressDtoStored: Ref<InputAddressDto> = ref(new InputAddressDto());

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

// 法人検索
const isCorpSearch: Ref<boolean> = ref(false);
const corpName: Ref<string> = ref("");
const corpNameKana: Ref<string> = ref("");
const branchName: Ref<string> = ref("");
const branchNamekana: Ref<string> = ref("");
const isGaikokuHoujin: ComputedRef<boolean> = computed(() => HoujinSbtsConstants.GAIKOKU === editCorpDto.value.houjinSbts);

// 住所・法人名とも支店フラグがOnなら編集許可Offなら検索時データを強制設定
const allAddress: ComputedRef<string> = computed(() => {
    if (editCorpDto.value.isShiten) {
        // return addressDto.value.addressPostal + addressDto.value.addressBlock + addressDto.value.addressBuilding;
        return addressDto.value.addressPostal;
    }
    else {
        // return addressDtoStored.value.addressPostal + addressDtoStored.value.addressBlock + addressDtoStored.value.addressBuilding;
        return addressDtoStored.value.addressPostal;
    }
});
const allname: ComputedRef<string> = computed(() => {
    if (editCorpDto.value.isShiten) {
        return corpName.value + branchName.value;
    } else {
        return corpName.value;
    }
});

function onHoujinSearch() {
    isCorpSearch.value = true;
}

/**
 * 法人番号表示
 */
function recieveCorpNoInterface(sendDto: HoujinNoInterface) {

    // 法人番号から取得情報の設定
    editCorpDto.value.houjinNo = sendDto.houjinNo;
    editCorpDto.value.corpName = sendDto.houjinName;
    editCorpDto.value.houjinSbts = sendDto.houjinSbts;
    corpName.value = sendDto.houjinName;
    corpNameKana.value = sendDto.houjinNameKana;
    const postalCode: string = sendDto.postalcode;
    // 郵便番号が正常7桁の場合は分割
    if (7 === postalCode.length) {
        addressDto.value.postalcode1 = postalCode.substring(0, 3);
        addressDto.value.postalcode2 = postalCode.substring(3, 7);
    } else {
        addressDto.value.postalcode1 = postalCode;
    }
    addressDto.value.addressPostal = sendDto.addressPrefecture + sendDto.addressCity;
    addressDto.value.addressBlock = sendDto.addressBlock;
    addressDto.value.addressBuilding = "";
    // 支店フラグ悪用防止用に検索時情報をストア
    addressDtoStored.value = structuredClone(toRaw(addressDto.value));

    // 法人番号DBに代表者情報はないので初期化
    editCorpDto.value.orgDelegateCode = "";
    editCorpDto.value.orgDelegate = "";

    // コード確認を別ボタンでする
    editCorpDto.value.corpNo = "";

    //非表示
    isCorpSearch.value = false;
}


function onSelectRow(selectedDto: CorpNoInterface) {
    // 検索データからコピーすべき元データを抽出
    // 取得情報の設定
    editCorpDto.value.corpNo = selectedDto.corpNo;
    editCorpDto.value.houjinNo = selectedDto.houjinNo;
    editCorpDto.value.houjinSbts = selectedDto.houjinSbts;
    editCorpDto.value.corpName = selectedDto.corpName;
    corpName.value = selectedDto.corpName;
    corpNameKana.value = selectedDto.corpNameKana;
    addressDto.value = selectedDto.addressDto;
    // 支店フラグ悪用防止用に検索時情報をストア
    addressDtoStored.value = structuredClone(toRaw(addressDto.value));

    editCorpDto.value.orgDelegateCode = selectedDto.orgDelegateCode;
    editCorpDto.value.orgDelegate = selectedDto.orgDelegate;
}

/**
* 法人番号キャンセル
*/
function recieveCancelCorpNo() {
    //非表示
    isCorpSearch.value = false;
}

/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    const resultDto: CheckRegistCorpResultInterface = mockCheckAlreadyRegist(editCorpDto.value.corpNo, editCorpDto.value.houjinNo);
    editCorpDto.value.corpNo = resultDto.savedCorpNo;
    alert(resultDto.message);
    listCorp.value.splice(0);
    for (const dto of resultDto.listCorptDto) {
        listCorp.value.push(dto);
    }
}

const isPersonSearch: Ref<boolean> = ref(false);

/**
 * 関連者個人検索を表示する
 */
function onPersonSearch() {
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
function recievePersonNoInterface(sendDto:PersonNoInterface) {
    editCorpDto.value.orgDelegate = sendDto.nameAll;
    editCorpDto.value.orgDelegateCode = sendDto.personNo;

    isPersonSearch.value = false;
}


function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}
</script>
<template>
    <h3>企業・団体編集</h3>
    <SearchCorpNo :list="listCorp" :is-footer="false" @send-corp-no-interface="onSelectRow"></SearchCorpNo>
    <br>
    <hr>
    <h3>収支報告書公開情報</h3>
    <div class="left-area">
        企業／団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="allname" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="allAddress" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegate" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>編集内容(連絡先)</h3>

    <div class="left-area">
        政治資金関連者コード(企業団体)
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.corpNo" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        法人番号
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.houjinNo" class="text-input" disabled="true"><button class="left-space"
            @click="onHoujinSearch">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        支店
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="editCorpDto.isShiten">支店(法人番号を変えずに名称追加、住所変更)
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        商号名称カナ
    </div>
    <div class="right-area">
        <input type="text" v-model="corpNameKana" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchNamekana"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        商号名称
    </div>
    <div class="right-area">
        <input type="text" v-model="corpName" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchName"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>
    <br>
    <ViewInputAddress :edit-dto="addressDto" :is-raise-edit-view="editCorpDto.isShiten"></ViewInputAddress>
    <br>
    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegateCode" class="code-input" disabled="true">
        <input type="text" v-model="editCorpDto.orgDelegate" class="text-input left-space" disabled="true"><button
            class="left-space" @click="onPersonSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>

    <h3>編集内容(違反判定情報)</h3>


    <div class="left-area">
        外国籍企業
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isGaikokuHoujin" disabled="true">外国籍企業である
    </div>
    <div class="clear-both"></div>



    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <hr>

    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchHoujinNo v-if="isCorpSearch" @send-cancel-houjin-no="recieveCancelCorpNo"
                @send-houjin-no-interface="recieveCorpNoInterface"></SearchHoujinNo>
        </div>
    </div>

    <div v-if="isPersonSearch" class="overBackground"></div>
    <div v-if="isPersonSearch">
        <div class="overComponent">
            <SearchPersonNo :is-footer="true" @send-canceel-person-no="recieveCancelPersonNo"
                @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
        </div>
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
