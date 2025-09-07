<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import ViewInputAddress from '../input_address/ViewInputAddress.vue';
import type PersonNoInterface from '../../../dto/partner_person/personNoDto';
import SearchPersonNo from '../search_person_no/SearchPersonNo.vue';
import type PoliOrgNoInterface from '../../../dto/partner_poli_org/poliOrgNoDto';
import InputOrgName from '../input_org_name/InputOrgName.vue';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type FrameworkCapsuleInterface from '../../../dto/frameworkCapsuleDto';
import FrameworkCapsuleDto from '../../../dto/frameworkCapsuleDto';
import type FrameworkResultInterface from '../../../dto/frameworkResultDto';
import router from '../../../router';
import RoutePathConstants from '../../../routePathConstants';
import InputAccess from '../input_access/InputAccess.vue';

const props = defineProps<{ editDto: PoliOrgNoInterface, isEditNew: boolean, userDto: UserPersonLeastInterface }>();
const editPoliOrgDto: ComputedRef<PoliOrgNoInterface> = computed(() => props.editDto)

// よく使う定数
const BLANK: string = "";
// const SERVWER_STATUS_OK: number = 200;
// const SERVWER_STATUS_ERROR: number = 400;

// 関連者個人検索
const isPersonSearch: Ref<boolean> = ref(false);
let searchPersonType: string = BLANK;
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
        editPoliOrgDto.value.accounrMgrLeastDto.personKanrenshaCode = sendDto.personNo;
        editPoliOrgDto.value.accounrMgrLeastDto.personName = sendDto.personNo;
    }

    // 代表者から呼び出した場合は選択結果は代表者に設定
    if (delegateId === searchPersonType) {
        editPoliOrgDto.value.orgDelegateLeastDto.personKanrenshaCode = sendDto.personNo;
        editPoliOrgDto.value.orgDelegateLeastDto.personName = sendDto.nameAll;
    }

    isPersonSearch.value = false;
}

// 住所・法人名とも支店フラグがOnなら編集許可Offなら検索時データを強制設定
const allAddress: ComputedRef<string> = computed(() => {
    return editPoliOrgDto.value.inputAddressDto.addressPostal;
});


function resetData() {
    // 関連者コード初期化
    //editPoliOrgDto.value = new PoliOrgNoDto();
}


/**
 * すでに同じ法人番号で登録されているかチェック
 */
function onCheckAlreadyRegist() {
    if (editPoliOrgDto.value.poliOrgNo !== BLANK) {
        alert("現在既存または新規と確定したデータを編集中です");
    } else {
        // 仮で時効の秒数基準で既存だったり新規だったり動作を変更する
        // TOD Back側で同一判定処理ができたら連結する
        const date: Date = new Date();
        if (date.getSeconds() % 2 == 0) {
            alert("新規データでした");
            editPoliOrgDto.value.poliOrgNo = "新規";
        } else {
            alert("既存データが存在します。変更が必要な場合はデータ検索からやり直してください");
            editPoliOrgDto.value.poliOrgNo = "1234-tyeer";
        }
    }
}

function onCancel() {
    router.push(RoutePathConstants.PAGE_LOGIN);
}

function onSave() {

    // 編集か新規作成かでアクセス先を変えるだけ
    let url = BLANK;
    if (props.isEditNew) {
        url = "http://localhost:6080/add-user/partner-poli-org";
    } else {
        url = "http://localhost:6080/add-user/partner-poli-org";
    }

    getAuthorizedPromiseArea().then(token => {
        const capsuleDto: Ref<FrameworkCapsuleInterface> = ref(new FrameworkCapsuleDto());
        capsuleDto.value.userPersonLeastDto = props.userDto;
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
        政治団体名称
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.inputOrgNameDto.orgName" disabled="true" class="max-input">
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
        <input type="text" v-model="editPoliOrgDto.orgDelegateLeastDto.personName" disabled="true" class="max-input">
    </div>
    <div class="clear-both"></div>

    <hr>

    <h3>連絡先</h3>

    <div class="left-area">
        (編集→)新規作成
    </div>
    <div class="right-area">
        <button @click="resetData" :disabled="!isEditNew">入力情報のリセット</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        政治団体仮コード
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.poliOrgNo" disabled="true"><button class="left-space"
            @click="onCheckAlreadyRegist">重複確認</button>
    </div>
    <div class="clear-both"></div>

    <!-- 団体名称入力  -->
    <InputOrgName :edit-dto="editPoliOrgDto.inputOrgNameDto"></InputOrgName>

    <!-- 住所入力 -->
    <ViewInputAddress :edit-dto="editPoliOrgDto.inputAddressDto" :is-raise-edit-view="true"></ViewInputAddress>

    <div class="left-area">
        団体区分
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="1"> 政党要件を満たす政党</span>
        <span class="left-space"><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="2"> 政党の支部</span>
        <span class="left-space"><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="3"> 政治資金団体</span>
        <br>
        <span><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="4"> 政治資金規正法第18条の2第1項の規定による政治団体</span>
        <span class="left-space"><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="5"> その他の政治団体</span>
        <span class="left-space"><input type="radio" v-model="editPoliOrgDto.dantaiKbn" :value="6"> その他の政治団体の支部</span>
    </div>
    <div class="clear-both"></div>

    <!-- 連絡先入力 -->
    <InputAccess :edit-dto="editPoliOrgDto.inputAccessDto"></InputAccess>

    <div class="left-area">
        団体代表者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.orgDelegateLeastDto.personKanrenshaCode" class="code-input"
            disabled="true">
        <input type="text" v-model="editPoliOrgDto.orgDelegateLeastDto.personName" class="text-input left-space"
            disabled="true"><button class="left-space" @click="onPersonSearch(delegateId)">検索</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        会計責任者
    </div>
    <div class="right-area">
        <input type="text" v-model="editPoliOrgDto.accounrMgrLeastDto.personKanrenshaCode" class="code-input"
            disabled="true">
        <input type="text" v-model="editPoliOrgDto.accounrMgrLeastDto.personKanrenshaCode" class="text-input left-space"
            disabled="true"><button class="left-space" @click="onPersonSearch(accountMgrId)">検索</button>
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

    <hr>
    <h3>変更履歴</h3>

    <div class="left-area">
        履歴表示
    </div>
    <div class="right-area">
        <button>展開</button>
    </div>
    <div class="clear-both"></div>

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
