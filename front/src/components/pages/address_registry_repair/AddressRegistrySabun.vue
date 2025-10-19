<script setup lang="ts">
import { ref, type Ref } from 'vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';
import ManagerInfo from '../../common/user_info/ManagerInfo.vue';


const tableData: Ref<string[][]> = ref([[]]);
const readFile: Ref<string> = ref("");

function recieveTextDataBlock(data: string) {
    tableData.value.splice(0);
    const allData: Ref<string[][]> = ref([[]]);
    allData.value = parseCSV(data);
    for (let i: number = 0; i < 10; i++) {
        tableData.value.push(allData.value[i])
    }
    readFile.value = "番地ファイル";
}

function recieveTextDataRsdt(data: string) {

    tableData.value.splice(0);
    const allData: Ref<string[][]> = ref([[]]);
    allData.value = parseCSV(data);
    for (let i: number = 0; i < 10; i++) {
        tableData.value.push(allData.value[i])
    }
    readFile.value = "住居ファイル";
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
    <ManagerInfo></ManagerInfo>
    <hr>

    <h1>アドレスベースレジストリ差分修正</h1>

    <!--csvファイル読み取り -->

    <!-- 番地ファイル用 -->
    <h3>番地ファイル選択</h3>
    <ReadCsv :is-text="true" @send-text-data="recieveTextDataBlock"></ReadCsv>

    <hr>
    <!-- 住居ファイル用 -->
    <h3>住居ファイル選択</h3>
    <ReadCsv :is-text="true" @send-text-data="recieveTextDataRsdt"></ReadCsv>

    <hr>

    <div class="one-line">
        読み取り結果( {{ readFile }} )<br>
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
