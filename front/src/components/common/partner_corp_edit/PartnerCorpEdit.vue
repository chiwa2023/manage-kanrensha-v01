<script setup lang="ts">
import { computed, ref, toRaw, type ComputedRef, type Ref } from 'vue';
import SearchHoujinNo from '../search_houjin_no/SearchHoujinNo.vue';
import type HoujinNoInterface from '../../../dto/partner_corp/houjinNoDto';
import type CorpNoInterface from '../../../dto/partner_corp/corpNoDto';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import CheckRegistCorpResultInterface from '../../../dto/partner_corp/checkRegistCorpResultDto';
import mockCheckAlreadyRegist from './mock/mockCheckAlreadyRegist';
import HoujinSbtsConstants from '../../../dto/partner_corp/houjinSbtsConstants';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import InputAccess from '../input_access/InputAccess.vue';
import InputKanrenshaPersonLeastDto from '../../../dto/input_person_name/inputKanrenshaPersonLeastDto';
import type SaveKanrenshaCorpCapsuleInterface from '../../../dto/partner_corp/saveKanrenshaCorpCapsuleDto';
import SaveKanrenshaCorpCapsuleDto from '../../../dto/partner_corp/saveKanrenshaCorpCapsuleDto';

// props,emit
const props = defineProps<{ editDto: CorpNoInterface, userDto: UserPersonLeastInterface, isEditNew: boolean }>();
const editCorpDto: ComputedRef<CorpNoInterface> = computed(() => { return props.editDto });

// よく使う定数
const BLANK: string = "";
// const SERVWER_STATUS_OK: number = 200;
// const SERVWER_STATUS_ERROR: number = 400;

// 編集用Dto
const addressDtoStored: Ref<InputAddressDto> = ref(new InputAddressDto());

const allCorpName: ComputedRef<string> = computed(() => {
    if (editCorpDto.value.isShiten) { return editCorpDto.value.inputOrgNameDto.orgName + "　" + branchName.value; }
    else { return editCorpDto.value.inputOrgNameDto.orgName; }
});

// 検索リスト
const listCorp: Ref<CorpNoInterface[]> = ref([]);

// 法人検索
const isCorpSearch: Ref<boolean> = ref(false);
const corpName: Ref<string> = ref(BLANK);
const corpNameKana: Ref<string> = ref(BLANK);
const branchName: Ref<string> = ref(BLANK);
const branchNamekana: Ref<string> = ref(BLANK);
const isGaikokuHoujin: ComputedRef<boolean> = computed(() => HoujinSbtsConstants.GAIKOKU === editCorpDto.value.houjinSbts);

function onHoujinSearch() {
    isCorpSearch.value = true;
}

/**
 * 法人番号表示
 */
function recieveCorpNoInterface(sendDto: HoujinNoInterface) {

    // 法人番号から取得情報の設定
    editCorpDto.value.houjinNo = sendDto.houjinNo;
    editCorpDto.value.inputOrgNameDto.orgName = sendDto.houjinName;
    editCorpDto.value.houjinSbts = sendDto.houjinSbts;
    corpName.value = sendDto.houjinName;
    corpNameKana.value = sendDto.houjinNameKana;
    const postalCode: string = sendDto.postalcode;
    // 郵便番号が正常7桁の場合は分割
    if (7 === postalCode.length) {
        editCorpDto.value.inputAddress.postalcode1 = postalCode.substring(0, 3);
        editCorpDto.value.inputAddress.postalcode2 = postalCode.substring(3, 7);
    } else {
        editCorpDto.value.inputAddress.postalcode1 = postalCode;
    }
    editCorpDto.value.inputAddress.addressPostal = sendDto.addressPrefecture + sendDto.addressCity;
    editCorpDto.value.inputAddress.addressBlock = sendDto.addressBlock;
    editCorpDto.value.inputAddress.addressBuilding = BLANK;
    // 支店フラグ悪用防止用に検索時情報をストア
    addressDtoStored.value = structuredClone(toRaw(editCorpDto.value.inputAddress));

    // 法人番号DBに代表者情報はないので初期化
    editCorpDto.value.orgDelegateLeastDto = new InputKanrenshaPersonLeastDto();

    // コード確認を別ボタンでする
    editCorpDto.value.corpNo = BLANK;

    //非表示
    isCorpSearch.value = false;
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
function recievePersonNoInterface(sendDto: PersonNoInterface) {
    editCorpDto.value.orgDelegateLeastDto.personKanrenshaCode = sendDto.personNo;
    editCorpDto.value.orgDelegateLeastDto.personName = sendDto.nameAll;

    isPersonSearch.value = false;
}

function resetData() {
    // コードのリセット
    //editCorpDto.value.corpNo = BLANK;
}

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}
function onSave() {

    // 編集か新規作成かでアクセス先を変えるだけ
    let url = BLANK;
    if (props.isEditNew) {
        url = "http://localhost:6080/add-user/partner-corp";
    } else {
        url = "http://localhost:6080/add-user/partner-corp";
    }

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<SaveKanrenshaCorpCapsuleInterface> = ref(new SaveKanrenshaCorpCapsuleDto());
        capsuleDto.value.userPersonLeastDto = props.userDto;
        capsuleDto.value.kanrenshaCorpDto = editCorpDto.value;
        if (token !== BLANK) {
            // 保存処理
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
</script>
<template>
    <h3>収支報告書公開情報</h3>
    <div class="left-area">
        企業／団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="allCorpName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        住所
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.inputAddress.addressPostal" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegateLeastDto.personName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>編集内容(連絡先)</h3>

    <div class="left-area">
        (編集→)新規作成
    </div>
    <div class="right-area">
        <button @click="resetData" :disabled="!isEditNew">入力情報のリセット</button>
    </div>
    <div class="clear-both"></div>

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
        商号名称かな
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.inputOrgNameDto.orgNameKana" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchNamekana"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        商号名称
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.inputOrgNameDto.orgName" class="text-input" disabled="true">
        <span class="left-space" v-if="editCorpDto.isShiten">支店：<input type="text" v-model="branchName"
                class="text-input" :disabled="!editCorpDto.isShiten"></span>
    </div>
    <div class="clear-both"></div>
    <br>
    <ViewInputAddress :edit-dto="editCorpDto.inputAddress" :is-raise-edit-view="editCorpDto.isShiten">
    </ViewInputAddress>
    <br>
    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editCorpDto.orgDelegateLeastDto.personKanrenshaCode" class="code-input"
            disabled="true">
        <input type="text" v-model="editCorpDto.orgDelegateLeastDto.personName" class="text-input left-space"
            disabled="true"><button class="left-space" @click="onPersonSearch">検索</button>
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

    <!-- 連絡先入力 -->
    <InputAccess :edit-dto="editCorpDto.inputAccessDto"></InputAccess>

    <hr>
    <h3>変更履歴</h3>
    <div class="left-area">
        履歴表示
    </div>
    <div class="right-area">
        <button>展開</button>
    </div>
    <div class="clear-both"></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <hr>

    <!-- 法人番号検索 -->
    <div v-if="isCorpSearch" class="overBackground"></div>
    <div v-if="isCorpSearch">
        <div class="overComponent">
            <SearchHoujinNo v-if="isCorpSearch" @send-cancel-houjin-no="recieveCancelCorpNo"
                @send-houjin-no-interface="recieveCorpNoInterface"></SearchHoujinNo>
        </div>
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
