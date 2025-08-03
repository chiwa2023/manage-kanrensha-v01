<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblMinCorpPagingResultInterface from '../../../dto/wktbl_min/searchWkTblMinCorpPagingResultDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblMinCorpPagingResultDto from '../../../dto/wktbl_min/searchWkTblMinCorpPagingResultDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import getMockWkTblCorpList from './mock/getMockWkTblCorpList';
import type WkTblPartnerCorpAddMinInterface from '../../../entity/wkTblPartnerCorpAddMinEntity';
import WkTblPartnerCorpAddMinEntity from '../../../entity/wkTblPartnerCorpAddMinEntity';

const pageOptionCorp: Ref<SelectOptionNumberInterface[]> = ref([]);
const corpCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const corpResultDto: Ref<SearchWkTblMinCorpPagingResultInterface> = ref(new SearchWkTblMinCorpPagingResultDto());

function onSearchCorp() {
    corpResultDto.value.allCount = 223;
    corpResultDto.value.limit = 30;
    pageOptionCorp.value = getPagingOption(corpResultDto.value);
    corpResultDto.value.listWktblCorp = getMockWkTblCorpList();
}

// ページング変更
function onChangePaging() {
    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerCorpAddMinInterface> = ref(new WkTblPartnerCorpAddMinEntity());
let findIndex:number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = corpResultDto.value.listWktblCorp.findIndex((e) => e.wkTblPartnerCorpAddMinId === editId);
    entityEdit.value = structuredClone(toRaw(corpResultDto.value.listWktblCorp[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {
    // 指定された値に置き換え
    corpResultDto.value.listWktblCorp.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
</script>
<template>
    <h3>関連者企業／団体検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="corpCapsuleDto.hasAffectNot">反映なし
        <span class="left-space"><input type="checkbox" v-model="corpCapsuleDto.hasFinished">作業完了</span>
        <span class="left-space"><input type="checkbox" v-model="corpCapsuleDto.hasHistorry">処理対象外履歴</span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        作業結果表示
    </div>
    <div class="right-area">
        <button @click="onSearchCorp">表示</button>
    </div>
    <div class="clear-both"></div>

    <h3>関連者企業／団体処理予定</h3>
    <div class="one-line">
        <select v-model="corpCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionCorp" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>企業／団体氏名</th>
                    <th>全住所</th>
                    <th>団体代表者</th>
                    <th>法人番号</th>
                </tr>
            </tbody>
            <tbody v-for="entityCorp of corpResultDto.listWktblCorp" :key="entityCorp.wkTblPartnerCorpAddMinId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entityCorp.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entityCorp.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entityCorp.wkTblPartnerCorpAddMinId)"
                            :disabled="!entityCorp.isLatest">{{ entityCorp.partnerName }}</button></td>
                    <td>{{ entityCorp.allAddress }}</td>
                    <td>{{ entityCorp.corpDelegate }}</td>
                    <td>{{ entityCorp.houjinNo }}</td>
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
                企業／団体名
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
                企業／団体代表者名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.corpDelegate" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                法人番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.houjinNo" />
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
