<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import type SearchWkTblStdCorpPagingResultInterface from '../../../dto/wktbl_std/searchWkTblStdCorpPagingResultDto';
import SearchWkTblStdCorpPagingResultDto from '../../../dto/wktbl_std/searchWkTblStdCorpPagingResultDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type WkTblMasterCorpInterface from '../../../entity/wkTblMasterCorpEntity';
import WkTblMasterCorpEntity from '../../../entity/wkTblMasterCorpEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UpdateWkTblStdCorpCapsuleInterface from '../../../dto/wktbl_std/updateWkTblStdCorpCapsuleDto';
import UpdateWkTblStdCorpCapsuleDto from '../../../dto/wktbl_std/updateWkTblStdCorpCapsuleDto';
import type UpdateWkTblStdCorpResultInterface from '../../../dto/wktbl_std/updateWkTblStdCorpResultDto';
import RoutePathConstants from '../../../routePathConstants';

// back側アクセス
const urlBack: string = RoutePathConstants.DOMAIN_BACK + RoutePathConstants.PATH_BACK;

const pageOptionCorp: Ref<SelectOptionNumberInterface[]> = ref([]);
const corpCapsuleDto: Ref<SearchWkTblPagingCapsuleInterface> = ref(new SearchWkTblPagingCapsuleDto());
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

const corpResultDto: Ref<SearchWkTblStdCorpPagingResultInterface> = ref(new SearchWkTblStdCorpPagingResultDto());

function onSearchCorp() {

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-std/search-corp";
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
const entityEdit: Ref<WkTblMasterCorpInterface> = ref(new WkTblMasterCorpEntity());
const editCapsuleDto: Ref<UpdateWkTblStdCorpCapsuleInterface> = ref(new UpdateWkTblStdCorpCapsuleDto());
editCapsuleDto.value.userPersonLeastDto = userDto.value;

let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = corpResultDto.value.listWktblCorp.findIndex((e) => e.wkTblMasterCorpId === editId);
    entityEdit.value = structuredClone(toRaw(corpResultDto.value.listWktblCorp[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblMasterCorpEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = urlBack + "/regist-bulk-master-std/update-corp";
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
                    const resultDto: UpdateWkTblStdCorpResultInterface = await response.json();
                    alert(resultDto.message);
                    // 表示更新
                    onSearchCorp();
                }
            })
            .catch((error) => { alert(error); });
    });

    // 指定された値に置き換え
    // corpResultDto.value.listWktblCorp.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
    // 編集コンポーネントを閉じる
    isEditData.value = false;

}

function onEditClose() {
    // 編集コンポーネントを閉じる
    isEditData.value = false;
}

const notUseText: string = "使用しないに変更;";
function onHideData() {
    entityEdit.value.judgeReason = notUseText;
    entityEdit.value.isAffected = false;
    entityEdit.value.isFinish = true;
    onEditUpdate();
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

        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>団体名称</th>
                    <th>全住所</th>
                    <th>団体代表者</th>
                    <th>法人番号</th>
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
                    <th>外国籍該否</th>
                    <th>法人種別</th>
                    <th>関連者団体名称かな</th>
                    <th>支店該当</th>
                    <th>団体代表者関連者コード</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of corpResultDto.listWktblCorp" :key="entity.wkTblMasterCorpId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="26">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblMasterCorpId)" :disabled="!entity.isLatest">{{
                        entity.partnerName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.corpDelegate }}</td>
                    <td>{{ entity.houjinNo }}</td>
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
                    <td>{{ entity.isForeign }}</td>
                    <td>{{ entity.houjinSbts }}</td>
                    <td>{{ entity.orgNameKana }}</td>
                    <td>{{ entity.isShiten }}</td>
                    <td>{{ entity.orgDelegateCode }}</td>
                    <td>{{ entity.snsServiceName }}</td>
                    <td>{{ entity.snsAccount }}</td>
                    <td>{{ entity.lgCode }}</td>
                    <td>{{ entity.machiazaId }}</td>
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
                企業／団体名称
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
                外国籍該否
            </div>
            <div class="right-area">
                <input type="checkbox" v-model="entityEdit.isForeign" />外国籍
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                法人種別
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.houjinSbts" />
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
                支店該否
            </div>
            <div class="right-area">
                <input type="checkbox" v-model="entityEdit.isForeign" />支店
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
                <button @click="onEditClose">閉じる</button><button class="left-space" @click="onEditUpdate()"
                    :disabled="isEdit()">更新</button>
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
    width: calc(200px * 27);
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
