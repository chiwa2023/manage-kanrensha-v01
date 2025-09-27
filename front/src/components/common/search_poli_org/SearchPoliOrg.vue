<script setup lang="ts">
import { ref, type Ref } from 'vue';
import type MasterPoliticalOrganizationInterface from '../../../entity/masterPoliticalOrganizationEntity';
import getAuthorizedPromiseArea from '../../../dto/login/getAuthorizedPromiseArea';
import type NaturalTextSearchPagingCapsuleInterface from '../../../dto/naturalTextSearchPagingCapsuleDto';
import NaturalTextSearchPagingCapsuleDto from '../../../dto/naturalTextSearchPagingCapsuleDto';
import type SearchKanrenshaPoliOrgResultInterface from '../../../dto/kanrensha/searchKanrenshaPoliOrgResultDto';
import PoliOrgDantaiKbnConstants from '../../../dto/partner_poli_org/poliOrgDantaiKbnConstants';

//props,emit
const props = defineProps<{ isFooter: boolean }>();
const emits = defineEmits(["sendCancelPoliOrgNo", "sendPoliOrgNoInterface"]);
const poliOrgList: Ref<MasterPoliticalOrganizationInterface[]> = ref([])

function onSearch() {

    getAuthorizedPromiseArea().then(token => {
        // 検索条件の設定
        const capsuleDto: NaturalTextSearchPagingCapsuleInterface = new NaturalTextSearchPagingCapsuleDto();
        capsuleDto.allCount = 0;
        capsuleDto.limit = 30;
        capsuleDto.pageNumber = 0;

        const url = "http://localhost:6080/user-kanrensha/search-poli-org";
        const method = "POST";
        const body = JSON.stringify(capsuleDto);
        const headers = {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'X-AUTH-TOKEN': 'Bearer ' + token
        };
        fetch(url, { method, headers, body })
            .then(async (response) => {
                const resultDto: SearchKanrenshaPoliOrgResultInterface = await response.json();
                poliOrgList.value = resultDto.listMasterPoliOrg;
            })
            .catch((error) => { alert(error); });
    });
}

/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelPoliOrgNo");
}

/**  
 * 入力内容を保存する
 */
function onSave(selectedRow: number) {
    // Idでフィルタしているので1件だけ取得できる
    const selectedDto: MasterPoliticalOrganizationInterface = poliOrgList.value.filter((e) => e.masterPoliticalOrganizationId === selectedRow)[0];
    emits("sendPoliOrgNoInterface", selectedDto);
}

</script>
<template>
    <h3>検索条件</h3>
    <div class="left-area">
        関連者政治団体番号(前方一致)
    </div>
    <div class="right-area">
        <input type="text" class="name-input">
    </div>
    <div class="clear-both"><br></div>

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
    <div class="clear-both"></div>
    <div class="left-area">
        検索
    </div>
    <div class="right-area">
        <button @click="onSearch">検索</button>
    </div>
    <div class="clear-both"></div>

    <hr>


    <h3>検索結果</h3>

    <div class="one-line">
        <table>
            <tbody>
                <tr>
                    <th>政治団体番号</th>
                    <th>政治団体名称</th>
                    <th>市区町村</th>
                    <th>代表者</th>
                    <th>団体区分</th>
                    <th>&nbsp;</th>
                </tr>
                <tr v-for="entity in poliOrgList" :key="entity.masterPoliticalOrganizationId">
                    <td>{{ entity.poliOrgKanrenshaCode }}</td>
                    <td>{{ entity.partnerName }}</td>
                    <td>{{ entity.allAddress }}</td>
                    <td>{{ entity.poliOrgDelegate }}</td>
                    <td>{{ PoliOrgDantaiKbnConstants.getLabel( entity.dantaiKbn) }}</td>
                    <td><button @click="onSave(entity.masterPoliticalOrganizationId)">選択</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="clear-both"><br></div>

    <div class="footer" v-if="props.isFooter">
        <button @click="onCancel" class="footer-button">キャンセル</button>
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
