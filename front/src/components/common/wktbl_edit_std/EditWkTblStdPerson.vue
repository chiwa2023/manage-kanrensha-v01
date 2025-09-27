<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type SearchWkTblPagingCapsuleInterface from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import SearchWkTblPagingCapsuleDto from '../../../dto/add_xml/searchWkTbPagingCapsuleDto';
import getPagingOption from '../../pages/paging/getPagingOption';
import type SearchWkTblStdPersonPagingResultInterface from '../../../dto/wktbl_std/searchWkTblStdPersonPagingResultDto';
import SearchWkTblStdPersonPagingResultDto from '../../../dto/wktbl_std/searchWkTblStdPersonPagingResultDto';
import type WkTblMasterPersonInterface from '../../../entity/wkTblMasterPersonEntity';
import WkTblMasterPersonEntity from '../../../entity/wkTblMasterPersonEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type UserPersonLeastInterface from '../../../dto/user/userPersonLeastDto';
import UserPersonLeastDto from '../../../dto/user/userPersonLeastDto';
import type UpdateWkTblStdPersonCapsuleInterface from '../../../dto/wktbl_std/updateWkTblStdPersonCapsuleDto';
import UpdateWkTblStdPersonCapsuleDto from '../../../dto/wktbl_std/updateWkTblStdPersonCapsuleDto';
import type UpdateWkTblStdPersonResultInterface from '../../../dto/wktbl_std/updateWkTblStdPersonResultDto';

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

const personResultDto: Ref<SearchWkTblStdPersonPagingResultInterface> = ref(new SearchWkTblStdPersonPagingResultDto());

function onSearchPerson() {
    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-master-std/search-person";
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
const entityEdit: Ref<WkTblMasterPersonInterface> = ref(new WkTblMasterPersonEntity());
const editCapsuleDto: Ref<UpdateWkTblStdPersonCapsuleInterface> = ref(new UpdateWkTblStdPersonCapsuleDto());
editCapsuleDto.value.userPersonLeastDto = userDto.value;

let findIndex: number = 0;
function onEditData(editId: number) {
    // 指定されたデータを呼び出し(編集決定時には置き換えするので配列indexが必要)
    findIndex = personResultDto.value.listWktblPerson.findIndex((e) => e.wkTblMasterPersonId === editId);
    entityEdit.value = structuredClone(toRaw(personResultDto.value.listWktblPerson[findIndex]));

    isEditData.value = true;
}
function onEditUpdate() {

    // 編集中のEntityを編集のためにBack側に受け渡し
    editCapsuleDto.value.wkTblMasterPersonEntity = entityEdit.value;

    getAuthorizedPromiseArea().then(token => {
        const url = "http://localhost:6080/regist-bulk-master-std/update-person";
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
                    const resultDto: UpdateWkTblStdPersonResultInterface = await response.json();
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
    // personResultDto.value.listWktblPerson.splice(findIndex, 1, structuredClone(toRaw(entityEdit.value)));
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
        <table class="std">
            <tbody>
                <tr>
                    <th>反映該否</th>
                    <th>個人の姓名</th>
                    <th>全住所</th>
                    <th>個人職業</th>
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
                    <th>姓名の姓</th>
                    <th>姓名の名</th>
                    <th>姓名のミドルネーム</th>
                    <th>姓名の姓のかな</th>
                    <th>姓名の名のかな</th>
                    <th>姓名のミドルネームのかな</th>
                    <th>職業の業種</th>
                    <th>職業の役職</th>
                    <th>職業のユーザ記載</th>
                    <th>職業法人番号</th>
                    <th>職業法人住所</th>
                    <th>職業法人名</th>
                    <th>SNS名称</th>
                    <th>SNSアカウント</th>
                    <th>地方公共団体コード</th>
                    <th>町字Id</th>
                    <th>街区Id</th>
                    <th>住居Id</th>
                    <th>住居2Id</th>
                </tr>
            </tbody>
            <tbody v-for="entity of personResultDto.listWktblPerson" :key="entity.wkTblMasterPersonId">
                <tr>
                    <td rowspan="2"><input type="checkbox" v-model="entity.isAffected" disabled="true">反映する</td>
                    <td colspan="31">{{ entity.judgeReason }}</td>
                </tr>
                <tr>
                    <td><button @click="onEditData(entity.wkTblMasterPersonId)" :disabled="!entity.isLatest">{{
                        entity.partnerName }}</button></td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.personShokugyou }}</td>
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
                    <td>{{ entity.lastName }}</td>
                    <td>{{ entity.firstName }}</td>
                    <td>{{ entity.middleName }}</td>
                    <td>{{ entity.lastNameKana }}</td>
                    <td>{{ entity.firstNameKana }}</td>
                    <td>{{ entity.middleNameKana }}</td>
                    <td>{{ entity.gyoushu }}</td>
                    <td>{{ entity.yakushoku }}</td>
                    <td>{{ entity.shokugyouUserWrite }}</td>
                    <td>{{ entity.corpNo }}</td>
                    <td>{{ entity.corpAddress }}</td>
                    <td>{{ entity.corpName }}</td>
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
                姓名の姓
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.lastName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                姓名の名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.firstName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                姓名のミドルネーム
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.middleName" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                姓名の姓のかな
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.lastNameKana" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                姓名の名のかな
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.firstNameKana" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                姓名のミドルネームのかな
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.middleNameKana" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                職業の業種
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.gyoushu" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                職業の役職
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.yakushoku" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                職業のユーザ記載
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.shokugyouUserWrite" />
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                職業法人番号
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.corpNo" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                職業法人住所
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.corpAddress" />
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                職業法人名
            </div>
            <div class="right-area">
                <input type="text" v-model="entityEdit.corpName" />
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
    width: calc(200px * 34);
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
