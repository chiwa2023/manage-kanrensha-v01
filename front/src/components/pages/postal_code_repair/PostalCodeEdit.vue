<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import AddressPostalInterface from '../../../entity/addressPostalEntity';
import AddressPostalDto from '../../../entity/addressPostalEntity';
import mockGetPostalCodeList from './mock/mockGetPostalCodeList';
import getPagingOption from '../paging/getPagingOption';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchPostalCodeCapsuleInterface from '../../../dto/postal/searchPostalCodeCapsuleDto';
import SearchPostalCodeCapsuleDto from '../../../dto/postal/searchPostalCodeCapsuleDto';
import type SearchPostalCodeResultInterface from '../../../dto/postal/searchPostalCodeResultDto';
import SearchPostalCodeResultDto from '../../../dto/postal/searchPostalCodeResultDto';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';

// ページング
const pageNumber: Ref<number> = ref(0);
const pageOptionPostal: Ref<SelectOptionNumberInterface[]> = ref([]);

// 検索条件と検索結果Dt0
const capsuleDto: Ref<SearchPostalCodeCapsuleInterface> = ref(new SearchPostalCodeCapsuleDto());
const resultDto: Ref<SearchPostalCodeResultInterface> = ref(new SearchPostalCodeResultDto());

// 編集対象
const entityEdit: Ref<AddressPostalInterface> = ref(new AddressPostalDto);
function onSearch() {
    resultDto.value = mockGetPostalCodeList();

    // 入力された検索語で郵便番号検索をする
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/postal-code/search";
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

    pageOptionPostal.value = getPagingOption(resultDto.value);
}
// ページング切り替え
function onChangePaging() {
    alert("ページング切り替え" + pageNumber.value);
    // TODO 編集があれば保存を促す
}

function onAddEntity() {
    // TODO 編集があれば保存を促す
    entityEdit.value = structuredClone(new AddressPostalDto());
}

const selectedId: Ref<number> = ref(0);
function onChangeEdit(id: number) {
    // TODO 編集があれば保存を促す
    entityEdit.value = structuredClone(toRaw(resultDto.value.listItem.filter(e => e.addressPostalId === id)[0]));
}

function onCancel() {
    alert("キャンセル");
    history.back();

}
function onSave() {
    alert("保存");

    // 編集された郵便番号
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/postal-code/save";
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
    <ManagerInfo></ManagerInfo>
    <hr>

    <h1>郵便番号情報編集</h1>

    <h3>編集郵便番号の指定</h3>
    <div class="left-area">
        検索条件(部分一致)
    </div>
    <div class="right-area">
        <input type="text" v-model="capsuleDto.addressWords">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        検索結果<br>
        <!-- ページング -->
        <select v-model="capsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionPostal" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>

        <table>
            <tbody>
                <tr>
                    <th>編集</th>
                    <th>郵便番号</th>
                    <th>原文書住所</th>
                    <th>表示住所</th>
                </tr>
                <tr v-for="entity of resultDto.listItem" :key="entity.addressPostalId">
                    <td><input type="radio" v-model="selectedId" id="listCommon" :value="entity.addressPostalId"
                            @click="onChangeEdit(entity.addressPostalId)"> </td>
                    <td>{{ entity.postal1 }}</td>
                    <td>{{ entity.addressOrg }}</td>
                    <td>{{ entity.addressName }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"></div>

    <h3>編集</h3>
    <div class="left-area">
        追加
    </div>
    <div class="right-area">
        <button @click="onAddEntity">追加</button>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        郵便番号
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.postal1" class="code-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        原文書住所
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.addressOrg">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        表示住所
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.addressName">
    </div>
    <div class="clear-both"></div>

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
