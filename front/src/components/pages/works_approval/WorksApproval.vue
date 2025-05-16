<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type CorpNoApprovalInterface from '../../../dto/partner_corp/corpNoApprovalDto';
import type PersonNoApprovalInterface from '../../../dto/partner_person/personNoApprovalDto';
import mockGetPersonApprovalList from './mock/mockGetPersonApprovalList';
import mockGetPoliOrgNoApprovalList from './mock/mockGetPoliOrgNoApprovalList';
import mockGetCorpApprovalList from './mock/mockGetCorpApprovalList';
import type PoliOrgNoApprovalInterface from '../../../dto/partner_poli_org/poliOrgNoApprovalDto';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import InputAddress from '../../common/input_address/InputAddress.vue';
import type InputShokugyouInterface from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyouDto from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyou from '../../common/input_shokugyou/InputShokugyou.vue';

const viewStatus: Ref<string> = ref("1");
const isAddressInput: Ref<boolean> = ref(false);
const isPortalAddressInput: Ref<boolean> = ref(false);

//初期表示日時
const yesterday: Date = new Date();
yesterday.setDate(yesterday.getDate() - 1);
const yesterdayText: string = yesterday.toISOString().substring(0, 10);

// 検索期間
const searchStartDate: Ref<string> = ref(yesterdayText);
const searchEndDate: Ref<string> = ref(yesterdayText);

const isSearchhApproval: Ref<boolean> = ref(true);

// 検索表示リスト
const listPerson: Ref<PersonNoApprovalInterface[]> = ref([]);
const listCorp: Ref<CorpNoApprovalInterface[]> = ref([]);
const listPoliOrg: Ref<PoliOrgNoApprovalInterface[]> = ref([]);

function onSearch() {
    switch (viewStatus.value) {
        case "1":
            listPerson.value = mockGetPersonApprovalList();
            listCorp.value = [];
            listPoliOrg.value = [];
            break;

        case "2":
            listCorp.value = mockGetCorpApprovalList();
            listPerson.value = [];
            listPoliOrg.value = [];
            break;

        case "3":
            listPerson.value = [];
            listPoliOrg.value = mockGetPoliOrgNoApprovalList();
            listCorp.value = [];
            listPerson.value = [];
            break;
        default:
            break;
    }
}

const inputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
const bakupInputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
let editCorpNo: string = "";
function editAddress(corpNo: string) {
    editCorpNo = corpNo;
    //　選択したデータ
    const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpNo === editCorpNo)[0];// idのため1件
    inputAddressDto.value = structuredClone(toRaw(editDto.inputAddress));
    bakupInputAddressDto.value = structuredClone(toRaw(editDto.inputAddress));
    //非表示
    isAddressInput.value = true;
}


/**
 * 関連者検索キャンセル
 */
function recieveCancelInputAddress() {

    //　選択したデータ
    const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpNo === editCorpNo)[0];// idのため1件
    editDto.inputAddress = bakupInputAddressDto.value;
    //非表示
    isAddressInput.value = false;
}

/**
 * 関連者検索選択
 * @param sendDto 選択Dto
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {

    //　選択したデータ
    const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpNo === editCorpNo)[0];// idのため1件
    editDto.inputAddress = sendDto;

    //非表示
    isAddressInput.value = false;
}


/** 支店を外す */
function changeIsShiten(corpNo: string) {
    const editDto: CorpNoApprovalInterface = listCorp.value.filter((e) => e.corpNo === corpNo)[0];// idのため1件

    if (editDto.isShiten === false) {

        if (confirm("支店入力をやめて、法人番号情報に書き換えしてもよいですか?")) {
            // 仮の法人番号の紐づ区データに更新
            editDto.corpNo = editDto.houjinNo + "-abcde";
            editDto.corpName = "ABCD企業";
            editDto.corpNameKana = "えーびーしーでぃーきぎょう";

            editDto.inputAddress.addressPostal = "法人番号住所1";
            editDto.inputAddress.addressBlock = "法人番号住所2";
            editDto.inputAddress.addressBuilding = "法人番号住所3";
            editDto.inputAddress.rsdtAddressPostl = "法人番号住所1";
            editDto.inputAddress.rsdtAddressBlock = "法人番号住所2";
            editDto.inputAddress.rsdtAddressBuilding = "法人番号住所3";
        } else {
            editDto.isShiten = true;
        }
    }
}


// 簡易住所入力(コピペ)
const inputAddressPortalDto: InputAddressDto = new InputAddressDto();
function onPortalAddressInput() {
    isPortalAddressInput.value = true;
}
function recieveCancelInputPortalAddress() {
    isPortalAddressInput.value = false;
}
function recieveInputPortalAddressInterface() {
    isPortalAddressInput.value = false;
}


// 簡易職業入力(コピペ)
const inputShokugyouDto: InputShokugyouInterface = new InputShokugyouDto();
const isShokugyouInput: Ref<boolean> = ref(false);
function onShokugyouInput() {
    isShokugyouInput.value = true;
}


function recieveCancelInputShokugyou() {
    isShokugyouInput.value = false;
}

function onCancel() {
    alert("キャンセル");
}
function onSave() {
    alert("保存");
}
</script>
<template>
    <h1>登録内容承認</h1>

    <div class="left-area">
        関連者区分
    </div>
    <div class="right-area">
        <span><input type="radio" id="editSelect" v-model="viewStatus" value="1">1.個人</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="2">2.企業／団体</span>
        <span class="left-space"><input type="radio" id="editSelect" v-model="viewStatus" value="3">3.政治団体</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索期間
    </div>
    <div class="right-area">
        <span><input type="date" v-model="searchStartDate" value="1">から</span>
        <span class="left-space"><input type="date" v-model="searchEndDate" value="3">まで</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        承認必要のみ
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="isSearchhApproval">承認必要作業のみ表示する
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <hr>

    <div v-if="viewStatus == '1'">
        <div class="one-line">
            <h3>検索結果</h3>
            <button @click="onPortalAddressInput">住所入力(簡易)</button>
            <button @click="onShokugyouInput" class="left-space">職業入力(簡易)</button><br><br>
            <table style="scroll">
                <tbody>
                    <tr>
                        <th rowspan="2">関連者番号</th>
                        <th rowspan="2">名前</th>
                        <th colspan="3">住所</th>
                        <th rowspan="2">職業</th>
                        <th rowspan="2">承認</th>
                    </tr>
                    <tr>
                        <th>住所郵便局まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                    </tr>
                </tbody>
                <tbody v-for="dto in listPerson" :key="dto.personNo">
                    <tr>
                        <td>{{ dto.personNo }} </td>
                        <td>{{ dto.nameAll }} </td>
                        <td><input type="text" v-model="dto.inputAddress.addressPostal"
                                :disabled="!dto.inputAddress.isEditAddressPostal"><br>{{
                                    dto.inputAddress.rsdtAddressPostl }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBlock"
                                :disabled="!dto.inputAddress.isEditAddressBlock"><br>{{
                                    dto.inputAddress.rsdtAddressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBuilding"
                                :disabled="!dto.inputAddress.isEditAddressBuilding"><br>{{
                                    dto.inputAddress.rsdtAddressBuilding
                                }}&nbsp;
                        </td>
                        <td>職　業:<input type="text" v-model="dto.shokugyou"
                                :disabled="dto.inputShokugyou.shokugyouUserWrite == ''"><br>
                            業　種:<input type="text" v-model="dto.inputShokugyou.gyoushu"
                                :disabled="dto.inputShokugyou.shokugyouUserWrite == ''"><br>役　職:<input type="text"
                                v-model="dto.inputShokugyou.yakushoku"
                                :disabled="dto.inputShokugyou.shokugyouUserWrite == ''"><br>ユーザ:<input type="text"
                                v-model="dto.inputShokugyou.shokugyouUserWrite"
                                :disabled="dto.inputShokugyou.shokugyouUserWrite == ''">
                        </td>
                        <td><input type="checkbox">登録内容を承認</td>
                    </tr>
                </tbody>
            </table>
        </div>


    </div>
    <div v-if="viewStatus == '2'">
        <div class="one-line">
            <h3>検索結果</h3>
            <table style="scroll">
                <tbody>
                    <tr>
                        <th rowspan="2">関連者番号</th>
                        <th rowspan="2">法人番号</th>
                        <th rowspan="2">企業・団体名称</th>
                        <th colspan="5">住所</th>
                        <th rowspan="2">承認</th>
                    </tr>
                    <tr>
                        <th>支店</th>
                        <th>住所郵便局まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                        <th>編集</th>
                    </tr>
                </tbody>
                <tbody v-for="dto in listCorp" :key="dto.corpNo">
                    <tr>
                        <td>{{ dto.corpNo }} </td>
                        <td>{{ dto.houjinNo }} </td>
                        <td><input type="text" v-model="dto.corpNameKana" :disabled="!dto.isShiten"><br><input
                                type="text" v-model="dto.corpName" :disabled="!dto.isShiten"></td>
                        <td><input type="checkbox" v-model="dto.isShiten" @change="changeIsShiten(dto.corpNo)">支店</td>
                        <td><input type="text" v-model="dto.inputAddress.addressPostal"
                                :disabled="!(dto.inputAddress.isEditAddressPostal || dto.isShiten)"><br>{{
                                    dto.inputAddress.rsdtAddressPostl }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBlock"
                                :disabled="!(dto.inputAddress.isEditAddressPostal || dto.isShiten)"><br>{{
                                    dto.inputAddress.rsdtAddressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBuilding"
                                :disabled="!(dto.inputAddress.isEditAddressPostal || dto.isShiten)"><br>{{
                                    dto.inputAddress.rsdtAddressBuilding
                                }}&nbsp;
                        </td>
                        <td><button @click="editAddress(dto.corpNo)">住所編集</button></td>

                        <td><input type="checkbox">登録内容を承認</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div v-if="viewStatus == '3'">
        <div class="one-line">
            <h3>検索結果</h3>
            <button @click="onPortalAddressInput">住所入力(簡易)</button><br><br>
            <table style="scroll">
                <tbody>
                    <tr>
                        <th rowspan="2">関連者番号</th>
                        <th rowspan="2">政治団体名称</th>
                        <th colspan="3">住所</th>
                        <th rowspan="2">承認</th>
                    </tr>
                    <tr>
                        <th>住所郵便局まで</th>
                        <th>住所番地</th>
                        <th>住所建物</th>
                    </tr>
                </tbody>
                <tbody v-for="dto in listPoliOrg" :key="dto.poliOrgNo">
                    <tr>
                        <td>{{ dto.poliOrgNo }} </td>
                        <td>{{ dto.inputName.orgNameKana }}<br>{{ dto.inputName.orgName }}</td>
                        <td><input type="text" v-model="dto.inputAddress.addressPostal"
                                :disabled="!dto.inputAddress.isEditAddressPostal"><br>{{
                                    dto.inputAddress.rsdtAddressPostl }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBlock"
                                :disabled="!dto.inputAddress.isEditAddressBlock"><br>{{
                                    dto.inputAddress.rsdtAddressBlock }}&nbsp;
                        </td>
                        <td><input type="text" v-model="dto.inputAddress.addressBuilding"
                                :disabled="!dto.inputAddress.isEditAddressBuilding"><br>{{
                                    dto.inputAddress.rsdtAddressBuilding
                                }}&nbsp;
                        </td>
                        <td><input type="checkbox">登録内容を承認</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="clear-both"><br></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
    </div>

    <!-- 住所入力 -->
    <div v-if="isAddressInput" class="overBackground"></div>
    <div v-if="isAddressInput">
        <div class="overComponent">
            <InputAddress v-if="isAddressInput" :edit-dto="inputAddressDto"
                @send-cancel-input-address="recieveCancelInputAddress"
                @send-input-address-interface="recieveInputAddressInterface">
                ></InputAddress>
        </div>
    </div>

    <!-- 住所入力(紐づけなし) -->
    <div v-if="isPortalAddressInput" class="overBackground"></div>
    <div v-if="isPortalAddressInput">
        <div class="overComponent">
            <InputAddress v-if="isPortalAddressInput" :edit-dto="inputAddressPortalDto"
                @send-cancel-input-address="recieveCancelInputPortalAddress"
                @send-input-address-interface="recieveInputPortalAddressInterface">

            </InputAddress>
        </div>
    </div>

    <!-- 職業入力(紐づけなし) -->
    <div v-if="isShokugyouInput" class="overBackground"></div>
    <div v-if="isShokugyouInput">
        <div class="overComponent">
            <InputShokugyou v-if="isShokugyouInput" :edit-dto="inputShokugyouDto" :isfooter="true"
                @send-cancel-input-shokugyou="recieveCancelInputShokugyou">
            </InputShokugyou>
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
    text-align: center;
}
</style>
