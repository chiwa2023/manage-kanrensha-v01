<script setup lang="ts">
import { ref, type Ref } from 'vue';
import InputAddressDto from '../../../dto/Input_address/inputAddressDto';
import type InputShokugyouInterface from '../../../dto/input_shokugyou/inputShokugyouDto';
import InputShokugyouDto from '../../../dto/input_shokugyou/inputShokugyouDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';
import type MasterPersonBaseEntityInterface from '../../../entity/masterPersonBaseEntity';
import MasterPersonBaseEntity from '../../../entity/masterPersonBaseEntity';
import InputAddressEditRegistory from '../../common/input_address_edit_registory/InputAddressEditRegistory.vue';
import InputShokugyouCompare from '../../common/input_shokugyou_compare/InputShokugyouCompare.vue';
import type MasterKanrenshaAddressBaseEntityInterface from '../../../entity/masterKanrenshaAddressBaseEntity';
import MasterKanrenshaAddressBaseEntity from '../../../entity/masterKanrenshaAddressBaseEntity';
import type SearchWorksApprovalCapsuleDtoInterface from '../../../dto/works_approval/searchApprovalCapsuleDto';
import SearchWorksApprovalCapsuleDto from '../../../dto/works_approval/searchApprovalCapsuleDto';
import type SearchWorksApprovalAddressResultDtoInterface from '../../../dto/works_approval/searchWorksApprovalAddressResultDto';
import type SearchWorksApprovalShokugyouResultDtoInterface from '../../../dto/works_approval/searchWorksApprovalShokugyouResultDto';
import SearchWorksApprovalAddressResultDto from '../../../dto/works_approval/searchWorksApprovalAddressResultDto';
import SearchWorksApprovalShokugyouResultDto from '../../../dto/works_approval/searchWorksApprovalShokugyouResultDto';
import type SearchApprovalResultDtoInterface from '../../../dto/works_approval/searchApprovalResultDto';
import SearchApprovalResultDto from '../../../dto/works_approval/searchApprovalResultDto';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SaveApprovalCapsuleDtoInterface from '../../../dto/works_approval/saveApprovalCapsuleDto';
import SaveApprovalCapsuleDto from '../../../dto/works_approval/saveApprovalCapsuleDto';
import type FrameworkMessageAndResultInterface from '../../../dto/frameworkMessageAndResultDto';
import FrameworkMessageAndResultDto from '../../../dto/frameworkMessageAndResultDto';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';


const userLeastDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
function recieveUser(user: UserPersonLeastInterface) {
    userLeastDto.value = user;
}

const isAddressInput: Ref<boolean> = ref(false);

// 検索条件
const capsuleDto: Ref<SearchWorksApprovalCapsuleDtoInterface> = ref(new SearchWorksApprovalCapsuleDto());

// 検索表示リスト

//const listAddress: Ref<MasterKanrenshaAddressBaseEntityInterface[]> = ref([]);
//const listShokugyou: Ref<MasterPersonBaseEntityInterface[]> = ref([]);

const resultDtoAddress: Ref<SearchWorksApprovalAddressResultDtoInterface> = ref(new SearchWorksApprovalAddressResultDto());
const resultDtoShokugyou: Ref<SearchWorksApprovalShokugyouResultDtoInterface> = ref(new SearchWorksApprovalShokugyouResultDto());



function onSearch() {

    // APIに渡す前にdatetextはdateに変換する
    capsuleDto.value.startDate = new Date(capsuleDto.value.startDateText);
    capsuleDto.value.endDate = new Date(capsuleDto.value.endDateText);

    // TODO 検索処理
    const resultDto: SearchApprovalResultDtoInterface = new SearchApprovalResultDto();
    resultDtoAddress.value = resultDto.resultDtoAddress;
    resultDtoShokugyou.value = resultDto.resultDtoShokugyou;

    resultDtoShokugyou.value.listShokugyou.push(new MasterPersonBaseEntity());
    resultDtoShokugyou.value.listShokugyou.push(new MasterPersonBaseEntity());
    resultDtoAddress.value.listAddress.push(new MasterKanrenshaAddressBaseEntity());
    resultDtoAddress.value.listAddress.push(new MasterKanrenshaAddressBaseEntity());
}


const inputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
const selectedAddress: Ref<number> = ref(0);
function onAddressInput(index: number) {
    selectedAddress.value = index;

    const baseEntity: MasterKanrenshaAddressBaseEntityInterface = resultDtoAddress.value.listAddress[index];

    // 1回だけ値を渡すために仮の値を経由する
    const dto: InputAddressDto = new InputAddressDto();
    dto.addressAll = "";
    dto.postalcode1 = baseEntity.postal1;
    dto.postalcode2 = baseEntity.postal2;
    dto.addressPostal = baseEntity.addressPostal;
    dto.addressBlock = baseEntity.addressBlock;
    dto.addressBuilding = baseEntity.addressBuilding;
    dto.isPostalEdit = baseEntity.isPostalEdit;
    dto.isBlockEdit = baseEntity.isBlockEdit;
    dto.isBuildingEdit = baseEntity.isBuildingEdit;

    // TODO コードは再度整理する
    dto.lgCode = baseEntity.lgCode;
    dto.machiazaId = baseEntity.machiazaId;
    dto.blkId = baseEntity.blkId;
    dto.rsdtId = baseEntity.rsdtId;

    inputAddressDto.value = dto;

    // コンポーネント表示
    isAddressInput.value = true;
}


/**
 * 住所入力キャンセル
 */
function recieveCancelInputAddress() {

    //非表示
    isAddressInput.value = false;
}

/**
 * 関連者検索選択
 * @param sendDto 選択Dto
 */
function recieveInputAddressInterface(sendDto: InputAddressDto) {

    const baseEntity: MasterKanrenshaAddressBaseEntityInterface = resultDtoAddress.value.listAddress[selectedAddress.value];

    baseEntity.postal1 = sendDto.postalcode1;
    baseEntity.postal2 = sendDto.postalcode2;
    baseEntity.addressPostal = sendDto.addressPostal;
    baseEntity.addressBlock = sendDto.addressBlock;
    baseEntity.addressBuilding = sendDto.addressBuilding;
    baseEntity.isPostalEdit = sendDto.isPostalEdit;
    baseEntity.isBlockEdit = sendDto.isBlockEdit;
    baseEntity.isBuildingEdit = sendDto.isBuildingEdit;

    // TODO コードは再度整理する
    baseEntity.lgCode = sendDto.lgCode;
    baseEntity.machiazaId = sendDto.machiazaId;
    baseEntity.blkId = sendDto.blkId;
    baseEntity.rsdtId = sendDto.rsdtId;

    //編集した値で入れ替え
    resultDtoAddress.value.listAddress.splice(selectedAddress.value, 1, baseEntity);

    //非表示
    isAddressInput.value = false;
}

// 職業入力
const inputShokugyouDto: Ref<InputShokugyouInterface> = ref(new InputShokugyouDto());
const isShokugyouInput: Ref<boolean> = ref(false);
const selectedShokugyou: Ref<number> = ref(0);

function onShokugyouInput(index: number) {
    selectedShokugyou.value = index;
    const baseEntity: MasterPersonBaseEntityInterface = resultDtoShokugyou.value.listShokugyou[selectedShokugyou.value];
    inputShokugyouDto.value.allShokugyou = baseEntity.personShokugyou;
    inputShokugyouDto.value.gyoushu = baseEntity.gyoushu;
    inputShokugyouDto.value.yakushoku = baseEntity.yakushoku;
    inputShokugyouDto.value.shokugyouUserWrite = baseEntity.shokugyouUserWrite;
    inputShokugyouDto.value.corpNo = baseEntity.corpNo;
    inputShokugyouDto.value.corpAddress = baseEntity.corpAddress;
    inputShokugyouDto.value.corpName = baseEntity.corpName;

    isShokugyouInput.value = true;
}


function recieveCancelInputShokugyou() {
    isShokugyouInput.value = false;
}

function recieveInputShokugyouInterface(shokugyouDto: InputShokugyouInterface) {

    const baseEntity: MasterPersonBaseEntityInterface = resultDtoShokugyou.value.listShokugyou[selectedShokugyou.value];
    baseEntity.personShokugyou = shokugyouDto.allShokugyou;
    baseEntity.gyoushu = shokugyouDto.gyoushu;
    baseEntity.yakushoku = shokugyouDto.yakushoku;
    baseEntity.shokugyouUserWrite = shokugyouDto.shokugyouUserWrite;
    baseEntity.corpNo = shokugyouDto.corpNo;
    baseEntity.corpAddress = shokugyouDto.corpAddress;
    baseEntity.corpName = shokugyouDto.corpName;

    //編集した値で入れ替え
    resultDtoShokugyou.value.listShokugyou.splice(selectedShokugyou.value, 1, baseEntity);

    isShokugyouInput.value = false;
}


function onCancel() {
    alert("キャンセル");
    history.back();

}
function onSave() {
    const capsuleDtoSave: SaveApprovalCapsuleDtoInterface = new SaveApprovalCapsuleDto()
    capsuleDtoSave.listAddress = resultDtoAddress.value.listAddress;
    capsuleDtoSave.listShokugyou = resultDtoShokugyou.value.listShokugyou;
    //capsuleDtoSave.userPersonLeastDto = userDto

    const resultDtoSave: FrameworkMessageAndResultInterface = new FrameworkMessageAndResultDto();
    resultDtoSave.message = "保存";
    alert(resultDtoSave.message);
}

// ページング
const pageOption: Ref<SelectOptionNumberInterface[]> = ref([]);
function onChangePaging() {
    // TODO (ページング情報をコンポーネントから受け取り)検索処理を実行
}

</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <ManagerInfo @send-user="recieveUser"></ManagerInfo>
    <hr>

    <h1>登録内容承認</h1>

    <div class="left-area">
        検索期間
    </div>
    <div class="right-area">
        <span><input type="date" v-model="capsuleDto.startDateText" value="1">から</span>
        <span class="left-space"><input type="date" v-model="capsuleDto.endDateText" value="3">まで</span>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        承認必要のみ
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="capsuleDto.isExcludeFinishedTask">承認必要作業のみ表示する
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

    <div class="one-line" style="overflow: scroll;">
        <h3>検索結果(住所)</h3>
        <!-- ページング -->
        <select @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select>
        <table>
            <tbody>
                <tr>
                    <th>関連者名称(姓名)</th>
                    <th>報告書表示住所</th>
                    <th>郵便番号</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                    <th>住所建物</th>
                    <th>行政区コード</th>
                    <th>町字Id</th>
                    <th>番地Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(entity, index) of resultDtoAddress.listAddress">
                    <td>({{ entity.kanrenshaCode }})<br>{{ entity.partnerName }}</td>
                    <td>{{ entity.addressPostal }}</td>
                    <td>{{ entity.postal1 }} -{{ entity.postal2 }}</td>
                    <td><input type="checkbox" v-model="entity.isPostalEdit">編集あり<br><input type="checkbox"
                            v-model="entity.isPostalAccept">承認<br>{{ entity.addressPostal }}</td>
                    <td><input type="checkbox" v-model="entity.isBlockEdit">編集あり<br><input type="checkbox"
                            v-model="entity.isBlockAccept">承認<br>{{ entity.addressBlock }}</td>
                    <td><input type="checkbox" v-model="entity.isBuildingEdit">編集あり<br><input type="checkbox"
                            v-model="entity.isBuildingAccept">承認<br>{{ entity.addressBuilding }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.machiazaId }}</td>
                    <td>{{ entity.blkId }}</td>
                    <td>{{ entity.rsdtId }}</td>
                    <td>{{ entity.rsdt2Id }}</td>
                    <td><button @click="onAddressInput(index)">編集</button></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line" style="overflow: scroll;">
        <h3>検索結果(職業)</h3>
        <!-- ページング -->
        <select @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select>
        <table style="width: 2400px;">
            <tbody>
                <tr>
                    <th>関連者名称(姓名)</th>
                    <th>編集／承認</th>
                    <th>職業表示</th>
                    <th>業種</th>
                    <th>役職</th>
                    <th>ユーザ入力</th>
                    <th>法人番号</th>
                    <th>法人住所</th>
                    <th>法人名称</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="(entity, index) of resultDtoShokugyou.listShokugyou">
                    <td>({{ entity.personKanrenshaCode }}) <br>{{ entity.partnerName }}</td>
                    <td><input type="checkbox" v-model="entity.isShokyouEdit" :disabled="true"></input>編集あり<br> <input
                            type="checkbox" v-model="entity.isShokyouAccept">承認</td>
                    <td><input type="text" v-model="entity.personShokugyou" :disabled="true"></td>
                    <td><input type="text" v-model="entity.gyoushu" :disabled="true"></td>
                    <td><input type="text" v-model="entity.yakushoku" :disabled="true"></td>
                    <td><input type="text" v-model="entity.shokugyouUserWrite" :disabled="true"></td>
                    <td><input type="text" v-model="entity.corpNo" :disabled="true"></td>
                    <td><input type="text" v-model="entity.corpAddress" :disabled="true"></td>
                    <td><input type="text" v-model="entity.corpName" :disabled="true"></td>
                    <td><button @click="onShokugyouInput(index)">編集</button></td>
                </tr>
            </tbody>
        </table>
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
            <InputAddressEditRegistory v-if="isAddressInput" :edit-dto="inputAddressDto"
                @send-cancel-input-address="recieveCancelInputAddress"
                @send-input-address-interface="recieveInputAddressInterface">
                ></InputAddressEditRegistory>
        </div>
    </div>

    <!-- 職業入力 -->
    <div v-if="isShokugyouInput" class="overBackground"></div>
    <div v-if="isShokugyouInput">
        <div class="overComponent">
            <InputShokugyouCompare v-if="isShokugyouInput" :edit-dto="inputShokugyouDto" :isfooter="true"
                @send-cancel-input-shokugyou="recieveCancelInputShokugyou"
                @send-input-shokugyou-interface="recieveInputShokugyouInterface">
            </InputShokugyouCompare>
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
    width: 200px;
}
</style>
