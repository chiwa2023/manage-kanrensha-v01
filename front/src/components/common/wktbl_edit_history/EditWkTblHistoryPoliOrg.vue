<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type SearchWkTblHistoryPoliOrgPagingResultInterface from '../../../dto/wktbl_history/searchWkTblHistoryPoliOrgPagingResultDto';
import SearchWkTblHistoryPoliOrgPagingResultDto from '../../../dto/wktbl_history/searchWkTblHistoryPoliOrgPagingResultDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type WkTblPartnerPoliOrgHistoryInterface from '../../../entity/wkTblPartnerPoliOrgHistoryEntity';
import WkTblPartnerPoliOrgHistoryEntity from '../../../entity/wkTblPartnerPoliOrgHistoryEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UpdateWkTblHistoryPoliOrgCapsuleInterface from '../../../dto/wktbl_history/updateWkTblHistoryPoliOrgCapsuleDto';
import UpdateWkTblHistoryPoliOrgCapsuleDto from '../../../dto/wktbl_history/updateWkTblHistoryPoliOrgCapsuleDto';
import type UpdateWkTblHistoryPoliOrgResultInterface from '../../../dto/wktbl_history/updateWkTblHistoryPoliOrgResultDto';

const pageOptionPoliOrg: Ref<SelectOptionNumberInterface[]> = ref([]);
const poliOrgCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const sessionStorage = window["sessionStorage"];
const userDtoText: string | null = sessionStorage.getItem("userDto");
const userDto: Ref<UserPersonLeastInterface> = ref(new UserPersonLeastDto());
if (userDtoText !== null) {
    userDto.value = JSON.parse(userDtoText);
}
poliOrgCapsuleDto.value.userLeast = userDto.value;
poliOrgCapsuleDto.value.limit = 30;
poliOrgCapsuleDto.value.pageNumber = 0;
poliOrgCapsuleDto.value.hasAffectNot = true;

const poliOrgResultDto: Ref<SearchWkTblHistoryPoliOrgPagingResultInterface> = ref(new SearchWkTblHistoryPoliOrgPagingResultDto());

function onSearchPoliOrg() {

    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-history/search-poli-org";
        const method = "POST";
        const body = JSON.stringify(poliOrgCapsuleDto.value);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                poliOrgResultDto.value = await response.json();
                pageOptionPoliOrg.value = getPagingOption(poliOrgResultDto.value);
            })
            .catch((error) => { alert(error); });
    });

}
// ページング変更
function onChangePaging() {
    onSearchPoliOrg();
}

// 編集用
const isEditData: Ref<boolean> = ref(false);
const entityEdit: Ref<WkTblPartnerPoliOrgHistoryInterface> = ref(new WkTblPartnerPoliOrgHistoryEntity());
const editCapsuleDto: Ref<UpdateWkTblHistoryPoliOrgCapsuleInterface> = ref(new UpdateWkTblHistoryPoliOrgCapsuleDto());
editCapsuleDto.value.userPersonLeastDto = userDto.value;

let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = poliOrgResultDto.value.listWktblPoliOrg.findIndex((e) => e.wkPartnerPoliOrgHistoryId === editId);
    entityEdit.value = structuredClone(toRaw(poliOrgResultDto.value.listWktblPoliOrg[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {
    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblPartnerPoliOrgHistoryEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-history/update-poli-org";
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
                    const resultDto: UpdateWkTblHistoryPoliOrgResultInterface = await response.json();
                    alert(resultDto.message);
                    if (response.status === 200) {
                        // 再表示
                        onSearchPoliOrg();
                    }
                }
            })
            .catch((error) => { alert(error); });
    });

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
