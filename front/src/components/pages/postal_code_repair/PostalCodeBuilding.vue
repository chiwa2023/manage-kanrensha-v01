<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import MockManagerInfo from '../../common/user_info/MockManagerInfo.vue';
import ViewInputAddress from '../../common/input_address/ViewInputAddress.vue';
import mockGetIllegularItem from './mock/mockGetIllegularItem';
import type PostalIrregularItemInterface from '../../../dto/postal/postalIrregularItemDto';
import PostalIrregularItemDto from '../../../dto/postal/postalIrregularItemDto';
import mockGetIllegularAddress from './mock/mockGetIllegularAddress';
import getPagingOption from '../paging/getPagingOption';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchPostalIllegularCapsuleInterface from '../../../dto/postal/searchPostalIllegularCapsuleDto';
import SearchPostalIllegularCapsuleDto from '../../../dto/postal/searchPostalIllegularCapsuleDto';
import type SearchPostalIllegularResultInterface from '../../../dto/postal/searchPostalIllegularResultDto';
import type GetDetailPostalIllegularResultInterface from '../../../dto/postal/getDetailPostalIllegularResultDto';
import GetDetailPostalIllegularResultDto from '../../../dto/postal/getDetailPostalIllegularResultDto';
import type GetDetailPostalIllegularCapsuleInterface from '../../../dto/postal/getDetailPostalIllegularCapsuleDto';
import GetDetailPostalIllegularCapsuleDto from '../../../dto/postal/getDetailPostalIllegularCapsuleDto';

const isVisibleDetail: Ref<boolean> = ref(false);
function isChangeview() {
    // 描画を転置
    isVisibleDetail.value = !isVisibleDetail.value;
}

// 初期で建物住所を全件取得する
const capsuleDtoItem: Ref<SearchPostalIllegularCapsuleInterface> = ref(new SearchPostalIllegularCapsuleDto());
// 建物の地階データを全件取得する
// getAuthorizedPromiseArea().then(token => {
//     if (token !== "") {
//         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
//         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
//
//         const url = "http://localhost:6080/postal-irregular/building";
//         const method = "POST";
//         const body = JSON.stringify(null);
//         const headers = {
//             'Accept': 'application/json',
//             'Content-Type': 'application/json',
//             'X-AUTH-TOKEN': 'Bearer ' + token
//         };
//         fetch(url, { method, headers, body })
//             .then(async (response) => {
//                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
//
//                 // alert(resultDto.message);
//             })
//             .catch((e) => { alert(e); });
//     } else {
//         alert("エラーのつもり");
//     }
// });

const resultDtoItem: Ref<SearchPostalIllegularResultInterface> = ref(mockGetIllegularItem());
const pageOptionIllegular: Ref<SelectOptionNumberInterface[]> = ref([]);
pageOptionIllegular.value = getPagingOption(resultDtoItem.value);

// 編集対象の詳細リスト
const capsuleDtoIllegular: Ref<GetDetailPostalIllegularCapsuleInterface> = ref(new GetDetailPostalIllegularCapsuleDto());
const resultDtoIllegular: Ref<GetDetailPostalIllegularResultInterface> = ref(new GetDetailPostalIllegularResultDto());

// 編集対象の変更
const selectedId: Ref<number> = ref(0);
const dtoEdit: Ref<PostalIrregularItemInterface> = ref(new PostalIrregularItemDto());
function onChangeEdit(id: number) {
    dtoEdit.value = structuredClone(toRaw(resultDtoItem.value.listItem.filter(e => e.addressPostalIrregularId === id)[0]));
    capsuleDtoIllegular.value.lgCode = dtoEdit.value.lgCode;
    resultDtoIllegular.value = mockGetIllegularAddress(dtoEdit.value.addressName);

    // 選択された建物の全フロアデータを取得する
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/postal-irregular/building-detail";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}

// ページング切り替え
function onChangePaging() {
    alert("ページング切り替え" + capsuleDtoItem.value.pageNumber);
    // TODO 編集済のデータがあれば切替前に編集内容破棄を確認して保存処理
}

function onCancel() {
    alert("キャンセル");
    history.back();

}
function onSave() {
    alert("保存");

    // 入力された住所を全フロア住所に展開する
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/postal-irregular/save-building";
    //         const method = "POST";
    //         const body = JSON.stringify(null);
    //         const headers = {
    //             'Accept': 'application/json',
    //             'Content-Type': 'application/json',
    //             'X-AUTH-TOKEN': 'Bearer ' + token
    //         };
    //         fetch(url, { method, headers, body })
    //             .then(async (response) => {
    //                 // const resultDto: FrameworkMessageAndResultInterface = await response.json();
    //
    //                 // alert(resultDto.message);
    //             })
    //             .catch((e) => { alert(e); });
    //     } else {
    //         alert("エラーのつもり");
    //     }
    // });

}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo></MockManagerInfo>
    <hr>

    <h1>フロア郵便番号追加</h1>

    <div class="one-line">
        ※共通住所名
        <!-- ページング -->
        <select v-model="capsuleDtoItem.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionIllegular" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>

        <table>
            <tbody>
                <tr>
                    <th>編集</th>
                    <th>郵便番号</th>
                    <th>住所名</th>
                    <th>地方自治体コード</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                </tr>
                <tr v-for="dto of resultDtoItem.listItem" :key="dto.addressPostalIrregularId">
                    <td><input type="radio" v-model="selectedId" id="listCommon" :value="dto.addressPostalIrregularId"
                            @click="onChangeEdit(dto.addressPostalIrregularId)"></td>
                    <td>{{ dto.postal1 }}</td>
                    <td>{{ dto.addressName }}</td>
                    <td>{{ dto.lgCode }}</td>
                    <td>{{ dto.inputAddress.addressPostal }}</td>
                    <td>{{ dto.inputAddress.addressBlock }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        ※該当郵便番号一覧<button @click="isChangeview" class="left-space">データ取得</button>
        <table v-if="isVisibleDetail">
            <tbody>
                <tr>
                    <th>郵便番号</th>
                    <th>住所共通</th>
                    <th>住所詳細</th>
                </tr>
                <tr v-for="entity of resultDtoIllegular.listIrregular" :key="entity.addressPostalIrregularId">
                    <td>{{ entity.postal1 }}</td>
                    <td>{{ entity.addressName }}</td>
                    <td>{{ entity.addressOrg }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <h3>住所入力</h3>
    <div class="left-area">
        共通住所
    </div>
    <div class="right-area">
        <input type="text" v-model="dtoEdit.addressName" disabled="true">
    </div>
    <div class="clear-both"></div>

    <ViewInputAddress :edit-dto="dtoEdit.inputAddress" :is-raise-edit-view="true"></ViewInputAddress>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
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
