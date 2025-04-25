<script setup lang="ts">
import { ref, type Ref } from 'vue';
import InputPersonName from './InputPersonName.vue';

const isInput: Ref<boolean> = ref(false);

function onInputPersonName() {

    // 表示
    isInput.value = true;
}

function recieveCancelInputPersonName() {

    // 非表示
    isInput.value = false;
}

function recieveInputPersonNameInterface() {

    // 非表示
    isInput.value = false;
}

</script>
<template>
    <h3>姓名入力</h3>
    <div class="left-area">
        姓名かな
    </div>
    <div class="right-area">
        <input type="text" class="max-input" disabled="true"><span class="left-space"><button
            @click="onInputPersonName">編集</button> </span>
        <br>
        <span>姓<input type="text" class="text-input" disabled="true"> </span>
        <span class="left-space">ミドルネーム<input type="text" class="text-input" disabled="true"> </span>
        <span class="left-space">名<input type="text" class="text-input" disabled="true"> </span>

    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        姓名漢字
    </div>
    <div class="right-area">
        <input type="text" class="max-input" disabled="true">
        <br>
        <span>姓<input type="text" class="text-input" disabled="true"> </span>
        <span class="left-space">ミドルネーム<input type="text" class="text-input" disabled="true"> </span>
        <span class="left-space">名<input type="text" class="text-input" disabled="true"> </span>
    </div>
    <div class="clear-both"></div>

    <div v-if="isInput" class="overBackground"></div>
    <div v-if="isInput">
        <div class="overComponent">
            <InputPersonName v-if="isInput" @send-cancel-input-person-name="recieveCancelInputPersonName"
                @send-input-person-name-interface="recieveInputPersonNameInterface">
                ></InputPersonName>
        </div>
    </div>

</template>
<style scoped></style>
