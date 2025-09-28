<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type SearchWkTblMinPersonPagingResultInterface from '../../../dto/wktbl_min/searchWkTblMinPersonPagingResultDto';
import SearchWkTblMinPersonPagingResultDto from '../../../dto/wktbl_min/searchWkTblMinPersonPagingResultDto';
import type WkTblPartnerPersonAddMinInterface from '../../../entity/wkTblPartnerPersonAddMin';
import WkTblPartnerPersonAddMinEntity from '../../../entity/wkTblPartnerPersonAddMin';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UpdateWkTblMinPersonCapsuleInterface from '../../../dto/wktbl_min/updateWkTblMinPersonCapsuleDto';
import UpdateWkTblMinPersonCapsuleDto from '../../../dto/wktbl_min/updateWkTblMinPersonCapsuleDto';
import type UpdateWkTblMinPersonResultInterface from '../../../dto/wktbl_min/updateWkTblMinPersonResultDto';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

// 表示必要なDto
const pageOptionPerson: Ref<SelectOptionNumberInterface[]> = ref([]);
const personCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
personCapsuleDto.value.userLeast = userDto.value;
personCapsuleDto.value.limit = 30;
personCapsuleDto.value.pageNumber = 0;
personCapsuleDto.value.hasAffectNot = true;

const personResultDto: Ref<SearchWkTblMinPersonPagingResultInterface> = ref(new SearchWkTblMinPersonPagingResultDto());

// 検索処理
function onSearchPerson() {
    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-min/search-person";
        const method = "POST";
        const body = JSON.stringify(personCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                personResultDto.value = await response.json();
                pageOptionPerson.value = getPagingOption(personResultDto.value);
            })
            .catch((error) => { alert(error); });
    });
}

// ページング変更
function onChangePaging() {
    onSearchPerson();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerPersonAddMinInterface> = ref(new WkTblPartnerPersonAddMinEntity());
const editCapsuleDto: Ref<UpdateWkTblMinPersonCapsuleInterface> = ref(new UpdateWkTblMinPersonCapsuleDto());
editCapsuleDto.value.userPersonLeastDto = userDto.value;

let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = personResultDto.value.listWktblPerson.findIndex((e) => e.wkTblPartnerPersonAddMinId === editId);
    entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {


    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblPartnerPersonAddMinEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-min/update-person";
        const method = "POST";
        const body = JSON.stringify(editCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                if (response.status < 400) {
                    // TODO 処理内容
                    const resultDto: UpdateWkTblMinPersonResultInterface = await response.json();
                    alert(resultDto.message);
                    if (response.status === 200) {
                        // 再表示
                        onSearchPerson();
                    }
                }
            })
            .catch((error) => { alert(error); });
    });


    // 指定された値に置き換え
    // personResultDto.value.listWktblPerson.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}

// 編集画面データ更新禁止
const listEditProhibit: string[] = [];
listEditProhibit.push("正常終了");
function isEdit(): boolean {
    return listEditProhibit.includes(entityEdit.value.judgeReason);
}

const notUseText: string = "使用しないに変更;";
function onHideData() {
    entityEdit.value.judgeReason = notUseText;
    entityEdit.value.isAffected = false;
    entityEdit.value.isFinish = true;
    onEditUpdate();
}

defineExpose({
    onSearchPerson,
});
</script>
<template>
    <h3>関連者個人検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="personCapsuleDto.hasAffectNot">反映なし
        <span class="left-space"><input type="checkbox" v-model="personCapsuleDto.hasFinished">作業完了</span>
        <span class="left-space"><input type="checkbox" v-model="personCapsuleDto.hasHistorry">処理対象外履歴</span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        作業結果表示
    </div>
    <div class="right-area">
        <button @click="onSearchPerson">表示</button>
    </div>
    <div class="clear-both"></div>

    <h3>関連者個人処理予定</h3>
    <div class="one-line">
        <!-- ページング -->
        <select v-model="personCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionPerson" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>個人氏名</th>
                    <th>全住所</th>
                    <th>職業</th>
                </tr>
            </tbody>
            <tbody v-for="entityPerson of personResultDto.listWktblPerson"
                :key="entityPerson.wkTblPartnerPersonAddMinId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entityPerson.isAffected" disabled="true">反映する</td>
                    <td colspan="3">{{ entityPerson.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entityPerson.wkTblPartnerPersonAddMinId)"
                            :disabled="!entityPerson.isLatest">{{ entityPerson.partnerName }}</button></td>
                    <td>{{ entityPerson.allAddress }}</td>
                    <td>{{ entityPerson.personShokugyou }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>

    <!-- 編集処理 -->
    <div v-if="isEditData" class="overBackground"></div>
    <div v-if="isEditData">
        <div class="overComponent">
            <div class="left-area">
                反映該否
            </div>
            <div class="right-area">
                <input type="checkbox" v-model="entityEdit.isAffected">反映あり<button @click="onHideData"
                    class="left-space">このデータを使用しない</button>
                <br>※データが重複していると反映該否が動かせないことがあります
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                判定理由
            </div>
            <div class="right-area">
                {{ entityEdit.judgeReason }}
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                個人姓名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.partnerName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                全住所
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.allAddress" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                個人職業
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.personShokugyou" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                &nbsp;
            </div>
            <div class="right-area">
                <button @click="onEditClose">閉じる</button><button class="left-space" @click="onEditUpdate()"
                    :disabled="isEdit()">更新</button>
            </div>
            <div class="clear-both"></div>
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

td.rowNum {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 2px;
    border-bottom-width: 2px;
    border-right-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}

tbody:after {
    content: "";
    height: 2px;
    width: 100%;
    padding: 2px 0;
    display: block;
}
</style>
