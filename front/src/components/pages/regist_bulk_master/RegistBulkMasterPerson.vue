<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import EditWkTblMinPerson from '../../common/wktbl_edit_min/EditWkTblMinPerson.vue';
import EditWkTblStdPerson from '../../common/wktbl_edit_std/EditWkTblStdPerson.vue';
import MockManagerInfo from '../../common/user_info/MockManagerInfo.vue';


// サンプル表示
const templateViewButtonText: ComputedRef<String> = computed(() => isVisibleTemplate.value ? "CSVサンプルを隠す" : "CSVサンプルを表示する");
const isVisibleTemplate: Ref<boolean> = ref(false);
function viewSample() {
    isVisibleTemplate.value = !isVisibleTemplate.value;
}

// csv読み出し
const tableData: Ref<string[][]> = ref([[]]);
function recieveTextDataBlock(data: string) {
    tableData.value = parseCSV(data);
}
function parseCSV(data: string): string[][] {
    return data.split('\r\n').map((row) => row.split(','));
}
function removeQuote(data: string): string {
    return data.replace('"', '').replace('"', '');
}

// 初期表示データフォーマットは最小
const formatMin: string = "min";
const formatStd: string = "std";
const isVisibleFormat: Ref<string> = ref(formatMin);

function onCancel() {
    alert("キャンセル");
    history.back();
}
function onSave() {
    alert("保存");
}
</script>
<template>
    <!-- 管理者メニュー兼チェック -->
    <MockManagerInfo></MockManagerInfo>

    <h1>関連者個人マスタ一括登録</h1>

    <div class="left-area">
        データフォーマット(最小／標準)
    </div>
    <div class="right-area">
        <span><input type="radio" v-model="isVisibleFormat" :value="formatMin" id="dataFormat">最小</span>
        <span class="left-space"><input type="radio" v-model="isVisibleFormat" :value="formatStd"
                id="dataFormat">標準</span>
    </div>
    <div class="clear-both"></div>

    <h3>CSVファイル選択</h3>
    <ReadCsv :is-text="true" @send-text-data="recieveTextDataBlock"></ReadCsv>

    <h3>読み取り結果(最初の10行)</h3>
    <div class="one-line">
        <table>
            <tbody>
                <tr v-for="row, index of tableData" :key="index">
                    <td v-for="cell, index of row" :key="index">
                        {{ removeQuote(cell) }}
                    </td>
                </tr>

            </tbody>
        </table>
    </div>

    <h3 v-if="isVisibleFormat === formatMin">最小フォーマット</h3>
    <div class="one-line" v-if="isVisibleFormat === formatMin">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は3列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>個人氏名</th>
                        <th>個人全住所</th>
                        <th>職業</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>迂回献金　太郎</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>教師</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>寄付上限　花子</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_master_min_person.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblMinPerson v-if="isVisibleFormat === formatMin"></EditWkTblMinPerson>

    <h3 v-if="isVisibleFormat !== formatMin">標準フォーマット</h3>
    <div class="one-line" v-if="isVisibleFormat !== formatMin">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は28列)
            <table class="std">
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
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
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意<br>5文字まで</th>
                        <th class="explain">任意<br>5文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須<br>9文字まで</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意<br>"はい","1","true","該当"の<br>いずれかが設定されているときは該当</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意</th>
                        <th class="explain">任意<br>7文字まで</th>
                        <th class="explain">任意<br>6文字まで</th>
                        <th class="explain">任意<br>8文字まで</th>
                        <th class="explain">任意<br>4文字まで</th>
                        <th class="explain">任意<br>6文字まで</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>迂回献金　ミカエル　太郎</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>団体役員</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>2丁目6番地</td>
                        <td>四角ビル7F</td>
                        <td>012</td>
                        <td>3456</td>
                        <td>023</td>
                        <td>4567</td>
                        <td>8901</td>
                        <td>taro@jakusho.net</td>
                        <td>http://jakusho.net/taro</td>
                        <td>いいえ</td>
                        <td>迂回献金</td>
                        <td>太郎</td>
                        <td>ミカエル</td>
                        <td>うかいけんきん</td>
                        <td>たろう</td>
                        <td>みかえる</td>
                        <td>水産業</td>
                        <td>役職者</td>
                        <td>団体役員</td>
                        <td>1-234-5678</td>
                        <td>三重県架空市湖畔町</td>
                        <td>とこぶし収穫組合</td>
                        <td>弱小ブログ</td>
                        <td>@uaikenkin</td>
                        <td>098765</td>
                        <td>1234</td>
                        <td>123</td>
                        <td>234</td>
                        <td>5678</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>寄付上限　花子</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td></td>
                        <td>宮崎県架空市湖畔町</td>
                        <td>3丁目9番地4</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td>087</td>
                        <td>654</td>
                        <td>3210</td>
                        <td>hanako@chan0poran.com</td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_master_std_person.csv">上記内容サンプルcsvをダウンロード</a><br>
        </div>
    </div>
    <!-- 登録結果と編集 -->
    <EditWkTblStdPerson v-if="isVisibleFormat !== formatMin"></EditWkTblStdPerson>


    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">送信</button>
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
    width: calc(200px * 33);
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
</style>
