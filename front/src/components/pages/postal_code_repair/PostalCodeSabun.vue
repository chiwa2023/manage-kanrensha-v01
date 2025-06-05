<script setup lang="ts">
import MockManagerInfo from '../../common/user_info/MockManagerInfo.vue';
import ReadCsv from '../../common/read_csv/ReadCsv.vue';




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
    <ReadCsv></ReadCsv>

    <hr>
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
