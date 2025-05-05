<script setup lang="ts">

</script>
<template>
    <h1>政治資金関連者標準化サイト</h1>
    <!-- 
    <h2>(関連者一括登録などができる)管理者ですか?(政治団体の取引相手である)ユーザですか?</h2>

    <div style="text-align: center;padding: auto;margin: auto;width: 90%;">
        <div style="float: left; width: 35%;text-align: left;border-width: 1px;border-style: solid;padding-left: 10%;">
            管理者ログイン<br>
            <input type="text"><br>
            <input type="text"><br>
        </div>

        <div style="float: right;width: 35%;;text-align: left;border-width: 1px;border-style: solid;padding-left: 10%;">
            ユーザログイン<br>
            <input type="text"><br>
            <input type="text"><br>
        </div>
    </div>
    -->

    <RouterLink to="/partner_manage">関連者管理</RouterLink><br>
    <RouterLink to="/partner_edit">関連者編集</RouterLink><br>

    <div style="clear: both;"></div>

    <hr>
    <RouterLink to="/component">コンポーネント作成台紙</RouterLink><br>

</template>
<style scoped></style>
