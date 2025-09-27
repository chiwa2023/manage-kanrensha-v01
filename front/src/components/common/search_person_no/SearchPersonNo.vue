<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type MasterPersonInterface from '../../../entity/masterPersonEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type NaturalTextSearchPagingCapsuleInterface from '../../../dto/naturalTextSearchPagingCapsuleDto';
import NaturalTextSearchPagingCapsuleDto from '../../../dto/naturalTextSearchPagingCapsuleDto';
import type SearchKanrenshaPersonResultInterface from '../../../dto/kanrensha/searchKanrenshaPersonResultDto';

//props,emit
const props = defineProps<{ isFooter: boolean }>();
const emits = defineEmits(["sendPersonNoInterface", "sendCanceelPersonNo"]);

const listPerson: Ref<MasterPersonInterface[]> = ref([]);

function onPersonSearch() {
    //listPerson.value = mockGetPersonList();

    getAuthorizedPromiseArea().then(token => {
        // 検索条件の設定
        const capsuleDto: NaturalTextSearchPagingCapsuleInterface = new NaturalTextSearchPagingCapsuleDto();
        capsuleDto.allCount = 0;
        capsuleDto.limit = 30;
        capsuleDto.pageNumber = 0;

        const url = "http://localhost:6080/user-kanrensha/search-person";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: SearchKanrenshaPersonResultInterface = await response.json();
                listPerson.value = resultDto.listMasterPerson;
            })
            .catch((error) => { alert(error); });
    });
}

function onSelectRow(personNo: number) {
    // コードから選択された個人を抽出する
    const selectedDto: MasterPersonInterface = listPerson.value.filter((e) => e.masterPersonId === personNo)[0];
    emits("sendPersonNoInterface", selectedDto);
}

function sendCancelPersonNo() {
    emits("sendCanceelPersonNo");
}

</script>
<template>
    <h3>関連者個人検索</h3>

    <div class="left-area">
        名前(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        郵便番号
    </div>
    <div class="right-area">
        <input type="text" class="code-input">&nbsp;-&nbsp;
        <input type="text" class="code-input">
    </div>
    <div class="clear-both"></div>
    <div class="left-area">
        住所(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="max-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        関連者コード(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="name-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onPersonSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>
    <h3>検索結果</h3>
    <table>
        <tbody>
            <tr>
                <th>関連者コード(個人)</th>
                <th>氏名</th>
                <th>住所</th>
                <th>職業</th>
                <th>&nbsp;</th>
            </tr>
            <tr v-for="dto of listPerson" :key="dto.masterPersonId">
                <td>{{ dto.personKanrenshaCode }}</td>
                <td>{{ dto.partnerName }}</td>
                <td>{{ dto.allAddress }}</td>
                <td>{{ dto.personShokugyou }}</td>
                <td><button @click="onSelectRow(dto.masterPersonId)">選択</button></td>
            </tr>
        </tbody>
    </table>
    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="sendCancelPersonNo" class="footer-button">キャンセル</button>
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
