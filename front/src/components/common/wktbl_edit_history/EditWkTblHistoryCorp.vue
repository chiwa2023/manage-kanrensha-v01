<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type SearchWkTblHistoryCorpPagingResultInterface from '../../../dto/wktbl_history/searchWkTblHistoryCorpPagingResultDto';
import SearchWkTblHistoryCorpPagingResultDto from '../../../dto/wktbl_history/searchWkTblHistoryCorpPagingResultDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type WkTblPartnerCorpHistoryInterface from '../../../entity/wkTblPartnerCorpHistoryEntity';
import WkTblPartnerCorpHistoryEntity from '../../../entity/wkTblPartnerCorpHistoryEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UpdateWkTblHistoryCorpCapsuleInterface from '../../../dto/wktbl_history/updateWkTblHistoryCorpCapsuleDto';
import UpdateWkTblHistoryCorpCapsuleDto from '../../../dto/wktbl_history/updateWkTblHistoryCorpCapsuleDto';
import type UpdateWkTblHistoryCorpResultInterface from '../../../dto/wktbl_history/updateWkTblHistoryCorpResultDto';

const pageOptionCorp: Ref<SelectOptionNumberInterface[]> = ref([]);
const corpCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const corpResultDto: Ref<SearchWkTblHistoryCorpPagingResultInterface> = ref(new SearchWkTblHistoryCorpPagingResultDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
corpCapsuleDto.value.userLeast = userDto.value;
corpCapsuleDto.value.limit = 30;
corpCapsuleDto.value.pageNumber = 0;
corpCapsuleDto.value.hasAffectNot = true;

function onSearchCorp() {

    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-history/search-corp";
        const method = "POST";
        const body = JSON.stringify(corpCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                corpResultDto.value = await response.json();
                pageOptionCorp.value = getPagingOption(corpResultDto.value);
            })
            .catch((error) => { alert(error); });
    });
}

// ページング変更
function onChangePaging() {
    onSearchCorp();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerCorpHistoryInterface> = ref(new WkTblPartnerCorpHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistoryCorpCapsuleInterface> = ref(new UpdateWkTblHistoryCorpCapsuleDto());
editCapsuleDto.value.userPersonLeastDto = userDto.value;

let findIndex:number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = corpResultDto.value.listWktblCorp.findIndex((e) => e.wkPartnerCorpHistoryId === editId);
    entityEdit.value = structuredClone(toRaw(corpResultDto.value.listWktblCorp[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblPartnerCorpHistoryEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-history/update-corp";
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
                    const resultDto: UpdateWkTblHistoryCorpResultInterface = await response.json();
                    alert(resultDto.message);
                    if (response.status === 200) {
                        // 正常に更新できた時だけ既存のリストと入れ替え
                        corpResultDto.value.listWktblCorp.splice(findIndex, 1, resultDto.wkTblPartnerCorpHistoryEntity);
                    }
                }
            })
            .catch((error) => { alert(error); });
    });

    // 指定された値に置き換え
    // corpResultDto.value.listWktblCorp.splice(findIndex,1,structuredClone(toRaw(entityEdit.value)));
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
        <!-- ページング -->
        <select v-model="corpCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionCorp" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>団体名称</th>
                    <th>団体住所</th>
                    <th>団体代表者氏名</th>
                    <th>関連者コード</th>
                </tr>
            </tbody>
            <tbody v-for="entity of corpResultDto.listWktblCorp" :key="entity.wkPartnerCorpHistoryId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkPartnerCorpHistoryId)"
                            :disabled="!entity.isLatest">{{ entity.partnerName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.corpDelegate }}</td>
                    <td>{{ entity.corpKanrenshaCode }}</td>
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
                企業／団体関連者コード
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.corpKanrenshaCode" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                &nbsp;
            </div>
            <div class="right-area">
                <button @click="onEditClose">閉じる</button><button class="left-space" @click="onEditUpdate()" :disabled="isEdit()">更新</button>
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
