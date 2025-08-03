<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type SearchWkTblMinPersonPagingResultInterface from '../../../dto/wktbl_min/searchWkTblMinPersonPagingResultDto';
import SearchWkTblMinPersonPagingResultDto from '../../../dto/wktbl_min/searchWkTblMinPersonPagingResultDto';
import getMockWkTblPersonList from './mock/getMockWkTblPersonList';
import type WkTblPartnerPersonAddMinInterface from '../../../entity/wkTblPartnerPersonAddMin';
import WkTblPartnerPersonAddMinEntity from '../../../entity/wkTblPartnerPersonAddMin';

// 表示必要なDto
const pageOptionPerson: Ref<SelectOptionNumberInterface[]> = ref([]);
const personCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const personResultDto: Ref<SearchWkTblMinPersonPagingResultInterface> = ref(new SearchWkTblMinPersonPagingResultDto());

// 検索処理
function onSearchPerson() {
    personResultDto.value.allCount = 195;
    personResultDto.value.limit = 30;
    pageOptionPerson.value = getPagingOption(personResultDto.value);
    personResultDto.value.listWktblPerson = getMockWkTblPersonList();
}

// ページング変更
function onChangePaging() {
    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerPersonAddMinInterface> = ref(new WkTblPartnerPersonAddMinEntity());
let findIndex:number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = personResultDto.value.listWktblPerson.findIndex((e) => e.wkTblPartnerPersonAddMinId === editId);
    entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {
    // 指定された値に置き換え
    personResultDto.value.listWktblPerson.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
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
                <input type="checkbox" v-model="entityEdit.isAffected">反映あり
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
                <button @click="onEditClose">閉じる</button><button class="left-space" @click="onEditUpdate()">更新</button>
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
