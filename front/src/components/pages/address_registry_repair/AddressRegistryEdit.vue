<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import MockManagerInfo from '../../common/user_info/MockManagerInfo.vue';
import type AddressAllCityInterface from '../../../entity/addressAllCityEntity';
import mockGetLgList from './mock/mockGetLgList';
import AddressRsdtTemplateInterface from '../../../entity/addressRsdtTemplateEntity';
import AddressRsdtTemplateEntity from '../../../entity/addressRsdtTemplateEntity';
import mockGetAddressRsdtList from './mock/mockGetAddressRsdtList';
import getPagingOption from '../paging/getPagingOption';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchLocalGovernmentResultInterface from '../../../dto/address_registory/searchLocalGovernmentResultDto';
import SearchLocalGovernmentResultDto from '../../../dto/address_registory/searchLocalGovernmentResultDto';
import type SearchLocalGovernmentCapsuleInterface from '../../../dto/address_registory/searchLocalGovernmentCapsuleDto';
import SearchLocalGovernmentCapsuleDto from '../../../dto/address_registory/searchLocalGovernmentCapsuleDto';
import type SearchAddressRegistoryCapsuleInterface from '../../../dto/address_registory/searchAddressRegistoryCapsuleDto';
import SearchAddressRegistoryCapsuleDto from '../../../dto/address_registory/searchAddressRegistoryCapsuleDto';
import type SearchAddressRegistoryResultInterface from '../../../dto/address_registory/searchAddressRegistoryResultDto';
import SearchAddressRegistoryResultDto from '../../../dto/address_registory/searchAddressRegistoryResultDto';

// 地方自治体検索
const selectedIdLg: Ref<string> = ref("");
const pageOptionLocalGov: Ref<SelectOptionNumberInterface[]> = ref([]);
const capsuleDtoLocalGov: Ref<SearchLocalGovernmentCapsuleInterface> = ref(new SearchLocalGovernmentCapsuleDto());
const resultDtoLocalGov: Ref<SearchLocalGovernmentResultInterface> = ref(new SearchLocalGovernmentResultDto());

// 地方自治体検索
function onSearchLocalGov() {
    resultDtoLocalGov.value = mockGetLgList(capsuleDtoLocalGov.value.pageNumber);

    // 住所の部分一致から自治体コードに紐づくアドレス・ベース・レジストリ住居検索処理(0件メッセージあり)
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/local-gov/search";
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

    pageOptionLocalGov.value = getPagingOption(resultDtoLocalGov.value);
}
// 地方自治体検索ページング
function onChangePagingLocalGov() {
    resultDtoLocalGov.value = mockGetLgList(capsuleDtoLocalGov.value.pageNumber);
}


// 選択された自治体から住居までリストを取得
const selectedIdRsdt: Ref<number> = ref(0);
const pageOptionRsdt: Ref<SelectOptionNumberInterface[]> = ref([]);
const capsuleDtoRsdt: Ref<SearchAddressRegistoryCapsuleInterface> = ref(new SearchAddressRegistoryCapsuleDto());
const resultDtoRsdt: Ref<SearchAddressRegistoryResultInterface> = ref(new SearchAddressRegistoryResultDto());

function onChangeEditLocalGov(id: number) {
    const entityLg: AddressAllCityInterface = resultDtoLocalGov.value.listAllCity.filter((e) => e.addressAllCityId === id)[0];
    resultDtoRsdt.value = mockGetAddressRsdtList(capsuleDtoRsdt.value.pageNumber, entityLg.lgCode);

    // 自治体コードをキーにした検索処理
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/address-regi-rsdt/search";
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

    pageOptionRsdt.value = getPagingOption(resultDtoRsdt.value);
}

// 住居までリストページング
function onChangePagingRsdt() {
    const entityLg: AddressAllCityInterface = resultDtoLocalGov.value.listAllCity.filter((e) => e.addressAllCityId === selectedIdRsdt.value)[0];
    resultDtoRsdt.value = mockGetAddressRsdtList(capsuleDtoRsdt.value.pageNumber, entityLg.lgCode);
}

// 住居データ編集対象
const entityEdit: Ref<AddressRsdtTemplateInterface> = ref(new AddressRsdtTemplateEntity());
function onChangeEditRsdt(id: number) {
    // TODO 編集されていれば保存処理をするかどうか確認
    entityEdit.value = structuredClone(toRaw(resultDtoRsdt.value.listRsdt.filter((e) => e.addressRsdtId === id)[0]));
}

function onAdd() {
    // TODO 編集されていれば保存処理をするかどうか確認
    entityEdit.value = structuredClone(new AddressRsdtTemplateEntity());
    selectedIdRsdt.value = 0; // 選択を外す
}

function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    alert("保存");

    // アドレス・ベース・レジストリ住居　保存処理
    // getAuthorizedPromiseArea().then(token => {
    //     if (token !== "") {
    //         // const conditionDto: SaveAddressRegistoryCapsuleInterface = new SaveAddressRegistoryCapsuleEntity();
    //         // conditionDto.addressRsdtTemplateEntity = entityEdit.value;
    //
    //         const url = "http://localhost:6080/address-regi-rsdt/save";
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

    <h1>アドレスベースレジストリ編集</h1>

    <h3>編集地方自治体の指定</h3>
    <div class="left-area">
        検索条件(部分一致)
    </div>
    <div class="right-area">
        <input type="text" v-model="capsuleDtoLocalGov.addressWords">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearchLocalGov">検索</button>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        検索結果<br>
        <!-- ページング -->
        <select v-model="capsuleDtoLocalGov.pageNumber" @change="onChangePagingLocalGov">
            <option v-for="option in pageOptionLocalGov" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>

        <table>
            <tbody>
                <tr>
                    <th>編集</th>
                    <th>地方公共団体コード</th>
                    <th>地方団体名</th>
                </tr>
                <tr v-for="entity of resultDtoLocalGov.listAllCity" :key="entity.addressAllCityId">
                    <td><input type="radio" v-model="selectedIdLg" id="listLg" :value="entity.addressAllCityId"
                            @click="onChangeEditLocalGov(entity.addressAllCityId)"> </td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.addressName }}</td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"></div>

    <h3>編集選択</h3>

    <div class="one-line">
        検索結果<br>
        <!-- ページング -->
        <select v-model="capsuleDtoRsdt.pageNumber" @change="onChangePagingRsdt">
            <option v-for="option in pageOptionRsdt" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>編集</th>
                    <th>地方公共団体コード</th>
                    <th>郵便番号</th>
                    <th>町字コード</th>
                    <th>住居コード</th>
                    <th>住所番地まで</th>
                    <th>住所建物</th>
                </tr>
                <tr v-for="entity of resultDtoRsdt.listRsdt">
                    <td><input type="radio" v-model="selectedIdRsdt" id="listRsdt" :value="entity.addressRsdtId"
                            @click="onChangeEditRsdt(entity.addressRsdtId)"> </td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.postalCode }}</td>
                    <td>{{ entity.machiazaId }}</td>
                    <td>{{ entity.parcelRsdtId }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td>{{ entity.addressBuilding }}</td>
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
        <button @click="onAdd">追加</button>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        地方公共団体コード
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.lgCode" disabled="true">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        郵便番号
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.postalCode" class="code-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        町字コード
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.machiazaId">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        住居コード
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.parcelRsdtId">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        住所建物まで
    </div>
    <div class="right-area">
        <input type="text" v-model="entityEdit.addressBlock" class="code-name">
        <input type="text" v-model="entityEdit.addressBuilding" class="code-name left-space">
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
