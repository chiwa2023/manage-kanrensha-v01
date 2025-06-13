<script setup lang="ts">
import { ref, type Ref } from 'vue';

//props,emit
const props = defineProps<{ isText: boolean }>();
const emits = defineEmits(["sendTextData","sendByteData"]);

//文字コード
const selectedCharCode: Ref<string> = ref("");

//ファイル指定ダイアログ
const selectFileInput: Ref<HTMLInputElement | undefined> = ref<HTMLInputElement>();
/**
 * ファイル選択ダイアログを表示する
 */
function onReadButton() {
    if (selectFileInput.value !== undefined) {
        selectFileInput.value.click();
    }
}

/**
 * 指定されたファイルを読み込む
 */
async function readTextFile() {
    if (selectFileInput.value !== null) {
        if (selectFileInput.value !== undefined) {
            if (selectFileInput.value.files !== null) {
                const file: File = selectFileInput.value.files[0];

                if (props.isText) {
                    emits("sendTextData", await file.text());

                } else {
                    emits("sendByteData", await file.bytes());
                }
            }
        }
    }
}

</script>
<template>
    <div class="left-area">
        読取りファイルの指定<br>
    </div>
    <div class="right-area">
        <input ref="selectFileInput" type="file" accept=".csv" @change="readTextFile" style="display:none;">
        <button @click="onReadButton">ファイルを指定して読み取り</button>
        文字が読めない場合
        <select v-model="selectedCharCode">
            <option value="UTF-8">UTF-8</option>
            <option value="Shift_JIS">Shift_JIS(Windows)</option>
        </select>
    </div>
    <div class="clear-both"></div>

    <!-- 
    <div class="one-line">
        読み取り結果<br>
        <table>
            <tbody>
                <tr v-for="row, index of data" :key="index">
                    <td v-for="cell, index of row" :key="index">
                        {{ cell }}
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"></div>
    -->

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
</style>
