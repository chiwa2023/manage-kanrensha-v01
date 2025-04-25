<script setup lang="ts">

//props,emit
const emits = defineEmits(["sendCancelInputPersonName", "sendInputPersonNameInterface"]);


function onCancel(){

    emits("sendCancelInputPersonName");
}

function onSave(){
    emits("sendInputPersonNameInterface");
}

</script>
<template>
    <h3>姓名入力</h3>
    <div class="left-area">
        姓名かな
    </div>
    <div class="right-area">
        <span>姓<input type="text" class="text-input"> </span>
        <span class="left-space">ミドルネーム<input type="text" class="text-input"> </span>
        <span class="left-space">名<input type="text" class="text-input"> </span>
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        姓名漢字
    </div>
    <div class="right-area">
        <span>姓<input type="text" class="text-input"> </span>
        <span class="left-space">ミドルネーム<input type="text" class="text-input"> </span>
        <span class="left-space">名<input type="text" class="text-input"> </span>
    </div>
    <div class="clear-both"></div>

    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">選択</button>
    </div>

</template>
<style scoped></style>
