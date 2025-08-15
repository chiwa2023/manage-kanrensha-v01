<script setup lang="ts">
import MockManagerInfo from '../../common/user_info/MockManagerInfo.vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import { ref, type Ref } from 'vue';

const tableData: Ref<string[][]> = ref([[]]);

function recieveTextData(data: string) {

    tableData.value = parseCSV(data);
    const title: string[] = [
        "1 全国地方公共団体コード",
        "2 （旧）郵便番号",
        "3 郵便番号",
        "4 都道府県名",
        "5 市区町村名",
        "6 町域名",
        "7 都道府県名",
        "8 市区町村名",
        "9 町域名",
        "10 一町域が二以上の郵便番号で表される場合の表示",
        "11 小字毎に番地が起番されている町域の表示",
        "12 丁目を有する町域の場合の表示",
        "13 一つの郵便番号で二以上の町域を表す場合の表示",
        "14 更新の表示",
        "15 変更理由"
    ];
    tableData.value.unshift(title);
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
    <hr>

    <h1>郵便番号差分修正</h1>

    <!--csvファイル読み取り -->
    <ReadCsv :is-text="true" @send-text-data="recieveTextData"></ReadCsv>

    <hr>

    <div class="one-line">
        読み取り結果<br>
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
    <div class="clear-both"></div>

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
    text-align: center;
}
</style>
