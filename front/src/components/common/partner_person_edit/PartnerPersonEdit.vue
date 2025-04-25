<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import SearchCorpNo from '../search_corp_no/SearchCorpNo.vue';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';

const BLANK: string = "";

// 住所入力用
const editAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
const allJuusho: ComputedRef<string> = computed(() => {
    // return editAddressDto.value.addressPostal + editAddressDto.value.addressBlock + editAddressDto.value.addressBuilding;
    return editAddressDto.value.addressPostal;
});

// 名前入力用
const allName: ComputedRef<string> = computed(() => firstName.value + "　" + middleName.value + lastName.value);
const firstName: Ref<string> = ref("");
const middleName: Ref<string> = ref("");
const lastName: Ref<string> = ref("");

// 職業入力定義
const yakushokuFree: string = "所属なし";
const yakushokuGeneral: string = "一般職員";
const yakushokuDirector: string = "役職者";
const yakushokuNoJob: string = "定職なし";

// 職業入力用
const allShokugyou: ComputedRef<string> = computed(() => {
    // return gyoushu.value + yakushoku.value; 

    switch (yakushoku.value) {
        // フリーランス
        case yakushokuFree:
            isShokugyoEdit.value = false;
            if (BLANK === shokugyouUserWrite.value) {
                return gyoushu.value + "業従事者";
            } else {
                return shokugyouUserWrite.value;
            }
        // 団体所属者
        case yakushokuGeneral:
            isShokugyoEdit.value = false;
            if (BLANK === shokugyouUserWrite.value) {
                return gyoushu.value + "業社員・職員";
            } else {
                return shokugyouUserWrite.value;
            }

        // 役職者
        case yakushokuDirector:
            isShokugyoEdit.value = true;
            if (BLANK === corpNo.value) {
                return gyoushu.value + "業役職者(社名記載拒否)";
            } else {
                return corpName.value + "(" + corpPrefecture.value + ")" + "役員";
            }
        // 定職なし
        case yakushokuNoJob:
            isShokugyoEdit.value = false;
            return shokugyouUserWrite.value;
        default:
            isShokugyoEdit.value = true;
            return "";
    }
});
const isShokugyoEdit: Ref<boolean> = ref(true);
const gyoushu: Ref<string> = ref("");
const yakushoku: Ref<string> = ref("");
const shokugyouUserWrite: Ref<string> = ref("");

/**
 * 法人情報受信
 */
function recieveCorpNoInterface(sendDto: CorpNoInterface) {

    corpNo.value = sendDto.corpNo;
    corpPrefecture.value = sendDto.addressDto.addressPostal;
    corpName.value = sendDto.corpName;

    //非表示
    isCorpSearch.value = false;
}

/**
 * 関連者検索キャンセル
 */
function recieveCancelCorpNo() {
    //非表示
    isCorpSearch.value = false;
}

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

/** 選択された関連者個人を受信する */
function recievePersonNoInterface(sendDto: PersonNoInterface) {

    // 住所
    editAddressDto.value = sendDto.inputAddress;

    // 名前
    firstName.value = sendDto.firstName;
    middleName.value = sendDto.middleName;
    lastName.value = sendDto.lastName;

    // 職業
    gyoushu.value = sendDto.gyoushu;
    yakushoku.value = sendDto.yakushoku;
    shokugyouUserWrite.value = sendDto.shokugyouUserWrite;

}

function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}

// 企業団体検索
const corpNo: Ref<string> = ref("");
const corpPrefecture: Ref<string> = ref("");
const corpName: Ref<string> = ref("");
const isCorpSearch: Ref<boolean> = ref(false);

/**
 * 法人検索表示
 */
function onSearchCorpNo() {
    isCorpSearch.value = true;
}

/**
 *住所編集受信
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {
    editAddressDto.value.addressPostal = sendDto.addressPostal;
    editAddressDto.value.addressBlock = sendDto.addressBlock;
    editAddressDto.value.addressBuilding = sendDto.addressBuilding;
}
</script>
<template>
    <h3>個人編集</h3>
    <SearchPersonNo :is-footer="false" @send-person-no-interface="recievePersonNoInterface"></SearchPersonNo>
    <hr>

    <h3>収支報告書公開情報</h3>

    <div class="left-area">
        姓名
    </div>
    <div class="right-area">
        <input type="text" v-model="allName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="allJuusho" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        職業
    </div>
    <div class="right-area">
        <input type="text" disabled="true" v-model="allShokugyou" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>収支報告書公開入力</h3>
    <div class="left-area">
        政治資金関連者コード(個人)
    </div>
    <div class="right-area">
        <input type="text" disabled="true">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        姓名
    </div>
    <div class="right-area">
        <span>姓<input v-model="firstName" type="text"></span>
        <span class="left-space">ミドルネーム<input type="text" v-model="middleName"></span>
        <span class="left-space">名<input type="text" v-model="lastName"></span>
    </div>
    <div class="clear-both"></div>

    <ViewInputAddress :edit-dto="editAddressDto" :is-raise-edit-view="true"
        @send-input-address-interface="recieveInputAddressInterface"></ViewInputAddress>

    <div class="left-area">
        職業(1)
    </div>
    <div class="right-area">
        <span>業種(日本標準産業分類)&nbsp;<select v-model="gyoushu">
                <option value=""></option>
                <option value="農林"> A.農業，林業</option>
                <option value="水産">B.漁業</option>
                <option value="鉱業">C.鉱業，採石業，砂利採取業</option>
                <option value="建設">D.建設業</option>
                <option value="製造">E.製造業</option>
                <option value="インフラ">F.電気・ガス・熱供給・水道業</option>
                <option value="情報通信">G.情報通信業</option>
                <option value="運輸">H.運輸業，郵便業</option>
                <option value="流通小売">I.卸売業，小売業</option>
                <option value="金融">J.金融業，保険業</option>
                <option value="不動産">K.不動産業，物品賃貸業</option>
                <option value="学術研究">L.学術研究，専門・技術サービス業</option>
                <option value="宿泊飲食">M.宿泊業，飲食サービス業</option>
                <option value="娯楽">N.生活関連サービス業，娯楽業</option>
                <option value="教育学習">O.教育，学習支援業</option>
                <option value="医療・福祉">P.医療，福祉</option>
                <option value="複合">Q.複合サービス事業</option>
                <option value="その他サービス">R.サービス業（他に分類されないもの）</option>
                <option value="公務">S.公務（他に分類されるものを除く）</option>
                <option value="分類不能">T.分類不能の産業</option>
            </select></span>
        <span class="left-space">役職&nbsp;<select v-model="yakushoku">
                <option :value="yakushokuFree">フリーランス(所属団体なし・法人登記なし)</option>
                <option :value="yakushokuGeneral">課長級以下職員(パート含む)</option>
                <option :value="yakushokuDirector">法人役員(一人企業の社長)</option>
                <option :value="yakushokuNoJob">定職なし</option>
            </select></span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        職業(2)
    </div>
    <div class="right-area">
        <div v-if="yakushokuDirector !== yakushoku"><input type="text" v-model="shokugyouUserWrite"
                :disabled="isShokugyoEdit" class="max-input"></div>
        <div v-if="yakushokuDirector === yakushoku">
            <input type="text" v-model="corpNo" class="code-input">
            <input type="text" v-model="corpPrefecture" class="code-input left-space">
            <input type="text" v-model="corpName" class="left-space text-input">
            <button class="left-space" @click="onSearchCorpNo">企業／団体検索</button>
        </div>
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>連絡先(情報確認のため使用、非公開)</h3>
    <div class="left-area">
        メールアドレス
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        SNSアカウント
    </div>
    <div class="right-area">
        <input type="email">
    </div>
    <div class="clear-both"></div>

    <hr>
    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchCorpNo :list="listCorp" :is-footer="true" @send-corp-no-interface="recieveCorpNoInterface"
                @send-canceel-corp-no="recieveCancelCorpNo"></SearchCorpNo>
        </div>
    </div>

</template>



<style scoped></style>
