<script setup lang="ts">
import { type Ref, ref, toRaw } from "vue";
import SelectOptionInterface from "../../../dto/selectOptionDto";
import InputAddressDto from "../../../dto/Input_address/inputAddressDto";
import type SelectOptionNumberInterface from "../../../dto/selectOptionNumberDto";
import RoutePathConstants from "../../../routePathConstants";

//props,emit
const props = defineProps<{ editDto: InputAddressDto }>();
const emits = defineEmits(["sendCancelInputAddress", "sendInputAddressInterface"]);

/** 入力用Dto */
const inputAddressDto: Ref<InputAddressDto> = ref(new InputAddressDto());
const previousAddressDto: Ref<InputAddressDto> = ref(structuredClone(toRaw(props.editDto)));

inputAddressDto.value = structuredClone(toRaw(props.editDto));


const addressPostal: Ref<string> = ref(props.editDto.addressPostal);
const addressBlock: Ref<string> = ref(props.editDto.addressBlock);

/** 住所郵便番号まで */
const selectedAddressPostal: Ref<number> = ref(-1);
const listPostalSuggest: Ref<SelectOptionNumberInterface[]> = ref([]);
const listBackupPostalSuggest: Ref<SelectOptionNumberInterface[]> = ref([]);

/** 住所郵便番地まで */
const selectedAddressBlock: Ref<string> = ref("");
const listBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);
const listBackupBlockSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 住所郵便建物 */
const selectedAddressBuilding: Ref<string> = ref("");
const listBuildingSuggest: Ref<SelectOptionInterface[]> = ref([]);

/** 地方自治体住居検索 */
//const isGyouseiku: Ref<boolean> = ref(false);

/** 郵便番号取得 */
function getAddressPostal() {

    listPostalSuggest.value.splice(0);
    //  郵便番号の形式となったらリストを取得する
    if (3 === inputAddressDto.value.postalcode1.length && 4 === inputAddressDto.value.postalcode2.length) {

        // getAuthorizedPromiseArea().then(token => {
        //     // 検索条件の設定
        //     const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
        //     conditionDto.postal1 = inputAddressDto.value.postalcode1;
        //     conditionDto.postal2 = inputAddressDto.value.postalcode2;
        //     const url = "http://localhost:6080/postal-search/postal";
        //     const method = "POST";
        //     const body = JSON.stringify(conditionDto);
        //     const headers = {
        //         'Accept': 'application/json',
        //         'Content-Type': 'application/json',
        //         'X-AUTH-TOKEN': 'Bearer ' + token
        //     };
        //     fetch(url, { method, headers, body })
        //         .then(async (response) => {
        //             const resultDto = await response.json();
        //             listPostalSuggest.value = resultDto.listOptions;
        //             listBackupPostalSuggest.value = structuredClone(toRaw(listPostalSuggest.value));
        //             isGyouseiku.value = resultDto.isGyouseikuData;

        //             // 1件だけの時は値を決定して番地までデータを検索
        //             if (listPostalSuggest.value !== null) {
        //                 if (listPostalSuggest.value.length === 1) {
        //                     selectedAddressPostal.value = listBackupPostalSuggest.value[0].value;
        //                     selectSuggestPostal();
        //                     searchBlock();
        //                 }
        //             }
        //         })
        //         .catch((e) => { alert(e); });
        // });
    }
}

/** 住所郵便番号候補選択時 */
function selectSuggestPostal() {
    const text: string = listBackupPostalSuggest.value.filter(e => e.value === selectedAddressPostal.value)[0].text;
    addressPostal.value = text;
    inputAddressDto.value.addressPostal = text;
    inputAddressDto.value.rsdtAddressPostl = text;

    searchBlock();
}

/** 住所番地までを検索 */
function searchBlock() {

    // getAuthorizedPromiseArea().then(token => {
    //     // 検索条件の設定
    //     const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
    //     conditionDto.selectedPostal = selectedAddressPostal.value;
    //     conditionDto.isGyouseikuData = isGyouseiku.value;

    //     const url = "http://localhost:6080/postal-search/block";
    //     const method = "POST";
    //     const body = JSON.stringify(conditionDto);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: PostalCodeBlockResultInterface = await response.json();
    //             listBlockSuggest.value = resultDto.listOptions;
    //             listBackupBlockSuggest.value = structuredClone(toRaw(listBlockSuggest.value));
    //             inputAddressDto.value.lgCode = resultDto.lgCode;
    //             listBlockSuggest.value;

    //             // 1件だけの時は値を決定して建物までデータを検索
    //             if (listBlockSuggest.value !== undefined) {
    //                 if (listBlockSuggest.value.length === 1) {
    //                     selectedAddressBlock.value = listBackupBlockSuggest.value[0].value;
    //                     searchBuilding();
    //                     selectSuggestBlock();
    //                 }
    //             }
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

/** 住所番地候補選択時 */
function selectSuggestBlock() {
    addressBlock.value = listBlockSuggest.value.filter(e => e.value === selectedAddressBlock.value)[0].text;

    const text = listBlockSuggest.value.filter(e => e.value === selectedAddressBlock.value)[0].text;
    inputAddressDto.value.addressBlock = text;
    inputAddressDto.value.rsdtAddressBlock = text;
    // TODO 現状は選択肢でコードと名称だけだが、公共団体コードなどを紐づけて利用する
    inputAddressDto.value.machiazaId = "2345678";
    inputAddressDto.value.blkId = "123";
    inputAddressDto.value.rsdtId = "456";

    //下層のサジェストの作成
    searchBuilding();
}

function searchBuilding() {

    // getAuthorizedPromiseArea().then(token => {
    //     // 検索条件の設定
    //     const conditionDto: PostalCodeCapsuleInterface = new PostalCodeCapsuleDto();
    //     conditionDto.selectedBlock = selectedAddressBlock.value;
    //     conditionDto.postalCode = inputAddressDto.value.postalcode1 + inputAddressDto.value.postalcode2;
    //     conditionDto.lgCode = inputAddressDto.value.lgCode;
    //     conditionDto.isGyouseikuData = isGyouseiku.value;

    //     const url = "http://localhost:6080/postal-search/building";
    //     const method = "POST";
    //     const body = JSON.stringify(conditionDto);
    //     const headers = {
    //         'Accept': 'application/json',
    //         'Content-Type': 'application/json',
    //         'X-AUTH-TOKEN': 'Bearer ' + token
    //     };
    //     fetch(url, { method, headers, body })
    //         .then(async (response) => {
    //             const resultDto: PostalCodeBuildingResultInterface = await response.json();
    //             listBuildingSuggest.value = resultDto.listOptions;
    //         })
    //         .catch((error) => { alert(error); });
    // });
}

/** 住所建物候補選択時 */
function selectSuggestBuilding() {
    inputAddressDto.value.addressBuilding = selectedAddressBuilding.value;
    inputAddressDto.value.rsdtAddressBuilding = selectedAddressBuilding.value;
}

/** 住所郵便番号フィルタ時 */
const filterPostal: Ref<string> = ref("");
function filterSuggestPostal() {
    const tempList = structuredClone(toRaw(listBackupPostalSuggest.value));
    listPostalSuggest.value = tempList.filter((dto) => { if (dto.text.includes(filterPostal.value)) { return true; } });
}

/** 住所番地フィルタ時 */
const filterBlock: Ref<string> = ref("");
function filterSuggestBlock() {
    const tempList = structuredClone(toRaw(listBackupBlockSuggest.value));
    listBlockSuggest.value = tempList.filter((dto) => { if (dto.text.includes(filterBlock.value)) { return true; } });
    listBlockSuggest.value;
}

/**  
 * 入力内容を破棄する
 */
function onCancel() {
    emits("sendCancelInputAddress");
}

/**  
 * 入力内容を保存する
 */
function onSave() {
    inputAddressDto.value.addressPostal = addressPostal.value;
    inputAddressDto.value.addressBlock = addressBlock.value;
    emits("sendInputAddressInterface", inputAddressDto.value);
}

// 前データ表示切替
const isShowPrevious: Ref<boolean> = ref(true);
function onShowPrevious() {
    isShowPrevious.value = !isShowPrevious.value;
}

function onAddAddress() {
    alert("値の追加ができました。追加したコードで再指定してください。");
}
</script>
<template>
    <h3>住所入力</h3>
    <div style="overflow-y: scroll">

        <div class="left-area">
            郵便番号
        </div>
        <div class="right-area">
            <input v-model="inputAddressDto.postalcode1" type="text" class="code-input"
                @input="getAddressPostal">&nbsp;-&nbsp;
            <input v-model="inputAddressDto.postalcode2" type="text" class="code-input" @input="getAddressPostal">
        </div>
        <div class="clear-both"><br></div>

        <div class="left-area">
            住所1
        </div>
        <div class="right-area">
            <select v-model="selectedAddressPostal" @change="selectSuggestPostal">
                <option v-for="optionDto in listPostalSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space">フィルタ<input v-model="filterPostal" type="text"
                    @input="filterSuggestPostal"></span><span class="left-space"><input
                    v-model="inputAddressDto.isPostalEdit" type="checkbox">編集</span><br>
            <textarea v-model="addressPostal" :disabled="!inputAddressDto.isPostalEdit"></textarea>
        </div>
        <div class="clear-both"><br></div>

        <div class="left-area">
            番地
        </div>
        <div class="right-area">
            <select v-model="selectedAddressBlock" @change="selectSuggestBlock">
                <option v-for="optionDto in listBlockSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space">フィルタ<input v-model="filterBlock" type="text"
                    @input="filterSuggestBlock"></span><span class="left-space"><input
                    v-model="inputAddressDto.isBlockEdit" type="checkbox">編集</span><br>
            <textarea v-model="addressBlock" :disabled="!inputAddressDto.isBlockEdit"></textarea>
        </div>
        <div class="clear-both"></div>

        <div class="left-area">
            建物
        </div>
        <div class="right-area">
            <select v-model="selectedAddressBuilding" @change="selectSuggestBuilding">
                <option v-for="optionDto in listBuildingSuggest" :key="optionDto.value" :value="optionDto.value">{{
                    optionDto.text }}</option>
            </select><span class="left-space"><input v-model="inputAddressDto.isBuildingEdit"
                    type="checkbox">編集</span><br>
            <textarea v-model="inputAddressDto.addressBuilding" :disabled="!inputAddressDto.isBuildingEdit"></textarea>
        </div>
        <div class="clear-both"></div>
        <div class="left-area">
            住所コード
        </div>
        <div class="right-area">
            <span>地方公共団体コード</span><input type="text" v-model="inputAddressDto.lgCode" class="code-input"
                disabled="true">
            <span class="left-space">町字Id</span><input type="text" v-model="inputAddressDto.machiazaId"
                class="code-input" disabled="true">
            <span class="left-space">街区Id</span><input type="text" v-model="inputAddressDto.blkId" class="code-input"
                :disabled="!inputAddressDto.isBlockEdit">
            <span class="left-space">住居Id</span><input type="text" v-model="inputAddressDto.rsdtId" class="code-input"
                :disabled="!(inputAddressDto.isBlockEdit || inputAddressDto.isBuildingEdit)">
            <span class="left-space">住居2Id</span><input type="text" v-model="inputAddressDto.rsdtId" class="code-input"
                :disabled="!inputAddressDto.isBuildingEdit">
            <br>
            <div style="text-align: right;">
                <button @click="onAddAddress">アドレス(街区、住居、住居2)Idを追加する</button>
            </div>
        </div>
        <div class="clear-both"></div>

        <div class="one-line">
            <routerLink :to=RoutePathConstants.PAGE_ADDRESS_REGI_EDIT>承認作業を中断(編集内容を破棄)して住所コード編集ページへ移動</routerLink>
        </div>
        <div class="clear-both"><br></div>

        <hr>

        <div class="one-line">
            現在データを確認 <button @click="onShowPrevious">表示／非表示</button>
        </div>

        <div class="clear-both"><br></div>

        <div v-if="isShowPrevious">

            <div class="left-area">
                郵便番号
            </div>
            <div class="right-area">
                <input v-model="previousAddressDto.postalcode1" type="text" class="code-input"
                    disabled="true">&nbsp;-&nbsp;
                <input v-model="previousAddressDto.postalcode2" type="text" class="code-input" disabled="true">
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                住所1
            </div>
            <div class="right-area">
                <textarea v-model="addressPostal" :disabled="true"></textarea>
            </div>
            <div class="clear-both"><br></div>

            <div class="left-area">
                番地
            </div>
            <div class="right-area">
                <textarea v-model="addressBlock" disabled="true"></textarea>
            </div>
            <div class="clear-both"></div>

            <div class="left-area">
                建物
            </div>
            <div class="right-area">
                <textarea v-model="inputAddressDto.addressBuilding" disabled="true"></textarea>
            </div>
            <div class="clear-both"></div>
            <div class="left-area">
                住所コード
            </div>
            <div class="right-area">
                <span>地方公共団体コード</span><input type="text" v-model="inputAddressDto.lgCode" class="code-input"
                    disabled="true">
                <span class="left-space">町字Id</span><input type="text" v-model="inputAddressDto.machiazaId"
                    class="code-input" disabled="true">
                <span class="left-space">街区Id</span><input type="text" v-model="inputAddressDto.blkId"
                    class="code-input" disabled="true">
                <span class="left-space">住居Id</span><input type="text" v-model="inputAddressDto.rsdtId"
                    class="code-input" disabled="true">
            </div>
            <div class="clear-both"></div>
        </div>

    </div>







    <div class="footer">
        <button @click="onCancel" class="footer-button">キャンセル</button>
        <button @click="onSave" class="footer-button left-space">選択</button>
    </div>

</template>
<style scoped></style>
