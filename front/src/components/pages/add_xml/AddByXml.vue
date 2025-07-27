<script setup lang="ts">
import { ref, toRaw, type Ref } from 'vue';
import UploadFile from '../../common/upload_file/UploadFile.vue';
import type SelectOptionNumberInterface from '../../../dto/selectOptionNumberDto';
import type PagingFrameworkInterface from '../../../dto/pagingFrameworkDto';
import PagingFrameworkDto from '../../../dto/pagingFrameworkDto';


const pageOption: Ref<SelectOptionNumberInterface[]> = ref([]);

const capsuleDto:Ref<PagingFrameworkInterface> = ref(new PagingFrameworkDto());


function recieveUint8ArrayDataBlock(data: Uint8Array) {
    alert("アップロード処理");
}

function onChangePaging() {
}

</script>
<template>
    <h1>政治資金収支報告書XMLより関連者登録</h1>

    <h3>CSVファイル選択</h3>
    <UploadFile @send-byte-data="recieveUint8ArrayDataBlock"></UploadFile>

        <div class="left-area">
        非表示項目
    </div>
    <div class="right-area">
        <input type="checkbox">同一ファイル内の同一項目<span><input type="checkbox">登録済項目</span>
    </div>
    <div class="clear-both"></div>


    <div class="one-line">

        処理結果<br>
        <!-- ページング -->
        <select v-model="capsuleDto.pageNumber" @change="onChangePaging">
            <option v-for="option in pageOption" :key="option.value" :value="option.value"> {{ option.text
            }}
            </option>
        </select><br>
        <table>
            <tbody>

            </tbody>
        </table>

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
}
</style>
