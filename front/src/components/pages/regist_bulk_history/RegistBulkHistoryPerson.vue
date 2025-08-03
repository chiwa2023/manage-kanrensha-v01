<script setup lang="ts">
import { computed, ref, type ComputedRef, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import EditWkTblHistoryPerson from '../../common/wktbl_edit_history/EditWkTblHistoryPerson.vue';
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

    <h1>関連者個人履歴一括登録</h1>

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

    <div class="one-line">
        <button @click="viewSample">{{ templateViewButtonText }}</button>
        <div v-if="isVisibleTemplate">
            ヘッダ必須。1行目は読み飛ばすので、ないと1行目が登録されません<br>
            最初の1列は不要です。(ファイル内は4列)
            <table>
                <tbody>
                    <tr>
                        <th class="hojo">要件</th>
                        <th>個人姓名</th>
                        <th>個人の住所</th>
                        <th>職業</th>
                        <th>関連者コード</th>
                    </tr>
                    <tr>
                        <th class="hojo">説明</th>
                        <th class="explain">必須</th>
                        <th class="explain">必須</th>
                        <th class="explain">任意<br>(項目省略不可)</th>
                        <th class="explain">必須</th>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>迂回献金　花子</td>
                        <td>和歌山県実在市山麓町</td>
                        <td>団体役員</td>
                        <td>12-ABCDE-5678</td>
                    </tr>
                    <tr>
                        <th class="hojo">データ例</th>
                        <td>不記載　直子</td>
                        <td>宮崎県架空市湖畔町</td>
                        <td></td>
                        <td>23-BCDEF-6789</td>
                    </tr>
                </tbody>
            </table>
            <a href="sample_csv/sample_bulk_history_person.csv">上記内容サンプルcsvをダウンロード</a>
        </div>
    </div>

    <!-- 登録結果と編集 -->
    <EditWkTblHistoryPerson></EditWkTblHistoryPerson>

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

th {
    border-style: solid;
    border-width: 1px;
}

th.hojo {
    background-color: lightgray;
}

th.explain {
    background-color: lightcyan;
}
</style>
