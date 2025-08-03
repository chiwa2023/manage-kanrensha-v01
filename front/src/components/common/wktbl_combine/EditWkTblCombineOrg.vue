<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type SearchWkTblCombineOrgPagingResultInterface from '../../../dto/wktbl_combine/searchWkTblCombineOrgPagingResultDto';
import SearchWkTblCombineOrgPagingResultDto from '../../../dto/wktbl_combine/searchWkTblCombineOrgPagingResultDto';
import getMockWkTblCombineOrgList from './mock/getMockWkTblCombineOrgList';
import KanrenshaKbnConstants from '../../../dto/kanrensha/kanrenshaKbnConstants';
import type WkTblPartnerCombineOrgInterface from '../../../entity/wkTblPartnerCombineOrgEntity';
import WkTblPartnerCombineOrgEntity from '../../../entity/wkTblPartnerCombineOrgEntity';
import YearOption from '../../../dto/wktbl_combine/yearOption';

const pageOptionCorp: Ref<SelectOptionNumberInterface[]> = ref([]);
const combineCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const combineResultDto: Ref<SearchWkTblCombineOrgPagingResultInterface> = ref(new SearchWkTblCombineOrgPagingResultDto());

const systemYearStart: number = 2019;
const systemYearEnd: number = 2025;
const listYearCheck: Ref<YearOption[]> = ref([]);
for (let index = systemYearStart; index <= systemYearEnd; index++) {
    const dto: YearOption = new YearOption();
    dto.year = index;
    dto.isSelect = false;
    listYearCheck.value.push(dto);
}

function onSearchCorp() {
    combineResultDto.value.allCount = 223;
    combineResultDto.value.limit = 30;
    pageOptionCorp.value = getPagingOption(combineResultDto.value);
    combineResultDto.value.listCombineOrg = getMockWkTblCombineOrgList();
}

// ページング変更
function onChangePaging() {
    // personResultDto.value.listWktblPerson = getMockWkTblPersonList();
}

// 初期表示データフォーマットは最小
const period: string = "period";
const point: string = "point";
const isSetPeriod: Ref<string> = ref(period);

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerCombineOrgInterface> = ref(new WkTblPartnerCombineOrgEntity());
let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = combineResultDto.value.listCombineOrg.findIndex((e) => e.wkTblPartnerCombineOrgId === editId);
    entityEdit.value = structuredClone(toRaw(combineResultDto.value.listCombineOrg[findIndex]));

    // 指定配列に基づきチェックボックスにチェックを打つ
    const listRegist = entityEdit.value.yearArrayText.split(":");
    for (const dto of listYearCheck.value) {
        if (listRegist.includes(String(dto.year))) {
            dto.isSelect = true;
        }
    }

    isEditData.value = true;
}
function onEditUpdate() {
    // 指定された値に置き換え
    combineResultDto.value.listCombineOrg.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));

    let text = "";
    if (isSetPeriod.value === period) {
        // 範囲による指定
        for (let index = entityEdit.value.startYear; index <= entityEdit.value.endYear; index++) {
                text = text + index + ":";
        }
    }
    else {
        // チェックボックスによる指定
        for (const dto of listYearCheck.value) {
            if (dto.isSelect) {
                text = text + dto.year + ":";
            }
        }
        entityEdit.value.startYear =0;
        entityEdit.value.endYear = 0;
    }
    const data = text === "" ? "" : text.substring(0, text.length - 1);
    combineResultDto.value.listCombineOrg[findIndex].yearArrayText = data;

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
        <input type="checkbox" v-model="combineCapsuleDto.hasAffectNot">反映なし
        <span class="left-space"><input type="checkbox" v-model="combineCapsuleDto.hasFinished">作業完了</span>
        <span class="left-space"><input type="checkbox" v-model="combineCapsuleDto.hasHistorry">処理対象外履歴</span>
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
        <!-- ページング -->
        <select v-model="combineCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionCorp" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>紐づけ関連者区分</th>
                    <th>個人関連者コード</th>
                    <th>個人氏名</th>
                    <th>団体関連者コード</th>
                    <th>団体名称</th>
                    <th>紐づけ開始年</th>
                    <th>紐づけ終了年</th>
                    <th>登録年列挙</th>
                </tr>
            </tbody>
            <tbody v-for="entity of combineResultDto.listCombineOrg" :key="entity.wkTblPartnerCombineOrgId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="8">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td>{{ KanrenshaKbnConstants.getLabel(entity.kanrenshaKbn) }}</td>
                    <td><button @click="onEditData(entity.wkTblPartnerCombineOrgId)" :disabled="!entity.isLatest">{{
                        entity.personKanrenshaCode }}</button></td>
                    <td>{{ entity.personName }}</td>
                    <td>{{ entity.orgKanrenshaCode }}</td>
                    <td>{{ entity.orgName }}</td>
                    <td>{{ entity.startYear }}</td>
                    <td>{{ entity.endYear }}</td>
                    <td>{{ entity.yearArrayText }}</td>
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
                関連者区分
            </div>
            <div class="right-area">
                {{ KanrenshaKbnConstants.getLabel(entityEdit.kanrenshaKbn) }}
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                個人関連者番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.personKanrenshaCode" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                個人姓名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.personName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                団体関連者番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgKanrenshaCode" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                団体名称
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgName" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                年度登録方法
            </div>
            <div class="right-area">
                <span><input type="radio" v-model="isSetPeriod" :value="period" id="dataFormat">期間で指定</span>
                <span class="left-space"><input type="radio" v-model="isSetPeriod" :value="point"
                        id="dataFormat">具体的に指定</span>
            </div>
            <div class="clear-both"></div>
            <!-- 登録年を期間で指定 -->
            <div v-if="isSetPeriod === period">
                <div class="left-area">
                    開始年
                </div>
                <div class="right-area">
                    <input type="number" v-model="entityEdit.startYear" />
                </div>
                <div class="clear-both"></div>
                <div class="left-area">
                    終了年
                </div>
                <div class="right-area">
                    <input type="number" v-model="entityEdit.endYear" />
                </div>
                <div class="clear-both"></div>
            </div>

            <!-- 登録年を期間で指定 -->
            <div v-if="isSetPeriod === point">
                <div class="left-area">
                    登録年
                </div>
                <div class="right-area">
                    <span v-for="dto in listYearCheck" :key="dto.year">
                        <input type="checkbox" v-model="dto.isSelect" />{{ dto.year }} 年
                    </span>
                </div>
                <div class="clear-both"></div>
            </div>

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
