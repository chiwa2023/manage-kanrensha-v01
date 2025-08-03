<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type SearchWkTblHistoryPoliOrgPagingResultInterface from '../../../dto/wktbl_history/searchWkTblHistoryPoliOrgPagingResultDto';
import SearchWkTblHistoryPoliOrgPagingResultDto from '../../../dto/wktbl_history/searchWkTblHistoryPoliOrgPagingResultDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import getMockWkTblPoliOrgList from './mock/getMockWkTblPoliOrgList';
import type WkTblPartnerPoliOrgHistoryInterface from '../../../entity/wkTblPartnerPoliOrgHistoryEntity';
import WkTblPartnerPoliOrgHistoryEntity from '../../../entity/wkTblPartnerPoliOrgHistoryEntity';

const pageOptionPoliOrg: Ref<SelectOptionNumberInterface[]> = ref([]);
const poliOrgCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const poliOrgResultDto: Ref<SearchWkTblHistoryPoliOrgPagingResultInterface> = ref(new SearchWkTblHistoryPoliOrgPagingResultDto());

function onSearchPoliOrg() {
    poliOrgResultDto.value.allCount = 313;
    poliOrgResultDto.value.limit = 30;
    pageOptionPoliOrg.value = getPagingOption(poliOrgResultDto.value);
    poliOrgResultDto.value.listWktblPoliOrg = getMockWkTblPoliOrgList();
}
// ページング変更
function onChangePaging() {
    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerPoliOrgHistoryInterface> = ref(new WkTblPartnerPoliOrgHistoryEntity());
let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = poliOrgResultDto.value.listWktblPoliOrg.findIndex((e) => e.wkPartnerPoliOrgHistoryId === editId);
    entityEdit.value = structuredClone(toRaw(poliOrgResultDto.value.listWktblPoliOrg[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {
    // 指定された値に置き換え
    poliOrgResultDto.value.listWktblPoliOrg.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}
function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}

</script>
<template>
    <h3>関連者政治団体検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="poliOrgCapsuleDto.hasAffectNot">反映なし
        <span class="left-space"><input type="checkbox" v-model="poliOrgCapsuleDto.hasFinished">作業完了</span>
        <span class="left-space"><input type="checkbox" v-model="poliOrgCapsuleDto.hasHistorry">処理対象外履歴</span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        作業結果表示
    </div>
    <div class="right-area">
        <button @click="onSearchPoliOrg">表示</button>
    </div>
    <div class="clear-both"></div>

    <h3>関連者政治団体処理予定</h3>
    <div class="one-line">
        <!-- ページング -->
        <select v-model="poliOrgCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionPoliOrg" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>政治団体名称</th>
                    <th>政治団体住所</th>
                    <th>政治団体代表者</th>
                    <th>関連者コード</th>
                </tr>
            </tbody>
            <tbody v-for="entity of poliOrgResultDto.listWktblPoliOrg" :key="entity.wkPartnerPoliOrgHistoryId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkPartnerPoliOrgHistoryId)" :disabled="!entity.isLatest"> {{
                            entity.partnerName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.poliOrgDelegate }}</td>
                    <td>{{ entity.poliOrgKanrenshaCode }}</td>
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
                政治団体名
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
                政治団体代表者名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.poliOrgDelegate" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                政治団体関連者コード
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.poliOrgKanrenshaCode" />
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
