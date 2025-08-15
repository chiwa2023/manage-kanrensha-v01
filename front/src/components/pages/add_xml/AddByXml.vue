<script setup lang="ts">
import { ref, type Ref } from 'vue';
import UploadFile from '../../common/upload_file/UploadFile.vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import KanrenshaKbnConstants from '../../../dto/kanrensha/kanrenshaKbnConstants';
import PoliOrgDantaiKbnConstants from '../../../dto/partner_poli_org/poliOrgDantaiKbnConstants';
import getMockRegistMasterXmlList from './mock/getMockRegistMasterXmlList';
import type SearchWkTblAllMainHistoryPagingResultInterface from '../../../dto/add_xml/searchWkTblAllMainHistoryPagingResultDto';
import SearchWkTblAllMainHistoryPagingResultDto from '../../../dto/add_xml/searchWkTblAllMainHistoryPagingResultDto';
import getPagingOption from '../paging/getPagingOption';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import EditWkTblMinPerson from '../../common/wktbl_edit_min/EditWkTblMinPerson.vue';
import EditWkTblMinCorp from '../../common/wktbl_edit_min/EditWkTblMinCorp.vue';
import EditWkTblMinPoliOrg from '../../common/wktbl_edit_min/EditWkTblMinPoliOrg.vue';

// 関連者区分定数
const kanrenshaKbnNoSelect: number = KanrenshaKbnConstants.NO_SELECT;
const kanrenshaKbnPerson: number = KanrenshaKbnConstants.PERSON;
const kanrenshaKbnCorp: number = KanrenshaKbnConstants.CORP;
const kanrenshaKbnPoliOrg: number = KanrenshaKbnConstants.POLI_ORG;

// 政治団体区分定数
const poliOrgKbnNoSelect: string = PoliOrgDantaiKbnConstants.NO_SELECT;
const poliOrgKbnSeitou: string = PoliOrgDantaiKbnConstants.SEITOU;
const poliOrgKbnSeitouShibu: string = PoliOrgDantaiKbnConstants.SEITOU_SHIBU;
const poliOrgKbnSeijishikin: string = PoliOrgDantaiKbnConstants.SEIJI_SHIKIN_DANTAI;
const poliOrgKbn18Jou2KouDantai: string = PoliOrgDantaiKbnConstants.DANTAI_18JOU_2KOU;
const poliOrgKbnSonota: string = PoliOrgDantaiKbnConstants.SONOTA;
const poliOrgKbnSonotaShibu: string = PoliOrgDantaiKbnConstants.SONOTA_SHIBU;

// 表示用Dto
const pageOptionAll: Ref<SelectOptionNumberInterface[]> = ref([]);
const allCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
const mainAndHistoryResultDto: Ref<SearchWkTblAllMainHistoryPagingResultInterface> = ref(new SearchWkTblAllMainHistoryPagingResultDto());


function recieveUint8ArrayDataBlock(data: Uint8Array) {
    alert("アップロード処理");
}

function onChangePaging() {
}

function onSaveWkTbl() {
    mainAndHistoryResultDto.value.listRegistDto = getMockRegistMasterXmlList();

    mainAndHistoryResultDto.value.allCount = 195;
    mainAndHistoryResultDto.value.limit = 30;
    pageOptionAll.value = getPagingOption(mainAndHistoryResultDto.value);
}

// 作業内容検索
function onSearchAll() {
}

// 様式によって元データが異なるので元データ分類リスト
const bikoList: number[] = [];
bikoList.push(3);
bikoList.push(6);

const fullList: number[] = [];
fullList.push(7);
fullList.push(8);
fullList.push(11);
fullList.push(12);

const nameAddressList: number[] = [];
nameAddressList.push(5);
nameAddressList.push(14);
nameAddressList.push(15);

const partnerList: number[] = [];
nameAddressList.push(4);

// 分類編集内容保存
function onSaveBunrui() {
    alert("保存");
}

function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    alert("保存");
}

</script>
<template>
    <h1>政治資金収支報告書XMLより関連者登録</h1>

    <h3>CSVファイル選択</h3>
    <UploadFile @send-byte-data="recieveUint8ArrayDataBlock"></UploadFile>

    <div class="left-area">
        XML解析処理
    </div>
    <div class="right-area">
        <button @click="onSaveWkTbl">解析結果表示</button>
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="allCapsuleDto.hasAffectNot">反映なし<span><input type="checkbox"
                v-model="allCapsuleDto.hasFinished">作業完了</span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        作業結果表示
    </div>
    <div class="right-area">
        <button @click="onSearchAll">表示</button>
    </div>
    <div class="clear-both"></div>


    <div class="one-line">
        関連者未区分<br>
        <!-- ページング -->
        <select v-model="allCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionAll" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>
                <tr>
                    <th>行番号</th>
                    <th>反映該否</th>
                    <th>個人氏名</th>
                    <th>全住所</th>
                    <th>入力項目1</th>
                    <th>入力項目2</th>
                </tr>
            </tbody>
            <tbody v-for="dto of mainAndHistoryResultDto.listRegistDto" style="margin-bottom: 1%;">

                <tr>
                    <td rowspan="3"> &nbsp;</td>
                    <td rowspan="2"><input type="checkbox" v-model="dto.isAffected" :disabled="dto.isDisabled">編集有効</td>
                    <td colspan="4">処理判定：{{ dto.judgeReason }}</td>
                </tr>

                <!-- 手掛かりが備考欄のみ(様式3,6) -->
                <tr v-if="bikoList.includes(dto.youshikiKbn)">
                    <td colspan="4"><input type="text" v-model="dto.bikou" :disabled="true"></td>
                </tr>

                <!-- 手掛かりが全部そろっている(様式7,8,11,12) -->
                <tr v-if="fullList.includes(dto.youshikiKbn)">
                    <td><input type="text" v-model="dto.inputSrcName" :disabled="true"></td>
                    <td><input type="text" v-model="dto.inputSrcAddress" :disabled="true"></td>
                    <td><input type="text" v-model="dto.inputSrcKey" :disabled="true"></td>
                    <td colspan="2">&nbsp;</td>
                </tr>

                <!-- 手掛かりが名前と住所(様式5,14,15) -->
                <tr v-if="nameAddressList.includes(dto.youshikiKbn)">
                    <td><input type="text" v-model="dto.inputSrcName" :disabled="true"></td>
                    <td><input type="text" v-model="dto.inputSrcAddress" :disabled="true"></td>
                    <td colspan="3">&nbsp;</td>
                </tr>

                <!-- 手掛かりが名前のみ(様式4) -->
                <tr v-if="partnerList.includes(dto.youshikiKbn)">
                    <td colspan="4"><input type="text" v-model="dto.inputSrcName" :disabled="true"></td>
                </tr>

                <tr>
                    <td>
                        様式 {{ dto.youshikiKbn }} 様式枝区分 {{ dto.youshikiEdaKbn }}<br>
                        <select v-model="dto.kanrenshaKbn"
                            :disabled="!dto.isAffected || fullList.includes(dto.youshikiKbn)">
                            <option :value=kanrenshaKbnNoSelect> </option>
                            <option :value=kanrenshaKbnPerson>個人</option>
                            <option :value=kanrenshaKbnCorp>企業／団体</option>
                            <option :value=kanrenshaKbnPoliOrg>政治団体</option>
                        </select>
                    </td>
                    <td>
                        名称<br>
                        <input type="text" v-model="dto.partnerName" :disabled="!dto.isAffected" />
                    </td>
                    <td>
                        全住所<br>
                        <input type="text" v-model="dto.allAddress" :disabled="!dto.isAffected" />
                    </td>
                    <td>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnNoSelect">
                            &nbsp;
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnPerson">
                            職業<br>
                            <input type="text" v-model="dto.personShokugyou" :disabled="!dto.isAffected" />
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnCorp">
                            団体代表者<br>
                            <input type="text" v-model="dto.orgDelegate" :disabled="!dto.isAffected" />
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnPoliOrg">
                            団体代表者<br>
                            <input type="text" v-model="dto.orgDelegate" :disabled="!dto.isAffected" />
                        </div>
                    </td>

                    <td>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnNoSelect">
                            &nbsp;
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnPerson">
                            &nbsp;
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnCorp">
                            法人番号<br>
                            <input type="text" v-model="dto.houjinNo" :disabled="!dto.isAffected" />
                        </div>
                        <div v-if="dto.kanrenshaKbn === kanrenshaKbnPoliOrg">
                            政治団体区分<br>
                            <select v-model="dto.dantaiKbn" :disabled="!dto.isAffected">
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
                    </td>

                </tr>
            </tbody>
        </table>
        <br>
        <button @click="onSaveBunrui">分類編集送信</button>
    </div>
    <div class="clear-both"><br></div>

    <hr>
    </hr>

        <!-- ワークテーブル編集マスタ最小個人 -->
    <EditWkTblMinPerson></EditWkTblMinPerson>

    <!-- 
    <h3>関連者個人検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="personCapsuleDto.hasAffectNot">反映なし<span><input type="checkbox"
                v-model="personCapsuleDto.hasFinished">作業完了</span>
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
                    <td>{{ entityPerson.partnerName }}</td>
                    <td>{{ entityPerson.allAddress }}</td>
                    <td>{{ entityPerson.personShokugyou }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>
    -->

    <hr>
    </hr>

    <!-- ワークテーブル編集マスタ最小企業／団体 -->
    <EditWkTblMinCorp></EditWkTblMinCorp>


    <!-- 
    <h3>関連者企業／団体検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="corpCapsuleDto.hasAffectNot">反映なし<span><input type="checkbox"
                v-model="corpCapsuleDto.hasFinished">作業完了</span>
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
                    <td>{{ entityCorp.partnerName }}</td>
                    <td>{{ entityCorp.allAddress }}</td>
                    <td>{{ entityCorp.corpDelegate }}</td>
                    <td>{{ entityCorp.houjinNo }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>
-->

    <hr>
    </hr>

        <!-- ワークテーブル編集マスタ最小政治団体 -->
    <EditWkTblMinPoliOrg></EditWkTblMinPoliOrg>

    <!-- 
    <h3>関連者政治団体検索条件</h3>
    <div class="left-area">
        検索項目
    </div>
    <div class="right-area">
        <input type="checkbox" v-model="poliOrgCapsuleDto.hasAffectNot">反映なし<span><input type="checkbox"
                v-model="poliOrgCapsuleDto.hasFinished">作業完了</span>
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
        <select v-model="poliOrgCapsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOptionPoliOrg" :key="option.value" :value="option.value"> {{ option.text
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
                    <th>団体区分</th>
                </tr>
            </tbody>
            <tbody v-for="entityPoliOrg of poliOrgResultDto.listWktblPoliOrg"
                :key="entityPoliOrg.wkTblPartnerPoliOrgAddMinId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entityPoliOrg.isAffected" disabled="true">反映する</td>
                    <td colspan="4">{{ entityPoliOrg.judgeReason }}</td>
                </tr>
                <tr>
                    <td>{{ entityPoliOrg.partnerName }}</td>
                    <td>{{ entityPoliOrg.allAddress }}</td>
                    <td>{{ entityPoliOrg.poliOrgDelegate }}</td>
                    <td>{{ PoliOrgDantaiKbnConstants.getLabel(entityPoliOrg.dantaiKbn) }}</td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>
    -->

    <hr>
    </hr>
    <br>
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

td.rowNum {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 2px;
    border-bottom-width: 2px;
    border-right-width: 1px;
}

td.top {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 1px;
    border-bottom-width: 1px;
    border-right-width: 1px;
}

td.topCorner {
    border-style: solid;
    border-top-width: 2px;
    border-left-width: 1px;
    border-bottom-width: 1px;
    border-right-width: 2px;
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
