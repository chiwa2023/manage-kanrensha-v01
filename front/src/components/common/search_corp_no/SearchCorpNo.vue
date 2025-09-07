<script setup lang="ts">
import { ref,  type Ref } from 'vue';
import mockGetCorpList from './mock/mockGetCorpList';
import type MasterCorporationInterface from '../../../entity/masterCorporationEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type NaturalTextSearchPagingCapsuleInterface from '../../../dto/naturalTextSearchPagingCapsuleDto';
import NaturalTextSearchPagingCapsuleDto from '../../../dto/naturalTextSearchPagingCapsuleDto';
import type SearchKanrenshaCorpResultInterface from '../../../dto/kanrensha/searchKanrenshaCorpResultDto';

//props,emit
const props = defineProps<{  isFooter: boolean }>();
const emits = defineEmits(["sendCorpNoInterface", "sendCanceelCorpNo"]);

//const listProps: ComputedRef<CorpInterface[]> = computed(() => { return props.list });
const listCorp: Ref<MasterCorporationInterface[]> = ref([]);
// watch(props.list, () => {
//     //alert("変更");
//     listCorp.value.splice(0);
//     for (const dto of props.list) {
//         listCorp.value.push(dto);
//     }
// });

// 検索リスト
function onCorpSearch() {
    listCorp.value = mockGetCorpList();

    getAuthorizedPromiseArea().then(token => {
        // 検索条件の設定
        const capsuleDto: NaturalTextSearchPagingCapsuleInterface = new NaturalTextSearchPagingCapsuleDto();
        capsuleDto.allCount = 0;
        capsuleDto.limit = 30;
        capsuleDto.pageNumber = 0;

        const url = "http://localhost:6080/user-kanrensha/search-corp";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: SearchKanrenshaCorpResultInterface = await response.json();
                listCorp.value = resultDto.listMasterCorp;
            })
            .catch((error) => { alert(error); });
    });

}

/** 行選択 */
function onSelectRow(selectedNo: number) {
    // 検索データからコピーすべき元データを抽出
    const selectedDto: MasterCorporationInterface = listCorp.value.filter((e) => e.masterCorporationId === selectedNo)[0];
    emits("sendCorpNoInterface", selectedDto);
}

function sendCancelCorpNo() {
    emits("sendCanceelCorpNo");
}

</script>
<template>
    <h3>検索条件</h3>
    <div class="left-area">
        法人番号(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="text-input">
    </div>
    <div class="clear-both"><br></div>

    <div class="left-area">
        名前(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="text-input">
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
        <input type="text" class="text-input">
    </div>
    <div class="clear-both"></div>

    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onCorpSearch">検索</button>
    </div>
    <div class="clear-both"></div>
    <hr>
    <h3>検索結果</h3>
    <div class="one-line">

        <table>
            <tbody>
                <tr>
                    <th>関連者コード(企業／団体)</th>
                    <th>法人番号</th>
                    <th>法人名</th>
                    <th>住所</th>
                    <th>代表者</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="dto of listCorp" :key="dto.masterCorporationId">
                    <td>{{ dto.corpKanrenshaCode }}</td>
                    <td>{{ dto.houjinNo }}</td>
                    <td>{{ dto.partnerName }}</td>
                    <td>{{ dto.allAddress }}</td>
                    <td>{{ dto.corpDelegate }}</td>
                    <td><button @click="onSelectRow(dto.masterCorporationId)">選択</button></td>
                </tr>
            </tbody>
        </table>
    </div>

    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="sendCancelCorpNo" class="footer-button">キャンセル</button>
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
