<script setup lang="ts">
import { computed, type ComputedRef } from 'vue';
import type InputAccessInterface from '../../../dto/input_access/inputAccessDto';
const props = defineProps<{ editDto: InputAccessInterface }>();
const inputDto: ComputedRef<InputAccessInterface> = computed(() => { return props.editDto });

</script>
<template>
    <div class="left-area">
        電話番号
    </div>
    <div class="right-area">
        <input type="text" v-model="inputDto.phon1" class="code-input">
        <input type="text" v-model="inputDto.phon2" class="code-input left-space">
        <input type="text" v-model="inputDto.phon3" class="code-input left-space">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        メールアドレス
    </div>
    <div class="right-area">
        <input type="text" v-model="inputDto.email" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        自身のポータルサイト
    </div>
    <div class="right-area">
        <input type="url" v-model="inputDto.myPortalUrl" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        SNS
    </div>
    <div class="right-area">
        サービス名：<input type="text" v-model="inputDto.snsServiceName" class="left-space text-input"><br>
        サービストップURL:<input type="url" v-model="inputDto.snsPortalUrl" class="left-space text-input"><br>
        アカウント名：<input type="text" v-model="inputDto.snsAccount" class="left-space text-input">
    </div>
    <div class="clear-both"></div>

</template>
<style scoped></style>
