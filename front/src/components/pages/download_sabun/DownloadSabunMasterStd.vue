<script setup lang="ts">
const now: Date = new Date();
const startDate: Date = new Date(now.getFullYear(), 1, 1);
const endDate: Date = new Date(now.getFullYear(), 3, 31);
</script>
<template>
    <h1>関連者マスタ標準データダウンロード(差分)</h1>

    <div class="one-line">
        {{ startDate.toLocaleDateString() }} から {{ endDate.toLocaleDateString() }}までを収録
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <a href="dump_master_sabun/sabun_master_std_all.zip" class="left-space">一括ダウンロード(master_std_all.zip)</a><br>
    </div>

    <div class="one-line">
        <h3>関連者個人</h3>
        <a href="dump_master_sabun/sabun_master_person_std.csv" class="left-space">関連者個人マスタ標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>関連者企業／団体</h3>
        <a href="dump_master_sabun/sabun_master_person_std.csv" class="left-space">関連者企業／団体標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

    <div class="one-line">
        <h3>関連者政治団体</h3>
        <a href="dump_master_sabun/sabun_master_person_std.csv" class="left-space">関連者政治団体標準(master_person_std.csv)</a><br>
    </div>
    <div class="clear-both"><br></div>

</template>
<style scoped></style>
