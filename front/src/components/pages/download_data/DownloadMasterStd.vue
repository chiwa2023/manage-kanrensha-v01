<script setup lang="ts">
function onCancel() {
    alert("キャンセル");
    history.back();
}
</script>
<template>
    <h1>関連者マスタ標準データダウンロード</h1>

    <div class="one-line">
        <a href="/dump/master_std_all.zip" class="left-space">一括ダウンロード(master_std_all.zip)</a><br>
    </div>

    <div class="one-line">
        <h3>関連者個人</h3>
        <a href="/dump/dump_master/master_person_std.csv" class="left-space">関連者個人マスタ標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>関連者企業／団体</h3>
        <a href="/dump/dump_master/master_corp_std.csv" class="left-space">関連者企業／団体標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>関連者政治団体</h3>
        <a href="/dump/dump_master/master_poli_org_std.csv" class="left-space">関連者政治団体標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
    </div>

</template>
<style scoped></style>
