<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type SearchWkTblStdPoliOrgPagingResultInterface from '../../../dto/wktbl_std/searchWkTblStdPoliOrgPagingResultDto';
import SearchWkTblStdPoliOrgPagingResultDto from '../../../dto/wktbl_std/searchWkTblStdPoliOrgPagingResultDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import getMockWkTblPoliOrgList from './mock/getMockWkTblPoliOrgList';
import PoliOrgDantaiKbnConstants from '../../../dto/partner_poli_org/poliOrgDantaiKbnConstants';
import type WkTblMasterPoliOrgInterface from '../../../entity/wkTblMasterPoliOrgEntity';
import WkTblMasterPoliOrgEntity from '../../../entity/wkTblMasterPoliOrgEntity';

// 政治団体区分定数
const poliOrgKbnNoSelect: string = PoliOrgDantaiKbnConstants.NO_SELECT;
const poliOrgKbnSeitou: string = PoliOrgDantaiKbnConstants.SEITOU;
const poliOrgKbnSeitouShibu: string = PoliOrgDantaiKbnConstants.SEITOU_SHIBU;
const poliOrgKbnSeijishikin: string = PoliOrgDantaiKbnConstants.SEIJI_SHIKIN_DANTAI;
const poliOrgKbn18Jou2KouDantai: string = PoliOrgDantaiKbnConstants.DANTAI_18JOU_2KOU;
const poliOrgKbnSonota: string = PoliOrgDantaiKbnConstants.SONOTA;
const poliOrgKbnSonotaShibu: string = PoliOrgDantaiKbnConstants.SONOTA_SHIBU;

const pageOptionPoliOrg: Ref<SelectOptionNumberInterface[]> = ref([]);
const poliOrgCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const poliOrgResultDto: Ref<SearchWkTblStdPoliOrgPagingResultInterface> = ref(new SearchWkTblStdPoliOrgPagingResultDto());

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
const entityEdit: Ref<WkTblMasterPoliOrgInterface> = ref(new WkTblMasterPoliOrgEntity());
let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = poliOrgResultDto.value.listWktblPoliOrg.findIndex((e) => e.wkTblMasterPoliOrgId === editId);
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
    <h3>関連者政治検索条件</h3>
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
        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>政治団体名称</th>
                    <th>全住所</th>
                    <th>団体代表者名</th>
                    <th>政治団体区分</th>
                    <th>住所郵便番号まで</th>
                    <th>住所番地まで</th>
                    <th>住所建物まで</th>
                    <th>郵便番号1</th>
                    <th>郵便番号2</th>
                    <th>電話番号市外局番</th>
                    <th>電話番号局番</th>
                    <th>電話番号番号</th>
                    <th>メールアドレス</th>
                    <th>自分の公式サイト</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>関連者団体名称かな</th>
                    <th>団体代表者関連者コード</th>
                    <th>会計責任者関連者個人コード</th>
                    <th>会計責任者関連者個人氏名</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of poliOrgResultDto.listWktblPoliOrg" :key="entity.wkTblMasterPoliOrgId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="25">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblMasterPoliOrgId)" :disabled="!entity.isLatest">{{
                            entity.partnerName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.poliOrgDelegate }}</td>
                    <td>{{ entity.dantaiKbn }}</td>
                    <td>{{ entity.addressPostal }}</td>
                    <td>{{ entity.addressBlock }}</td>
                    <td>{{ entity.addressBuilding }}</td>
                    <td>{{ entity.postal1 }}</td>
                    <td>{{ entity.postal2 }}</td>
                    <td>{{ entity.phon1 }}</td>
                    <td>{{ entity.phon2 }}</td>
                    <td>{{ entity.phon3 }}</td>
                    <td>{{ entity.email }}</td>
                    <td>{{ entity.myPortalUrl }}</td>
                    <td>{{ entity.snsServiceName }}</td>
                    <td>{{ entity.snsAccount }}</td>
                    <td>{{ entity.orgNameKana }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
                    <td>{{ entity.accountMgrCode }}</td>
                    <td>{{ entity.accountMgrName }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.machiazaId }}1</td>
                    <td>{{ entity.blkId }}</td>
                    <td>{{ entity.rsdtId }}</td>
                    <td>{{ entity.rsdt2Id }}</td>
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
                団体区分
            </div>
            <div class="right-area">
                <select v-model="entityEdit.dantaiKbn">
                    <option :value=poliOrgKbnNoSelect> </option>
                    <option :value=poliOrgKbnSeitou>{{ PoliOrgDantaiKbnConstants.getLabel(poliOrgKbnSeitou)
                    }}</option>
                    <option :value=poliOrgKbnSeitouShibu>{{
                        PoliOrgDantaiKbnConstants.getLabel(poliOrgKbnSeitouShibu) }}</option>
                    <option :value=poliOrgKbnSeijishikin>{{
                        PoliOrgDantaiKbnConstants.getLabel(poliOrgKbnSeijishikin) }}</option>
                    <option :value=poliOrgKbn18Jou2KouDantai>{{
                        PoliOrgDantaiKbnConstants.getLabel(poliOrgKbn18Jou2KouDantai) }}</option>
                    <option :value=poliOrgKbnSonota>{{ PoliOrgDantaiKbnConstants.getLabel(poliOrgKbnSonota)
                    }}</option>
                    <option :value=poliOrgKbnSonotaShibu>{{
                        PoliOrgDantaiKbnConstants.getLabel(poliOrgKbnSonotaShibu) }}</option>
                </select>
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住所郵便番号まで
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.addressPostal" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住所番地まで
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.addressBlock" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住所建物まで
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.addressBuilding" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                郵便番号1
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.postal1" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                郵便番号2
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.postal2" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                電話番号市外局番
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.phon1" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                電話番号局番
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.phon2" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                電話番号番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.phon3" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                メールアドレス
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.email" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                自分の公式サイト
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.myPortalUrl" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                SNS名称
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.snsServiceName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                SNSアカウント
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.snsAccount" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                関連者団体名称かな
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgNameKana" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                団体代表者関連者コード
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.orgDelegateCode" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                会計責任者関連者個人コード
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.accountMgrCode" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                会計責任者関連者個人氏名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.accountMgrName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                地方公共団体コード
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.lgCode" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                町字Id
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.machiazaId" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                街区Id
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.blkId" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住居Id
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.rsdtId" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住居2Id
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.rsdt2Id" />
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
:root {
    --cell_width: 200 px;
}

table {
    border-style: solid;
    border-width: 1px;
}

table.std {
    border-style: solid;
    border-width: 1px;
    width: calc(200px * 25);
}

td {
    border-style: solid;
    border-width: 1px;
}

th {
    border-style: solid;
    border-width: 1px;
}

th.hojo {
    background-color: lightgray;
    width: --cell_width px;
}

th.explain {
    background-color: lightcyan;
    width: --cell_width px;
}

tbody:after {
    content: "";
    height: 2px;
    width: 100%;
    padding: 2px 0;
    display: block;
}
</style>
